package com.hb.todo;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.hb.todo.pojos.Todos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class addTodoActivity extends AppCompatActivity {

    private TextView tvNameTodo;
    private Spinner spnUrgency;
    private Button btnAdd;
    private Button btnCancel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_todo);

        //Récupération des éléments de layout
        tvNameTodo = findViewById(R.id.tvNameTodo);
        spnUrgency = findViewById(R.id.spnUrgency);
        btnAdd = findViewById(R.id.btnAdd);
        btnCancel = findViewById(R.id.btnCancel);

        // Initialisations des degrès d'urgence pour le spinner
        String[] urgencies = new String[]{
                getString(R.string.spn_urgency_low_urgency),
                getString(R.string.spn_urgency_medium_urgency),
                getString(R.string.spn_urgency_high_urgency)
    };

        final List<String> urgenciesList = new ArrayList<>(Arrays.asList(urgencies));

        // Initializing an ArrayAdapter
        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                this,R.layout.spinner_urgency_item,urgenciesList);

        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_urgency_item);
        spnUrgency.setAdapter(spinnerArrayAdapter);


        //Ajout du boutton de retour sur le menu
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTodo = tvNameTodo.getText().toString();
                if(nameTodo.length() < 3 ){
                    Toast toast = Toast.makeText(getApplicationContext(), getString(R.string.toast_not_enough_characters), Toast.LENGTH_LONG);
                    toast.show();
                }else{
                    Todos todo = new Todos(nameTodo, spnUrgency.getSelectedItem().toString());

                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("todo",todo);
                    setResult(addTodoActivity.RESULT_OK,resultIntent);
                    finish();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent resultIntent = new Intent();
                setResult(addTodoActivity.RESULT_CANCELED, resultIntent);
                finish();
            }
        });


    }
}