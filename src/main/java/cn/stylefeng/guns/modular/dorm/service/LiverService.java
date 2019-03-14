package cn.stylefeng.guns.modular.dorm.service;

import cn.stylefeng.guns.modular.dorm.entity.Liver;
import cn.stylefeng.guns.modular.dorm.mapper.LiverMapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @auther wmm
 * @date 2019/2/18 15:39
 */
@Service
public class LiverService extends ServiceImpl<LiverMapper,Liver> {

    public List<Map<String, Object>> list(String condition) {
        return this.baseMapper.list(condition);
    }
}
