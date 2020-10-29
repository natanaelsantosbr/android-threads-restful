package br.natanael.android.androidthreads.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.natanael.android.androidthreads.R;
import br.natanael.android.androidthreads.api.IDataService;
import br.natanael.android.androidthreads.model.Foto;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitListActivity extends AppCompatActivity {

    private Button btnLista;
    private TextView txtLinha;
    private Retrofit retrofit;
    private List<Foto> listaFotos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit_list);

        btnLista  = findViewById(R.id.btnLista);
        txtLinha = findViewById(R.id.txtLinha);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        btnLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recuperarLista();
            }


        });
    }
    private void recuperarLista() {
        IDataService service = retrofit.create(IDataService.class);

        Call<List<Foto>> call = service.recuperarFotos();

        call.enqueue(new Callback<List<Foto>>() {
            @Override
            public void onResponse(Call<List<Foto>> call, Response<List<Foto>> response) {
                if(response.isSuccessful())
                {
                    listaFotos = response.body();

                    for (int i = 0; i < listaFotos.size(); i++) {
                        Foto foto = listaFotos.get(i);
                        Log.d("resultado", "resultado: " + foto.getId() + " / " + foto.getTitle());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Foto>> call, Throwable t) {

            }
        });
    }
}
