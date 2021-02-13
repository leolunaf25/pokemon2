package com.example.pokemon2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.pokemon2.model.Pokemon;

import java.util.ArrayList;

public class PokemonAdapter extends BaseAdapter{

    Context contexto;
    ArrayList<Pokemon> datos;
    private static LayoutInflater inflater = null;

    public PokemonAdapter(Context contexto, ArrayList<Pokemon> datos) {
        this.contexto = contexto;
        this.datos = datos;
        inflater = (LayoutInflater) contexto.getSystemService(contexto.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return datos.size();
    }

    @Override
    public Object getItem(int position) {
        return datos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final View vista = inflater.inflate(R.layout.listapokemon, null);

        ImageView imageView = vista.findViewById(R.id.poke);

        Glide.with(contexto)
                .load(contexto.getResources().getString(R.string.imagemin)
                        +datos.get(position).getId()+
                        contexto.getResources().getString(R.string.imageurlfin))

                /* Retorna un gif
                *  .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-v/black-white/animated/"
                        +datos.get(position).getId()+".gif")
                * */

                .centerCrop()
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);

        TextView name = vista.findViewById(R.id.nombre);
        TextView number = vista.findViewById(R.id.number);

        name.setText(datos.get(position).getName());
        number.setText(datos.get(position).getNumber());

        return vista;
    }
}