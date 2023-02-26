package com.example.parkingdost;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import java.util.*;

import androidx.appcompat.app.AppCompatActivity;

import com.example.parkingdost.databinding.ActivityLetsgoBinding;
//import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.auth.User;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.Arrays;
import java.util.regex.Pattern;

// ...



public class reglin extends AppCompatActivity {
    private static final String TAG = "abcd";
    ActivityLetsgoBinding binding;
  FirebaseAuth auth;
  FirebaseDatabase db;
  DatabaseReference ref;
  ProgressDialog pd;
    private static final int RC_SIGN_IN = 9001;
    private GoogleSignInClient mGoogleSignInClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityLetsgoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //setContentView(binding.getRoot());
        auth=FirebaseAuth.getInstance();
        pd=new ProgressDialog(this);
        pd.setTitle("Signing up");
        pd.setMessage("we are signing you up");
        db=FirebaseDatabase.getInstance();
        ref=db.getReference();
        binding.textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(reglin.this,signinactivity.class);
                startActivity(intent);
            }
        });
        binding.sigup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            pd.show();

                String email = binding.editTextTextEmailAddress2.getText().toString().trim();

                auth.createUserWithEmailAndPassword(email.trim(), binding.editTextTextPassword.getText().toString().trim()).addOnCompleteListener(task -> {

                    if (task.isSuccessful()) {
                        pd.dismiss();
                        String b = binding.editTextTextEmailAddress2.getText().toString().trim();
                        String a = binding.editTextTextPersonName2.getText().toString().trim();
                        String c = binding.editTextPhone.getText().toString().trim();
                        String d = binding.editTextTextPassword.getText().toString().trim();

                        String id = Objects.requireNonNull(task.getResult().getUser()).getUid();

//                    String id= Objects.requireNonNull(task.getResult().getUser()).getUid();
                        Users user = new Users(a, b, c, d);
                        ref.child("USERS").child(id).setValue(user);
                        Toast.makeText(reglin.this, "signup successfull", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(reglin.this, MainActivity.class);
                        startActivity(intent);
                    }
                    else{
                        pd.dismiss();
                        Toast.makeText(reglin.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
                });



//            Intent intent=new Intent(reglin.this,signup.class);
//            startActivity(intent);
            }
        });


        // ...

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        // ...


        // ...

      binding.google1.setOnClickListener(view -> {
          pd.show();
          Intent signInIntent = mGoogleSignInClient.getSignInIntent();
          startActivityForResult(signInIntent, RC_SIGN_IN);
          pd.dismiss();

      });

        // fb auth
        if(auth.getCurrentUser()!=null)
        {
            Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                auth(account.getIdToken());
                secndactivity();
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);
                // ...
            }
        }
    }
    public void secndactivity()
    {
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    private void auth(String idToken) {
    }

    private void firebaseAuthWithGoogle(String idToken) {
        pd=new ProgressDialog(this);
        pd.setTitle("Regristration time");
        pd.setMessage("lets do regristration");
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        FirebaseAuth.getInstance().signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            if(user!=null)
                            {
                            Intent intent=new Intent(reglin.this,MainActivity.class);
                            startActivity(intent);}
                            // updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            // updateUI(null);
                        }

                    }
                });

    }




}