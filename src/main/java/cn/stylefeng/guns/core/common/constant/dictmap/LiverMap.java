package cn.stylefeng.guns.core.common.constant.dictmap;

import cn.stylefeng.guns.core.common.constant.dictmap.base.AbstractDictMap;

/**
 * @auther wmm
 * @date 2019/2/18 15:52
 */
public class LiverMap extends AbstractDictMap {
    @Override
    public void init() {

    }

    @Override
    protected void initBeWrapped() {

        putFieldWrapperMethodName("liverId", "getLiverName");
    }
}
