package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button ButtonSave;
    private EditText EditTextInput;
    private ListView ListViewToDo;
    private ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButtonSave = findViewById(R.id.Button_Save_ToDo);
        EditTextInput = findViewById(R.id.EditText_Input_ToDo);
        ListViewToDo = findViewById(R.id.ListView_Display_ToDo);

        // First click setup with interface
        ButtonSave.setOnClickListener(this);

        //Init they array adapter
        adapter = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1);

        // Second event setup annonymous interface implimination
        ListViewToDo.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Object toBeRemoved = adapter.getItem(i);
                adapter.remove(adapter.getItem(i));
                Toast.makeText(MainActivity.this, toBeRemoved.toString(), Toast.LENGTH_SHORT).show();
                return true; //IF I consume this event
            }
        });
    }

    @Override
    public void onClick(View v) {
        String userInput = EditTextInput.getText().toString();
        if(userInput.isEmpty()) {
            Toast.makeText(this, "Do not upload empty ToDo's", Toast.LENGTH_SHORT).show();
        } else{
            addNewTodo(userInput);
            // Remove input
            EditTextInput.getText().clear();
        }
    }

    private void addNewTodo(String userInput) {
        adapter.add(userInput);
        ListViewToDo.setAdapter(adapter);
    }
}