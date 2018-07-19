package com.wealthbook.android.screen.forgotpassword.model;

import com.google.gson.annotations.SerializedName;

public class ForgotPasswordReq {
    @SerializedName("email")
    private String email;

    @SerializedName("reset_password_code")
    private String resetPasswordCode;

    @SerializedName("new_password")
    private String newPassword;

    public ForgotPasswordReq(String email, String resetPasswordCode, String newPassword) {
        this.email = email;
        this.resetPasswordCode = resetPasswordCode;
        this.newPassword = newPassword;
    }

    public String getEmail() {
        return email;
    }

    public String getResetPasswordCode() {
        return resetPasswordCode;
    }

    public String getNewPassword() {
        return newPassword;
    }
}
