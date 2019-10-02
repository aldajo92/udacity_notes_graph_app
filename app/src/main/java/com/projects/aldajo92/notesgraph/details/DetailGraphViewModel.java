package com.projects.aldajo92.notesgraph.details;

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
import com.projects.aldajo92.notesgraph.models.EntryNoteModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

class DetailGraphViewModel extends ViewModel {

    private FirebaseDatabase database = FirebaseDatabase.getInstance();

    private String uid;

    private DataSetNoteModel model;

    private DatabaseReference databaseRef;

    private MutableLiveData<List<EntryNoteModel>> liveDataListEntries = new MutableLiveData<>();

    public DetailGraphViewModel(DataSetNoteModel model) {
        this.model = model;
        uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        databaseRef = database.getReference("/users/" + uid + "/data/graphs" + "/" + model.getID() + "/entryNoteModelList");

        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                GenericTypeIndicator<List<EntryNoteModel>> other = new GenericTypeIndicator<List<EntryNoteModel>>() {
                };
                List<EntryNoteModel> list = dataSnapshot.getValue(other);
                if (list != null) {
                    liveDataListEntries.postValue(list);
                } else {
                    liveDataListEntries.postValue(new ArrayList<>());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void addItem(EntryNoteModel model) {
        model.setTimestamp(Calendar.getInstance(Locale.getDefault()).getTimeInMillis());
        List<EntryNoteModel> list = liveDataListEntries.getValue();
        if(list == null){
            list = new ArrayList<>();
        }
        list.add(model);
        databaseRef.setValue(list);
    }

    public void updateItem(int position, EntryNoteModel model) {
        databaseRef.child(String.valueOf(position)).setValue(model);
    }

    public void deleteItem(int position, EntryNoteModel model) {
        List<EntryNoteModel> list = liveDataListEntries.getValue();
        if(list != null){
            list.remove(position);
            databaseRef.setValue(list);
        }
    }

    public void favoriteSelected(boolean selected) {
        databaseRef.getParent().child("isFavorite").setValue(selected);
    }

    public DataSetNoteModel getModel() {
        return model;
    }

    public MutableLiveData<List<EntryNoteModel>> getLiveDataListEntries() {
        return liveDataListEntries;
    }

    public void setLiveDataListEntries(MutableLiveData<List<EntryNoteModel>> liveDataListEntries) {
        this.liveDataListEntries = liveDataListEntries;
    }

    private String TAG = "TAG_ADJ";

}
