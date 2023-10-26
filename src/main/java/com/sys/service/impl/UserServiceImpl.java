package com.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sys.entity.User;
import com.sys.mapper.UserMapper;
import com.sys.service.IUserService;
import com.sys.utils.JwtUtil;
import com.sys.vo.LoginVo;
import com.sys.vo.RegisterVo;
import com.sys.vo.RespBean;
import com.sys.vo.RespBeanEnum;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author zhijian-wang
 * @since 2023-10-08
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public RespBean login(LoginVo loginVo) {
        String mobile = loginVo.getMobile();
        String password = loginVo.getPassword();

        // Check if mobile and password are not empty
        if (StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)) {
            return RespBean.error(RespBeanEnum.LOGIN_ERROR);
        }

        // Get user by mobile
        User user = userMapper.selectById(mobile);
        if (user == null) {
            return RespBean.error(RespBeanEnum.LOGIN_ERROR);
        }

        // Validate password
        if (!password.equals(user.getPassword())) {
            return RespBean.error(RespBeanEnum.LOGIN_ERROR);
        }

        // Generate JWT token
        String token = JwtUtil.createJWT(user.getUid(), "User Authenticated", JwtUtil.JWT_TTL);
        return RespBean.success(token);

    }

    @Override
    public User verify(String token) {
        try {
            Claims claims = JwtUtil.parseJWT(token);
            String userId = claims.getId();

            // Validate token by retrieving the user
            User user = userMapper.selectById(userId);

            if(user == null) {
                // Handle case where user does not exist
                throw new Exception("User does not exist.");
            }

            return user;
        } catch (Exception e) {
            // Handle other exceptions like invalid token
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public RespBean register(RegisterVo registerVo) {
        User user = new User();
        LocalDateTime now = LocalDateTime.now();
        user.setUid(registerVo.getMobile()); // Assuming mobile is used as uid
        user.setPassword(registerVo.getPassword()); // Note: Ensure this is hashed and salted
        user.setNickname(registerVo.getNickname());
        user.setHead(registerVo.getHead());
        user.setMajor(registerVo.getMajor());
        user.setIdentity("user");
        // Set other necessary fields
        int result = userMapper.insert(user);
        if (result > 0) {
            return RespBean.success("Registration successful");
        } else {
            return RespBean.error(RespBeanEnum.REGISTRATION_ERROR);
        }
    }
}

