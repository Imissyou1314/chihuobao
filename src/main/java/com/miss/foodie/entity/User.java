package com.miss.foodie.entity;

import java.util.Date;

/**
 * @author:MissYou
 * @CreateTime:2016/11/14:16:26
 * @description:
 */
public class User {

    private Integer userId;
    private String userName;
    private String password;
    private Date createTime;

    /**
     * Getter for property 'userId'.
     *
     * @return Value for property 'userId'.
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * Setter for property 'userId'.
     *
     * @param userId Value to set for property 'userId'.
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * Getter for property 'userName'.
     *
     * @return Value for property 'userName'.
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Setter for property 'userName'.
     *
     * @param userName Value to set for property 'userName'.
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Getter for property 'password'.
     *
     * @return Value for property 'password'.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter for property 'password'.
     *
     * @param password Value to set for property 'password'.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Getter for property 'createTime'.
     *
     * @return Value for property 'createTime'.
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * Setter for property 'createTime'.
     *
     * @param createTime Value to set for property 'createTime'.
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "User[" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", createTime=" + createTime +
                ']';
    }
}
