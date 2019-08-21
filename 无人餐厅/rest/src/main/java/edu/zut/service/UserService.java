package edu.zut.service;

import edu.zut.entity.User;


public interface UserService {

    public User findByNameAndPwd(User user);

    public boolean updateById(User user);
}
