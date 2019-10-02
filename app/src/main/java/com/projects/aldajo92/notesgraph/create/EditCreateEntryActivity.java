package com.projects.aldajo92.notesgraph.create;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.projects.aldajo92.notesgraph.R;
import com.projects.aldajo92.notesgraph.models.EntryNoteModel;
import com.projects.aldajo92.notesgraph.utils.CalendarUtils;

import java.util.Calendar;

import static com.projects.aldajo92.notesgraph.utils.CalendarUtils.DATE_LABEL_FORMAT;

public class EditCreateEntryActivity extends AppCompatActivity {
    public static int REQUEST_EDIT_ENTRY = 0x03;
    public static int REQUEST_CREATE_ENTRY = 0x7;

    public static String EXTRA_POSITION = "com.projects.aldajo92.extra_position";
    public static String EXTRA_ENTRY_MODEL = "com.projects.aldajo92.extra_entry_model";
    public static String EXTRA_REQUEST_CODE = "com.projects.aldajo92.extra_request_code";

    public static int RESULT_DELETE_ITEM = 0x66;

    private TextView textViewDate;
    private EditText editTextDescription;
    private EditText editTextValue;
    private ImageButton imageButtonDelete;

    private Button buttonCreate;

    private EntryNoteModel model;
    private boolean isEditMode = false;
    private int position = -1;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_entry);

        editTextValue = findViewById(R.id.editText_value);
        textViewDate = findViewById(R.id.textView_current_date);
        editTextDescription = findViewById(R.id.editText_description);
        imageButtonDelete = findViewById(R.id.imageButton_delete);

        buttonCreate = findViewById(R.id.button_create);
        buttonCreate.setOnClickListener(v -> validateInputData());

        imageButtonDelete.setOnClickListener(v -> deleteItem());

        setTitle(R.string.title_create_entry);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }

        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_REQUEST_CODE) && intent.hasExtra(EXTRA_ENTRY_MODEL)) {
            int requestCode = intent.getIntExtra(EXTRA_REQUEST_CODE, 0);
            model = intent.getParcelableExtra(EXTRA_ENTRY_MODEL);
            position = intent.getIntExtra(EXTRA_POSITION, -1);
            if (requestCode == REQUEST_EDIT_ENTRY) {
                textViewDate.setText(CalendarUtils.timestampToCalendarString(model.getTimestamp(), DATE_LABEL_FORMAT));
                editTextValue.setText(String.format("%s", model.getValue()));
                editTextDescription.setText(model.getDescription());

                buttonCreate.setText(R.string.text_update);
                imageButtonDelete.setVisibility(View.VISIBLE);

                isEditMode = true;
            }
        } else {
            long time = Calendar.getInstance().getTimeInMillis();
            textViewDate.setText(CalendarUtils.timestampToCalendarString(time, DATE_LABEL_FORMAT));
        }
    }

    private void deleteItem() {
        Intent intent = new Intent();
        intent.putExtra(EXTRA_ENTRY_MODEL, model);
        intent.putExtra(EXTRA_POSITION, position);
        setResult(RESULT_DELETE_ITEM, intent);
        finish();
    }

    private void validateInputData() {
        String textValue = editTextValue.getText().toString();
        if (!textValue.isEmpty()) {

            long date = Long.parseLong(textViewDate.getText().toString());
            float value = Float.parseFloat(textValue);
            String description = editTextDescription.getText().toString();

            if (isEditMode) {
                model.setTimestamp(date);
                model.setValue(value);
                model.setDescription(description);
            } else {
                model = new EntryNoteModel(date, value, description, null);
            }

            Intent intent = new Intent();
            intent.putExtra(EXTRA_ENTRY_MODEL, model);
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
