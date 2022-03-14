package com.example.parkingsearchapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SignUpScreen#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignUpScreen extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SignUpScreen() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SignUpScreen.
     */
    // TODO: Rename and change types and number of parameters
    public static SignUpScreen newInstance(String param1, String param2) {
        SignUpScreen fragment = new SignUpScreen();
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
        View view = inflater.inflate(R.layout.fragment_sign_up_screen, container, false);
        Button buttonSignUp = view.findViewById(R.id.buttonSignUp);
        EditText email_text = view.findViewById(R.id.editEmail);
        EditText first_name_text = view.findViewById(R.id.editFirstName);
        EditText last_name_text = view.findViewById(R.id.editLastName);
        EditText phone_text = view.findViewById(R.id.editPhone);
        EditText user_name_text = view.findViewById(R.id.editUserName);
        EditText password_text =view.findViewById(R.id.editPassword);

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(email_text.getText().toString().isEmpty() || first_name_text.getText().toString().isEmpty() || last_name_text.getText().toString().isEmpty() || phone_text.getText().toString().isEmpty() || user_name_text.getText().toString().isEmpty() || password_text.getText().toString().isEmpty())
                {
                    if(email_text.getText().toString().isEmpty()){
                        email_text.setError("שדה זה הוא שדה חובה!");
                    }

                    if(password_text.getText().toString().isEmpty()){
                        password_text.setError("שדה זה הוא שדה חובה!");
                    }

                    if(first_name_text.getText().toString().isEmpty()){
                        first_name_text.setError("שדה זה הוא שדה חובה!");
                    }

                    if(last_name_text.getText().toString().isEmpty()){
                    last_name_text.setError("שדה זה הוא שדה חובה!"); }

                    if(phone_text.getText().toString().isEmpty()){
                    phone_text.setError("שדה זה הוא שדה חובה!"); }

                    if(user_name_text.getText().toString().isEmpty()){
                    user_name_text.setError("שדה זה הוא שדה חובה!");
                    }
                }
                else {
                    MainActivity mainActivity = (MainActivity) getActivity();

                    mainActivity.RegFunc();
                    mainActivity.addData();
                    mainActivity.getData();
                }

            }
        });

        return view;
    }
}