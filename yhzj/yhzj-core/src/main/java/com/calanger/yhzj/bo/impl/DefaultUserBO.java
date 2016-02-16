package com.calanger.yhzj.bo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.calanger.common.bo.AbstractBO;
import com.calanger.common.dao.DAO;
import com.calanger.yhzj.bo.UserBO;
import com.calanger.yhzj.dao.UserDAO;
import com.calanger.yhzj.model.User;
import com.calanger.yhzj.vo.UserVO;

@Service("userBO")
public class DefaultUserBO extends AbstractBO<User, UserVO, java.lang.Integer> implements UserBO {
    @Autowired
    private UserDAO userDAO;

    @Override
    protected DAO<User, UserVO, java.lang.Integer> getDAO() {
        return userDAO;
    }
}
