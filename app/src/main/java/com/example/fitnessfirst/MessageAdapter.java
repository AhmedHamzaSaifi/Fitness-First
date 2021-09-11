package com.example.fitnessfirst;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class MessageAdapter extends RecyclerView.Adapter {
    Context context;
    ArrayList<Message> messages;
    final int item_sent=1;
    final int item_recieve=2;


    public MessageAdapter(Context context, ArrayList<Message> messages) {
        this.context = context;
        this.messages = messages;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType==item_sent){
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.send_message,parent,false);
            return new SentViewHolder(view);
        }else {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recieve_message,parent,false);
            return new RecieverViewHolder(view);
        }
    }

    @Override
    public int getItemViewType(int position) {
        Message message=messages.get(position);
        if (FirebaseAuth.getInstance().getUid().equals(message.getSenderId())){
            return item_sent;
        }else {
            return item_recieve;
        }
       // return super.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Message message=messages.get(position);
        if (holder.getClass()==SentViewHolder.class){
            SentViewHolder viewHolder=(SentViewHolder)holder;
            viewHolder.send.setText(message.getMessage());
        }else {
            RecieverViewHolder viewHolder=(RecieverViewHolder)holder;
            viewHolder.recieve.setText(message.getMessage());
        }
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public class SentViewHolder extends RecyclerView.ViewHolder{
        public TextView send;
        public SentViewHolder(@NonNull View itemView) {
            super(itemView);
            send=itemView.findViewById(R.id.textView_sendmessage);
        }
    }
    public class RecieverViewHolder extends RecyclerView.ViewHolder{
        public TextView recieve;
        public RecieverViewHolder(@NonNull View itemView) {
            super(itemView);
            recieve=itemView.findViewById(R.id.textView_recievemessage);
        }
    }
}
