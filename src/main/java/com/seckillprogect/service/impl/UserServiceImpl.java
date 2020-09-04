package com.seckillprogect.service.impl;

import com.seckillprogect.dao.UserDOMapper;
import com.seckillprogect.dao.UserPasswordDOMapper;
import com.seckillprogect.dataobject.UserDO;
import com.seckillprogect.dataobject.UserPasswordDO;
import com.seckillprogect.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.seckillprogect.service.model.UserModel;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDOMapper userDOMapper;
    @Autowired
    private UserPasswordDOMapper userPasswordDOMapper;

    @Override
    public UserModel getUserById(Integer id) {
        //调用userdomapper获取到对应用户的dataobject
        UserDO userDO = userDOMapper.selectByPrimaryKey(id);
        if (userDO==null){
            return null;
        }
        //通过用户id获取对应用户密码加密信息
        UserPasswordDO userPasswordDO = userPasswordDOMapper.selectByUserId(userDO.getId());
        return convertFromDataObject(userDO,userPasswordDO);
    }
    private UserModel convertFromDataObject(UserDO userDO, UserPasswordDO userPasswordDO){
        if (userDO==null){
            return null;
        }
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userDO,userModel);
        if (userPasswordDO!=null){
            userModel.setEncrptPassword(userPasswordDO.getEncrptPassword());
        }

        return userModel;
    }

}
