package cn.stylefeng.guns.modular.dorm.enums;

import lombok.Getter;

/**
 * @auther wmm
 * @date 2019/3/13 9:04
 */
@Getter
public enum LiverStatusEnum {

    IS_LIVING(0,"正常入住"),
    IS_NOT_VALID(1,"过期"),
    ;
//    IS_NOT_LIVING(2,"已办退宿");

    private Integer code;
    private String message;

    LiverStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
