package be.pxl.a08_listview;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ArrayAdapter adapterBrit;
    private ArrayAdapter adapterFrans;
    private List<String> listBrit;
    private List<String> listFrans;
    private ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listview = (ListView) findViewById(R.id.listview);
        Resources res = getResources();
        listBrit = new ArrayList<>(Arrays.asList(res.getStringArray(R.array.Brit)));
        listFrans = new ArrayList<>(Arrays.asList(res.getStringArray(R.array.Frans)));

        adapterBrit = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, listBrit);
        adapterFrans = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, listFrans);

        listview.setAdapter(adapterBrit);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {
                final String item = (String) parent.getItemAtPosition(position);
                Context context = getApplicationContext();
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, item, duration);
                toast.show();
            }

        });

        registerForContextMenu(listview);
    }



    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        String title = listview.getAdapter().getItem(info.position).toString();
        menu.setHeaderTitle(title);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.delete:
                ArrayAdapter adapter = (ArrayAdapter) listview.getAdapter();
                adapter.remove(adapter.getItem((int)info.id));
                adapter.notifyDataSetChanged();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.brit:
                listview.setAdapter(adapterBrit);
                return true;
            case R.id.frans:
                listview.setAdapter(adapterFrans);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
