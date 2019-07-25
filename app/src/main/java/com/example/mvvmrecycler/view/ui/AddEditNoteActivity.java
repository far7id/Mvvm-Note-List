package com.example.mvvmrecycler.view.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.example.mvvmrecycler.R;

public class AddEditNoteActivity extends AppCompatActivity
{
    public static final String EXTRA_ID = "com.example.mvvmrecycler.EXTRA_ID";
    public static final String EXTRA_TITLE = "com.example.mvvmrecycler.EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION = "com.example.mvvmrecycler.EXTRA_DESCRIPTION";
    public static final String EXTRA_PRIORITY = "com.example.mvvmrecycler.EXTRA_PRIORITY";

    private EditText edittxt_title;
    private EditText edittxt_description;
    private NumberPicker number_picker_priority;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        edittxt_title = findViewById(R.id.edittxt_title_xml);
        edittxt_description = findViewById(R.id.edittxt_description_xml);
        number_picker_priority = findViewById(R.id.number_picker_priority_xml);

        number_picker_priority.setMinValue(1);
        number_picker_priority.setMaxValue(10);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);

        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID))
        {
            setTitle("Edit Note");
            edittxt_title.setText(intent.getStringExtra(EXTRA_TITLE));
            edittxt_description.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
            number_picker_priority.setValue(intent.getIntExtra(EXTRA_PRIORITY, 1));
        }

        else
        {
            setTitle("Add Note");
        }
    }

    private void saveNote()
    {
        String title = edittxt_title.getText().toString();
        String description = edittxt_description.getText().toString();
        int priority = number_picker_priority.getValue();

        if (title.trim().isEmpty() || description.trim().isEmpty())
        {
            Toast.makeText(this, "please insert title and description", Toast.LENGTH_LONG).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE, title);
        data.putExtra(EXTRA_DESCRIPTION, description);
        data.putExtra(EXTRA_PRIORITY, priority);

        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1)
        {
            data.putExtra(EXTRA_ID, id);
        }

        setResult(RESULT_OK, data);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.save_note:
                saveNote();
                return true;
                default:
                    return super.onOptionsItemSelected(item);
        }
    }
}
