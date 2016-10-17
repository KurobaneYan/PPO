package com.kurobane.yan.ppo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.StringTokenizer;

class DBHelper extends SQLiteOpenHelper {
    private static DBHelper instance = null;

    private static final String DATABASE_NAME = "ppo_tasks";
    private static final int DATABASE_VERSION = 1;

    private static final String TASKS_TABLE_NAME = "tasks";
    private static final String TASK_ID = "task_id";
    private static final String TASK_NAME = "task_name";
    private static final String TASK_IS_DAILY = "task_is_daily";
    private static final String TASK_IS_IMPORTANT= "task_is_important";

    private static final String[] TASK_COLUMNS =
            {TASK_ID, TASK_NAME, TASK_IS_DAILY, TASK_IS_IMPORTANT};

    private DBHelper(Context applicationContext) {
        super(applicationContext, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static DBHelper getInstance(Context applicationContext) {
        if (instance == null) {
            instance = new DBHelper(applicationContext);
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TASKS_TABLE = "CREATE TABLE " + TASKS_TABLE_NAME + " ( " +
                TASK_ID + "INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TASK_NAME + "TEXT, " +
                TASK_IS_DAILY + "INTEGER, " +
                TASK_IS_IMPORTANT + "INTEGER )";
        db.execSQL(CREATE_TASKS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TASKS_TABLE_NAME);
    }

    public void addTask(Task task) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TASK_ID, task.getName());
        values.put(TASK_NAME, task.getName());
        values.put(TASK_IS_DAILY, task.isDaily() ? 1 : 0);
        values.put(TASK_IS_IMPORTANT, task.isImportant() ? 1 : 0);

        db.insert(TASKS_TABLE_NAME, null, values);
        db.close();
    }

    public Task getTask(String name) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                TASKS_TABLE_NAME,
                TASK_COLUMNS,
                TASK_NAME + " = ?",
                new String[] { name },
                null,
                null,
                null
        );

        Task task = new Task();
        if (cursor != null) {
            cursor.moveToFirst();

            task.setId(cursor.getInt(0));
            task.setName(cursor.getString(1));
            task.setDaily(cursor.getInt(2) != 0);
            task.setImportant(cursor.getInt(3) != 0);

            cursor.close();
        }

        return task;
    }

    public int updateTask(Task task) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TASK_ID, task.getName());
        values.put(TASK_NAME, task.getName());
        values.put(TASK_IS_DAILY, task.isDaily() ? 1 : 0);
        values.put(TASK_IS_IMPORTANT, task.isImportant() ? 1 : 0);

        int i = db.update(TASKS_TABLE_NAME,
                values,
                TASK_NAME + " = ?",
                new String[] {String.valueOf(task.getName())});

        db.close();

        return i;
    }

    public void deleteTask(Task task) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TASKS_TABLE_NAME,
                TASK_NAME + " = ?",
                new String[] {String.valueOf(task.getName())});

        db.close();
    }

    public ArrayList<Task> getAllTasks() {
        ArrayList<Task> tasks = new ArrayList<>();

        String query = "SELECT * FROM " + TASKS_TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                Task task = new Task(cursor.getString(1));
                task.setId(cursor.getInt(0));
                task.setDaily(cursor.getInt(2) != 0);
                task.setImportant(cursor.getInt(3) != 0);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return tasks;
    }
}
