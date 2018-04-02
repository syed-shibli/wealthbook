package com.example.daffolapmac.wealthbook.api;

import com.example.daffolapmac.wealthbook.common.ConformationRes;
import com.example.daffolapmac.wealthbook.screen.advisoragreement.mode.UserAgreementReq;
import com.example.daffolapmac.wealthbook.screen.advisoragreement.mode.UserAgreementRes;
import com.example.daffolapmac.wealthbook.screen.detailportfolio.model.PortfolioDetailRes;
import com.example.daffolapmac.wealthbook.screen.forgotpassword.model.ForgotPasswordReq;
import com.example.daffolapmac.wealthbook.screen.home.model.PendingAlertCountRes;
import com.example.daffolapmac.wealthbook.screen.login.model.LoginRequest;
import com.example.daffolapmac.wealthbook.screen.login.model.LoginRes;
import com.example.daffolapmac.wealthbook.screen.login.model.LoginTroubleRes;
import com.example.daffolapmac.wealthbook.screen.myallocation.model.MyAllocationRes;
import com.example.daffolapmac.wealthbook.screen.news.model.NewsRes;
import com.example.daffolapmac.wealthbook.screen.notificationalert.model.LatestPortfolioReviewRes;
import com.example.daffolapmac.wealthbook.screen.notificationalert.model.UpdatePortfolioReq;
import com.example.daffolapmac.wealthbook.screen.pendingalert.model.PendingAlertRes;
import com.example.daffolapmac.wealthbook.screen.portfolio.model.AllPortfolioRes;
import com.example.daffolapmac.wealthbook.screen.stockeod.model.StockEodRes;
import com.example.daffolapmac.wealthbook.screen.updates.model.UpdateRes;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    /**
     * Relative login URL of the resource
     * @param request Request body
     * @return Return the converted result of this login if converter is not assign then use ResponseBody class
     */
    @POST("auth/login")
    Call<LoginRes> login(@Body LoginRequest request);

    /**
     * Relative get news list URL of the resource
     * @return Return list of news
     */
    @GET("news/all")
    Call<NewsRes> getNews(@Query("token") String token);

    /**
     * Relative URL to send reset OTP to email
     * @param email Email where send OTP
     * @return Return confirmation message
     */
    @GET("auth/reset_password")
    Call<ConformationRes> emailOTP(@Query("email") String email);

    /**
     * Relative URL to change password
     * @param request Request body
     * @return Return Forgot password success or error response
     */
    @POST("auth/change_password")
    Call<ConformationRes> forgotPassword(@Body ForgotPasswordReq request);

    /**
     * Relative URL to login trouble
     * @return Return Email value for send email
     */
    @GET("wb/tangram_support")
    Call<LoginTroubleRes> loginTrouble();

    /**
     * Relative URL to get all update list
     * @return Return list of update
     */
    @GET("doc/all")
    Call<List<UpdateRes>> updateList(@Query("token") String token);

    /**
     * Relative URL to get selected update details
     * @return Return details
     */
    @GET("doc/document")
    Call<UpdateRes> getSelectedUpdateDetails(@Query("id") String id, @Query("token") String token);

    /**
     * Relative URL to get all allocation
     * @param token Token
     * @return Return all allocation account data
     */
    @GET("va/account_allocation")
    Call<MyAllocationRes> allAllocation(@Query("token") String token);

    /**
     * Relative URL to get all portfolio data
     * @param token Auth Token
     * @return Return all portfolio data model
     */
    @GET("portfolio/all")
    Call<AllPortfolioRes> getAllPortfolio(@Query("token") String token);

    /**
     * Relative URL to get selected portfolio details
     * @param id    ID of portfolio
     * @param token Auth token
     * @return Return selected portfolio details
     */
    @GET("portfolio/hold")
    Call<PortfolioDetailRes> getSelectedPortfolio(@Query("id") int id, @Query("token") String token);

    /**
     * Relative URL to get all pending alert list
     * @param token Auth token
     * @return Return pending alert response data
     */
    @GET("va/pending_alerts")
    Call<PendingAlertRes> getPendingAlert(@Query("token") String token);

    /**
     * Relative URL for accept/decline portfolio alert of notification
     * @param token Token
     * @param req   Update portfolio req
     * @return Return Updated result
     */
    @POST("va/update_portfolio_review")
    Call<PendingAlertRes> updatePortfolioNotification(@Query("token") String token, @Body UpdatePortfolioReq req);

    /**
     * Relative URL to get latest review of portfolio data
     * @param token Auth token
     * @return Latest portfolio data
     */
    @GET("va/latest_review")
    Call<LatestPortfolioReviewRes> latestPortfolioReview(@Query("token") String token);

    /**
     * Relative URL to get latest review of portfolio data
     * @param token Auth token
     * @param id    Pending alert id
     * @return Latest portfolio data
     */
    @GET("va/latest_review")
    Call<LatestPortfolioReviewRes> pendingPortfolioReview(@Query("token") String token, @Query("pending_alert_id") int id);

    /**
     * Relative URL to update user agreement status
     * @param token Auth token
     * @param req   Request body
     * @return Agreement status res
     */
    @POST("wb/user_agreement")
    Call<UserAgreementRes> updateUserAgreementStatus(@Query("token") String token, @Body UserAgreementReq req);

    /**
     * Relative URL to get specific stock of EOD
     * @param token  Auth Token
     * @param id     Stock id
     * @param ticker Ticker
     * @return Array of stock value
     */
    @GET("portfolio/eod")
    Call<List<StockEodRes>> getSpecificStockEOD(@Query("token") String token, @Query("id") int id, @Query("ticker") String ticker);

    /**
     * Get all pending alert count
     * @param token Auth token
     * @return Return count
     */
    @GET("va/pending_alert_count")
    Call<PendingAlertCountRes> pendingAlertCount(@Query("token") String token);
}
