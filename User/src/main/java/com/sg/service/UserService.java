package com.sg.service;

import com.sg.entity.User;
import com.sg.result.Result;

public interface UserService {
    Result login(User user);

    Result logout();

    Result register(User user);

    Result deleteUserByLoginName(User user);

    Result getUsers(Integer current, Integer pageSize);

    Result getUserById(int i);

    Result deleteUserById(int i);

    Result updateUserInfo(User user);
}
