package com.fh.service.impl;

import com.fh.mapper.AreaMapper;
import com.fh.mapper.UserMapper;
import com.fh.model.User;
import com.fh.model.vo.DataTableResult;
import com.fh.model.vo.UserQuery;
import com.fh.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private AreaMapper areaMapper;


    @Override
    public User selectUserByName(String name) {
        return userMapper.selectUserByName(name);
    }

    @Override
    public void addUser(User user) {
        user.setRegisterdate(new Date());
        userMapper.addUser(user);
    }

    @Override
    public DataTableResult selectUser(UserQuery userQuery) {
        Long count = userMapper.selectCount(userQuery);
        List<User> userList = userMapper.selectUser(userQuery);
        for (User user : userList) {
            user.setAreaName(areaMapper.selectAreaNameByAreaId(user.getAreaId()));
        }
        DataTableResult dataTableResult = new DataTableResult(userQuery.getDraw(),count,count,userList);
        return dataTableResult;
    }
}
