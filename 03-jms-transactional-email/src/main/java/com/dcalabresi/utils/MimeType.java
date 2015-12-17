package com.dcalabresi.utils;

/**
 * Created by damiancalabresi on 03/12/14.
 */
public enum MimeType {
    TEXT("text/plain"), HTML("text/html");

    private String mimeType;

    MimeType(String mimeString) {
        this.mimeType = mimeString;

    }

    public String getMimeType() {
        return mimeType;
    }

}
