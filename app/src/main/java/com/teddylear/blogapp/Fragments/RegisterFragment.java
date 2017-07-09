package com.teddylear.blogapp.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.util.Log;
import com.teddylear.blogapp.Objects.NewUserHelper;
import com.teddylear.blogapp.R;


public class RegisterFragment extends Fragment {
    private View mView;
    private EditText mFirstNameEditText;
    private EditText mLastNameEditText;
    private EditText mEmailEditText;
    private EditText mPasswordEditText;
    private OnNewUserListener mOnNewUserListener;

    public RegisterFragment() {
        // Required empty public constructor
    }

    public interface OnNewUserListener {
        void registerUser(NewUserHelper newUserHelper);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mOnNewUserListener = (OnNewUserListener) context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        Button enterRegisterButton = (Button) view.findViewById(R.id.enterRegisterButton);
        enterRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getActivity().getBaseContext(), "Register new User" , Toast.LENGTH_SHORT).show();
                registerUser();
            }
        });
        mFirstNameEditText = (EditText) view.findViewById(R.id.firstNameEditText);
        mLastNameEditText = (EditText) view.findViewById(R.id.lastNameEditText);
        mEmailEditText = (EditText) view.findViewById(R.id.emailEditText);
        mPasswordEditText = (EditText) view.findViewById(R.id.passwordEditText);
        mView = view;
        return view;
    }

    private void registerUser(){
        //Toast.makeText(getActivity().getBaseContext(), "Register Button" , Toast.LENGTH_SHORT).show();
        NewUserHelper newUser = new NewUserHelper();
        newUser = makeNewUser();
        mOnNewUserListener.registerUser(newUser);
    }

    private NewUserHelper makeNewUser(){
        NewUserHelper newUser = new NewUserHelper();
        String firstName = mFirstNameEditText.getText().toString();
        //Log.d("newUserTest", firstName);
        String lastName = mLastNameEditText.getText().toString();
        //Log.d("newUserTest", lastName);
        String email = mEmailEditText.getText().toString();
        //Log.d("newUserTest",email);
        String password = mPasswordEditText.getText().toString();
        //Log.d("newUserTest", password);
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        newUser.setEmail(email);
        newUser.setPassword(password);
        return newUser;
    }

}
