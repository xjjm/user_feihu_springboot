package com.fh.service;

import com.fh.model.User;
import com.fh.model.vo.DataTableResult;
import com.fh.model.vo.UserQuery;

public interface UserService {
    User selectUserByName(String name);

    void addUser(User user);

    DataTableResult selectUser(UserQuery userQuery);
}
