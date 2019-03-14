package cn.stylefeng.guns.core.common.constant.dictmap;

import cn.stylefeng.guns.core.common.constant.dictmap.base.AbstractDictMap;

/**
 * @auther wmm
 * @date 2019/1/16 13:39
 */
public class BuildingDict  extends AbstractDictMap {

    @Override
    public void init() {
        put("buildingId", "楼栋名称");
    put("num", "楼栋排序");
    put("pid", "上级名称");
    put("simplename", "楼栋简称");
    put("fullname", "楼栋全称");
    put("description", "备注");
}

    @Override
    protected void initBeWrapped() {
        putFieldWrapperMethodName("buildingId", "getBuildingName");
        putFieldWrapperMethodName("pid", "getDeptName");
    }
}