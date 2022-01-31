package com.hb.todo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.hb.todo.pojos.DAO;
import com.hb.todo.pojos.Todos;

import java.util.ArrayList;
import java.util.List;

public class TodoDAO extends DAO {
    public TodoDAO(Context context) {
        super(new TodoDBHelper(context));
    }

    public Todos find(Long id) {
        Todos todo = null;

        Cursor cursor = db.rawQuery("select * from " + TodoDBHelper.TODO_TABLE_NAME +
                " where " + TodoDBHelper.TODO_KEY_COLUMN_INDEX + " = ?", new String[]{String.valueOf(id)});

        if (cursor != null && cursor.moveToFirst()) {
            todo = new Todos();
            todo.setId(Integer.parseInt(cursor.getString(0)));
            todo.setName(cursor.getString(1));
            todo.setUrgency(cursor.getString(2));

            cursor.close();
        }

        return todo;
    }

    public List<Todos> list() {
        open();

        List<Todos> todos = new ArrayList<>();

        Cursor cursor = db.rawQuery("select * from " +
                TodoDBHelper.TODO_TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                Todos todo = new Todos();
                todo.setId((int) cursor.getLong(TodoDBHelper.TODO_KEY_COLUMN_INDEX));
                todo.setName(cursor.getString(TodoDBHelper.TODO_NAME_COLUMN_INDEX));
                todo.setUrgency(cursor.getString(TodoDBHelper.TODO_URGENCY_COLUMN_INDEX));

                todos.add(todo);

                cursor.moveToNext();
            }
        }

        cursor.close();

        return todos;
    }

    public void add(Todos todo) {
        open();

        ContentValues value = new ContentValues();

        value.put(TodoDBHelper.TODO_NAME, todo.getName());
        value.put(TodoDBHelper.TODO_URGENCY, todo.getUrgency());

        db.insert(TodoDBHelper.TODO_TABLE_NAME, null, value);
    }
}
