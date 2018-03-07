package com.example.daffolapmac.wealthbook;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.example.daffolapmac.wealthbook.api.RetrofitClient;
import com.example.daffolapmac.wealthbook.common.BaseActivityImpl;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivityImpl {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    // TODO Remove this after api testing done
    @OnClick(R.id.btn_login)
    public void login(){
        showProgress();
        LoginRequest request = new LoginRequest("stageuser1@wealthbook.biz", "WBstage123@");
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
