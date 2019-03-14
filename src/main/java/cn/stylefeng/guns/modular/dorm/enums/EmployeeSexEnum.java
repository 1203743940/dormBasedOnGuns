package cn.stylefeng.guns.modular.dorm.enums;

import lombok.Getter;

/**
 * @auther wmm
 * @date 2019/3/13 8:56
 */

@Getter
public enum EmployeeSexEnum {

    MAIL(0,"男"),
    FEMALE(1,"女"),
    UNKNOWN(2,"未知")
    ;
    private Integer code;
    private String message;

    EmployeeSexEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
