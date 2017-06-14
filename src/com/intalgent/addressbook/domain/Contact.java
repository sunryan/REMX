package com.intalgent.addressbook.domain;

public class Contact
{
    private String driverClass;
    private String jdbcUrl;
    private String userName;
    private String password;
    private String outUrl;

    public Contact() {

    }


    public Contact(String driverClass, String jdbcUrl, String userName, String password, String outUrl) {
        this.driverClass = driverClass;
        this.jdbcUrl = jdbcUrl;
        this.userName = userName;
        this.password = password;
        this.outUrl = outUrl;
    }

    public String getDriverClass() {
        return driverClass;
    }

    public void setDriverClass(String driverClass) {
        this.driverClass = driverClass;
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOutUrl() {
        return outUrl;
    }

    public void setOutUrl(String outUrl) {
        this.outUrl = outUrl;
    }
}
