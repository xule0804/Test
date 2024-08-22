package com.pccw.test.service;


public interface MailService {

    /**
     * Send mail
     *
     * @param email
     * @return
     */
    void sendMail(String email, String nickName) throws Exception;
}
