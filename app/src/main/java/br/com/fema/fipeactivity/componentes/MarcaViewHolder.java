package br.com.fema.fipeactivity.componentes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import br.com.fema.fipeactivity.Activity2Marcas;
import br.com.fema.fipeactivity.Activity3Veiculos;
import br.com.fema.fipeactivity.R;
import br.com.fema.fipeactivity.classes.Marca;

public class MarcaViewHolder  extends RecyclerView.ViewHolder{

    private Marca marca;
    private TextView tvFipeName;

    public MarcaViewHolder(final View itemView) {
        super(itemView);
        tvFipeName = (TextView) itemView.findViewById(R.id.tvFipeName);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity) itemView.getContext();

                Bundle params = new Bundle();
                params.putSerializable("PARAM", marca);

                Intent intent = new Intent(activity, Activity3Veiculos.class);
                intent.putExtras(params);

                activity.startActivity(intent);

                String msg = "Isto é um teste... Selecionou "+marca.getFipeName()+"! ";
                Toast.makeText(activity, msg, Toast.LENGTH_LONG).show();
            }
        });

    }

    public void setMarca(Marca marca){
        this.marca = marca;
        tvFipeName.setText(marca.getFipeName());
    }

}