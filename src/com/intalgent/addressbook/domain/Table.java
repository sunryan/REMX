package com.intalgent.addressbook.domain;


import org.apache.poi.hssf.usermodel.HSSFCell;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 表结构
 * Created by lr on 2017/6/12.
 */
public class Table {

    private String tableName;    //表名

    private List<String> primaryList;  //主键

    private String unique;  //唯一字段

    private String comment; //表说明

    private List<TableField> fields;

    private Map<String,List<String>> keyMap; //所有索引map key：索引名， value:列名，多个

    private HSSFCell cell; //位置


    public Map<String, List<String>> getKeyMap() {
        return keyMap;
    }

    public void setKeyMap(Map<String, List<String>> keyMap) {
        this.keyMap = keyMap;
    }

    public Table(String tableName) {
        this.tableName = tableName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<String> getPrimaryList() {
        return primaryList;
    }

    public void addPrimaryList(String primary) {
        if(primaryList == null ){
            primaryList = new ArrayList<String>();
        }
        this.primaryList.add(primary);
    }

    public String getUnique() {
        return unique;
    }

    public void setUnique(String unique) {
        this.unique = unique;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<TableField> getFields() {
        return fields;
    }

    public void setFields(List<TableField> fields) {
        this.fields = fields;
    }

    public HSSFCell getCell() {
        return cell;
    }

    public void setCell(HSSFCell cell) {
        this.cell = cell;
    }
}
