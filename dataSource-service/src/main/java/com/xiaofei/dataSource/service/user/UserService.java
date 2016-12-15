package com.xiaofei.dataSource.service.user;

import com.xiaofei.dataSource.dal.mapper.UserMapper;
import com.xiaofei.dataSource.dal.model.UserDO;
import com.xiaofei.dataSource.service.db.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2016/12/14 0014.
 */
@Service
@Slf4j
public class UserService {

    @Autowired
    UserMapper userMapper;

    @Transactional
    @DataSource("local")
    public int insertUser(UserDO userDO){
        log.info("call insertUser parameter:{}",userDO);
        return this.userMapper.insert(userDO);
    }


    @Transactional
    @DataSource(value = "local")
    public UserDO selectUser(Long id){
        log.info("call selectUser parameter:{}",id);
        return this.userMapper.selectById(id);
    }

}
