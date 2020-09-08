package com.seckillprogect.controller;

import com.seckillprogect.controller.viewobject.UserVo;
import com.seckillprogect.error.BusinessException;
import com.seckillprogect.error.EmBusinessError;
import com.seckillprogect.response.CommonReturnType;
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
public class UserController extends BaseController{
    @Autowired
    private UserService userService;

    @RequestMapping("/get")
    @ResponseBody
    public CommonReturnType getUser(@RequestParam(name = "id")Integer id) throws BusinessException {
        //调用service服务获取对应id的用户对象并返回给前端
        UserModel userModel = userService.getUserById(id);
        //若获取的用户id不存在
        if (userModel==null){
            throw new BusinessException(EmBusinessError.USER_NOT_EXIST);
        }
        //将核心领域模型用户对象转化为可供UI使用的viewobject
        UserVo userVo =  convertFromModel(userModel);

        //返回通用对象
        return CommonReturnType.create(userVo);
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
