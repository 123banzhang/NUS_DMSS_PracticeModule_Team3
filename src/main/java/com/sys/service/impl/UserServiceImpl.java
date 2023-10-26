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

        // 检查手机号和密码是否为空
        if (StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)) {
            return RespBean.error(RespBeanEnum.LOGIN_ERROR);
        }

        // 根据手机号获取用户
        User user = userMapper.selectById(mobile);
        if (user == null) {
            return RespBean.error(RespBeanEnum.LOGIN_ERROR);
        }

        // 校验密码，这里你可以使用你自己的加密和校验逻辑
        if (!password.equals(user.getPassword())) { // 这里只是一个简单的示例，实际应用中密码应该是加密的
            return RespBean.error(RespBeanEnum.LOGIN_ERROR);
        }

        // 生成token
        String token = JwtUtil.createToken(user); // Assuming JwtUtil.createToken() generates a JWT
        return RespBean.success(token);

    }

    @Override
    public RespBean verify(String token) {
        User user = JwtUtil.verifyToken(token); // Assuming JwtUtil.verifyToken() extracts user info from a JWT
        if (user != null) {
            return RespBean.success(user);
        } else {
            return RespBean.error(RespBeanEnum.VERIFICATION_ERROR);
        }
    }

    @Override
    public RespBean register(RegisterVo registerVo) {
        User user = new User();
        LocalDateTime now = LocalDateTime.now();
        user.setUid(registerVo.getMobile()); // Assuming mobile is used as uid
        user.setPassword(registerVo.getPassword()); // Note: Ensure this is hashed and salted
        user.setNickname(registerVo.getNickname());
        user.setRegisterDate(now);
        user.setLastLoginDate(now);
        user.setLoginCount(0);
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

