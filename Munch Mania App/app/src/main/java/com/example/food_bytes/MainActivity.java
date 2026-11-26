package com.example.food_bytes;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private FrameLayout frameLayout;
    private Animation slideUpAnimation,slideDownAnimation;
    private EditText lUserName,lPassword;
    private Button lSignInButton,lSignUpButton;
    private EditText sUserName,sPassword,sPhoneNo,sEmail,sConfirmPassword;
    private Button sSignInButton,sSignUpButton;
    private boolean flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        frameLayout=findViewById(R.id.frameLayout);
        slideUpAnimation= AnimationUtils.loadAnimation(this,R.anim.slide_up);
        slideDownAnimation=AnimationUtils.loadAnimation(this,R.anim.slide_down);

        loadSignInView();
    }

    private void loadSignInView() {
        frameLayout.removeAllViews();
        View signInView = LayoutInflater.from(this).inflate(R.layout.sign_in, frameLayout, false);
        frameLayout.addView(signInView);

        flag=true;

        lUserName=findViewById(R.id.lUserName);
        lPassword=findViewById(R.id.lPassword);
        lSignInButton=findViewById(R.id.lSignIn);
        lSignUpButton=findViewById(R.id.lSignUp);

        lSignInButton.setOnClickListener(this::signIn);
        lSignUpButton.setOnClickListener(this::slideUp);
    }

    private void signIn(View view) {
        if(lUserName.getText().toString().equals("username") && lPassword.getText().toString().equals("password")) {
            Toast.makeText(getApplicationContext(),"Sign In Successful!",Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(),"Sign In Failed!",Toast.LENGTH_SHORT).show();
        }
    }

    private void loadSignUpView() {
        frameLayout.removeAllViews();
        View signUpView = LayoutInflater.from(this).inflate(R.layout.sign_up, frameLayout, false);
        frameLayout.addView(signUpView);

        flag=false;

        sUserName=findViewById(R.id.sUserName);
        sPhoneNo = findViewById(R.id.sPhoneNo);
        sEmail = findViewById(R.id.sEmail);
        sPassword=findViewById(R.id.sPassword);
        sConfirmPassword = findViewById(R.id.sConfirmPassword);
        sSignInButton=findViewById(R.id.sSignIn);
        sSignUpButton=findViewById(R.id.sSignUp);

        sSignInButton.setOnClickListener(this::slideDown);
        sSignUpButton.setOnClickListener(this::signUp);
    }

    private void signUp(View view) {
        if(checkValidation()) {
            Toast.makeText(getApplicationContext(),"Sign In Successful!",Toast.LENGTH_SHORT).show();
            //Intent to Dashboard
            slideDown(view);
        } else {
            Toast.makeText(getApplicationContext(),"Sign In Failed!",Toast.LENGTH_SHORT).show();
        }
    }

    private void slideUp(View view) {
        frameLayout.startAnimation(slideUpAnimation);
        loadSignUpView();
    }

    private void slideDown(View view) {
        frameLayout.startAnimation(slideDownAnimation);
        loadSignInView();
    }

    private boolean checkValidation() {
        boolean userNameCondition=checkUserNameLength(sUserName);
        boolean phoneNoCondition=checkPhoneNoLength(sPhoneNo);
        boolean emailCondition=checkEmail(sEmail);
        boolean passwordCondition=checkPassword(sPassword);
        boolean confirmPasswordCondition=checkConfirmPassword(sPassword,sConfirmPassword);
        if(!userNameCondition) {
            sUserName.setError("Minimum User Name Length is Three Characters");
        }
        if(!phoneNoCondition) {
            sPhoneNo.setError("Phone Number Length Must be Ten Digits");
        }
        if(!emailCondition) {
            sEmail.setError("Provided Email is Not a Valid Email Address");
        }
        if(!passwordCondition) {
            sPassword.setError("Provided Password is Not a Valid Password");
        }
        if(!confirmPasswordCondition) {
            sConfirmPassword.setError("Provided Passwords Do Not Match");
        }
        return (userNameCondition && phoneNoCondition && emailCondition && passwordCondition && confirmPasswordCondition);
    }

    private boolean checkUserNameLength(EditText text) {
        return (text.getText().toString().length()>=3);
    }
    private boolean checkPhoneNoLength(EditText text) {
        return (text.getText().toString().length()==10);
    }
    private boolean checkEmail(EditText text) {
        return (Patterns.EMAIL_ADDRESS.matcher(text.getText().toString()).matches());
    }
    private boolean checkPassword(EditText text) {
        return ((Pattern.compile("^(?=.*[A_Z])(?=.*[@#$%^&+=])(?=.*[0-9]).{8,12}$")).matcher(text.getText().toString()).matches());
    }
    private boolean checkConfirmPassword(EditText text1,EditText text2) {
        return (text1.getText().toString().equals(text2.getText().toString()));
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        if(flag) {
            builder.setTitle("Exit");
            builder.setMessage("Are you sure you want to exit the app?");
            builder.setPositiveButton("Yes", (dialogInterface, i) -> finishAffinity());
        } else {
            builder.setTitle("Go Back");
            builder.setMessage("Are you sure you want to go back?");
            builder.setPositiveButton("Yes", (dialogInterface, i) -> slideDown(new View(getApplicationContext())));
        }
        builder.setNegativeButton("No", (dialogInterface, i) -> dialogInterface.dismiss());
        builder.setCancelable(false);
        builder.show();
    }
}