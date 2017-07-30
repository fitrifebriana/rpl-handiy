package com.handiy.handiy.signin;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.handiy.handiy.MainActivity;
import com.handiy.handiy.R;
import com.handiy.handiy.data.UserModel;

import java.util.List;

public class SigninActivity extends AppCompatActivity implements SignInContract.View, GoogleApiClient.OnConnectionFailedListener {
    private SignInButton btnSignIn;
    private Button btnForgetPassword;
    private Button btnActivitySignUp;

    private GoogleSignInOptions signInOptions;
    private GoogleApiClient googleApiClient;
    private static final String TAG = SigninActivity.class.getSimpleName();
    private static final int RC_SIGN_IN = 9006;
    public ProgressDialog progressDialog;

    SignInPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        declareWidget();

        loginInit();

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
                signIn();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);

        }
    }

    private void declareWidget() {
        btnForgetPassword = (Button)findViewById(R.id.btn_forget_password);
        btnSignIn = (SignInButton) findViewById(R.id.btn_sign_in);
        btnActivitySignUp = (Button)findViewById(R.id.btn_activity_sign_up);
        progressDialog = new ProgressDialog(SigninActivity.this);

        presenter = new SignInPresenter(SigninActivity.this);
    }

    private void loginInit() {
        signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, signInOptions)
                .build();
        btnSignIn.setSize(SignInButton.SIZE_WIDE);

    }

    private void signIn(){
        Intent i = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(i, RC_SIGN_IN);
    }

    private void handleSignInResult(GoogleSignInResult result){
        Log.e("masuk","b");
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()){
            GoogleSignInAccount account = result.getSignInAccount();
            presenter.postSignIn(account.getId(), account.getEmail(), account.getDisplayName());
           // presenter.signInUp(idUser, account.getDisplayName(), account.getEmail(), );
        }
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showProgress() {
        progressDialog = ProgressDialog.show(this, "", "Login...", true, false);
    }

    @Override
    public void hideProgress() {
        progressDialog.dismiss();
    }

    @Override
    public void showErrorMessage(String message) {

    }

    @Override
    public void showMainView(List<UserModel> userDataModel) {
        Log.e("id ", userDataModel.get(0).getUsername());
        Intent intent = new Intent(SigninActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
