package com.teddylear.blogapp.AsyncTasks;

import android.os.AsyncTask;
import android.util.Log;
import com.teddylear.blogapp.Fragments.RegisterFragment;
import com.teddylear.blogapp.MainActivity;
import com.teddylear.blogapp.Objects.NewUserHelper;
import com.teddylear.blogapp.Objects.User;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * Created by ken on 6/14/2017.
 */

public class RegisterAsyncTask extends AsyncTask<NewUserHelper, Void, User> {
    private WeakReference<MainActivity> mWeakRefActivity;
    private NewUserHelper mNewUserHelper;
    private String mURL = "/registerNewUser.php";

    public RegisterAsyncTask(MainActivity mainActivity, NewUserHelper newUserHelper){
        this.mWeakRefActivity = new WeakReference<> (mainActivity);
        this.mNewUserHelper = newUserHelper;
    }

    @Override
    protected User doInBackground (NewUserHelper... newUsers) {
        User user = new User();
        try {

            URL object = new URL(mURL);
            HttpURLConnection con = (HttpURLConnection) object.openConnection();
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept", "application/json");
            con.setRequestMethod("POST");
            con.connect();

            JSONObject newUser = new JSONObject();
            newUser.put("firstname", mNewUserHelper.getFirstName());
            newUser.put("lastname", mNewUserHelper.getLastName());
            newUser.put("email", mNewUserHelper.getEmail());
            newUser.put("password", mNewUserHelper.getPassword());
            Log.d("JSONOBJ", newUser.toString());
            //String phpPost = URLEncoder.encode(newUser.toString(),"UTF-8");
            OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
            wr.write(newUser.toString());
            wr.flush();

            //display what returns the POST request

            StringBuilder sb = new StringBuilder();
            int HttpResult = con.getResponseCode();
            if (HttpResult == HttpURLConnection.HTTP_OK) {
                //log message corret and put into object to send back at the bottom. then start main menu fragment.
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(con.getInputStream(), "UTF-8"));
                String line = null;
                while ((line = br.readLine()) != null) {
                    sb.append(line + "\n");
                }
                br.close();
                //make log
                Log.d("REG RETURN", sb.toString());
                //TODO put result in User object
            } else {
                Log.d("REG RETURN", con.getResponseMessage());
            }
            user = null;//TODO REMOVE LATER
            con.disconnect();
        }catch (IOException|JSONException e){
            Log.e("Register Error", e.toString());
            user = null;
        }
        return user;
    }

    @Override
    protected void onPostExecute(User user){
        super.onPostExecute(user);
        MainActivity mainActivity = mWeakRefActivity.get();
        if (mainActivity ==  null) return;
        mainActivity.afterRegisterUser(user);
    }

}
