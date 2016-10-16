package be.pxl.a06_optionsmenu;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.deletemenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        CharSequence text;
        Toast toast;
        switch (item.getItemId()) {
            case R.id.content:
                text = "Content";
                toast = Toast.makeText(context, text, duration);
                toast.show();
                return true;
            case R.id.display:
                text = "Display";
                toast = Toast.makeText(context, text, duration);
                toast.show();
                return true;
            case R.id.create:
                text = "Create";
                toast = Toast.makeText(context, text, duration);
                toast.show();
                return true;
            case R.id.delete:
                text = "Delete";
                toast = Toast.makeText(context, text, duration);
                toast.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
