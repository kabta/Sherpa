package com.example.sherpaatourguide.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.example.sherpaatourguide.R;
import com.example.sherpaatourguide.fragments.LoginFragment;

public class LoginActivity extends AppCompatActivity {
    FrameLayout mainContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        LoginFragment LoginFragment = new LoginFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.mainframeContainer, LoginFragment).commit();
    }
}