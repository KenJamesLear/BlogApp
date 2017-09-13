package com.teddylear.blogapp.Objects;

/**
 * Created by teddylear on 9/12/17.
 */

public class URLLinks {
    private String mainURL = "";
    private String registerUserURL = "/registerNewUser.php";
    private String loginUserURL = "/loginUser.php";

    public URLLinks(){}

    public String getRegisterUserURL() {
        return mainURL + registerUserURL;
    }

    public String getLoginUserURL() {
        return mainURL + loginUserURL;
    }
}
