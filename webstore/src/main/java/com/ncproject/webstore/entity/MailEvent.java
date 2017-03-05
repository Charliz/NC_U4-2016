package com.ncproject.webstore.entity;

import java.io.Serializable;

/**
 * Created by Champion on 01.03.2017.
 */
public class MailEvent implements Serializable {

    private String to; //recipient address
    private String message;
    private String subject;

    public MailEvent() {
    }

    public String get_To() {
        return to;
    }
    public void set_To(String to) {
        this.to = to;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "MailEvent{" +
                "to='" + to + '\'' +
                ", message='" + message + '\'' +
                ", subject='" + subject + '\'' +
                '}';
    }
}
