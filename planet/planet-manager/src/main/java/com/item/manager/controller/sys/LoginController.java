package com.item.manager.controller.sys;

import com.item.manager.entity.sys.User;
import com.item.manager.service.sys.UserService;
import com.item.manager.util.JwtUtil;
import com.item.manager.util.ResultVOUtil;
import com.item.manager.vo.ResultVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zcm
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class LoginController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResultVO<Object> login(@RequestBody User loginUser) {

        //1. 获取信息
        String username = loginUser.getUsername();
        String password = loginUser.getPassword();

        //2. 用户认证信息,登陆所有的验证都交给shiro
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(
                username, password);
        try {
            //进行验证，这里可以捕获异常，然后返回对应信息
            subject.login(usernamePasswordToken);
        } catch (UnknownAccountException e) {
            log.error("用户名不存在！", e);
            return ResultVOUtil.getFailureMsg("用户名不存在！");
        } catch (AuthenticationException e) {
            log.error("账号或密码错误！", e);
            return ResultVOUtil.getFailureMsg("账号或密码错误！");
        } catch (AuthorizationException e) {
            log.error("没有权限！", e);
            return ResultVOUtil.getFailureMsg("没有权限");
        }
        //正确，生成token，返回
        Map<String, Object> tokenMap = new HashMap<String, Object>(2);
        tokenMap.put("username", username);
        String token = jwtUtil.generateToken(tokenMap);
        return ResultVOUtil.getSuccess(token);

    }

    @GetMapping("/loginVerification")
    public ResultVO<Object> loginVerification() {
        return ResultVOUtil.getLoginVerification();
    }


}
