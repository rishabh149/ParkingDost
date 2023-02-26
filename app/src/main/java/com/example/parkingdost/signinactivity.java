package com.example.parkingdost;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.parkingdost.databinding.ActivitySigninactivityBinding;
import com.example.parkingdost.databinding.ActivitySignupBinding;
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

public class signinactivity extends AppCompatActivity {
    ActivitySigninactivityBinding binding;
    FirebaseDatabase db;
    DatabaseReference ref;
    private static final int RC_SIGN_IN = 123;
    FirebaseAuth auth;
    private GoogleSignInClient mGoogleSignInClient;
    private int requestCode;
    private int resultCode;
    private Intent data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySigninactivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        auth= FirebaseAuth.getInstance();
        db= FirebaseDatabase.getInstance();
        ref=db.getReference();
        String mail=binding.etEmail.getText().toString();
        String p=binding.etPassword.getText().toString();
        binding.singh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signInWithEmailAndPassword(mail,p).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                   if(task.isSuccessful())
                   {

                       Intent intent=new Intent(signinactivity.this,MainActivity.class);
                       startActivity(intent);
                   }
                   else
                   {
                       Toast.makeText(signinactivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                   }
                    }
                });
            }
        });
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        // Create a GoogleSignInClient object
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        if(auth.getCurrentUser()!=null)
        {
            Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);
        }
            binding.google.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {





                            signInWithGoogle();


            }

                private void signInWithGoogle() {
                    Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                    startActivityForResult(signInIntent, RC_SIGN_IN);
                }
                });
    }


// Handle the result of the Google sign-in intent
        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            this.requestCode = requestCode;
            this.resultCode = resultCode;
            this.data = data;
            super.onActivityResult(requestCode, resultCode, data);

            if (requestCode == RC_SIGN_IN) {
                Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                // Sign in successful, authenticate with Firebase
                GoogleSignInAccount account = null;
                try {
                    account = task.getResult(ApiException.class);
                    secndactivity();
                } catch (ApiException e) {
                    e.printStackTrace();
                }
                firebaseAuthWithGoogle(account.getIdToken());
            }
        }
    public void secndactivity()
    {
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }

        private void firebaseAuthWithGoogle(String idToken) {
                AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
                FirebaseAuth.getInstance().signInWithCredential(credential)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            private Object TAG;

                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                    // ...
                                    Intent intent = new Intent(signinactivity.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    // Sign in failed
                                    final int w = Log.w((String) TAG, "signInWithCredential:failure", task.getException());
                                    // ...
                                }
                            }
                        });
            }
}