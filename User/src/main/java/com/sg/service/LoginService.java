package com.sg.service;

import com.sg.entity.User;
import com.sg.result.Result;

public interface LoginService {
    Result login(User user);

    Result logout();
}
