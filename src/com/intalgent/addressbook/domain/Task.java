package com.intalgent.addressbook.domain;

import org.apache.poi.hssf.usermodel.HSSFCell;

/**
 * 事件 定时任务
 * Created by lr on 2017/6/15.
 */
public class Task {

    private String name;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    private String content; //内容

    private String comment; //说明

    private HSSFCell cell; //位置

    public Task(String name, String content, String comment) {
        this.name = name;
        this.content = content;
        this.comment = comment;
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
}
