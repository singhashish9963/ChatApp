package com.example.chatapp.Repository;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.chatapp.model.ChatGroup;
import com.example.chatapp.model.ChatMessage;
import com.example.chatapp.views.GroupsActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Repository {

    MutableLiveData<List<ChatGroup>> chatGroupMutableLiveData;
    FirebaseDatabase database;
    DatabaseReference reference;
    DatabaseReference groupreference;
    MutableLiveData<List<ChatMessage>> messagesLiveData;
    private MutableLiveData<Boolean> firebaseAuthLiveData = new MutableLiveData<>();

    public Repository() {
        this.chatGroupMutableLiveData = new MutableLiveData<>();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference();
        messagesLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<Boolean> firebaseAnonymousAuth(Context context) {
        FirebaseAuth.getInstance().signInAnonymously().addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Log.d("Repository", "Firebase Anonymous Auth Successful");
                    firebaseAuthLiveData.postValue(true);
                } else {
                    Log.e("Repository", "Firebase Anonymous Auth Failed", task.getException());
                    firebaseAuthLiveData.postValue(false);
                }
            }
        });
        return firebaseAuthLiveData;
    }

    public String getCurrentUserid() {
        return FirebaseAuth.getInstance().getUid();
    }

    public void signOUT() {
        FirebaseAuth.getInstance().signOut();
    }

    public MutableLiveData<List<ChatGroup>> getChatGroupMutableLiveData() {
        List<ChatGroup> groupList = new ArrayList<>();

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                groupList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    ChatGroup group = new ChatGroup(dataSnapshot.getKey());
                    groupList.add(group);
                }
                chatGroupMutableLiveData.postValue(groupList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });


        return chatGroupMutableLiveData;
    }

    public void createNewChatGroup(String groupName) {
        reference.child(groupName).setValue(groupName);

    }

    public MutableLiveData<List<ChatMessage>> getMessagesLiveData(String groupName) {
        groupreference = database.getReference().child(groupName);

        List<ChatMessage> messageList = new ArrayList<>();
        groupreference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                messageList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    ChatMessage message = dataSnapshot.getValue(ChatMessage.class);
                    messageList.add(message);
                }
                messagesLiveData.postValue(messageList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        return messagesLiveData;
    }

    public void sendMessage(String messageText,String chatGroup) {
        DatabaseReference ref = database.getReference(chatGroup);
        if (!messageText.trim().equals("")) {
            ChatMessage msg = new ChatMessage(
                    FirebaseAuth.getInstance().getCurrentUser().getUid(), messageText, System.currentTimeMillis()

            );
            String randomKey = ref.push().getKey();
            ref.child(randomKey).setValue(msg);

        }

    }

}
