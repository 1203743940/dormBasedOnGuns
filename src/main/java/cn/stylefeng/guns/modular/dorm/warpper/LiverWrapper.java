package cn.stylefeng.guns.modular.dorm.warpper;

import cn.stylefeng.guns.core.common.constant.factory.ConstantFactory;
import cn.stylefeng.roses.core.base.warpper.BaseControllerWrapper;
import cn.stylefeng.roses.kernel.model.page.PageResult;
import com.baomidou.mybatisplus.plugins.Page;

import java.util.List;
import java.util.Map;

/**
 * @auther wmm
 * @date 2019/2/18 15:49
 */
public class LiverWrapper extends BaseControllerWrapper {
    public LiverWrapper(Map<String, Object> single) {
        super(single);
    }

    public LiverWrapper(List<Map<String, Object>> multi) {
        super(multi);
    }

    public LiverWrapper(Page<Map<String, Object>> page) {
        super(page);
    }

    public LiverWrapper(PageResult<Map<String, Object>> pageResult) {
        super(pageResult);
    }

    @Override
    protected void wrapTheMap(Map<String, Object> map) {
        Long creater = (Long) map.get("createUser");
        map.put("createrName", ConstantFactory.me().getUserNameById(creater));
    }
}
