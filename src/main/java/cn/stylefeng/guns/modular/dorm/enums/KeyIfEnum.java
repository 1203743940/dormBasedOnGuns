package cn.stylefeng.guns.modular.dorm.enums;

import lombok.Getter;

/**
 * @auther wmm
 * @date 2019/3/13 9:15
 */
@Getter
public enum KeyIfEnum {

    TRUE(0,"是"),
    FALSE(1,"否"),
    UNKNOWN(2,"未知")
    ;

    private Integer code;
    private String message;

    KeyIfEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
