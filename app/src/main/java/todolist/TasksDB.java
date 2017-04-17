package todolist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class TasksDB extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String TASKS_TABLE_NAME = "tasks";

    TasksDB(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase dbTasks) {
        Log.d(" in onCreate tasksDB", "oncreated");
        createTable(dbTasks);
    }

    private void createTable(SQLiteDatabase dbTasks) {
        String createSQL = "CREATE TABLE " + TASKS_TABLE_NAME + "(" + "_id INTEGER PRIMARY KEY AUTOINCREMENT, " + "Task TEXT);";
        dbTasks.execSQL(createSQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase dbTasks, int oldVersion, int newVersion) {
        String dropSQL = "DROP TABLE IF EXISTS " + TASKS_TABLE_NAME + ";";
        dbTasks.execSQL(dropSQL);
        createTable(dbTasks);
        Log.d("in onUpgrade TasksDB", "onUpgrade");
    }

    public void insertData(String task) {
        SQLiteDatabase dbTasks = this.getWritableDatabase();
        String insert = "INSERT INTO " + TASKS_TABLE_NAME + "(Task) SELECT '" + task + "' As Task;";
        dbTasks.execSQL(insert);
    }

}

