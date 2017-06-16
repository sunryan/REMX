package com.intalgent.addressbook.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * 索引
 * Created by lr on 2017/6/15.
 */
public class TableIndex {
    private String name; //索引名称

    private List<String>  columnNameList = new ArrayList<String>();       //栏位，可能有多个

    private String type;   //索引类型

    private String comment;

    private Boolean isUnique;

    public TableIndex(String name, String type,String comment) {
        this.name = name;
        this.type = type;
        this.comment = comment;
    }


   /* public String getTypeStr(){
        String typeStr = "";
        switch ( this.type){
            case 0 : typeStr = "没有索引"; break;
            case 1 : typeStr = "聚集索引"; break;
            case 2 : typeStr = "哈希表索引"; break;
            case 3 : typeStr = "其他索引"; break;
        }
        return typeStr;
    }*/

    public void addColumnName(String columnName) {
        columnNameList.add(columnName);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColumnNames() {
        StringBuilder sb = new StringBuilder("");
        for(int i = 0; i < columnNameList.size(); i++){
            sb.append(columnNameList.get(i));
            if(i < columnNameList.size() - 1){
                sb.append(",");
            }
        }
        return sb.toString();
    }

    public void setColumnNameList(List<String> columnNameList) {
        this.columnNameList = columnNameList;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Boolean getUnique() {
        return isUnique;
    }

    public void setUnique(Boolean unique) {
        isUnique = unique;
    }
}
