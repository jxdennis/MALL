package com.shop.dao;

import com.shop.entity.User;
import com.shop.util.C3p0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import java.util.List;

public class UserDao {
    private final QueryRunner runner = new QueryRunner(C3p0Utils.getDataSource());

    private static final String USER_SELECT = """
            SELECT u.id, u.username, u.password, u.role, u.id_card AS idCard,
                   u.province, u.city, u.district, u.reg_time AS regTime,
                   p.name AS provinceName, c.name AS cityName, d.name AS districtName
            FROM user u
            LEFT JOIN region p ON u.province = p.id
            LEFT JOIN region c ON u.city = c.id
            LEFT JOIN region d ON u.district = d.id
            """;

    public void add(User user) throws Exception {
        String sql = "INSERT INTO user(username, password, role, id_card, province, city, district) VALUES(?,?,?,?,?,?,?)";
        runner.update(sql, user.getUsername(), user.getPassword(), user.getRole(), user.getIdCard(),
                user.getProvince(), user.getCity(), user.getDistrict());
    }

    public User findByUsername(String username) throws Exception {
        return runner.query(USER_SELECT + " WHERE u.username = ?", new BeanHandler<>(User.class), username);
    }

    public User login(String username, String password) throws Exception {
        return runner.query(USER_SELECT + " WHERE u.username = ? AND u.password = ?",
                new BeanHandler<>(User.class), username, password);
    }

    public List<User> findAll() throws Exception {
        return runner.query(USER_SELECT + " ORDER BY u.id DESC", new BeanListHandler<>(User.class));
    }

    public void deleteById(int id) throws Exception {
        runner.update("DELETE FROM user WHERE id = ?", id);
    }

    public long countByRole(String role) throws Exception {
        Number value = runner.query("SELECT COUNT(*) FROM user WHERE role = ?", new ScalarHandler<>(), role);
        return value.longValue();
    }
}
