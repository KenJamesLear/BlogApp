package com.teddylear.blogapp.AsyncTasks;

import android.os.AsyncTask;

import com.teddylear.blogapp.MainActivity;
import com.teddylear.blogapp.Objects.NewUserHelper;
import com.teddylear.blogapp.Objects.URLLinks;
import com.teddylear.blogapp.Objects.User;

import java.lang.ref.WeakReference;

/**
 * Created by teddylear on 7/20/17.
 */

public class LoginAsyncTask extends AsyncTask<User, Void, Boolean> {
    private WeakReference<MainActivity> mWeakRefActivity;
    private User mUser;
    private String mURL;

    public LoginAsyncTask(MainActivity mainActivity, User newUser, URLLinks link){
        this.mWeakRefActivity = new WeakReference<>(mainActivity);
        this.mUser = newUser;
        this.mURL = link.getLoginUserURL();
    }

    @Override
    protected Boolean doInBackground (User... loginUser) {
        Boolean result = false;
        return result;
    }

    @Override
    protected void onPostExecute(Boolean result){

    }
}
