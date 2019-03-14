package cn.stylefeng.guns.modular.dorm.mapper;

import cn.stylefeng.guns.modular.dorm.entity.Liver;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @auther wmm
 * @date 2019/2/18 13:38
 */
public interface LiverMapper extends BaseMapper<Liver> {


    /**
     * 获取人员列表
     */
    List<Map<String, Object>> list(@Param("condition") String condition);
}
