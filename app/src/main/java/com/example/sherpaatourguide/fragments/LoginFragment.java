package com.example.sherpaatourguide.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sherpaatourguide.R;
import com.example.sherpaatourguide.activity.AddDataActivity;
import com.example.sherpaatourguide.activity.LocationPermissionActivity;
import com.example.sherpaatourguide.activity.ui.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    TextView forgetPassword, registerTV;
    Button login;
    EditText emailField, passwordField;
    FirebaseAuth fAuth;

    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter
     *               +
     *               1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
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
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        login = view.findViewById(R.id.loginBtn);
        emailField = view.findViewById(R.id.emailFieldET);
        passwordField = view.findViewById(R.id.passwordFieldET);
        registerTV = view.findViewById(R.id.registerTV);
        fAuth = FirebaseAuth.getInstance();


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( validateData()){
                    String email = emailField.getText().toString();
                    String password = passwordField.getText().toString();

                    fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {

                                String uid = task.getResult().getUser().getUid();
                                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                                firebaseDatabase.getReference().child("Users").child(uid).child("usertype").addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        int usertype = snapshot.getValue(Integer.class);
                                        if(usertype== 0){

                                        //    createAToast("Login Successful");
                                            openHome();
                                        }
                                        if(usertype == 1){
                                            createAToast("Admin Login Successful");

                                            openAdmin();
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });

                            }
                            
                        }
                    });

                }
            }
        });
//go to register form
        registerTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignupFragment signUpFragment = new SignupFragment();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.mainframeContainer, signUpFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        // authentication of the user



        return view;

    }
    private Boolean validateData(){
        Boolean isALLOK = true;
        String email = emailField.getText().toString();
        String password = passwordField.getText().toString();

        if(email.isEmpty()  && password.isEmpty()){
            emailField.setError("Please fill this area");
            passwordField.setError("Please fill this area");
            createAToast("Email and password must not be empty");
            return false;
        }else if(email.isEmpty()){
            emailField.setError("Please fill this area");
            return false;
        }
        else if(password.isEmpty()){
            passwordField.setError("Please fill this area");
            return false;
        }
        if (password.length() < 8) {
            passwordField.setError("Password length is short");
            return false;
        }


        if(email =="admin" && password =="admin"){
            createAToast("Admin Login");

            return true;
        }

        return isALLOK;
    }



    void createAToast(String message){

        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }


    void openHome() {
        Intent locationIntent = new Intent(getActivity(), LocationPermissionActivity.class);
        startActivity(locationIntent);
        getActivity().finish();
    }

    public void openAdmin(){
        Intent adminIntent = new Intent(getActivity(), AddDataActivity.class);
        startActivity(adminIntent);
        getActivity().finish();
    }
}