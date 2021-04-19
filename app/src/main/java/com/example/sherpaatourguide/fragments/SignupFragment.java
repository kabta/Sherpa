package com.example.sherpaatourguide.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.sherpaatourguide.R;
import com.example.sherpaatourguide.Users;
import com.example.sherpaatourguide.activity.ui.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SignupFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignupFragment extends Fragment {
    TextView alreadyAMemberTV;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    int selected = 0;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    RadioButton femaleRadioBtn, maleRadioBtn;
    RadioGroup genderRadioGroup;

    FirebaseAuth fAuth;
    Button signup;
    EditText emailField, passwordField, confirmpassword;
    FirebaseDatabase firebaseDatabase;

    public SignupFragment() {

        // Required empty public constructor
    }

    public static SignupFragment newInstance(String param1, String param2) {
        SignupFragment fragment = new SignupFragment();
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
        View view = inflater.inflate(R.layout.fragment_signup, container, false);
        genderRadioGroup = view.findViewById(R.id.radioGroup);
        alreadyAMemberTV = view.findViewById(R.id.alreadyAMemberTV);
        femaleRadioBtn = view.findViewById(R.id.radioFemale);
        maleRadioBtn = view.findViewById(R.id.radioMale);
        emailField = view.findViewById(R.id.emailFieldET);
        passwordField = view.findViewById(R.id.passwordFieldET);
        confirmpassword = view.findViewById(R.id.confirmPasswordFieldET);
        signup = view.findViewById(R.id.signupBtn);
        fAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        genderRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radioMale)
                    selected = 1;
                else if (checkedId == R.id.radioFemale)
                    selected = 2;
            }
        });

        alreadyAMemberTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginFragment loginFragment = new LoginFragment();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.mainframeContainer, loginFragment);
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateData()) {
                    String email = emailField.getText().toString();
                    String password = passwordField.getText().toString();

                    fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                String uid = task.getResult().getUser().getUid();

                                Users user = new Users(uid, emailField.getText().toString(), passwordField.getText().toString(),0);
                                firebaseDatabase.getReference().child("Users").child(uid).setValue(user);
                                createAToast("User created successfully");
                                openHome();
                            }else
                            {
                                createAToast("Error !!!");
                            }
                        }
                    });

                }
            }
        });



        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private Boolean validateData() {
        Boolean isALLOK = true;

        String email = emailField.getText().toString();
        String password = passwordField.getText().toString();
        int selectedID = genderRadioGroup.getCheckedRadioButtonId();
        if(!(selectedID == R.id.radioFemale || selectedID == R.id.radioMale)) {
            createAToast("Select Male or female");

            return false;
        }





        if (email.isEmpty() && password.isEmpty()) {
            emailField.setError("Please fill this area");
            passwordField.setError("Please fill this area");
            createAToast("Email and password must not be empty");
            return false;
        } else if (email.isEmpty()) {
            emailField.setError("Please fill this area");
            return false;

        } else if (password.isEmpty()) {
            passwordField.setError("Please fill this area");
            return false;
        }


        if (password.length() < 8) {
            passwordField.setError("Password length is short");
            return false;
        }

        return isALLOK;




    }




    void createAToast(String message) {

        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    void openHome() {
        Intent locationIntent = new Intent(getActivity(), MainActivity.class);
        startActivity(locationIntent);
        getActivity().finish();
    }
}