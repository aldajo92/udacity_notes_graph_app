package com.projects.aldajo92.notesgraph.widget.service;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.projects.aldajo92.notesgraph.models.DataSetNoteModel;

import java.util.HashMap;

class GraphWidgetViewModel extends AndroidViewModel {

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference databaseRef;
    private String uid;

    private MutableLiveData<HashMap<String, DataSetNoteModel>> liveDataGraphs = new MutableLiveData<>();

    public GraphWidgetViewModel(Application application) {
        super(application);

        uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        configFBListener();
    }

    public void configFBListener() {
        databaseRef = database.getReference("/users/" + uid + "/data/graphs");
        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                GenericTypeIndicator<HashMap<String, DataSetNoteModel>> other = new GenericTypeIndicator<HashMap<String, DataSetNoteModel>>() {
                };
                HashMap<String, DataSetNoteModel> map = dataSnapshot.getValue(other);
                if (map != null) {
                    liveDataGraphs.postValue(map);
                } else {
                    liveDataGraphs.postValue(new HashMap<>());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public MutableLiveData<HashMap<String, DataSetNoteModel>> getLiveDataGraphs() {
        return liveDataGraphs;
    }

//    public RecipeDatabase getDatabase() {
//        return database;
//    }
}