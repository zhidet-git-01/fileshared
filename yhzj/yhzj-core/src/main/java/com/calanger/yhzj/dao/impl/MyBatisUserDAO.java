package com.calanger.yhzj.dao.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.calanger.common.dao.AbstractDAO;
import com.calanger.yhzj.dao.UserDAO;
import com.calanger.yhzj.model.User;
import com.calanger.yhzj.vo.UserVO;

@Repository("userDAO")
public class MyBatisUserDAO extends AbstractDAO<User, UserVO, java.lang.Integer> implements UserDAO {
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Override
    protected SqlSessionTemplate getSqlSessionTemplate() {
        return sqlSessionTemplate;
    }

    @Override
    protected String getNamespace() {
        return "userDAO";
    }
}
