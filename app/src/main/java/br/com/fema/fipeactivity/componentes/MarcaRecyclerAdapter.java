package br.com.fema.fipeactivity.componentes;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import br.com.fema.fipeactivity.R;
import br.com.fema.fipeactivity.classes.Marca;
import br.com.fema.fipeactivity.componentes.MarcaViewHolder;

public class MarcaRecyclerAdapter extends RecyclerView.Adapter<MarcaViewHolder>{

    private List<Marca> data = new LinkedList<>();

    @Override
    public MarcaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_marca,parent,false);
        return new MarcaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MarcaViewHolder holder, int position) {
        holder.setMarca(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<Marca> data){
        this.data.clear();
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    public void ordenarPor(final String pesquisa){

        Collections.sort(data, new Comparator<Marca>() {
            @Override
            public int compare(Marca m1, Marca m2) {
                String pLC =  pesquisa.toLowerCase();
                String m1LC = m1.getFipeName().toLowerCase();
                String m2LC = m2.getFipeName().toLowerCase();

                boolean t1Start = m1LC.startsWith(pLC);
                boolean t2Start = m2LC.startsWith(pLC);

                if( t1Start && t2Start ){
                    return m1LC.compareTo(m2LC);
                }
                if( t1Start ){
                    return -1;
                }
                if( t2Start ){
                    return 1;
                }
                return 0;
            }
        });
        notifyDataSetChanged();
    }
}