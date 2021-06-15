package com.example.homework4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    private EditText etTitle, etDescription;
    private Button  btn_open_add_task;
    public int pos;
    TaskModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        etTitle = findViewById(R.id.et_title);
        etDescription =findViewById(R.id.et_description);
        btn_open_add_task = findViewById(R.id.btn_add);
        Intent intent = getIntent();

        String Title = intent.getStringExtra("title");
        String Description = intent.getStringExtra("description");
        intent.putExtra("pos", 0);
        etTitle.setText(Title);
        etDescription.setText(Description);
        initAddTaskButton();
    }

    public void initAddTaskButton() {
        btn_open_add_task.setOnClickListener(v -> {
           String title = etTitle.getText().toString();
           String description = etDescription.getText().toString();
               Intent intent = new Intent(SecondActivity.this, MainActivity.class);
               intent.putExtra("title", title);
               intent.putExtra("description", description);
               intent.putExtra("pos",pos);
               setResult(RESULT_OK, intent);
               if (model != null){
                   model.setTitle(title);
                   model.setDescription(description);
               }
               finish();
        });

        Intent intent = getIntent();
        String title2 = intent.getStringExtra("title");
        String description2 = intent.getStringExtra("description");
        pos = intent.getIntExtra("pos",0);
        etTitle.setText(title2);
        etDescription.setText(description2);
    }
}