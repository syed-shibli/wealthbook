package com.example.daffolapmac.wealthbook.screen.login.presenter;

public interface ILoginScreenPresenter {

    /**
     * To perform login
     *
     * @param email Email string
     * @param pass  Pass string
     */
    void performLogin(String email, String pass);
}