package com.example.fitnessfirst;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.concurrent.TimeUnit;

//import com.example.fitnessfirst.databinding.ActivityVerifyBinding;

public class verify extends AppCompatActivity {
//ActivityVerifyBinding binding;
    FirebaseAuth auth;
    EditText phonenumber;
    Button register;
    //String verificationID;



//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (data !=null){
//            if (data.getData() !=null){
//                profileimage.setImageURI(data.getData());
//                selectedimage=data.getData();
//            }
//        }
//    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //binding=ActivityVerifyBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_verify);
       auth=FirebaseAuth.getInstance();
        if (auth.getCurrentUser()!=null){
            startActivity(new Intent(verify.this,MainActivity.class));
            finish();
        }

        phonenumber=findViewById(R.id.editText_PhoneNumber);
        register=findViewById(R.id.button_Continue);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhoneAuthProvider.getInstance().verifyPhoneNumber(phonenumber.getText().toString().trim(),60,TimeUnit.SECONDS,
                        verify.this,new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {

                            }

                            @Override
                            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                super.onCodeSent(s, forceResendingToken);
                                Intent intent=new Intent(verify.this,OTP.class);
                                intent.putExtra("VERIFICATION ID",s);
                                intent.putExtra("PhoneNumber",phonenumber.getText().toString());
                                startActivity(intent);
                            }
                        });



            }
        });
    }

}