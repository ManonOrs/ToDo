package com.hb.todo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.hb.todo.pojos.Todos;

public class MainActivity extends AppCompatActivity {

    private TextView tvTodos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Récupération des éléments de layout
        tvTodos = findViewById(R.id.tvTodos);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addTodo:

                Intent intent = new Intent(getApplicationContext(), addTodoActivity.class);
                startActivityForResult(intent, 2);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == addTodoActivity.RESULT_OK) {
            Todos result = (Todos) data.getSerializableExtra("todo");
            String tvTodosText = tvTodos.getText().toString();
            tvTodos.setText(tvTodosText + "\n" + result.getName() + ": " +result.getUrgency());
        }
        if (resultCode == addTodoActivity.RESULT_CANCELED) {
            // Write your code if there's no result
        }
    }
}