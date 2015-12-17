package com.dcalabresi.mail.model;

/**
 * Created by damian on 10/07/15.
 */
public class EmailStatus {

    private String toAddress;
    private String status;
    private String rejectReason;

    public EmailStatus(String toAddress, String status, String rejectReason) {
        this.toAddress = toAddress;
        this.status = status;
        this.rejectReason = rejectReason;
    }

    public String getToAddress() {
        return toAddress;
    }

    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRejectReason() {
        return rejectReason;
    }

    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
    }

}
