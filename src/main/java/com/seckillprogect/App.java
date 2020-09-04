package com.seckillprogect;

import com.seckillprogect.dao.UserDOMapper;
import com.seckillprogect.dataobject.UserDO;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Hello world!
 *
 */
@SpringBootApplication(scanBasePackages = {"com.seckillprogect"})  //指定主启动类
@RestController
@MapperScan("com.seckillprogect.dao")
public class App 
{
    @Autowired
    private UserDOMapper userDOMapper;

    @RequestMapping("/")
    public String home(){
        UserDO userDO = userDOMapper.selectByPrimaryKey(0);
        if (userDO==null){
            return "用户不存在";
        }else {
            return userDO.getName();
        }
    }
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        SpringApplication.run(App.class,args);
    }
}
