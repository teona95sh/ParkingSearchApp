package com.example.parkingsearchapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SignInScreen#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignInScreen extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SignInScreen() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainScreen.
     */
    // TODO: Rename and change types and number of parameters
    public static SignInScreen newInstance(String param1, String param2) {
        SignInScreen fragment = new SignInScreen();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_in_screen, container, false);
        TextView textView = view.findViewById(R.id.textView2);
        EditText mail = view.findViewById(R.id.editTextTextEmailAddress);
        EditText password = view.findViewById(R.id.editTextTextPassword);
        Button buttonSignIn = view.findViewById(R.id.buttonSignIn);


        String  text = "אם אין ברשותך משתמש, לחץ כאן";
        SpannableString ss = new SpannableString(text);
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                Navigation.findNavController(view).navigate(R.id.action_signInScreen_to_signUpScreen);
            }
        };
        ss.setSpan(clickableSpan,25,28, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(ss);
        textView.setMovementMethod(LinkMovementMethod.getInstance());

        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mail.getText().toString().isEmpty() || password.getText().toString().isEmpty())
                {
                    if(mail.getText().toString().isEmpty()){
                        mail.setError("שדה זה הוא שדה חובה!");
                    }
                    if(password.getText().toString().isEmpty()){
                        password.setError("שדה זה הוא שדה חובה!");
                    }
                }
                else {
                        MainActivity mainActivity = (MainActivity) getActivity();
                        mainActivity.loginFunc(v);
                        Navigation.findNavController(v).navigate(R.id.action_signInScreen_to_mainScreen);
                }
            }
        });

        return view;
    }


}