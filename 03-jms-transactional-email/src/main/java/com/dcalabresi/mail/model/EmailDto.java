package com.dcalabresi.mail.model;

import com.dcalabresi.utils.MimeType;

import java.io.Serializable;

/**
 * Created by damian on 08/07/15.
 */
public class EmailDto implements Serializable {

    private static final long serialVersionUID = -7220680047416315914L;

    private String from;
    private String fromName;
    private String to;
    private String unsubscribeUrl;
    private String subject;
    private String body;
    private MimeType mimeType;
    private String fileBase64;
    private String fileName;

    public EmailDto() {
    }

    public EmailDto(String from, String fromName, String to, String unsubscribeUrl, String subject, String body, MimeType mimeType, String fileBase64, String fileName) {
        this.from = from;
        this.fromName = fromName;
        this.to = to;
        this.unsubscribeUrl = unsubscribeUrl;
        this.subject = subject;
        this.body = body;
        this.mimeType = mimeType;
        this.fileBase64 = fileBase64;
        this.fileName = fileName;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getUnsubscribeUrl() {
        return unsubscribeUrl;
    }

    public void setUnsubscribeUrl(String unsubscribeUrl) {
        this.unsubscribeUrl = unsubscribeUrl;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public MimeType getMimeType() {
        return mimeType;
    }

    public void setMimeType(MimeType mimeType) {
        this.mimeType = mimeType;
    }

    public String getFileBase64() {
        return fileBase64;
    }

    public void setFileBase64(String fileBase64) {
        this.fileBase64 = fileBase64;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

}
