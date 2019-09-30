package com.projects.aldajo92.notesgraph.home.settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.projects.aldajo92.notesgraph.R;
import com.projects.aldajo92.notesgraph.models.UserModel;

public class SettingsFragment extends Fragment {

    private UserModel userModel;

    private Button buttonSignOut;
    private TextView textViewName;
    private TextView textViewEmail;


    private SettingsListener settingsListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        buttonSignOut = view.findViewById(R.id.button_sign_out);
        textViewName = view.findViewById(R.id.textView_user_name);
        textViewEmail = view.findViewById(R.id.textView_place_email);

        buttonSignOut.setOnClickListener(v -> settingsListener.onSingOut());
        textViewName.setText(userModel.getName());
        textViewEmail.setText(userModel.getEmail());
    }

    public static SettingsFragment createInstance(UserModel userModel, SettingsListener listener) {
        SettingsFragment settingsFragment = new SettingsFragment();
        settingsFragment.settingsListener = listener;
        settingsFragment.userModel = userModel;
        return settingsFragment;
    }

}
