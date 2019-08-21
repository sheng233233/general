package edu.zut.service.impl;

import edu.zut.entity.User;
import edu.zut.mapper.UserMapper;
import edu.zut.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper um;

    @Override
    public User findByNameAndPwd(User user) {
        return um.findByNameAndPwd(user);
    }

    @Override
    public boolean updateById(User user) {
        return true;
    }
}
