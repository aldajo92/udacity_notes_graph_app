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

public class CreateGraphActivity extends AppCompatActivity {
    public static int REQUEST_CREATE_GRAPH = 0x17;
    public static String EXTRA_MODEL_RESULT = "com.projects.aldajo92.extra_model_result";

    private EditText editTextTitle;
    private EditText editTextDescription;
    private EditText editTextUnits;

    private Button buttonCreate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_graph);

        editTextTitle = findViewById(R.id.editText_title);
        editTextDescription = findViewById(R.id.editText_description);
        editTextUnits = findViewById(R.id.editText_units);

        buttonCreate = findViewById(R.id.button_create);
        buttonCreate.setOnClickListener(v -> validateInputData());

        this.setTitle(R.string.title_create_graph);

        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void validateInputData() {
        String title = editTextTitle.getText().toString();
        String description = editTextDescription.getText().toString();
        String units = editTextUnits.getText().toString();

        DataSetNoteModel model = new DataSetNoteModel(title, description, units, null);

        if (!title.isEmpty()) {
            Intent intent = new Intent();
            intent.putExtra(EXTRA_MODEL_RESULT, model);
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
