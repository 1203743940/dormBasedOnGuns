package cn.stylefeng.guns.modular.dorm.warpper;

import cn.stylefeng.guns.core.common.constant.factory.ConstantFactory;
import cn.stylefeng.roses.core.base.warpper.BaseControllerWrapper;
import cn.stylefeng.roses.kernel.model.page.PageResult;
import com.baomidou.mybatisplus.plugins.Page;

import java.util.List;
import java.util.Map;

/**
 * 房间列表的包装
 * @auther wmm
 * @date 2019/1/15 9:41
 */
public class RoomWarpper extends BaseControllerWrapper {
    public RoomWarpper(Map<String, Object> single) {
        super(single);
    }

    public RoomWarpper(List<Map<String, Object>> multi) {
        super(multi);
    }

    public RoomWarpper(Page<Map<String, Object>> page) {
        super(page);
    }

    public RoomWarpper(PageResult<Map<String, Object>> pageResult) {
        super(pageResult);
    }

    @Override
    protected void wrapTheMap(Map<String, Object> map) {
        Long creater = (Long) map.get("createUser");
        map.put("createrName", ConstantFactory.me().getUserNameById(creater));
    }
}
