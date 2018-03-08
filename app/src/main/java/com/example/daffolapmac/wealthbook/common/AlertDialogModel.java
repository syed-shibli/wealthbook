package com.example.daffolapmac.wealthbook.common;

public class AlertDialogModel {

    private String mTitle;
    private String mMsg;
    private String mYesBtn;
    private String mNoBtn;
    private int mAction;
    private boolean isCancelable;

    public boolean isCancelable() {
        return isCancelable;
    }

    public void setCancelable(boolean cancelable) {
        isCancelable = cancelable;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public String getMsg() {
        return mMsg;
    }

    public void setMsg(String msg) {
        this.mMsg = msg;
    }

    public String getYesBtn() {
        return mYesBtn;
    }

    public void setYesBtn(String yesBtn) {
        this.mYesBtn = yesBtn;
    }

    public String getNoBtn() {
        return mNoBtn;
    }

    public void setNoBtn(String noBtn) {
        this.mNoBtn = noBtn;
    }

    public int getAction() {
        return mAction;
    }

    public void setAction(int mAction) {
        this.mAction = mAction;
    }
}
