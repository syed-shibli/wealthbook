package com.example.daffolapmac.wealthbook.screen.notificationalert.model;

import com.google.gson.annotations.SerializedName;

public class LatestPortfolioReviewData {
    @SerializedName("wb_va_user_customer_portfolio_history_id")
    private Integer wbVaUserCustomerPortfolioHistoryId;

    @SerializedName("wb_va_product_name")
    private String wbVaProductName;

    @SerializedName("wb_customer_name")
    private String wbCustomerName;

    @SerializedName("wb_account_number")
    private String wbAccountNumber;

    @SerializedName("review_date")
    private String reviewDate;

    @SerializedName("is_editable")
    private int isEditable;

    @SerializedName("wb_status_id")
    private int wbStatusId;

    @SerializedName("wb_va_user_customer_account_id")
    private String wbVaUserCustomerAccountId;

    @SerializedName("wb_customer_user_id")
    private int wbCustomerUserId;

    @SerializedName("wb_status_updated_date")
    private String wbStatusUpdatedDate;

    @SerializedName("portfolio_type")
    private String portfolioType;

    @SerializedName("wb_va_category_id")
    private int wbVaCategoryId;

    @SerializedName("wb_va_company_id")
    private int wbVaCompanyId;

    @SerializedName("status_date")
    private String statusDate;

    public int getWbVaUserCustomerPortfolioHistoryId() {
        return wbVaUserCustomerPortfolioHistoryId;
    }

    public String getWbVaProductName() {
        return wbVaProductName;
    }

    public String getWbCustomerName() {
        return wbCustomerName;
    }

    public String getWbAccountNumber() {
        return wbAccountNumber;
    }

    public String getReviewDate() {
        return reviewDate;
    }

    public int getIsEditable() {
        return isEditable;
    }

    public int getWbStatusId() {
        return wbStatusId;
    }

    public String getWbVaUserCustomerAccountId() {
        return wbVaUserCustomerAccountId;
    }

    public int getWbCustomerUserId() {
        return wbCustomerUserId;
    }

    public String getWbStatusUpdatedDate() {
        return wbStatusUpdatedDate;
    }

    public String getPortfolioType() {
        return portfolioType;
    }

    public int getWbVaCategoryId() {
        return wbVaCategoryId;
    }

    public int getWbVaCompanyId() {
        return wbVaCompanyId;
    }

    public String getStatusDate() {
        return statusDate;
    }

    public void setWbStatusId(int wbStatusId) {
        this.wbStatusId = wbStatusId;
    }
}
