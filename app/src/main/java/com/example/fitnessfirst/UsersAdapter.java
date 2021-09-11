package com.example.fitnessfirst;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ViewHolder> {
   private Context context;
    private  ArrayList<Users> appusers;
    public TextView profileimage;

    public UsersAdapter(Context context, ArrayList<Users> appusers) {
        this.context = context;
        this.appusers = appusers;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.row_conversations,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Users users=appusers.get(position);
        String senderid= FirebaseAuth.getInstance().getUid();
        String senderroom=senderid+users.getUid();
        FirebaseDatabase.getInstance().getReference().child("decrypt").child(senderroom).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists())
                {                String lastMsg=snapshot.child("Last Message").getValue(String.class);
                long time=snapshot.child("Last Message Time").getValue(Long.class);
                    Date date=new Date(time);
                    SimpleDateFormat dateFormat=new SimpleDateFormat("dd/MM/yy  h:mm a");

                String exact_time= dateFormat.format(date);
                    Log.d("jabbah", "onDataChange: "+exact_time);
                holder.lastmessage.setText(lastMsg);
                holder.time.setText(exact_time);
            }else {
                    holder.lastmessage.setText("Tap to Chat");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        holder.profilename.setText(users.getName());
        Glide.with(context).load(users.getProfileimage()).placeholder(R.drawable.avatar).into(holder.profileimage);
      //  holder.profileimage.setText(String.valueOf(users.getProfileimage()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,chat.class);
                intent.putExtra("name",users.getName());
                intent.putExtra("uid",users.getUid());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return appusers.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView profileimage;
         TextView profilename;
         TextView lastmessage,time;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            profileimage=itemView.findViewById(R.id.imageView_profilepic);
            profilename=itemView.findViewById(R.id.textView_username);
            lastmessage=itemView.findViewById(R.id.textView_lastmessage);
            time=itemView.findViewById(R.id.textView_time);

        }


    }
}
