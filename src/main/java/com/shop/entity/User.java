package com.shop.entity;

import java.util.Date;

public class User {
    private int id;
    private String username;
    private String password;
    private String role;
    private String idCard; // 对应数据库 id_card
    private int province;
    private int city;
    private int district;
    private Date regTime;  // 对应数据库 reg_time

    // 必须保留无参构造（DbUtils反射需要）
    public User() {
    }

    // 全参构造（方便手动创建对象）
    public User(int id, String username, String password, String role, String idCard, int province, int city, int district, Date regTime) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.idCard = idCard;
        this.province = province;
        this.city = city;
        this.district = district;
        this.regTime = regTime;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getIdCard() { return idCard; }
    public void setIdCard(String idCard) { this.idCard = idCard; }

    public int getProvince() { return province; }
    public void setProvince(int province) { this.province = province; }

    public int getCity() { return city; }
    public void setCity(int city) { this.city = city; }

    public int getDistrict() { return district; }
    public void setDistrict(int district) { this.district = district; }

    public Date getRegTime() { return regTime; }
    public void setRegTime(Date regTime) { this.regTime = regTime; }
}