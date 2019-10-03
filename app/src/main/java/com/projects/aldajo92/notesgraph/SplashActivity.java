package com.projects.aldajo92.notesgraph;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.projects.aldajo92.notesgraph.home.HomeActivity;
import com.projects.aldajo92.notesgraph.models.UserModel;

import static com.projects.aldajo92.notesgraph.utils.Constants.USER_EXTRA;

public class SplashActivity extends AppCompatActivity {

    public static int SPLASH_DURATION = 1000;
    private static final int RC_SIGN_IN = 0X17;

    private FirebaseAuth firebaseAuth;
    private GoogleSignInClient googleSignInClient;

    private SignInButton button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        button = findViewById(R.id.button_login);

        firebaseAuth = FirebaseAuth.getInstance();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(this, gso);

        button.setOnClickListener(v -> signIn());

    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        validateLogin(currentUser);
        updateUI(currentUser);
    }

    private void launchMainActivity(FirebaseUser account){
        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra(USER_EXTRA, new UserModel(account.getDisplayName(), account.getEmail()));
        startActivity(intent);
        finish();
    }

    private void signIn() {
        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
        button.setVisibility(View.GONE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                fireBaseAuthWithGoogle(account);
            } catch (ApiException e) {
                Snackbar.make(findViewById(R.id.layout_splash), getString(R.string.text_authentication_failed), Snackbar.LENGTH_SHORT).show();
                button.setVisibility(View.VISIBLE);
            }
        }
    }

    private void fireBaseAuthWithGoogle(GoogleSignInAccount signInAccount) {
        AuthCredential credential = GoogleAuthProvider.getCredential(signInAccount.getIdToken(), null);
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
                        validateLogin(currentUser);
                        updateUI(currentUser);
                    } else {
                        Snackbar.make(findViewById(R.id.layout_splash), getString(R.string.text_authentication_failed), Snackbar.LENGTH_SHORT).show();
                        button.setVisibility(View.VISIBLE);
                    }
                });
    }

    private void validateLogin(@Nullable FirebaseUser account){
        if (account != null) {
            new Handler().postDelayed(() -> launchMainActivity(account), SPLASH_DURATION);
        }
    }

    private void updateUI(@Nullable FirebaseUser account) {
        if (account != null) {
            button.setVisibility(View.GONE);
        } else {
            button.setVisibility(View.VISIBLE);
        }
    }
}
