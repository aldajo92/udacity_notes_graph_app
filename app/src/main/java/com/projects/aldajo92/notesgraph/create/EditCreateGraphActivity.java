package com.projects.aldajo92.notesgraph.create;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.projects.aldajo92.notesgraph.R;
import com.projects.aldajo92.notesgraph.models.DataSetNoteModel;

public class EditCreateGraphActivity extends AppCompatActivity {
    public static int REQUEST_EDIT_GRAPH = 0x05;
    public static int REQUEST_CREATE_GRAPH = 0x17;

    public static String EXTRA_POSITION = "com.projects.aldajo92.extra_position";
    public static String EXTRA_NOTE_MODEL = "com.projects.aldajo92.extra_model_result";
    public static String EXTRA_REQUEST_CODE = "com.projects.aldajo92.extra_request_code";

    private EditText editTextTitle;
    private EditText editTextDescription;
    private EditText editTextUnits;

    private Button buttonCreate;

    private DataSetNoteModel model;
    private boolean isEditMode = false;
    private int position = -1;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_graph);

        editTextTitle = findViewById(R.id.editText_title);
        editTextDescription = findViewById(R.id.editText_description);
        editTextUnits = findViewById(R.id.editText_units);

        buttonCreate = findViewById(R.id.button_create);
        buttonCreate.setOnClickListener(v -> validateInputData());

        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }

        Intent intent = getIntent();
        if(intent.hasExtra(EXTRA_REQUEST_CODE) && intent.hasExtra(EXTRA_NOTE_MODEL)){
            int requestCode = intent.getIntExtra(EXTRA_REQUEST_CODE, 0);
            model = intent.getParcelableExtra(EXTRA_NOTE_MODEL);
            position = intent.getIntExtra(EXTRA_POSITION, -1);
            if(requestCode == REQUEST_EDIT_GRAPH){
                editTextTitle.setText(model.getTitle());
                editTextDescription.setText(model.getDescription());
                editTextUnits.setText(model.getUnits());

                buttonCreate.setText(R.string.text_update);

                isEditMode = true;
            }
            setTitle(R.string.title_edit_graph_title);
        } else {
            setTitle(R.string.title_create_graph_title);
        }
    }

    private void validateInputData() {
        String title = editTextTitle.getText().toString();
        String description = editTextDescription.getText().toString();
        String units = editTextUnits.getText().toString();

        if(isEditMode){
            model.setTitle(title);
            model.setDescription(description);
            model.setUnits(units);
        } else {
            model = new DataSetNoteModel(title, description, units, null);
        }

        if (!title.isEmpty()) {
            Intent intent = new Intent();
            intent.putExtra(EXTRA_NOTE_MODEL, model);
            intent.putExtra(EXTRA_POSITION, position);
            setResult(RESULT_OK, intent);
            finish();
        } else {
            Toast.makeText(this, R.string.check_input_data, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
