package com.dylan.test.auth.controller;

import com.dylan.test.auth.common.consts.ActionResult;
import com.dylan.test.auth.common.consts.RequestConsts;
import com.dylan.test.auth.entity.vo.AddUserVo;
import com.dylan.test.auth.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/admin/addUser")
    public ActionResult addUser(@RequestBody AddUserVo addUserVo) {
        if (!addUserVo.validate()) {
            return ActionResult.failure(RequestConsts.FAILURE_MISSING_PARAMETER, "Parameter missing, please check the request parameters");
        }
        return ActionResult.success(userService.addUser(addUserVo));
    }

    @GetMapping("/user/{resource}")
    public ActionResult<String> userResource(@PathVariable("resource") String resource){
        return ActionResult.success("success " + resource);
    }


}
