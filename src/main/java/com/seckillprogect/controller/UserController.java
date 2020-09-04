package com.seckillprogect.controller;

import com.seckillprogect.controller.viewobject.UserVo;
import com.seckillprogect.service.UserService;
import com.seckillprogect.service.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("user")
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/get")
    @ResponseBody
    public UserVo getUser(@RequestParam(name = "id")Integer id){
        //调用service服务获取对应id的用户对象并返回给前端
        UserModel userModel = userService.getUserById(id);
        //将核心领域模型用户对象转化为可供UI使用的viewobject
        return convertFromModel(userModel);
    }
    private UserVo convertFromModel(UserModel userModel){
        if (userModel==null){
            return null;
        }
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(userModel,userVo);
        return userVo;
    }
}
