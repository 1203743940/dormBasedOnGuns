package cn.stylefeng.guns.modular.dorm.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * @auther wmm
 * @date 2019/2/18 11:00
 */

@TableName("tb_liver")
public class Liver implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 住户编号
     */
    @TableId(value = "LIVER_ID",type = IdType.ID_WORKER)
    private Long liverId;
    /**
     * 房间号
     */
    @TableField("ROOM_ID")
    private Long roomId;
    /**
     *
     */
    @TableField("PID")
    private String pid;
    /**
     * 姓名
     */
    @TableField("EMPLOYEE_NAME")
    private String employeeName;
    /**
     * 性别
     */
    @TableField("SEX")
    private Integer sex;
    /**
     * 出生日期
     */
    @TableField("BIRTHDAY")
    private String birthday;
    /**
     * 联系电话
     */
    @TableField("PHONE")
    private String phone;
    /**
     * 身份证号
     */
    @TableField("IDENTIFY")
    private String identify;

    /**
     * 公司
     */
    @TableField("COMPANY")
    private String company;
    /**
     * 学历
     */
    @TableField("EDUCATION")
    private String education;
    /**
     * 职位
     */
    @TableField("JOB")
    private String job;
    /**
     * 家庭住址
     */
    @TableField("ADDRESS")
    private String address;

    /**
     * 住宿日期
     */
    @TableField("START_DATE")
    private String startDate;

    /**
     * 退宿日期
     */
    @TableField("END_DATE")
    private String endDate;

    public Integer getLiverStatus() {
        return liverStatus;
    }

    public void setLiverStatus(Integer liverStatus) {
        this.liverStatus = liverStatus;
    }

    /**
     * 住户状态
     */
    @TableField("LIVER_STATUS")

    private Integer liverStatus;
    /**
     * 是否可见
     */
    @TableField("VISIABLE")
    private Integer visiable;
    /**
     * 是否持有钥匙
     */
    @TableField("KEY_IF")
    private Integer keyIf;
    /**
     * 备注
     */
    @TableField("COMMENT")
    private String comment;
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


    public Long getLiverId() {
        return liverId;
    }

    public void setLiverId(Long liverId) {
        this.liverId = liverId;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdentify() {
        return identify;
    }

    public void setIdentify(String identify) {
        this.identify = identify;
    }



    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Integer getKeyIf() {
        return keyIf;
    }

    public Integer getVisiable() {
        return visiable;
    }

    public void setVisiable(Integer visiable) {
        this.visiable = visiable;
    }

    public void setKeyIf(Integer keyIf) {
        this.keyIf = keyIf;
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

    @Override
    public String toString() {
        return "Liver{" +
                "liverId=" + liverId +
                ", roomId=" + roomId +
                ", pid='" + pid +
                ", employeeName='" + employeeName +
                ", sex='" + sex +
                ", birthday='" + birthday +
                ", phone='" + phone +
                ", identify='" + identify +
                ", company='" + company +
                ", education='" + education +
                ", job='" + job +
                ", address='" + address +
                ", startDate='" + startDate +
                ", endDate='" + endDate +
                ", liverStatus='" + liverStatus +
                ", keyIf='" + keyIf +
                ", comment='" + comment +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", createUser=" + createUser +
                ", updateUser=" + updateUser +
                '}';
    }
}
