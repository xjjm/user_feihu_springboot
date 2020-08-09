package com.fh.interceptor;

import com.fh.model.User;
import com.fh.util.JwtUtils;
import com.fh.util.RedisPoolUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String token = request.getParameter("token");
        User user = JwtUtils.unsign(token, User.class);
        if(user==null){
            throw new Exception();
        }
        Jedis redisPool = RedisPoolUtil.getRedisPool();
        String retoken = redisPool.get("token_"+user.getName());
        if(StringUtils.isEmpty(retoken)){
            throw new Exception();
        }
        if(!token.equals(retoken)){
            throw new Exception();
        }
        redisPool.setex("token_"+user.getName(),60*30,token);
        RedisPoolUtil.setRedis(redisPool);
        request.setAttribute("user",user);
        return true;
    }
}
