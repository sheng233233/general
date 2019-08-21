package edu.zut.mapper;

import edu.zut.entity.User;

public interface UserMapper {

    public User findByNameAndPwd(User user);


}
