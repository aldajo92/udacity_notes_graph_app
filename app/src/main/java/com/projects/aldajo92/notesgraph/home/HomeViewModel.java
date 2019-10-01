package com.projects.aldajo92.notesgraph.home;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.projects.aldajo92.notesgraph.models.DataSetNoteModel;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

class HomeViewModel extends ViewModel {

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private String uid;

    private MutableLiveData<HashMap<String, DataSetNoteModel>> liveDataGraphs = new MutableLiveData<>();

    private DatabaseReference databaseRef;

    public HomeViewModel() {
        createData();
    }

    private void createData() {
        uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
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

    public void removeItem(DataSetNoteModel model) {
        databaseRef.child(model.getID()).removeValue();
    }

    public void addItem(DataSetNoteModel model) {
        String key = databaseRef.push().getKey();
        if (key != null) {
            model.setID(key);
            model.setDate(Calendar.getInstance(Locale.getDefault()).getTimeInMillis());
            databaseRef.child(key).setValue(model);
        }
    }

    public void editItem(DataSetNoteModel model, int position) {
        databaseRef.child(String.valueOf(model.getID())).setValue(model);
    }

    public static String TAG = "ADJ_TAG";

    public MutableLiveData<HashMap<String, DataSetNoteModel>> getLiveDataGraphs() {
        return liveDataGraphs;
    }
}
