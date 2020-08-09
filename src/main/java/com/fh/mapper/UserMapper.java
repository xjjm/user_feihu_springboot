package com.fh.mapper;

import com.fh.model.User;
import com.fh.model.vo.UserQuery;

import java.util.List;

public interface UserMapper {
    User selectUserByName(String name);

    void addUser(User user);

    Long selectCount(UserQuery userQuery);

    List<User> selectUser(UserQuery userQuery);
}
