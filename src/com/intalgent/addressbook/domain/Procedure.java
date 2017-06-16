package com.intalgent.addressbook.domain;

import org.apache.poi.hssf.usermodel.HSSFCell;

/**
 * 函数，存储计划
 * Created by lr on 2017/6/15.
 */
public class Procedure {

    private String name;

    private Short type; //1 ：存储过程 2:函数

    private String comment; //说明

    private HSSFCell cell; //位置

    private String sqlModle;

    private String content; //内容

    public Procedure(String name, Short type, String comment) {
        this.name = name;
        this.type = type;
        this.comment = comment;
    }

    public String getSqlModle() {
        return sqlModle;
    }

    public void setSqlModle(String sqlModle) {
        this.sqlModle = sqlModle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public HSSFCell getCell() {
        return cell;
    }

    public void setCell(HSSFCell cell) {
        this.cell = cell;
    }

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }
}
