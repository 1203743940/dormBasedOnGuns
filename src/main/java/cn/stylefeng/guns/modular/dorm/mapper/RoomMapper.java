package cn.stylefeng.guns.modular.dorm.mapper;

import cn.stylefeng.guns.core.common.node.TreeviewNode;
import cn.stylefeng.guns.core.common.node.ZTreeNode;
import cn.stylefeng.guns.modular.dorm.entity.Room;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;
/**
 * 房间表Mapper接口
 * @auther wmm
 * @date 2019/1/15 9:41
 */
public interface RoomMapper extends BaseMapper<Room> {


    /**
     * 获取ztree的节点列表
     */
    List<ZTreeNode> tree();


    /**
     * 获取房间列表
     */
    List<Map<String, Object>> list(@Param("condition") String condition, @RequestParam("buildingId")String buildingId);
    /**
     * 获取所有部门树列表
     */
    List<TreeviewNode> treeviewNodes();



    /**
     * 根据条件查询用户列表
     */
        List<Map<String, Object>> selectRooms( @Param("roomId") Long roomId, @Param("capacity") String capacity);

        List<ZTreeNode> roomTreeList();
        }
