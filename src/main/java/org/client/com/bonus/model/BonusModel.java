package org.client.com.bonus.model;

import java.util.Date;

public class BonusModel {
    //领取人
    private String account;
    //领取时间
    private Date starttime;
    //失效时间
    private Date endtime;
    //金额
    private Double money;
    //需满足金额
    private Double condition;

    public BonusModel() {
    }

    public BonusModel(String account, Date starttime, Date endtime, Double money, Double condition) {
        this.account = account;
        this.starttime = starttime;
        this.endtime = endtime;
        this.money = money;
        this.condition = condition;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Double getCondition() {
        return condition;
    }

    public void setCondition(Double condition) {
        this.condition = condition;
    }

    @Override
    public String toString() {
        return "BonusModel{" +
                "account='" + account + '\'' +
                ", starttime=" + starttime +
                ", endtime=" + endtime +
                ", money=" + money +
                ", condition=" + condition +
                '}';
    }
}
