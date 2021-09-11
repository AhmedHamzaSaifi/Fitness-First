package com.example.fitnessfirst;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class chat extends AppCompatActivity {
    RecyclerView recyclerView;
   MessageAdapter adapter;
    ArrayList<Message> messages;
    String senderroom,recieverroom;
    EditText text;
    Button send;
    FirebaseDatabase database;
    DatabaseReference reference;
    String seckey="",bytefour="";
    KeyGenerator keyGenerator;
    SecretKey secretKey = null;
    byte[] cipherText ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        try {
            keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(128);
            secretKey = keyGenerator.generateKey();
            // seckey=encoderfun(secretKey.toString().getBytes());
            Log.d("abc", "onClick: "+secretKey);
        } catch (Exception e) {
            e.printStackTrace();
        }



        recyclerView=(RecyclerView)findViewById(R.id.message_list);
        database=FirebaseDatabase.getInstance();
        reference=database.getReference();

        messages=new ArrayList<>();
        adapter=new MessageAdapter(this,messages);
        recyclerView.setAdapter(adapter);

        String senderuid= FirebaseAuth.getInstance().getUid();
        String name=getIntent().getStringExtra("name");
        String recieveruid=getIntent().getStringExtra("uid");
        senderroom=senderuid+recieveruid;
        recieverroom=recieveruid+senderuid;

        reference.child("decrypt").child(senderroom).child("msg").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                messages.clear();
               for (DataSnapshot dataSnapshot:snapshot.getChildren()){

                   Message message=dataSnapshot.getValue(Message.class);

                  // String msg=message.getMessage();
                   // Log.d("abc", "onDataChange: "+msg);

                  // Log.d("abc", "onDataChange: "+encText.toString());

                  // Log.d("abc", "onDataChange: "+iv.toString());
                  // String seck=message.getSecret_key();

                   //byte[] encodedSecretKey = decoderfun(seck);
                   //Log.d("abc", "onDataChange: "+encodedSecretKey.toString());

                   //SecretKey originalSecretKey = new SecretKeySpec(encodedSecretKey, "AES");
                  // Log.d("abc", "onDataChange: "+encoderfun(originalSecretKey.toString().getBytes()));

                  // Log.d("abc", "onDataChange: "+decryptedText);
                   Log.d("abc", "on done "+seckey);
                  // message.setMessage(decryptedText);




                  messages.add(message);
               }
               adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




        text=(EditText)findViewById(R.id.editText_messagetype);
        send=(Button)findViewById(R.id.button_send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String messagetxt=text.getText().toString();

                seckey=encoderfun(secretKey.toString().getBytes());
                Log.d("abc", "onClick:sec "+seckey);
                byte[] IV = new byte[16];
                SecureRandom random;
                random = new SecureRandom();
                random.nextBytes(IV);

                try {
                    cipherText = Encrypt.encrypt(messagetxt.getBytes(), secretKey, IV);



                } catch (Exception e) {
                    e.printStackTrace();
                }String encryptedmessage=encoderfun(cipherText);
                bytefour=encoderfun(IV);

                Log.d("jija", "onClick: byte"+bytefour);


                Date date=new Date();
                date.getTime();
               // Log.d("hakka", "onClick: "+date.getTime());
                Message message=new Message(encryptedmessage,senderuid,bytefour,date.getTime());
                Message message1=new Message(messagetxt,senderuid,bytefour,date.getTime());
                text.setText("");
                String randomkey=database.getReference().push().getKey();
                HashMap<String,Object> lastmsgObj=new HashMap<>();
                lastmsgObj.put("Last Message",message1.getMessage());
                lastmsgObj.put("Last Message Time",date.getTime());
                reference.child("decrypt").child(senderroom).updateChildren(lastmsgObj);
                reference.child("decrypt").child(recieverroom).updateChildren(lastmsgObj);

                reference.child("chats").child(senderroom).child("messages").child(randomkey).setValue(message).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        reference.child("chats").child(recieverroom).child("messages").child(randomkey).setValue(message).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                //xx
                              /*  Message message1=new Message(message.getMessage(),message.getByte_four());
                                reference.child("last message").child("a").setValue(message1).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        reference.child("last message").addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                               // String dataSnapshot=snapshot.child("a").getValue(message1.getMessage());

                                                String bytfour=snapshot.child("a").child("byte_four").getValue().toString();
                                                String lmsg=snapshot.child("a").child("message").getValue().toString();
                                                byte[] encText = decoderfun(lmsg);
                                                byte[] iv = decoderfun(bytfour);
                                                String decryptedText = Decrypt.decrypt(encText,secretKey,iv);
                                                Log.d("llaa", "onDataChange: "+decryptedText);
                                                reference.child("decrypted msg").child(senderroom).child(database.getReference().push().getKey()).setValue(decryptedText).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void aVoid) {
                                                        reference.child("decrypted msg").child(recieverroom).child(database.getReference().push().getKey()).setValue(decryptedText).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                            @Override
                                                            public void onSuccess(Void aVoid) {

                                                            }
                                                        });
                                                    }
                                                });
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {

                                            }
                                        });
                                    }
                                });*/

                                //yy


                                //yahan se

                                //
                            }
                        });

                    }
                });

                reference.child("decrypt").child(senderroom).child("msg").child(randomkey).setValue(message1).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        reference.child("decrypt").child(recieverroom).child("msg").child(randomkey).setValue(message1). addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {

                            }
                        });
                    }
                });



                /* reference.child("lastmessage").child("a").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        //messages.clear();
                            String msg=snapshot.child("message").getValue().toString();
                            Message message=snapshot.getValue(Message.class);
                            Log.d("GG", "onDataChange: "+message.getMessage());
                            //String msg=message.getMessage();
                           // Log.d("abc", "onDataChange: "+msg);
                            byte[] encText = decoderfun(msg);
                            Log.d("abc", "onDataChange: "+encText.toString());
                            byte[] iv = decoderfun(bytefour);
                            Log.d("abc", "onDataChange: "+iv.toString());
                            byte[] encodedSecretKey = decoderfun(seckey);
                            Log.d("abc", "onDataChange: "+encodedSecretKey.toString());

                            SecretKey originalSecretKey = new SecretKeySpec(encodedSecretKey, 0, encodedSecretKey.length, "AES");
                            Log.d("abc", "onDataChange: "+encoderfun(originalSecretKey.toString().getBytes()));
                            String decryptedText = Decrypt.decrypt(encText,secretKey,iv);
                            Log.d("abc", "onDataChange: "+decryptedText);
                            Log.d("abc", "on done "+seckey);
                            message.setMessage(decryptedText);
                            messages.add(message);

                            Log.d("abc", "onDataChange: yy"+messages.size());

                        //adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });*/
            }
        });

      //  String abc=seckey;
      //  Log.d("jija", "onCreate: seck"+abc);

//       seckey=encoderfun(secretKey.toString().getBytes());
      //  Log.d("jijo", "onClick: "+seckey);



        getSupportActionBar().setTitle(name);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
    public static String encoderfun(byte[] decval) {
        String conVal= Base64.encodeToString(decval,Base64.DEFAULT);
        return conVal;
    }
    public static byte[] decoderfun(String enval) {
        byte[] conVal = Base64.decode(enval,0);
        return conVal;

    }
}