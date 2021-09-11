package com.example.fitnessfirst;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

//import com.example.fitnessfirst.databinding.ActivityOTPBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class OTP extends AppCompatActivity {
//ActivityOTPBinding binding;
FirebaseAuth  auth;
EditText inputcode;
Button smsverify;
String verificationId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // binding=ActivityOTPBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_o_t_p);
        auth=FirebaseAuth.getInstance();
        inputcode=(EditText)findViewById(R.id.editText_otp);

        String VerificationId=getIntent().getStringExtra("VERIFICATION ID");
        String Phonenumber=getIntent().getStringExtra("PhoneNumber");
//        PhoneAuthOptions options= PhoneAuthOptions.newBuilder(auth).setPhoneNumber(phonenumber).setTimeout(60L, TimeUnit.SECONDS)
//                .setActivity(OTP.this).setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
//                    @Override
//                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
//
//                    }
//
//                    @Override
//                    public void onVerificationFailed(@NonNull FirebaseException e) {
//
//                    }
//
//                    @Override
//                    public void onCodeSent(@NonNull String verifyId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
//                        super.onCodeSent(verifyId, forceResendingToken);
//                         verificationId=verifyId;
//
//                    }
//                })
//                .build();
//        PhoneAuthProvider.verifyPhoneNumber(options);
        smsverify=(Button)findViewById(R.id.button_verifyotp);
        smsverify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                 PhoneAuthCredential phoneAuthCredential=PhoneAuthProvider.getCredential(VerificationId,inputcode.getText().toString().trim());
                 FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                     @Override
                     public void onComplete(@NonNull Task<AuthResult> task) {
//                    if (task.isSuccessful()){
                        Intent intent=new Intent(OTP.this,profile.class);
                        intent.putExtra("PhoneNumber",Phonenumber);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                     //}
                 });
                }

                    });



    }



}