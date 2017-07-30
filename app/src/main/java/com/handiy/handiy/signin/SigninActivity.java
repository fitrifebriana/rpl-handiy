package com.handiy.handiy.signin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.handiy.handiy.MainActivity;
import com.handiy.handiy.R;

public class SigninActivity extends AppCompatActivity {
    private Button btnSignIn;
    private Button btnForgetPassword;
    private Button btnActivitySignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        declareWidget();
        buttonClickedActionPerformed();
    }

    private void buttonClickedActionPerformed() {
        btnForgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Button forget password clicked!", Toast.LENGTH_SHORT).show();
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SigninActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void declareWidget() {
        btnForgetPassword = (Button)findViewById(R.id.btn_forget_password);
        btnSignIn = (Button)findViewById(R.id.btn_sign_in);
        btnActivitySignUp = (Button)findViewById(R.id.btn_activity_sign_up);
    }
}
