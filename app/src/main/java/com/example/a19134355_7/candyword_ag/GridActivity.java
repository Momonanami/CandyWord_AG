package com.example.a19134355_7.candyword_ag;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class GridActivity extends AppCompatActivity
{
   // public List<String>nombres;
    private GridView gridView;
    private  Myadapter  myadapter;
    ArrayList<String> nombres = new ArrayList<String>();
    private int contador=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);

        gridView = findViewById(R.id.gridView);

        nombres= new ArrayList<String>();
        nombres.add("Bombom: "+'\n'+'\n'+"Dulce bañado con chocolate");
        nombres.add("Frugele: "+'\n'+'\n'+"Gomita en forma de rectangulo ");
        nombres.add("Lolipop: "+'\n'+'\n'+"Paleta de caramelo ");

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                Toast.makeText(GridActivity.this,""+nombres.get(i),Toast.LENGTH_LONG).show();
            }
        });

        myadapter = new Myadapter(this,R.layout.grid_item,nombres);
        gridView.setAdapter(myadapter);
        //registramos el adaptador
        registerForContextMenu(gridView);
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
                Intent intent = new Intent(GridActivity.this,ListActivity.class);
                intent.putExtra("nombres",nombres); //intent, myadapter, objeto de la clase
                startActivity(intent);
            default:
                return  super.onOptionsItemSelected(item);

        }
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu,View v, ContextMenu.ContextMenuInfo menuInfo)
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
