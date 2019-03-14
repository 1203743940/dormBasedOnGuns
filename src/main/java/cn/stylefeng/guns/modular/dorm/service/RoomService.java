package cn.stylefeng.guns.modular.dorm.service;

import cn.stylefeng.guns.core.common.node.TreeviewNode;
import cn.stylefeng.guns.core.common.node.ZTreeNode;
import cn.stylefeng.guns.modular.dorm.entity.Room;
import cn.stylefeng.guns.modular.dorm.mapper.RoomMapper;
import cn.stylefeng.roses.core.datascope.DataScope;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @auther wmm
 * @date 2019/1/15 9:41
 */

@Service
public class RoomService extends ServiceImpl<RoomMapper,Room> {




    public List<Map<String, Object>> list(String condition,String buildingId) {
        return this.baseMapper.list(condition,buildingId);
    }

    /**
     * 获取ztree的节点列表
     */
    public List<ZTreeNode> roomTreeList() {
        return this.baseMapper.roomTreeList();
    }


    /**
     * 获取ztree的节点列表
     */
    public List<TreeviewNode> treeviewNodes() {
        return this.baseMapper.treeviewNodes();
    }

    public List<Map<String,Object>> selectRooms( Long roomId,String capacity) {
        return  this.baseMapper.selectRooms(roomId,capacity);
    }

    public List<ZTreeNode> tree() {
        return this.baseMapper.tree();
    }
}
