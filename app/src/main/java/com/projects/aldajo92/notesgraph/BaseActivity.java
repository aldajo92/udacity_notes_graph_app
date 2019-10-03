package com.projects.aldajo92.notesgraph;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.material.snackbar.Snackbar;
import com.projects.aldajo92.notesgraph.network.CheckConnectionListener;
import com.projects.aldajo92.notesgraph.network.CheckConnectionTask;

@SuppressLint("Registered")
public class BaseActivity extends AppCompatActivity implements CheckConnectionListener {

    private CheckConnectionTask connectionTask;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new CheckConnectionTask(this).execute();
    }

    @Override
    protected void onResume() {
        super.onResume();
        new CheckConnectionTask(this).execute();
    }

    public void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void connectionEvent(Boolean isConnected) {
        showErrorMessage(getString(R.string.text_error_network));
        if (!isConnected) {
            showErrorMessage(getString(R.string.text_error_network));
        }
    }

    public void showErrorMessage(String message){
        Snackbar snackbar = Snackbar.make(findViewById(R.id.content), message, Snackbar.LENGTH_LONG);
        View snackBarView = snackbar.getView();
        snackBarView.setBackgroundColor(ContextCompat.getColor(this, R.color.red_pastel));
        snackbar.show();
    }
}
