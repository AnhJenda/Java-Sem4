package com.example.day4.utils;

import java.util.Collection;
import java.util.List;

/*
    @author: Dinh Quang Anh
    Date   : 6/14/2023
    Project: day4
*/
public class SelectQueryBuilder {
    // y nghia: la doi tuowng dai dien cho 1 cau truy van select
    private String selectClause;   // select cai gi
    private String fromClause;   // tuwf cais gif
    private ParamHolder paramHolder;
    private StringBuilder havingClauseBuilder;
    private OrderClauseBuilder orderClauseBuilder;
    private StringBuilder limitClause;
    private StringBuilder groupByClauseBuilder;
    private WhereClauseBuilder whereClauseBuilder;

    public SelectQueryBuilder(String selectClause) {
        this.selectClause = selectClause;
        this.fromClause = fromClause;
        this.paramHolder = new ParamHolder();
        this.whereClauseBuilder = new WhereClauseBuilder();
        this.havingClauseBuilder = new StringBuilder();
        this.orderClauseBuilder = new OrderClauseBuilder();
        this.limitClause = new StringBuilder();
        this.groupByClauseBuilder = new StringBuilder();
    }

    public void andClause(String clause){
        this.whereClauseBuilder.andClause(clause);
    }
    public void orClause(String clause){
        this.whereClauseBuilder.orClause(clause);
    }

    public void setSelectClause(String selectClause) {
        this.selectClause = selectClause;
    }

    public void setFromClause(String fromClause) {
        this.fromClause = fromClause;
    }

    public void addParams(Object param){
        this.paramHolder.add(param);
    }
    public void addParams(Collection<? extends Object> params) {
        paramHolder.addAll(params);
    }

    public List<Object> _getParams() {
        return paramHolder._getParams();
    }
    public String getQuery() {
        StringBuilder query = new StringBuilder();
        query.append(String.format(" SELECT %s", selectClause));
        query.append(String.format(" FROM %s", fromClause));

        if (!whereClauseBuilder.isEmpty()) {
            query.append(String.format(" WHERE %s", whereClauseBuilder.getClause()));
        }

        if (groupByClauseBuilder.length() > 0) {
            query.append(String.format(" GROUP BY %s", groupByClauseBuilder.toString()));
        }

        if (havingClauseBuilder.length() > 0) {
            query.append(String.format(" HAVING %s", havingClauseBuilder.toString()));
        }

        if (!orderClauseBuilder.isEmpty()) {
            query.append(String.format(" ORDER BY %s", orderClauseBuilder.getClause()));
        }

        if (limitClause.length() > 0) {
            query.append(String.format(" LIMIT %s", limitClause.toString()));
        }

        return query.toString();
    }
}
