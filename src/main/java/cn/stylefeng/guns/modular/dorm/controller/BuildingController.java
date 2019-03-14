/**
 * Copyright 2018-2020 stylefeng & fengshuonan (https://gitee.com/stylefeng)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.stylefeng.guns.modular.dorm.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.stylefeng.guns.core.common.annotion.BussinessLog;
import cn.stylefeng.guns.core.common.annotion.Permission;
import cn.stylefeng.guns.core.common.constant.dictmap.BuildingDict;
import cn.stylefeng.guns.core.common.constant.factory.ConstantFactory;
import cn.stylefeng.guns.core.common.node.TreeviewNode;
import cn.stylefeng.guns.core.common.node.ZTreeNode;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import cn.stylefeng.guns.modular.dorm.entity.Building;
import cn.stylefeng.guns.modular.dorm.model.BuildingDto;
import cn.stylefeng.guns.modular.dorm.service.BuildingService;
import cn.stylefeng.guns.modular.dorm.warpper.BuildingTreeWarpper;
import cn.stylefeng.guns.modular.dorm.warpper.BuildingWarpper;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import cn.stylefeng.roses.core.treebuild.DefaultTreeBuildFactory;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.RequestEmptyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * 部门控制器
 *
 * @author fengshuonan
 * @Date 2017年2月17日20:27:22
 */
@Controller
@RequestMapping("/building")
public class BuildingController extends BaseController {

    private String PREFIX = "/dorm/building/";

    @Autowired
    private BuildingService buildingService;

    /**
     * 跳转到部门管理首页
     *
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "building.html";
    }

    /**
     * 跳转到添加部门
     */
    @RequestMapping("/building_add")
    public String buildingAdd() {
        return PREFIX + "building_add.html";
    }

    /**
     * 跳转到修改
     */
    @Permission
    @RequestMapping("/building_update")
    public String buildingUpdate(@RequestParam("buildingId") Long buildingId) {

        if (ToolUtil.isEmpty(buildingId)) {
            throw new RequestEmptyException();
        }

        //缓存部门修改前详细信息
        Building building = buildingService.selectById(buildingId);
        LogObjectHolder.me().set(building);

        return PREFIX + "building_edit.html";
    }

    /**
     * 获取部门的tree列表，ztree格式
     */
    @RequestMapping(value = "/tree")
    @ResponseBody
    public List<ZTreeNode> tree() {
        List<ZTreeNode> tree = this.buildingService.tree();
        tree.add(ZTreeNode.createParent());
        return tree;
    }

    /**
     * 获取部门的tree列表，treeview格式
     */
    @RequestMapping(value = "/treeview")
    @ResponseBody
    public List<TreeviewNode> treeview() {
        List<TreeviewNode> treeviewNodes = this.buildingService.treeviewNodes();

        //构建树
        DefaultTreeBuildFactory<TreeviewNode> factory = new DefaultTreeBuildFactory<>();
        factory.setRootParentId("0");
        List<TreeviewNode> results = factory.doTreeBuild(treeviewNodes);

        //把子节点为空的设为null
        BuildingTreeWarpper.clearNull(results);

        return results;
    }

    /**
     * 新增部门
     *
     * @author fengshuonan
     * @Date 2018/12/23 4:57 PM
     */
    @BussinessLog(value = "添加楼栋", key = "simpleName", dict = BuildingDict.class)
    @RequestMapping(value = "/add")
    @Permission
    @ResponseBody
    public ResponseData add(Building building) {
        this.buildingService.addBuilding(building);
        return SUCCESS_TIP;
    }

    /**
     * 获取所有楼栋列表
     *
     * @author fengshuonan
     * @Date 2018/12/23 4:57 PM
     */
    @RequestMapping(value = "/list")
    @Permission
    @ResponseBody
    public Object list(@RequestParam(value = "condition", required = false) String condition,
                       @RequestParam(value = "buildingId", required = false) String buildingId) {
        List<Map<String, Object>> list = this.buildingService.list(condition, buildingId);
        return super.warpObject(new BuildingWarpper(list));
    }

    /**
     * 楼栋详情
     */
    @RequestMapping(value = "/detail/{buildingId}")
    @Permission
    @ResponseBody
    public Object detail(@PathVariable("buildingId") Long buildingId) {
        Building building = buildingService.selectById(buildingId);
        BuildingDto buildingDto = new BuildingDto();
        BeanUtil.copyProperties(building, buildingDto);
        buildingDto.setPName(ConstantFactory.me().getBuildingName(buildingDto.getPid()));
        return buildingDto;
    }

    /**
     * 修改楼栋
     */
    @BussinessLog(value = "修改楼栋", key = "simpleName", dict = BuildingDict.class)
    @RequestMapping(value = "/update")
    @Permission
    @ResponseBody
    public ResponseData update(Building building) {
        buildingService.editBuilding(building);
        return SUCCESS_TIP;
    }

    /**
     * 删除部门
     *
     * @author fengshuonan
     * @Date 2018/12/23 4:57 PM
     */
    @BussinessLog(value = "删除楼栋", key = "buildingId", dict = BuildingDict.class)
    @RequestMapping(value = "/delete")
    @Permission
    @ResponseBody
    public ResponseData delete(@RequestParam Long buildingId) {

        //缓存被删除的部门名称
        LogObjectHolder.me().set(ConstantFactory.me().getBuildingName(buildingId));

        buildingService.deleteBuilding(buildingId);

        return SUCCESS_TIP;
    }

    /**
     * 获取菜单列表(选择父级菜单用)
     */
//    @RequestMapping(value = "/selectBuildingTreeList")
//    @ResponseBody
//    public List<ZTreeNode> selectBuildingTreeList() {
//        List<ZTreeNode> roleTreeList = this.buildingService.buildingTreeList();
//        roleTreeList.add(ZTreeNode.createParent());
//        return roleTreeList;
//    }


}
