package com.niit.service;

import com.niit.model.User;
import org.apache.ibatis.annotations.Param;

public interface IUserService {

    boolean checkUsername(String username);

    boolean insertSelective(User record);

    boolean active(String code);

    User getByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
}
