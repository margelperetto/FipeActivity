package br.com.fema.fipeactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import br.com.fema.fipeactivity.classes.Tipo;

public class Activity1Tipos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity1_tipos);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getString(R.string.app_name));
        toolbar.setSubtitle(getString(R.string.subtitle_tipos));

        View llCarros = findViewById(R.id.tipoCarro);
        View llMoto = findViewById(R.id.tipoMoto);
        View llCaminhao = findViewById(R.id.tipoCaminhao);

        llCarros.setOnClickListener(  new TipoClickListener(Tipo.CARROS));
        llMoto.setOnClickListener(    new TipoClickListener(Tipo.MOTOS));
        llCaminhao.setOnClickListener(new TipoClickListener(Tipo.CAMINHOES));
    }

    class TipoClickListener implements View.OnClickListener{
        private Tipo tipo;

        TipoClickListener(Tipo tipo) {
            this.tipo = tipo;
        }

        @Override
        public void onClick(View view) {
            Bundle params = new Bundle();
            params.putSerializable("PARAM", tipo);

            Intent intent = new Intent(Activity1Tipos.this,Activity2Marcas.class);
            intent.putExtras(params);

            startActivity(intent);
        }
    }

}