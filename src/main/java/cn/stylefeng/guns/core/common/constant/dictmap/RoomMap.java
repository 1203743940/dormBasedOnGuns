package cn.stylefeng.guns.core.common.constant.dictmap;

import cn.stylefeng.guns.core.common.constant.dictmap.base.AbstractDictMap;

/**
 * @auther wmm
 * @date 2019/1/15 13:47
 */
public class RoomMap extends AbstractDictMap {
    @Override
    public void init() {
        put("roomId","房间号");
        put("capacity","房间容量");
        put("liveNum","住户数量");
        put("bedNum","床位数量");
        put("deskNum","桌子数量");
        put("chairNum","椅子数量");
        put("chestNum","衣柜数量");
        put("airNum","空调数量");
        put("keyNum","钥匙数量");
        put("utilities","水电费");
        put("comment","备注");
    }

    @Override
    protected void initBeWrapped() {
        putFieldWrapperMethodName("sex", "getSexName");
        putFieldWrapperMethodName("pid", "getSingleRoleName");
        putFieldWrapperMethodName("deptId", "getDeptName");
        putFieldWrapperMethodName("roleId", "getSingleRoleName");
        putFieldWrapperMethodName("ids", "getMenuNames");
    }
}
