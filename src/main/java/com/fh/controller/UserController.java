package com.fh.controller;

import com.fh.model.vo.DataTableResult;
import com.fh.model.vo.UserQuery;
import com.fh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("UserController")
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping("selectUser.do")
    public DataTableResult selectUser(UserQuery userQuery){
        DataTableResult dataTableResult = userService.selectUser(userQuery);
        return dataTableResult;
    }

}
