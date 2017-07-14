package com.teddylear.blogapp;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.teddylear.blogapp.AsyncTasks.RegisterAsyncTask;
import com.teddylear.blogapp.Fragments.LoginFragment;
import com.teddylear.blogapp.Fragments.RegisterFragment;
import com.teddylear.blogapp.Fragments.StartMenuFragment;
import com.teddylear.blogapp.Objects.NewUserHelper;
import com.teddylear.blogapp.Objects.User;

public class MainActivity extends AppCompatActivity implements StartMenuFragment.OnExitListener,
        StartMenuFragment.OnRegisterListener,
        RegisterFragment.OnNewUserListener{

    private RegisterAsyncTask mRegisterAsyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        StartMenuFragment startMenuFragment = new StartMenuFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.frame_container, startMenuFragment);
        ft.commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void startRegisterFragment(){
        RegisterFragment registerFragment = new RegisterFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frame_container, registerFragment);
        ft.addToBackStack("REGISTER");
        ft.commit();
    }

    public void end(){
        finish();
    }

    public void registerUser(NewUserHelper newUserHelper){
        RegisterAsyncTask registerAsyncTask = new RegisterAsyncTask(this, newUserHelper);
        mRegisterAsyncTask = registerAsyncTask;
        registerAsyncTask.execute();
    }

    public void afterRegisterUser(Boolean result){
        //TODO update with what you want to do here, two options with either success or not
        if (result == false){
            Toast.makeText(this, "Error with registering user" , Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Account created", Toast.LENGTH_SHORT).show();
            StartMenuFragment startMenuFragment = new StartMenuFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.frame_container, startMenuFragment);
            ft.addToBackStack("STARTMENU");
            ft.commit();

        }
    }

    public void startLoginFragment(){
        LoginFragment loginFragment = new LoginFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frame_container, loginFragment);
        ft.addToBackStack("LOGIN");
        ft.commit();
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        if (mRegisterAsyncTask != null) mRegisterAsyncTask.cancel(true);
    }


}
