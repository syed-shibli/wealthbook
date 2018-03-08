package com.example.daffolapmac.wealthbook.screen.login.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.daffolapmac.wealthbook.R;
import com.example.daffolapmac.wealthbook.api.RetrofitClient;
import com.example.daffolapmac.wealthbook.common.BaseActivityImpl;
import com.example.daffolapmac.wealthbook.screen.login.model.LoginRequest;
import com.example.daffolapmac.wealthbook.screen.login.model.LoginRes;
import com.example.daffolapmac.wealthbook.widget.wbedittext.WBEditTextWithRounded;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends BaseActivityImpl {

    @BindView(R.id.edt_username)
    WBEditTextWithRounded mEdtUserName;

    @BindView(R.id.edt_user_password)
    WBEditTextWithRounded mEdtUserPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        setListener();
    }

    /**
     * Set listener for all field
     */
    private void setListener() {
        mEdtUserName.registerKeyListener();
        mEdtUserPassword.registerKeyListener();
    }

    @OnClick(R.id.btn_login)
    public void doLogin() {
        showProgress();
        // stageuser1@wealthbook.biz
        // WBstage123@
        LoginRequest request = new LoginRequest(mEdtUserName.getValue(), mEdtUserPassword.getValue());
        RetrofitClient.getApiService().login(request).enqueue(new Callback<LoginRes>() {
            @Override
            public void onResponse(@NonNull Call<LoginRes> call, @NonNull Response<LoginRes> response) {
                Log.d("Success : ", "S");
                hideProgress();
            }

            @Override
            public void onFailure(@NonNull Call<LoginRes> call, @NonNull Throwable t) {
                Log.d("Failure : ", "F");
                hideProgress();
            }
        });
    }
}
