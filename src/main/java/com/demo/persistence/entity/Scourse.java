package com.demo.persistence.entity;

import java.io.Serializable;

public class Scourse implements Serializable {
    private Integer id;

    private String dbkey;

    private String driver;

    private String url;

    private String username;

    private String password;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDbkey() {
        return dbkey;
    }

    public void setDbkey(String dbkey) {
        this.dbkey = dbkey == null ? null : dbkey.trim();
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver == null ? null : driver.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Scourse other = (Scourse) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getDbkey() == null ? other.getDbkey() == null : this.getDbkey().equals(other.getDbkey()))
            && (this.getDriver() == null ? other.getDriver() == null : this.getDriver().equals(other.getDriver()))
            && (this.getUrl() == null ? other.getUrl() == null : this.getUrl().equals(other.getUrl()))
            && (this.getUsername() == null ? other.getUsername() == null : this.getUsername().equals(other.getUsername()))
            && (this.getPassword() == null ? other.getPassword() == null : this.getPassword().equals(other.getPassword()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getDbkey() == null) ? 0 : getDbkey().hashCode());
        result = prime * result + ((getDriver() == null) ? 0 : getDriver().hashCode());
        result = prime * result + ((getUrl() == null) ? 0 : getUrl().hashCode());
        result = prime * result + ((getUsername() == null) ? 0 : getUsername().hashCode());
        result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", dbkey=").append(dbkey);
        sb.append(", driver=").append(driver);
        sb.append(", url=").append(url);
        sb.append(", username=").append(username);
        sb.append(", password=").append(password);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public Scourse(String dbkey, String driver, String url, String username, String password) {
        this.dbkey = dbkey;
        this.driver = driver;
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public Scourse(String url, String username) {
        this.url = url;
        this.username = username;
    }

    public Scourse() {
    }
}