package com.projects.aldajo92.notesgraph.home.settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.projects.aldajo92.notesgraph.R;

public class SettingsFragment extends Fragment {

    private Button buttonSignOut;
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
        buttonSignOut.setOnClickListener(v -> settingsListener.onSingOut());
    }

    public static SettingsFragment createInstance(SettingsListener listener) {
        SettingsFragment settingsFragment = new SettingsFragment();
        settingsFragment.settingsListener = listener;
        return settingsFragment;
    }

}
