package com.example.day4.utils;

import com.example.day4.constant.SqlStatementEnum;

/*
    @author: Dinh Quang Anh
    Date   : 6/14/2023
    Project: day4
*/
public class WhereClauseBuilder {
    private StringBuilder whereClause;

    public WhereClauseBuilder() {
        this.whereClause = new StringBuilder();
    }

    public void andClause (String clause){
        // todo: thêm vào đoạn and ...
        if (!isEmpty()){
            this.whereClause.append(SqlStatementEnum.SPACE.value)
                    .append(SqlStatementEnum.AND.value)
                    .append(SqlStatementEnum.SPACE.value);
        }
        this.whereClause.append(clause);
    }
    public void orClause (String clause){
        // todo: thêm vào đoạn or ...
        if (!isEmpty()){
            this.whereClause.append(SqlStatementEnum.SPACE.value)
                    .append(SqlStatementEnum.OR.value)
                    .append(SqlStatementEnum.SPACE.value);
        }
        this.whereClause.append(clause);
    }

    public boolean isEmpty(){
        return this.whereClause.toString().isEmpty();
    }

    public String getClause(){
        return this.whereClause.toString();
    }
}
