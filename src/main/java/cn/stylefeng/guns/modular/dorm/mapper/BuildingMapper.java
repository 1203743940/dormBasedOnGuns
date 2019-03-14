package cn.stylefeng.guns.modular.dorm.mapper;

import cn.stylefeng.guns.core.common.node.TreeviewNode;
import cn.stylefeng.guns.core.common.node.ZTreeNode;
import cn.stylefeng.guns.modular.dorm.entity.Building;
import cn.stylefeng.guns.modular.system.entity.Dept;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 部门表 Mapper 接口
 * </p>
 *
 * @author stylefeng
 * @since 2018-12-07
 */
public interface BuildingMapper extends BaseMapper<Building> {

    /**
     * 获取ztree的节点列表
     */
    List<ZTreeNode> tree();

    /**
     * 获取所有楼栋列表
     */
    List<Map<String, Object>> list(@Param("condition") String condition, @Param("buildingId") String buildingId);

    /**
     * 获取所有楼栋树列表
     */
    List<TreeviewNode> treeviewNodes();
    /**
     * 获取所有楼栋树列表
     */
    List<ZTreeNode> buildingTreeList();
}
