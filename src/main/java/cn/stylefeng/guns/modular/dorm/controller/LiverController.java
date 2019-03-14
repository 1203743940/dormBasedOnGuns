package cn.stylefeng.guns.modular.dorm.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.stylefeng.guns.core.common.annotion.BussinessLog;
import cn.stylefeng.guns.core.common.constant.dictmap.LiverMap;
import cn.stylefeng.guns.core.common.constant.factory.ConstantFactory;
import cn.stylefeng.guns.core.common.exception.BizExceptionEnum;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import cn.stylefeng.guns.core.shiro.ShiroKit;
import cn.stylefeng.guns.modular.dorm.entity.Liver;
import cn.stylefeng.guns.modular.dorm.entity.Room;
import cn.stylefeng.guns.modular.dorm.model.LiverDto;
import cn.stylefeng.guns.modular.dorm.service.LiverService;
import cn.stylefeng.guns.modular.dorm.enums.EmployeeSexEnum;
import cn.stylefeng.guns.modular.dorm.enums.KeyIfEnum;
import cn.stylefeng.guns.modular.dorm.enums.LiverStatusEnum;
import cn.stylefeng.guns.modular.dorm.enums.VisiableEnum;
import cn.stylefeng.guns.modular.dorm.service.RoomService;
import cn.stylefeng.guns.modular.dorm.warpper.LiverWrapper;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @auther wmm
 * @date 2019/2/18 15:41
 */

@Controller
@RequestMapping("/liver")
public class LiverController extends BaseController {

    private String PREFIX = "/dorm/liver/";

    @Autowired
    private RoomService roomService;

    @Autowired
    private LiverService liverService;

    /**
     * 跳转到通知列表首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "liver.html";
    }

    /**
     * 跳转到添加通知
     */
    @RequestMapping("/liver_add/{roomId}")
    public String liverAdd(@PathVariable Long roomId, Model model) {
        model.addAttribute("roomId", roomId);
        return PREFIX + "liver_add.html";
    }

    /**
     * 跳转到修改通知
     */
    @RequestMapping("/liver_update")
    public String liverUpdate(@RequestParam("liverId") Long liverId, Model model) {
        Liver liver = this.liverService.selectById(liverId);
        model.addAllAttributes(BeanUtil.beanToMap(liver));
        LogObjectHolder.me().set(liver);
        return PREFIX + "liver_edit.html";
    }

    /**
     * 跳转到首页通知
     */
    @RequestMapping("/hello")
    public String hello() {
        List<Map<String, Object>> livers = liverService.list(null);
        super.setAttr("liverList", livers);
        return PREFIX + "liver_index.html";
    }

    /**
     * 楼栋详情
     */
    @RequestMapping(value = "/detail/{liverId}")
    @ResponseBody
    public Object detail(@PathVariable("liverId") Long liverId) {
        Liver liver = liverService.selectById(liverId);
//        RoomDto roomDto = new RoomDto();
//        BeanUtil.copyProperties(room, roomDto);
//        roomDto.setPName(ConstantFactory.me().getRoomName(roomDto.getPid()));
        return liver;
    }

