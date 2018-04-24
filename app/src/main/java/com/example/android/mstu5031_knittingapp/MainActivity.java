package com.example.android.mstu5031_knittingapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth msAuth;
    private FirebaseAuth.AuthStateListener authListener;

    @Override
    protected void onStart() {
        super.onStart();
        msAuth.addAuthStateListener(authListener);
    }

    @Override

    public void onStop() {
        super.onStop();
        msAuth.removeAuthStateListener(authListener);
    }

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference userReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        msAuth = FirebaseAuth.getInstance();
        msAuth = FirebaseAuth.getInstance();

        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null){
                    userReference = database.getReference(user.getUid());
                } else {
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                }
            }
        };
        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null){
                    userReference = database.getReference(user.getUid());
                } else {
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                }
            }
        };





    }


    public void viewMatchMade(View view) {
        Intent intent = new Intent(this, CreateActivity.class);
        startActivity(intent);
    }

    public void viewPairInfo (View view) {
        Intent intent = new Intent(this, ViewPairActivity.class);
        startActivity(intent);
    }

    public void viewCreate(View view) {
        Intent intent = new Intent(this, CreateActivity.class);
        startActivity(intent);
    }

    public void fakeLogin(View view) {
        Intent intent = new Intent(this, CreateActivity.class);
        startActivity(intent);
    }

    public void viewLibrary(View view) {
        Intent intent = new Intent(this, LibraryViewActivity.class);
        startActivity(intent);
    }
}
