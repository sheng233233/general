package edu.zut.service;

import edu.zut.entity.User;


public interface UserService {

    /**
     * 用户名和id查询
     * @param user 封装的user对象
     * @return
     */
    public User findByNameAndPwd(User user);


    public boolean updateById(User user);
}
