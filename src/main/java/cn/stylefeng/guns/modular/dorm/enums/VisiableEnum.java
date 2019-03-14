package cn.stylefeng.guns.modular.dorm.enums;

import lombok.Getter;

/**
 * @auther wmm
 * @date 2019/3/13 9:10
 */
@Getter
public enum VisiableEnum {
    IS_VISIABLE(0,"可见"),
    IS_NOT_VISIABLE(1,"不可见")
    ;


    private Integer code;
    private String message;

    VisiableEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
