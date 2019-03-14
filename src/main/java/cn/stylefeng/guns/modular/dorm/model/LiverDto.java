package cn.stylefeng.guns.modular.dorm.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @auther wmm
 * @date 2019/3/6 16:03
 */
@Data
public class LiverDto implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 住户编号
     */
    private Long liverId;
    /**
     * 房间号
     */
    private Long roomId;
    /**
     *
     */
    private String pid;
    /**
     * 姓名
     */
    private String employeeName;
    /**
     * 性别
     */
    private Integer sex;
    /**
     * 出生日期
     */
    private String birthday;
    /**
     * 联系电话
     */
    private String phone;
    /**
     * 身份证号
     */
    private String identify;

    /**
     * 公司
     */
    private String company;
    /**
     * 学历
     */
    private String education;
    /**
     * 职位
     */
    private String job;
    /**
     * 家庭住址
     */
    private String address;

    /**
     * 住宿日期
     */
    private String startDate;

    /**
     * 退宿日期
     */
    private String endDate;


    /**
     * 住户状态
     */

    private Integer liverStatus;


    private Integer visiable;
    /**
     * 是否持有钥匙
     */
    private Integer keyIf;
    /**
     * 备注
     */
    private String comment;

}
