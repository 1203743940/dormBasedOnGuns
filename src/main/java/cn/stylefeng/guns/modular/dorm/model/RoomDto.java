package cn.stylefeng.guns.modular.dorm.model;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @auther wmm
 * @date 2019/2/25 14:19
 */
@Data
public class RoomDto implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 房间号
     */
    private Long roomId;
    /**
     * 父部门id
     */
    private Long pid;
    /**
     * 父级ids
     */
    private String pids;
    /**
     * 父部门名称
     */
    private String pName;
    /**
     * 房间容量
     */
    private Integer capacity;
    /**
     * 居住人数
     */
    private Integer liverNum;
    /**
     * 床数量
     */
    private Integer bedNum;
    /**
     * 桌子数量
     */
    private Integer deskNum;
    /**
     * 椅子数量
     */
    private Integer chairNum;
    /**
     * 衣柜数量
     */
    private Integer chestNum;
    /**
     * 空调数量
     */
    private Integer airNum;
    /**
     * 钥匙数量
     */
    private Integer keyNum;
    /**
     * 水电费
     */
    private BigDecimal utilities;
    /**
     * 备注
     */
    private String comment;
    /**
     * 房间状态
     */
    private String status;
}
