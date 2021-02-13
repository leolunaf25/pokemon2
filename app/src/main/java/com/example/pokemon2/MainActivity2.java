package com.example.pokemon2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import com.example.pokemon2.apires.Apires;
import com.example.pokemon2.model.Pokemon;
import com.example.pokemon2.model.PokemonRes;
import com.example.pokemon2.model.PokemonRes2;
import com.example.pokemon2.model.Type_;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity2 extends AppCompatActivity {

    private Retrofit retrofit;
    ProgressBar conexion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        conexion = findViewById(R.id.pbconexion2);

        Bundle bundle = new Bundle();
        bundle = getIntent().getExtras();
        int id = (int) bundle.getLong(getResources().getString(R.string.id));
        int number = (int) bundle.getLong(getResources().getString(R.string.number));

        retrofit = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.baseurl2))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Pdatos(id);

    }

    private void  ima (int id){
        ImageView imageView = (ImageView) findViewById(R.id.pokem);
        /* Regresa un gif
       final String URL = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-v/black-white/animated/"
                + (id+1) + ".gif";Glide.with(this).load(URL).centerCrop().crossFade().diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);*/
        final String URL = getResources().getString(R.string.imageurlini)
                + (id+1) + getResources().getString(R.string.imageurlfin);
        Glide.with(this).load(URL).centerCrop().crossFade().diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);

    }

    private void Pdatos(int id){
        Apires service = retrofit.create(Apires.class);
        Call<PokemonRes2> pokemonRes2Call = service.obtenerPokemon2(id+1);

        pokemonRes2Call.enqueue(new Callback<PokemonRes2>() {
            @Override
            public void onResponse(Call<PokemonRes2> call, Response<PokemonRes2> response) {
                if(response.isSuccessful()){

                    PokemonRes2 pokemonRes2 = response.body();
                    String nombre = pokemonRes2.getName();
                    String prim = nombre.substring(0,1);
                    String res = nombre.substring(1, nombre.length());
                    prim = prim.toUpperCase();
                    nombre = prim + res;

                    String experiencia =  pokemonRes2.getBase_experience();
                    String altura = pokemonRes2.getHeight();
                    String peso = pokemonRes2.getWeight();

                    TextView nom = findViewById(R.id.namem);
                    nom.setText(nombre);

                    TextView exp = findViewById(R.id.experience);
                    exp.setText(experiencia);
                    TextView exp2 = findViewById(R.id.experience2);
                    exp2.setText(getResources().getString(R.string.experience));

                    TextView height = findViewById(R.id.height);
                    height.setText(altura);
                    TextView hei2 = findViewById(R.id.height2);
                    hei2.setText(getResources().getString(R.string.height));

                    TextView weight = findViewById(R.id.weight);
                    weight.setText(peso);
                    TextView weight2 = findViewById(R.id.weight2);
                    weight2.setText(getResources().getString(R.string.weight));

                    TextView typ = findViewById(R.id.type);
                    typ.setText(getResources().getString(R.string.type));

                    ArrayList<Pokemon> types = pokemonRes2.getTypes();
                    for (int i = 0; i < types.size(); i++) {

                        Pokemon pokemon = types.get(i);
                        Type_ type = pokemon.getType();

                        //String nametype = type.getName();
                        String urltype = type.getUrl();
                        ima(id);

                        conexion.setVisibility(View.GONE);

                        if (i==0){
                            String[] urlnum = urltype.split(getResources().getString(R.string.line));
                            int numeurl = Integer.parseInt(urlnum[urlnum.length - 1]);
                            imagen_tipo(numeurl);
                        }
                        if (i==1){
                            String[] urlnum = urltype.split(getResources().getString(R.string.line));
                            int numeurl = Integer.parseInt(urlnum[urlnum.length - 1]);
                            imagen_tipo2(numeurl);
                        }
                    }
                }else {
                    Toast.makeText(getApplicationContext(),getResources().getString(R.string.error1),
                            Toast.LENGTH_SHORT).show();
                    MainActivity2.this.finish();
                }
            }

            @Override
            public void onFailure(Call<PokemonRes2> call, Throwable t) {
                Toast.makeText(getApplicationContext(),getResources().getString(R.string.error1),
                        Toast.LENGTH_SHORT).show();
                MainActivity2.this.finish();
            }
        });
    }

    private void imagen_tipo(int idtype){
        int imagetype = 0;
        if (idtype==1){
            imagetype = R.drawable.normal;
        }
        if (idtype==2){
            imagetype = R.drawable.fighting;
        }
        if (idtype==3){
            imagetype = R.drawable.flying;
        }
        if (idtype==4){
            imagetype = R.drawable.poison;
        }
        if (idtype==5){
            imagetype = R.drawable.ground;
        }
        if (idtype==6){
            imagetype = R.drawable.rock;
        }
        if (idtype==7){
            imagetype = R.drawable.bug;
        }
        if (idtype==8){
            imagetype = R.drawable.ghost;
        }
        if (idtype==9){
            imagetype = R.drawable.steel;
        }
        if (idtype==10){
            imagetype = R.drawable.fire;
        }
        if (idtype==11){
            imagetype = R.drawable.water;
        }
        if (idtype==12){
            imagetype = R.drawable.grass;
        }
        if (idtype==13){
            imagetype = R.drawable.electric;
        }
        if (idtype==14){
            imagetype = R.drawable.psychic;
        }
        if (idtype==15){
            imagetype = R.drawable.ice;
        }
        if (idtype==16){
            imagetype = R.drawable.dragon;
        }
        if (idtype==17){
            imagetype = R.drawable.dark;
        }
        if (idtype==18){
            imagetype = R.drawable.fairy;
        }
        ImageView imageView = (ImageView) findViewById(R.id.type1);
        imageView.setImageResource(imagetype);
    }

    private void imagen_tipo2(int idtype){
        int imagetype = 0;
        if (idtype==1){
            imagetype = R.drawable.normal;
        }
        if (idtype==2){
            imagetype = R.drawable.fighting;
        }
        if (idtype==3){
            imagetype = R.drawable.flying;
        }
        if (idtype==4){
            imagetype = R.drawable.poison;
        }
        if (idtype==5){
            imagetype = R.drawable.ground;
        }
        if (idtype==6){
            imagetype = R.drawable.rock;
        }
        if (idtype==7){
            imagetype = R.drawable.bug;
        }
        if (idtype==8){
            imagetype = R.drawable.ghost;
        }
        if (idtype==9){
            imagetype = R.drawable.steel;
        }
        if (idtype==10){
            imagetype = R.drawable.fire;
        }
        if (idtype==11){
            imagetype = R.drawable.water;
        }
        if (idtype==12){
            imagetype = R.drawable.grass;
        }
        if (idtype==13){
            imagetype = R.drawable.electric;
        }
        if (idtype==14){
            imagetype = R.drawable.psychic;
        }
        if (idtype==15){
            imagetype = R.drawable.ice;
        }
        if (idtype==16){
            imagetype = R.drawable.dragon;
        }
        if (idtype==17){
            imagetype = R.drawable.dark;
        }
        if (idtype==18){
            imagetype = R.drawable.fairy;
        }
        ImageView imageView = (ImageView) findViewById(R.id.type2);
        imageView.setImageResource(imagetype);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        MainActivity2.this.finish();
        Animatoo.animateZoom(this);
    }
}