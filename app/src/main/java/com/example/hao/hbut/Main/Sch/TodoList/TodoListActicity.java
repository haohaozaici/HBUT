package com.example.hao.hbut.Main.Sch.TodoList;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.hao.hbut.R;
import com.example.hao.hbut.model.Database;

/**
 * Created by haohao on 2017/3/21.
 */

public class TodoListActicity extends AppCompatActivity {

    private Database data = Database.instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todolist_dialog_layout);




    }
}
