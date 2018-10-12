package com.llx.bear.model.resultModel;

import android.databinding.BaseObservable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Author:  zhangshijie
 * Time: 2017/9/8 14:04
 */

public class BaseResultModel extends BaseObservable implements Serializable {

    public int Code;
    public boolean Success;
    @SerializedName("Error")
    public Object Error;
    @SerializedName("Message")
    public String Message;
    public long ResponseTicks;

    public int getCode() {
        return Code;
    }

    public void setCode(int code) {
        Code = code;
    }

    public boolean isSuccess() {
        return Success;
    }

    public void setSuccess(boolean success) {
        Success = success;
    }

    public Object getError() {
        return Error;
    }

    public void setError(Object error) {
        Error = error;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public long getResponseTicks() {
        return ResponseTicks;
    }

    public void setResponseTicks(long responseTicks) {
        ResponseTicks = responseTicks;
    }
}
