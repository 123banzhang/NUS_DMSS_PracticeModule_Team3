import com.Application;
import com.sys.entity.User;
import com.sys.mapper.UserMapper;
import com.sys.service.impl.UserServiceImpl;
import com.sys.utils.JwtUtil;
import com.sys.vo.LoginVo;
import com.sys.vo.RegisterVo;
import com.sys.vo.RespBean;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = Application.class, properties = {"spring.config.location=classpath:application.yml"})
@Transactional
public class AuthTest {

    @Resource
    private UserServiceImpl userService;

    @Resource
    private UserMapper userMapper;

    private User user;
    private String userToken;

    @BeforeEach
    void setUp() {
        // Prepare a user entity
        user = new User();
        user.setUid("18012345777");
        user.setPassword("123456");
        user.setNickname("testNickname");
        user.setMajor("testMajor");
        user.setHead("testHead");
        user.setIdentity("user");
        // Insert user to DB for testing purposes
        userMapper.insert(user);

        // Generate token for testing
        userToken = JwtUtil.createJWT(user.getUid(), "User Authenticated", JwtUtil.JWT_TTL);
    }

    @Test
    void loginSuccessTest() {
        LoginVo loginVo = new LoginVo();
        loginVo.setMobile(user.getUid());
        loginVo.setPassword(user.getPassword());

        RespBean respBean = userService.login(loginVo);

        assertEquals(200, respBean.getCode());
        assertNotNull(respBean.getObject());
    }

    @Test
    void loginFailureTest() {
        LoginVo loginVo = new LoginVo();
        loginVo.setMobile(user.getUid());
        loginVo.setPassword("123");

        RespBean respBean = userService.login(loginVo);

        assertEquals(500210, respBean.getCode());
        assertNull(respBean.getObject());
    }

    @Test
    void verifyTest() {
        User verifiedUser = userService.verify(userToken);

        assertNotNull(verifiedUser);
        assertEquals(user.getUid(), verifiedUser.getUid());
    }

    @Test
    void registerSuccessTest() {
        RegisterVo registerVo = new RegisterVo();
        registerVo.setMobile("18077777777");
        registerVo.setPassword("123456");
        registerVo.setNickname("newNickname");
        registerVo.setMajor("newMajor");
        registerVo.setHead("newHead");

        RespBean respBean = userService.register(registerVo);

        assertEquals(200, respBean.getCode());
    }

    @Test
    void registerFailureTest() {
        RegisterVo registerVo = new RegisterVo();
        // Assume this mobile is already taken
        registerVo.setMobile(user.getUid());
        registerVo.setPassword("123");
        registerVo.setNickname("newNickname");

        RespBean respBean = userService.register(registerVo);

        assertEquals(500213, respBean.getCode());
    }
}


