package com.teddylear.blogapp.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.teddylear.blogapp.Objects.User;
import com.teddylear.blogapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    private EditText mEmailEditText;
    private EditText mPasswordEditText;
    private View mView;
    private OnLoginUserListener mOnLoginUserListener;

    public LoginFragment() {
        // Required empty public constructor
    }

    public interface OnLoginUserListener{
        void loginUser(User user);
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        mOnLoginUserListener = (OnLoginUserListener) context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        Button loginButton = (Button) view.findViewById(R.id.enterRegisterButton);
        loginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                prepLoginUser();
            }

        });
        mEmailEditText = (EditText) view.findViewById(R.id.loginEmailEditText);
        mPasswordEditText = (EditText) view.findViewById(R.id.loginPasswordEditText);
        mView = view;
        return view;
    }

    private void prepLoginUser(){
        User user = new User();
        String email = mEmailEditText.getText().toString();
        String password = mPasswordEditText.getText().toString();
        user.setEmail(email);
        user.setPassword(email);
        mOnLoginUserListener.loginUser(user);
    }
}
