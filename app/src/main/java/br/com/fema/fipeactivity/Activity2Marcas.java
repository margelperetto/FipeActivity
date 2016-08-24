package br.com.fema.fipeactivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.com.fema.fipeactivity.classes.FipeService;
import br.com.fema.fipeactivity.classes.Marca;
import br.com.fema.fipeactivity.classes.Tipo;
import br.com.fema.fipeactivity.componentes.MarcaRecyclerAdapter;

public class Activity2Marcas extends AppCompatActivity {

    private Tipo tipo;
    private MarcaRecyclerAdapter adapter;
    private MaterialSearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2_marcas);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.title_marcas));
        setSupportActionBar(toolbar);

        searchView = (MaterialSearchView) findViewById(R.id.search_view);
        searchView.setHint("Pesquisar");

        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener(){
            @Override
            public boolean onQueryTextSubmit(String query) { return false;}

            @Override
            public boolean onQueryTextChange(String pesquisa) {
                if(pesquisa!=null && !pesquisa.isEmpty()){
                    adapter.ordenarPor(pesquisa);
                }
                return true;
            }
        });

        adapter = new MarcaRecyclerAdapter();

        RecyclerView.LayoutManager layoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);

        RecyclerView recycler = (RecyclerView) findViewById(R.id.recycler);
        recycler.setLayoutManager(layoutManager);
        recycler.setAdapter(adapter);

        if(getIntent().getExtras()!=null){
            tipo = (Tipo) getIntent().getExtras().getSerializable("PARAM");
        }

        conectarFipe();
    }

    private void conectarFipe(){
        new AsyncTask<Void,Void,List<JSONObject>>(){
            private ProgressDialog dialog;
            @Override
            protected void onPreExecute() {
                dialog = ProgressDialog.show(Activity2Marcas.this, "", getString(R.string.carregando), true);
            }
            @Override
            protected List<JSONObject> doInBackground(Void... voids) {
                String url = "http://fipeapi.appspot.com/api/1/"+tipo.getNome()+"/marcas.json";
                return FipeService.getListJSONFromUrl(url);
            }
            @Override
            protected void onPostExecute(List<JSONObject> list) {
                try {
                    List<Marca> marcas = new ArrayList<>();
                    for(JSONObject json : list){
                        Marca m = new Marca(
                                json.getString("id"),
                                json.getString("fipe_name"),
                                json.getString("name")
                        );
                        marcas.add(m);
                    }
                    adapter.setData(marcas);
                    if(marcas.isEmpty()){
                        AlertDialog.Builder builder = new AlertDialog.Builder(Activity2Marcas.this)
                                .setTitle(getString(R.string.titulo_erro_busca))
                                .setMessage(getString(R.string.erro_busca))
                                .setCancelable(true);

                        AlertDialog alert = builder.create();
                        alert.show();
                    }
                } catch (Exception e) {
                    Log.e("FIPE",e.getMessage(),e);
                } finally {
                    dialog.cancel();
                }
            }
        }.execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_padrao, menu);

        MenuItem itemSearch = menu.findItem(R.id.menu_search);
        searchView.setMenuItem(itemSearch);

        return true;
    }

}