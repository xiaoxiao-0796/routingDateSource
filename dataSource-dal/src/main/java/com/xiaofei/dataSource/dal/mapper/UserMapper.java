package com.xiaofei.dataSource.dal.mapper;

import com.xiaofei.dataSource.dal.model.UserDO;

/**
 * Created by Administrator on 2016/12/14 0014.
 */
public interface UserMapper {

    public int insert(UserDO userDO);

    public UserDO selectById(Long id);
}
