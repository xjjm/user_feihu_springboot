package com.fh.controller;

import com.fh.model.Area;
import com.fh.model.User;
import com.fh.service.AreaService;
import com.fh.service.UserService;
import com.fh.util.JwtUtils;
import com.fh.util.RedisPoolUtil;
import com.fh.util.UploadUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("LoginController")
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private AreaService areaService;



    @RequestMapping("test")
    public String test(){
        return "success";
    }

    @RequestMapping("login.do")
    public Map login(String name,String password){
        Map map = new HashMap();
        try {
            if(StringUtils.isEmpty(name)){
                //账号为空
                map.put("code",1);
                return map;
            }
            if(StringUtils.isEmpty(password)){
                //密码为空
                map.put("code",2);
                return map;
            }
            User user = userService.selectUserByName(name);
            if(user==null){
                //账号不存在
                map.put("code",3);
                return map;
            }
            if(!(password.equals(user.getPassword()))){
                //密码错误
                map.put("code",4);
                return map;
            }
            String sign = JwtUtils.sign(user, 1000 * 60 * 60 * 24);
            Jedis redisPool = RedisPoolUtil.getRedisPool();
            redisPool.setex("token_"+user.getName(),60*30,sign);
            RedisPoolUtil.setRedis(redisPool);
            //登录成功
            map.put("code",5);
            map.put("token",sign);
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }

    @RequestMapping("register.do")
    public Map register(User user){
        Map map = new HashMap();
        try {
            if(StringUtils.isEmpty(user.getName())){
                //账号为空
                map.put("code",1);
                return map;
            }
            if(StringUtils.isEmpty(user.getPassword())){
                //密码为空
                map.put("code",2);
                return map;
            }
            User user1 = userService.selectUserByName(user.getName());
            if(user1!=null){
                //账号已存在
                map.put("code",3);
                return map;
            }
            userService.addUser(user);
            //注册成功
            map.put("code",4);
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }

    @RequestMapping("selectArea.do")
    public Map selectArea(){
        Map map = new HashMap();
        try {
            List<Area> areaList = areaService.selectArea();
            map.put("data",areaList);
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }

    @RequestMapping("ossUploadFile.do")
    public Map ossUploadFile(MultipartFile image){
        Map map = new HashMap();
        try {
            String uploadFile = UploadUtil.uploadFile(image);
            map.put("fileName",uploadFile);
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }

}
