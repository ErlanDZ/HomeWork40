package com.example.homework4;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Adapter adapter;
    private FloatingActionButton btnOpenAddTask;
    List<TaskModel> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        btnOpenAddTask = findViewById(R.id.btn_open_add_task);
        initFloatingActionButton();
        initRecyclerView();

    }

    private void initRecyclerView() {
        //list.add(new TaskModel("title", "description"));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
       adapter = new Adapter(list, this);
        recyclerView.setAdapter(adapter);
        adapter.setListener(new Interface() {
            @Override
            public void itemClick(int position) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("title", adapter.list.get(position).getTitle());
                intent.putExtra("description", adapter.list.get(position).getDescription());
                intent.putExtra("pos", position);
                startActivityForResult(intent, 101);
            }
        });

    }


    private void initFloatingActionButton() {
        btnOpenAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class );
                startActivityForResult(intent,100);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK){
            if (data != null) {
                TaskModel model = new TaskModel(data.getStringExtra("title"), data.getStringExtra("description"));
                adapter.addTask(model);
            }
        }
        if (requestCode==101 && resultCode== RESULT_OK){
            if (data != null){
                TaskModel modell =  new TaskModel(data.getStringExtra("title"), data.getStringExtra("description"));
                int position = data.getIntExtra("pos",0);

                adapter.dataTry(position, modell);
            }
        }

    }
}