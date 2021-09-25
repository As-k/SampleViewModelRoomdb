package com.myapplication.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.myapplication.R;
import com.myapplication.databinding.ActivityAddNoteBinding;

public class AddNoteActivity extends AppCompatActivity {

    public static final String EXTRA_ID = "EXTRA_ID";
    public static final String EXTRA_TITLE = "EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION = "EXTRA_DESCRIPTION";
    public static final String EXTRA_PRIORITY = "EXTRA_PRIORITY";
    ActivityAddNoteBinding bindingAddNote;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindingAddNote = DataBindingUtil.setContentView(this, R.layout.activity_add_note);

        bindingAddNote.noPickerPriority.setMinValue(1);
        bindingAddNote.noPickerPriority.setMaxValue(10);
        getSupportActionBar().setHomeAsUpIndicator(getResources().getDrawable(R.drawable.ic_close));
        if (getIntent().hasExtra(EXTRA_ID)) {
            setTitle("Edit Note");
            bindingAddNote.titleEt.setText(getIntent().getStringExtra(EXTRA_TITLE));
            bindingAddNote.descriptionEt.setText(getIntent().getStringExtra(EXTRA_DESCRIPTION));
            bindingAddNote.noPickerPriority.setValue(getIntent().getIntExtra(EXTRA_PRIORITY, 1));
        } else {
            setTitle("Add Note");
        }
    }

    private void saveNote() {
        String title = bindingAddNote.titleEt.getText().toString().trim();
        String description = bindingAddNote.descriptionEt.getText().toString().trim();
        int priority = bindingAddNote.noPickerPriority.getValue();

        if (title.isEmpty() || description.isEmpty()) {
            Toast.makeText(this, "Please insert a title and description", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent();
        intent.putExtra(EXTRA_TITLE, title);
        intent.putExtra(EXTRA_DESCRIPTION, description);
        intent.putExtra(EXTRA_PRIORITY, priority);

        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1) {
            intent.putExtra(EXTRA_ID, id);
        }

        setResult(RESULT_OK, intent);
        finish();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_new_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.saveNote:
                saveNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