    /**
     * 获取通知列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) throws ParseException {
        List<Map<String, Object>> list = this.liverService.list(condition);
        List<Map<String, Object>> mapList=new ArrayList<>();
        for (Map m : list) {


            if (m.get("visiable").equals(VisiableEnum.IS_VISIABLE.getCode())) {
                if (m.get("sex").equals(EmployeeSexEnum.MAIL.getCode())) {
                    m.put("sex", EmployeeSexEnum.MAIL.getMessage());
                } else if (m.get("sex").equals(EmployeeSexEnum.FEMALE.getCode())) {
                    m.put("sex", EmployeeSexEnum.FEMALE.getMessage());
                } else if (m.get("sex").equals(EmployeeSexEnum.UNKNOWN.getCode())) {
                    m.put("sex", EmployeeSexEnum.UNKNOWN.getMessage());
                }
                if (m.get("keyIf").equals(KeyIfEnum.TRUE.getCode())) {
                    m.put("keyIf", KeyIfEnum.TRUE.getMessage());
                } else if (m.get("keyIf").equals(KeyIfEnum.FALSE.getCode())) {
                    m.put("keyIf", KeyIfEnum.FALSE.getMessage());
                } else if (m.get("keyIf").equals(KeyIfEnum.UNKNOWN.getCode())) {
                    m.put("keyIf", KeyIfEnum.UNKNOWN.getMessage());
                }

                if(ToolUtil.isNotEmpty(m.get("endDate"))) {
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date endDate = dateFormat.parse((String) m.get("endDate"));
                    long validDays = ( new Date().getTime() - endDate.getTime()) / 1000 / 3600 / 24;
                    if (validDays >0) {
                        m.put("liverStatus", LiverStatusEnum.IS_NOT_VALID.getMessage() + validDays + "天");
                    }else {
                        m.put("liverStatus", LiverStatusEnum.IS_LIVING.getMessage());
                    }
                }else {
                    m.put("liverStatus", LiverStatusEnum.IS_LIVING.getMessage());
                }
                mapList.add(m);
            }
        }
        return super.warpObject(new LiverWrapper(mapList));
    }

    /**
     * 新增通知
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    @BussinessLog(value = "新增住户", key = "liver", dict = LiverMap.class)
    @Transactional
    public Object add(Liver liver) {
        if (ToolUtil.isOneEmpty(liver)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        Room room = this.roomService.selectById(liver.getRoomId());
        Integer nowCapacity=room.getCapacity()-room.getLiverNum();
        if(nowCapacity<=0){
            throw new ServiceException(BizExceptionEnum.CAPACITY_IS_NOT_ENOUGH);
        }
        room.setLiverNum(room.getLiverNum()+1);
        this.roomService.updateById(room);
        liver.setCreateUser(ShiroKit.getUserNotNull().getId());
        liver.setCreateTime(new Date());
        this.liverService.insert(liver);
        return SUCCESS_TIP;
    }

    /**
     * 注销用户
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    @BussinessLog(value = "注销用户", key = "liverId", dict = LiverMap.class)
    @Transactional
    public Object delete(@RequestParam Long liverId) {

        //缓存通知名称
        LogObjectHolder.me().set(ConstantFactory.me().getUserNameById(liverId));

        Liver liver = this.liverService.selectById(liverId);
        Room room = this.roomService.selectById(liver.getRoomId());
        room.setLiverNum(room.getLiverNum()-1);
        this.roomService.updateById(room);
        liver.setVisiable(VisiableEnum.IS_NOT_VISIABLE.getCode());
        this.liverService.updateById(liver);

        return SUCCESS_TIP;
    }
    /**
     * 通知用户
     */
    @RequestMapping(value = "/notice")
    @ResponseBody
    @BussinessLog(value = "通知用户", key = "liverId", dict = LiverMap.class)
    public Object notice(@RequestParam Long liverId) {

        //缓存通知名称
        LogObjectHolder.me().set(ConstantFactory.me().getUserNameById(liverId));
        Liver liver = this.liverService.selectById(liverId);
        String phone = liver.getPhone();
        String employeeName=liver.getEmployeeName();
        System.out.println("尊敬的"+employeeName+",您于南沙海港集装箱码头的宿舍合约将逾期。请及时联系综合事务部，进行续约!");
        return SUCCESS_TIP;
    }

    /**
     * 修改通知
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    @BussinessLog(value = "修改住户", key = "update", dict = LiverMap.class)

    public Object update(LiverDto liverDto) {
        if (ToolUtil.isOneEmpty(liverDto)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        Liver old = this.liverService.selectById(liverDto.getLiverId());
        BeanUtil.copyProperties(liverDto, old);
        old.setUpdateTime(new Date());

        this.liverService.updateById(old);
        return SUCCESS_TIP;
    }

}
