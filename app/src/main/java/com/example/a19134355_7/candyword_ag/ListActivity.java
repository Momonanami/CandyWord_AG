package com.example.a19134355_7.candyword_ag;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {
    private ListView listView;
    private  Myadapter  myadapter;
    //private List<String>nombres;
    private int contador=0;

    ArrayList<String> nombres = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

      /* super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myadapter = new Myadapter(this,R.layout.grid_item,nombres);
        ArrayList<String> lista = (ArrayList<String>) getIntent().getSerializableExtra("nombres");
        ArrayAdapter<String> adapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,lista);

        //enlazamos el adaptador con el listview
        listView.setAdapter(adapter);*/

       // listView = findViewById(R.id.ListView);
        //listView.setAdapter(myadapter);

 //       this.myadapter.notifyDataSetChanged();
   //     ArrayAdapter<String> adapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,nombres);
        //enlazamos el adaptador con el listview
     //   listView.setAdapter(adapter);
       // Myadapter myadapter= new Myadapter(this,R.layout.list_item,nombres);
         //   listView.setAdapter(myadapter);

         //  super.onCreate(savedInstanceState);
           // setContentView(R.layout.activity_main);

            //listView = findViewById(R.id.ListView);


          // ArrayList<String> nombres = (ArrayList<String>) getIntent().getSerializableExtra("nombres");
          //  final List<String> nombres= new ArrayList<String>();

            // forma visual que mostraremos los datos
           //ArrayAdapter<String> adapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,nombres);
            //enlazamos el adaptador con el listview
           //listView.setAdapter(adapter);

          /*  listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Toast.makeText(ListActivity.this,"Clic en "+nombres.get(i),Toast.LENGTH_LONG).show();
                }
            });
            //enlazamos con nuestro adaptador personalizado
            Myadapter myadapter= new Myadapter(this,R.layout.list_item,nombres);
            listView.setAdapter(myadapter);*/
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.ListView);

      //  final List<String> nombres1= new ArrayList<String>();
        final ArrayList<String> nombres = (ArrayList<String>) getIntent().getSerializableExtra("nombres");
        // forma visual que mostraremos los datos
        ArrayAdapter<String> adapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,nombres);
        //enlazamos el adaptador con el listview
        myadapter = new Myadapter(this,R.layout.grid_item,nombres);
        listView.setAdapter(adapter);
        registerForContextMenu(listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(ListActivity.this,""+nombres.get(i),Toast.LENGTH_LONG).show();
            }
        });
        //enlazamos con nuestro adaptador personalizado
        Myadapter myadapter= new Myadapter(this,R.layout.list_item,nombres);
        listView.setAdapter(myadapter);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar_menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {

        switch (item.getItemId())
        {
            case R.id.add_item:
                this.nombres.add("Candy "+"Dulce tradicional "+ (++contador));
                this.myadapter.notifyDataSetChanged();
                return true;

            case R.id.cambio:
                Intent intent = new Intent(ListActivity.this,GridActivity.class);
                intent.putExtra("nombres",nombres); //intent, myadapter, objeto de la clase
                startActivity(intent);
            default:
                return  super.onOptionsItemSelected(item);

        }
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu,v, menuInfo);
        MenuInflater inflater= getMenuInflater();
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        menu.setHeaderTitle(this.nombres.get(info.position));
        inflater.inflate(R.menu.context_menu,menu);

    }
    @Override
    public boolean onContextItemSelected(MenuItem item)
    {
        AdapterView.AdapterContextMenuInfo info= (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId())
        {
            case R.id.delete_item:
                //eliminamos el item cicleado
                this.nombres.remove(info.position);
                //notificamos al adapter del cambio
                this.myadapter.notifyDataSetChanged();
                return true;

            default:
                return super.onContextItemSelected(item);
        }
    }
}

