package com.sg.service;

import com.sg.entity.User;
import com.sg.result.Result;

public interface UserService {
    Result login(User user);

    Result logout();

    Result register(User user);
}
