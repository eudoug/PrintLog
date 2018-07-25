package com.fpc.model;

public class UserLogPrint {
    private String user;
    private Integer total;

    public UserLogPrint() {
    }

    public UserLogPrint(String user, Integer total) {
        this.user = user;
        this.total = total;
    }

    public String getUser() {
        return this.user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Integer getTotal() {
        return this.total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public void incrementPrintLog(int totalPages) {
        if (this.total == null) {
            this.total = 0;
        }
        this.total = this.total + totalPages;
    }

    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = 31 * result + (this.user == null ? 0 : this.user.hashCode());
        return result;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        UserLogPrint other = (UserLogPrint)obj;
        if (this.user == null ? other.user != null : !this.user.equals(other.user)) {
            return false;
        }
        return true;
    }
}