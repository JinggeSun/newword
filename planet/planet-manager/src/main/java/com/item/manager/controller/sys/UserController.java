package com.item.manager.controller.sys;

import com.item.manager.entity.sys.User;
import com.item.manager.service.sys.UserService;
import com.item.manager.util.ResultVOUtil;
import com.item.manager.vo.ResultVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zcm
 */
@RestController
@RequestMapping("/sys/user")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class UserController {

    private final UserService userService;

    @PostMapping("/creat")
    public ResultVO<Object> create(@RequestBody User user){
        //创建密码
        return ResultVOUtil.getSuccess("");
    }

}
