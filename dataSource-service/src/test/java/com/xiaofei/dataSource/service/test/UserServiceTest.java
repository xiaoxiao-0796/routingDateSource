package com.xiaofei.dataSource.service.test;

import com.xiaofei.dataSource.dal.model.UserDO;
import com.xiaofei.dataSource.service.user.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2016/12/14 0014.
 */
public class UserServiceTest extends BaseSpringTest {

    @Autowired
    UserService userService;

    @Test
    public void testInsert(){
        UserDO userDO = new UserDO();
        userDO.setName("xiao");
        userDO.setPasswd("1987654");
        int insert = this.userService.insertUser(userDO);
        System.out.print("======="+insert);
    }

    @Test
    public void testselect(){
//        DataSourceContextHolder.setDataSource("remote");
//        System.out.print("当前数据库："+DataSourceContextHolder.getDataSource());
        UserDO userDO = this.userService.selectUser(1L);
        System.out.print("======="+userDO);
    }
}
