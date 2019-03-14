package cn.stylefeng.guns.modular.dorm.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.stylefeng.guns.core.common.annotion.BussinessLog;
import cn.stylefeng.guns.core.common.annotion.Permission;
import cn.stylefeng.guns.core.common.constant.Const;
import cn.stylefeng.guns.core.common.constant.dictmap.RoomMap;
import cn.stylefeng.guns.core.common.constant.factory.ConstantFactory;
import cn.stylefeng.guns.core.common.exception.BizExceptionEnum;
import cn.stylefeng.guns.core.common.node.ZTreeNode;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import cn.stylefeng.guns.core.shiro.ShiroKit;
import cn.stylefeng.guns.modular.dorm.entity.Room;
import cn.stylefeng.guns.modular.dorm.model.RoomDto;
import cn.stylefeng.guns.modular.dorm.service.RoomService;
import cn.stylefeng.guns.modular.dorm.warpper.RoomWarpper;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.datascope.DataScope;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.RequestEmptyException;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @auther wmm
 * @date 2019/1/15 11:56
 */

@Controller
@RequestMapping("/room")
public class RoomController extends BaseController {

    private String PREFIX = "/dorm/room/";

    @Autowired
    private RoomService roomService;


    /**
     * 跳转到房间列表首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "room.html";
    }

    /**
     * 跳转到添加房间
     */
    @RequestMapping("/room_add")
    public String roomAdd() {
        return PREFIX + "room_add.html";
    }

    /**
     * 跳转到修改房间
     */

    @RequestMapping("/room_update")
    public String roomUpdate(@RequestParam("roomId") Long roomId) {
        if (ToolUtil.isEmpty(roomId)) {
            throw new RequestEmptyException();
        }

        Room room=this.roomService.selectById(roomId);
//        model.addAllAttributes(BeanUtil.beanToMap(room));
//        model.addAttribute("roomId",roomId);
        LogObjectHolder.me().set(room);
        return PREFIX + "room_edit.html";
    }

    /**
     * 楼栋详情
     */
    @RequestMapping(value = "/detail/{roomId}")
    @ResponseBody
    public Object detail(@PathVariable("roomId") Long roomId) {
        Room room = roomService.selectById(roomId);
        RoomDto roomDto = new RoomDto();
        BeanUtil.copyProperties(room, roomDto);
        roomDto.setPName(ConstantFactory.me().getRoomName(roomDto.getPid()));
        return roomDto;
    }


    /**
     * 跳转到首页房间
     *
     * @author fengshuonan
     * @Date 2018/12/23 6:06 PM
     */
/*
    @RequestMapping("/hello")
    public String hello() {
        List<Map<String, Object>> rooms = roomService.list(null);
        super.setAttr("roomList", rooms);
        return PREFIX + "room_index.html";
    }
*/

    /**
     * 获取房间列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(@RequestParam(required = false) Long roomId,
                       @RequestParam(required = false) String capacity,
                       @RequestParam(required = false) String remain) {
        List<Map<String, Object>> allRooms = this.roomService.selectRooms(roomId, capacity);
        if(ToolUtil.isEmpty(remain)) {
            return super.warpObject(new RoomWarpper(allRooms));
        }
        List<Map<String, Object>> selectedRooms = new ArrayList<>();
        for (Map m : allRooms) {
            if (Integer.parseInt(remain) == ((int) m.get("capacity")-(int) m.get("liverNum"))){
                selectedRooms.add(m);
            }
        }
        return super.warpObject(new RoomWarpper(selectedRooms));
    }

    /**
     * 新增房间
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    @Permission
    @BussinessLog(value = "新增房间", key = "roomId", dict = RoomMap.class)
    public Object add(Room room) {
        if (ToolUtil.isOneEmpty(room, room.getRoomId(), room.getCapacity())) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        room.setCreateUser(ShiroKit.getUserNotNull().getId());
        room.setCreateTime(new Date());
        this.roomService.insert(room);
        return SUCCESS_TIP;
    }

    /**
     * 删除房间
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    @BussinessLog(value = "删除房间", key = "roomId", dict = RoomMap.class)
    public Object delete(@RequestParam Long roomId) {

        //缓存房间名称
        LogObjectHolder.me().set(ConstantFactory.me().getRoomRoomId(roomId));

        this.roomService.deleteById(roomId);

        return SUCCESS_TIP;
    }

    /**
     * 修改房间
     */
    @Permission
    @RequestMapping(value = "/update")
    @ResponseBody
    @BussinessLog(value = "修改房间", key = "roomId", dict = RoomMap.class)
    public Object update(Room room) {
        if (ToolUtil.isOneEmpty(room, room.getRoomId(), room.getCapacity(),room.getLiverNum())) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
//        Room old = this.roomService.selectById(room.getRoomId());
//        old.setCapacity(room.getCapacity());
//        old.setLiverNum(room.getLiverNum());
        this.roomService.updateById(room);
        return SUCCESS_TIP;
    }

    /**
     * 获取宿舍房间tree列表,ztree格式
     */
    @RequestMapping(value = "/selectRoomTreeList")
    @ResponseBody
    public List<ZTreeNode> selectRoomTreeList(){
        List<ZTreeNode> roomTreeList=this.roomService.roomTreeList();
        roomTreeList.add(ZTreeNode.createParent());
        return roomTreeList;
    }

    /**
     * 获取房间的tree列表，ztree格式
     */
    @RequestMapping(value = "/tree")
    @ResponseBody
    public List<ZTreeNode> tree() {
        List<ZTreeNode> tree = this.roomService.tree();
        tree.add(ZTreeNode.createParent());
        return tree;
    }



}
