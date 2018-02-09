package org.client.com.token.model;

import java.io.Serializable;

public class TokenModel implements Serializable {
    private String uuid;
    private String account;
    private long times;
    private String author;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public long getTimes() {
        return times;
    }

    public void setTimes(long times) {
        this.times = times;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public TokenModel() {
        super();
    }

    public TokenModel(String uuid, String account, long times, String author) {
        this.uuid = uuid;
        this.account = account;
        this.times = times;
        this.author = author;
    }

    @Override
    public String toString() {
        return "TokenModel{" +
                "uuid='" + uuid + '\'' +
                ", account='" + account + '\'' +
                ", times=" + times +
                ", author='" + author + '\'' +
                '}';
    }
}
