package com.example.pokemon2;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.pokemon2.apires.Apires;
import com.example.pokemon2.model.Pokemon;
import com.example.pokemon2.model.PokemonRes;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private Retrofit retrofit;
    ListView lv;
    ArrayList<Pokemon> datos = new ArrayList<>();
    PokemonAdapter adaptador;
    ProgressBar conexion;
    public static int zoom_in = R.anim.zoom_in;
    MediaPlayer mediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        conexion = findViewById(R.id.pbConexion);

        mediaPlayer = MediaPlayer.create(this, R.raw.pikachu);
        mediaPlayer.start();

        retrofit = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.baseurl))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Pdatos( );
    }


    public static int existeEnArreglo(int[] arreglo, int busqueda) {
        for (int x = 0; x < arreglo.length; x++) {
            if (arreglo[x] == busqueda) {
                return x;
            }
        }
        return -1;
    }


    private  void mostrar(String def, String number, long id){

        lv = findViewById(R.id.lv);
        Pokemon pokemonTmp = new Pokemon(id,def,getResources().getString(R.string.signo) + number,null);
        datos.add(pokemonTmp);
        adaptador  = new PokemonAdapter(this,datos);
        lv.setAdapter(adaptador);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int number, long id) {
                Intent intent = new Intent(MainActivity.this,MainActivity2.class);
                Bundle bundle = new Bundle();
                bundle.putLong(getResources().getString(R.string.id),id);
                bundle.putLong(getResources().getString(R.string.number),number);
                intent.putExtras(bundle);

                startActivity(intent);
                overridePendingTransition(zoom_in,0);
            }
        });
    }

    private void Pdatos(){

        Apires service = retrofit.create(Apires.class);
        Call<PokemonRes> pokemonResCall = service.obtenerPokemon();

        pokemonResCall.enqueue(new Callback<PokemonRes>() {
            @Override
            public void onResponse(Call<PokemonRes> call, Response<PokemonRes> response) {
                if(response.isSuccessful()){

                    conexion.setVisibility(View.GONE);

                    PokemonRes pokemonRes = response.body();
                    ArrayList<Pokemon> listaPokemon =  pokemonRes.getResults();

                    for (int i = 0; i < listaPokemon.size(); i++){
                        Pokemon pokemon = listaPokemon.get(i);
                        int number = i+1;
                        long id = number;

                        String def = pokemon.getName();
                        String prim = def.substring(0,1);
                        String res = def.substring(1, def.length());
                        prim = prim.toUpperCase();
                        def = prim + res;
                        mostrar(def,String.valueOf(number), id);
                    }

                }else {
                    Toast.makeText(getApplicationContext(),getResources().getString(R.string.error1),
                            Toast.LENGTH_SHORT).show();
                    MainActivity.this.finish();
                }
            }

            @Override
            public void onFailure(Call<PokemonRes> call, Throwable t) {
                Toast.makeText(getApplicationContext(),getResources().getString(R.string.error1),
                        Toast.LENGTH_SHORT).show();
                MainActivity.this.finish();
            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        MainActivity.this.finish();
        Animatoo.animateFade(this);
    }
    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mediaPlayer.pause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer.release();
    }
}