package com.fdm.socialhub.entity;


import javax.persistence.*;

/**
 * Created by saurabh.jain on 02/09/2015.
 */
@Entity
@Table(name = "users")
public class User {
    @Id private long user_id;
    @Column private String username;
    @Column private String password;
    @Column private String userType;


    public User() {
        username = "Saurabh";
        userType = "Normal user";
    }


    public long getId() {
        return user_id;
    }

    public void setId(long id) {
        this.user_id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
