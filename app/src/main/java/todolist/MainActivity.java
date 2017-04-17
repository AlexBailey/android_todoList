package todolist;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.CursorAdapter;
import android.widget.SimpleCursorAdapter;
import android.content.Intent;

public class MainActivity extends ActionBarActivity {

    private TasksDB tasksDatabase = null;
    private Cursor tasks = null;
    private ListView lvTasks = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        refresh();
    }

    @Override
    protected void onResume(){
        super.onResume();
        refresh();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.addTask) {
            Intent loadTaskInput = new Intent(this, addTask.class);
            startActivity(loadTaskInput);
        }

        return super.onOptionsItemSelected(item);
    }

    public void refresh() {

        SQLiteDatabase.CursorFactory factory = null;

        tasksDatabase = new TasksDB(this, "tasksDB", factory);

        String[] columnNames = {"Task", BaseColumns._ID};
        lvTasks = (ListView)findViewById(R.id.listView);

        int[] targetLayoutIDs = {R.id.textName};

        SQLiteDatabase db = tasksDatabase.getReadableDatabase();

        tasks = db.query("tasks", columnNames, null, null, null, null, null);

        CursorAdapter adapter = new SimpleCursorAdapter(this,
                R.layout.task_item, tasks, columnNames, targetLayoutIDs, 0);

        lvTasks.setAdapter(adapter);
    }
}
