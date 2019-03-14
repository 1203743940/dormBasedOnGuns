package cn.stylefeng.guns.modular.dorm.entity;


import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 房间表
 * @auther wmm
 * @date 2019/1/15 9:41
 */
@TableName("tb_room")
public class Room implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 房间号
     */
    @TableId(value = "ROOM_ID",type= IdType.ID_WORKER)
    private Long roomId;

    public Long getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Long buildingId) {
        this.buildingId = buildingId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @TableField("BUILDING_ID")
    private Long buildingId;

    /**
     * 父部门id
     */


    @TableField("PID")
    private Long pid;
    /**
     * 父级ids
     */
    @TableField("PIDS")
    private String pids;
    /**
     * 房间容量
     */
    @TableField("CAPACITY")
    private Integer capacity;
    /**
     * 居住人数
     */
    @TableField("LIVER_NUM")
    private Integer liverNum;
    /**
     * 床数量
     */
    @TableField("BED_NUM")
    private Integer bedNum;
    /**
     * 桌子数量
     */
    @TableField("DESK_NUM")
    private Integer deskNum;
    /**
     * 椅子数量
     */
    @TableField("CHAIR_NUM")
    private Integer chairNum;
    /**
     * 衣柜数量
     */
    @TableField("CHEST_NUM")
    private Integer chestNum;
    /**
     * 空调数量
     */
    @TableField("AIR_NUM")
    private Integer airNum;
    /**
     * 钥匙数量
     */
    @TableField("KEY_NUM")
    private Integer keyNum;
    /**
     * 水电费
     */
    @TableField("UTILITIES")
    private BigDecimal utilities;
    /**
     * 备注
     */
    @TableField("COMMENT")
    private String comment;
    /**
     * 房间状态
     */
    @TableField("STATUS")
    private String status;

    /**
     * 创建时间
     */
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private Date createTime;
    /**
     * 修改时间
     */
    @TableField(value = "UPDATE_TIME", fill = FieldFill.UPDATE)
    private Date updateTime;
    /**
     * 创建人
     */
    @TableField(value = "CREATE_USER", fill = FieldFill.INSERT)
    private Long createUser;
    /**
     * 修改人
     */
    @TableField(value = "UPDATE_USER", fill = FieldFill.UPDATE)
    private Long updateUser;

    @Override
    public String toString() {
        return "Room{" +
                "roomId='" + roomId +
                "buildingId='" + buildingId +
                ", pid=" + pid +
                ", pids='" + pids +
                ", capacity=" + capacity +
                ", liverNum=" + liverNum +
                ", bedNum=" + bedNum +
                ", deskNum=" + deskNum +
                ", chairNum=" + chairNum +
                ", chestNum=" + chestNum +
                ", airNum=" + airNum +
                ", keyNum=" + keyNum +
                ", utilities=" + utilities +
                ", comment='" + comment +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", createUser=" + createUser +
                ", updateUser=" + updateUser +
                '}';
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getPids() {
        return pids;
    }

    public void setPids(String pids) {
        this.pids = pids;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getLiverNum() {
        return liverNum;
    }

    public void setLiverNum(Integer liverNum) {
        this.liverNum = liverNum;
    }

    public Integer getBedNum() {
        return bedNum;
    }

    public void setBedNum(Integer bedNum) {
        this.bedNum = bedNum;
    }

    public Integer getDeskNum() {
        return deskNum;
    }

    public void setDeskNum(Integer deskNum) {
        this.deskNum = deskNum;
    }

    public Integer getChairNum() {
        return chairNum;
    }

    public void setChairNum(Integer chairNum) {
        this.chairNum = chairNum;
    }

    public Integer getChestNum() {
        return chestNum;
    }

    public void setChestNum(Integer chestNum) {
        this.chestNum = chestNum;
    }

    public Integer getAirNum() {
        return airNum;
    }

    public void setAirNum(Integer airNum) {
        this.airNum = airNum;
    }

    public Integer getKeyNum() {
        return keyNum;
    }

    public void setKeyNum(Integer keyNum) {
        this.keyNum = keyNum;
    }

    public BigDecimal getUtilities() {
        return utilities;
    }

    public void setUtilities(BigDecimal utilities) {
        this.utilities = utilities;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }

    public Long getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Long updateUser) {
        this.updateUser = updateUser;
    }
}
