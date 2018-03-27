package com.example.daffolapmac.wealthbook.screen.advisoragreement.presenter;

public interface IUserAgreementScreenPresenter {

    /**
     * create req for accept/decline agreement
     * @param id     User id
     * @param status Status: 0 if decline otherwise 1
     */
    void acceptDeclineAgreement(String id, int status);

    /**
     * Disconnect api call
     */
    void disconnect();
}
