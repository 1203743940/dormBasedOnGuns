package cn.stylefeng.guns.modular.dorm.service;

import cn.stylefeng.guns.core.common.exception.BizExceptionEnum;
import cn.stylefeng.guns.core.common.node.TreeviewNode;
import cn.stylefeng.guns.core.common.node.ZTreeNode;
import cn.stylefeng.guns.modular.dorm.entity.Building;
import cn.stylefeng.guns.modular.dorm.mapper.BuildingMapper;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class BuildingService extends ServiceImpl<BuildingMapper, Building> {

    @Resource
    private BuildingMapper buildingMapper;

    /**
     * 新增楼栋
     */
    @Transactional(rollbackFor = Exception.class)
    public void addBuilding(Building building) {

        if (ToolUtil.isOneEmpty(building, building.getSimpleName(), building.getFullName(), building.getPid())) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }

        //完善pids,根据pid拿到pid的pids
        this.buildingSetPids(building);

        this.insert(building);
    }

    /**
     * 修改楼栋
     */
    @Transactional(rollbackFor = Exception.class)
    public void editBuilding(Building building) {

        if (ToolUtil.isOneEmpty(building, building.getBuildingId(), building.getSimpleName(), building.getFullName(), building.getPid())) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }

        //完善pids,根据pid拿到pid的pids
        this.buildingSetPids(building);

        this.updateById(building);
    }

    /**
     * 删除楼栋
     */
    @Transactional
    public void deleteBuilding(Long buildingId) {
        Building building = buildingMapper.selectById(buildingId);

        //根据like查询删除所有级联的楼栋
        Wrapper<Building> wrapper = new EntityWrapper<>();
        wrapper = wrapper.like("PIDS", "%[" + building.getBuildingId() + "]%");
        List<Building> subBuildings = buildingMapper.selectList(wrapper);
        for (Building temp : subBuildings) {
            this.deleteById(temp.getBuildingId());
        }

        this.deleteById(building.getBuildingId());
    }

    /**
     * 获取ztree的节点列表
     */
    public List<ZTreeNode> tree() {
        return this.baseMapper.tree();
    }

    /**
     * 获取ztree的节点列表
     */
    public List<TreeviewNode> treeviewNodes() {
        return this.baseMapper.treeviewNodes();
    }

    /**
     * 获取所有楼栋列表
     */
    public List<Map<String, Object>> list(String condition, String buildingId) {
        return this.baseMapper.list(condition, buildingId);
    }

    /**
     * 设置楼栋的父级ids
     */
    private void buildingSetPids(Building building) {
        if (ToolUtil.isEmpty(building.getPid()) || building.getPid().equals(0L)) {
            building.setPid(0L);
            building.setPids("[0],");
        } else {
            Long pid = building.getPid();
            Building temp = this.selectById(pid);
            String pids = temp.getPids();
            building.setPid(pid);
            building.setPids(pids + "[" + pid + "],");
        }
    }

    /**
     * 获取楼栋列表树
     */
    public List<ZTreeNode> buildingTreeList() {
        return this.baseMapper.buildingTreeList();
    }
}
