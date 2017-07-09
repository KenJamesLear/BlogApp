package com.teddylear.blogapp.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.teddylear.blogapp.R;

public class StartMenuFragment extends Fragment implements View.OnClickListener {
    private OnExitListener mExitListener;
    private OnRegisterListener mRegisterListener;

    public interface OnExitListener {
        void end();
    }

    public interface OnRegisterListener {
        void startRegisterFragment();
    }

    public StartMenuFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mExitListener = (OnExitListener) context;
        mRegisterListener = (OnRegisterListener) context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_start_menu, container, false);
        Button registerButton = (Button) view.findViewById(R.id.startRegisterButton);
        registerButton.setOnClickListener(this);
        Button loginButton = (Button) view.findViewById(R.id.startLoginButton);
        loginButton.setOnClickListener(this);
        Button exitButton = (Button) view.findViewById(R.id.startMenuExitButton);
        exitButton.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.startRegisterButton:
                //Toast.makeText(getActivity().getBaseContext(), "Register" , Toast.LENGTH_SHORT).show();
                mRegisterListener.startRegisterFragment();
                break;
            case R.id.startLoginButton:
                Toast.makeText(getActivity().getBaseContext(), "Login", Toast.LENGTH_SHORT).show();
                break;
            case R.id.startMenuExitButton:
                //Toast.makeText(getActivity().getBaseContext(), "Exit", Toast.LENGTH_SHORT).show();
                mExitListener.end();
                break;
        }
    }




}
