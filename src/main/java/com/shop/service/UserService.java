package com.shop.service;

import com.shop.dao.UserDao;
import com.shop.entity.User;
import com.shop.util.ValidationUtil;
import java.util.List;

public class UserService {
    private final UserDao userDao = new UserDao();

    public void register(User user, String confirmPassword) throws Exception {
        if (!ValidationUtil.isSupportedRole(user.getRole())) {
            throw new IllegalArgumentException("请选择正确的注册角色");
        }
        if (user.getUsername() == null || user.getUsername().trim().length() < 3) {
            throw new IllegalArgumentException("用户名至少需要 3 个字符");
        }
        if (!ValidationUtil.isStrongPassword(user.getPassword())) {
            throw new IllegalArgumentException("密码至少 8 位，并包含字母和数字");
        }
        if (!user.getPassword().equals(confirmPassword)) {
            throw new IllegalArgumentException("两次输入的密码不一致");
        }
        if (!ValidationUtil.isValidIdCard(user.getIdCard())) {
            throw new IllegalArgumentException("身份证号格式不正确");
        }
        if (user.getProvince() <= 0 || user.getCity() <= 0 || user.getDistrict() <= 0) {
            throw new IllegalArgumentException("请选择完整地区");
        }
        if (userDao.findByUsername(user.getUsername()) != null) {
            throw new IllegalArgumentException("用户名已被注册");
        }
        userDao.add(user);
    }

    public User login(String username, String password) throws Exception {
        return userDao.login(username, password);
    }

    public boolean exists(String username) throws Exception {
        return username != null && userDao.findByUsername(username.trim()) != null;
    }

    public List<User> findAll() throws Exception {
        return userDao.findAll();
    }

    public void delete(int id, int currentUserId) throws Exception {
        if (id == currentUserId) {
            throw new IllegalArgumentException("不能删除当前登录账号");
        }
        userDao.deleteById(id);
    }

    public long countByRole(String role) throws Exception {
        return userDao.countByRole(role);
    }
}
