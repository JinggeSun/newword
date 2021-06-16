package com.item.manager.controller.jvmlog;

import com.item.manager.service.jvmlog.FSNamesystemService;
import com.item.manager.service.jvmlog.JvmmetricsService;
import com.item.manager.util.ResultVOUtil;
import com.item.manager.vo.ResultVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zcm
 */
@RestController
@RequestMapping("/jvmlog")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class JvmLogController {

    private final FSNamesystemService fsNamesystemService;
    private final JvmmetricsService jvmmetricsService;

    /**
     *
     * @return
     */
    @GetMapping("/info")
    public ResultVO<Object> getInfo(){
        return ResultVOUtil.getSuccess(jvmmetricsService.list());
    }


}
