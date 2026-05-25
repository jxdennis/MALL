package com.shop.dao;
import com.shop.entity.User;
import com.shop.util.C3p0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

public class UserDao {
    private QueryRunner qr = new QueryRunner(C3p0Utils.getDataSource());

    public void addUser(User u) throws Exception {
        String sql = "INSERT INTO user(username, password, role, id_card, province, city, district) VALUES(?,?,?,?,?,?,?)";
        qr.update(sql, u.getUsername(), u.getPassword(), u.getRole(), u.getIdCard(), u.getProvince(), u.getCity(), u.getDistrict());
    }

    public User findByUsername(String username) throws Exception {
        String sql = "SELECT * FROM user WHERE username = ?";
        return qr.query(sql, new BeanHandler<>(User.class), username);
    }

    public User login(String username, String password) throws Exception {
        String sql = "SELECT * FROM user WHERE username = ? AND password = ?";
        return qr.query(sql, new BeanHandler<>(User.class), username, password);
    }
}