package com.calanger.yhzj.vo;

import java.util.ArrayList;
import java.util.List;

import com.calanger.common.dao.Expression;
import com.calanger.common.dao.ExpressionChain;
import com.calanger.yhzj.model.User;

public class UserVO extends User {
    private static final long serialVersionUID = 1L;

    private List<ExpressionChain> expressionChainList;

    public UserVO() {
        expressionChainList = new ArrayList<ExpressionChain>();
    }

    public UserVO or(ExpressionChain expressionChain) {
        expressionChainList.add(expressionChain);
        return this;
    }

    public UserVO or(Expression expression) {
        expressionChainList.add(new ExpressionChain().and(expression));
        return this;
    }

    public UserVO and(Expression expression) {
        if (expressionChainList.isEmpty()) {
            expressionChainList.add(new ExpressionChain());
        }
        expressionChainList.get(0).and(expression);
        return this;
    }

    public List<ExpressionChain> getExpressionChainList() {
        return expressionChainList;
    }

    public void setExpressionChainList(List<ExpressionChain> expressionChainList) {
        this.expressionChainList = expressionChainList;
    }
}
