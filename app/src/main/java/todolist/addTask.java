package todolist;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class addTask extends ActionBarActivity {

    EditText editText;
    Button button;
    private TasksDB tasksDatabase = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        editText = (EditText) findViewById(R.id.editText);
        button = (Button) findViewById(R.id.button);

        SQLiteDatabase.CursorFactory factory = null;
        tasksDatabase = new TasksDB(this, "tasksDB", factory);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                tasksDatabase.insertData(editText.getText().toString());
                Intent loadTaskInput = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(loadTaskInput);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_task, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
