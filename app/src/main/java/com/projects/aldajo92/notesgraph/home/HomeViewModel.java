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

import java.util.ArrayList;
import java.util.List;

class HomeViewModel extends ViewModel {

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

    private MutableLiveData<List<DataSetNoteModel>> liveDataGraphs = new MutableLiveData<>();

    private DatabaseReference databaseRef = database.getReference("/users/" + uid + "/data/graphs");

    public HomeViewModel() {
        createData();
    }

    private void createData() {

//        DatabaseReference databaseRef = database.getReference("/users/" + uid + "/data");
//        Map<String, List<DataSetNoteModel>> info = new HashMap<>();
//        info.put("graphs", list);
//        databaseRef.setValue(info);


//        users.put("gracehop", new UserModel("December 9, 1906", "Grace Hopper"));
//        DatabaseReference usersRef = databaseRef.child("users");

//        databaseRef.addChildEventListener()
        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                GenericTypeIndicator<List<DataSetNoteModel>> other = new GenericTypeIndicator<List<DataSetNoteModel>>() {};
                List<DataSetNoteModel> list = dataSnapshot.getValue(other);
                if(list != null){
                    liveDataGraphs.postValue(list);
                } else {
                    liveDataGraphs.postValue(new ArrayList<>());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void removeItem(int position){
        List<DataSetNoteModel> liveDataGraphsValue = liveDataGraphs.getValue();
        if(liveDataGraphsValue != null){
            liveDataGraphsValue.remove(position);
            databaseRef.setValue(liveDataGraphsValue);
        }
    }

    public void addItem(DataSetNoteModel data){
        String position = "0";
        List<DataSetNoteModel> liveDataGraphsValue = liveDataGraphs.getValue();
        if(liveDataGraphsValue != null){
            position = String.valueOf(liveDataGraphsValue.size());
        }
//        databaseRef.push().getKey();
        databaseRef.child(position).setValue(data);
    }

    public void editItem(DataSetNoteModel model, int position) {
        databaseRef.child(String.valueOf(position)).setValue(model);
    }

    private void createFavoriteData() {
        List<DataSetNoteModel> list1 = new ArrayList<>();
        list1.add(new DataSetNoteModel("Title Favorite", "description", new ArrayList<>()));
        list1.add(new DataSetNoteModel("Title Favorite", "description", new ArrayList<>()));
        list1.add(new DataSetNoteModel("Title Favorite", "description", new ArrayList<>()));
        list1.add(new DataSetNoteModel("Title Favorite", "description", new ArrayList<>()));
        list1.add(new DataSetNoteModel("Title Favorite", "description", new ArrayList<>()));
        list1.add(new DataSetNoteModel("Title Favorite", "description", new ArrayList<>()));
    }

    public static String TAG = "ADJ_TAG";

    public MutableLiveData<List<DataSetNoteModel>> getLiveDataGraphs() {
        return liveDataGraphs;
    }
}
