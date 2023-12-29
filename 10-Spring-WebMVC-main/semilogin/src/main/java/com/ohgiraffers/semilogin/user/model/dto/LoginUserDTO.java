package com.ohgiraffers.semilogin.user.model.dto;

import com.ohgiraffers.semilogin.common.UserAuth;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class LoginUserDTO {
    private int userCode;
    private String userEmail;
    private String userNickName;
    private String password;
    private String userStatus;
    private int petCode;
    private Date registDate;
    private Date updateDate;
    private UserAuth userAuth;

    public LoginUserDTO() {
    }

    public LoginUserDTO(int userCode, String userEmail, String userNickName, String password, String userStatus, int petCode, Date registDate, Date updateDate, UserAuth userAuth) {
        this.userCode = userCode;
        this.userEmail = userEmail;
        this.userNickName = userNickName;
        this.password = password;
        this.userStatus = userStatus;
        this.petCode = petCode;
        this.registDate = registDate;
        this.updateDate = updateDate;
        this.userAuth = userAuth;
    }
    public List<String> getAuth(){
        if(this.userAuth.getAuth().length()>0){
            return Arrays.asList(this.userAuth.getAuth().split(","));
        }
        return new ArrayList<>();
    }

    public int getUserCode() {
        return userCode;
    }

    public void setUserCode(int userCode) {
        this.userCode = userCode;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public int getPetCode() {
        return petCode;
    }

    public void setPetCode(int petCode) {
        this.petCode = petCode;
    }

    public Date getRegistDate() {
        return registDate;
    }

    public void setRegistDate(Date registDate) {
        this.registDate = registDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public UserAuth getUserAuth() {
        return userAuth;
    }

    public void setUserAuth(UserAuth userAuth) {
        this.userAuth = userAuth;
    }

    @Override
    public String toString() {
        return "LoginUserDTO{" +
                "userCode=" + userCode +
                ", userEmail='" + userEmail + '\'' +
                ", userNickName='" + userNickName + '\'' +
                ", password='" + password + '\'' +
                ", userStatus='" + userStatus + '\'' +
                ", petCode=" + petCode +
                ", registDate=" + registDate +
                ", updateDate=" + updateDate +
                ", userAuth=" + userAuth +
                '}';
    }
}
