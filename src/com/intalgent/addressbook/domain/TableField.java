package com.intalgent.addressbook.domain;

/**
 * 字段属性
 * Created by lr on 2017/6/12.
 */
public class TableField {

    private String tableCat;
    private String tableSchemaNaame;
    private String tableName;
    private String columnName;
    private int dataType;
    private String dataTypeName;
    private int columnSize;
    private int decimalDigits;
    private int numPrecRadix;
    private int nullAble;
    private String remarks;
    private String columnDef;
    private int sqlDataType;
    private int sqlDatetimeSub;
    private int charOctetLength;
    private int ordinalPosition;
    private String isNullAble;
    private String isAutoincrement;

    public TableField(String tableCat, String tableSchemaNaame, String tableName, String columnName, int dataType, String dataTypeName, int columnSize, int decimalDigits, int numPrecRadix, int nullAble, String remarks, String columnDef, int sqlDataType, int sqlDatetimeSub, int charOctetLength, int ordinalPosition, String isNullAble, String isAutoincrement) {
        this.tableCat = tableCat;
        this.tableSchemaNaame = tableSchemaNaame;
        this.tableName = tableName;
        this.columnName = columnName;
        this.dataType = dataType;
        this.dataTypeName = dataTypeName;
        this.columnSize = columnSize;
        this.decimalDigits = decimalDigits;
        this.numPrecRadix = numPrecRadix;
        this.nullAble = nullAble;
        this.remarks = remarks;
        this.columnDef = columnDef;
        this.sqlDataType = sqlDataType;
        this.sqlDatetimeSub = sqlDatetimeSub;
        this.charOctetLength = charOctetLength;
        this.ordinalPosition = ordinalPosition;
        this.isNullAble = isNullAble;
        this.isAutoincrement = isAutoincrement;
    }

    public String getTableCat() {
        return tableCat;
    }

    public void setTableCat(String tableCat) {
        this.tableCat = tableCat;
    }

    public String getTableSchemaNaame() {
        return tableSchemaNaame;
    }

    public void setTableSchemaNaame(String tableSchemaNaame) {
        this.tableSchemaNaame = tableSchemaNaame;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public int getDataType() {
        return dataType;
    }

    public void setDataType(int dataType) {
        this.dataType = dataType;
    }

    public String getDataTypeName() {
        return dataTypeName;
    }

    public void setDataTypeName(String dataTypeName) {
        this.dataTypeName = dataTypeName;
    }

    public int getColumnSize() {
        return columnSize;
    }

    public void setColumnSize(int columnSize) {
        this.columnSize = columnSize;
    }

    public int getDecimalDigits() {
        return decimalDigits;
    }

    public void setDecimalDigits(int decimalDigits) {
        this.decimalDigits = decimalDigits;
    }

    public int getNumPrecRadix() {
        return numPrecRadix;
    }

    public void setNumPrecRadix(int numPrecRadix) {
        this.numPrecRadix = numPrecRadix;
    }

    public int getNullAble() {
        return nullAble;
    }

    public void setNullAble(int nullAble) {
        this.nullAble = nullAble;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getColumnDef() {
        return columnDef;
    }

    public void setColumnDef(String columnDef) {
        this.columnDef = columnDef;
    }

    public int getSqlDataType() {
        return sqlDataType;
    }

    public void setSqlDataType(int sqlDataType) {
        this.sqlDataType = sqlDataType;
    }

    public int getSqlDatetimeSub() {
        return sqlDatetimeSub;
    }

    public void setSqlDatetimeSub(int sqlDatetimeSub) {
        this.sqlDatetimeSub = sqlDatetimeSub;
    }

    public int getCharOctetLength() {
        return charOctetLength;
    }

    public void setCharOctetLength(int charOctetLength) {
        this.charOctetLength = charOctetLength;
    }

    public int getOrdinalPosition() {
        return ordinalPosition;
    }

    public void setOrdinalPosition(int ordinalPosition) {
        this.ordinalPosition = ordinalPosition;
    }

    public String getIsNullAble() {
        return isNullAble;
    }

    public void setIsNullAble(String isNullAble) {
        this.isNullAble = isNullAble;
    }

    public String getIsAutoincrement() {
        return isAutoincrement;
    }

    public void setIsAutoincrement(String isAutoincrement) {
        this.isAutoincrement = isAutoincrement;
    }
}
