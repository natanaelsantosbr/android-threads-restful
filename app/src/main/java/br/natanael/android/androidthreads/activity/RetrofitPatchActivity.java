package br.natanael.android.androidthreads.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.natanael.android.androidthreads.R;
import br.natanael.android.androidthreads.api.IDataService;
import br.natanael.android.androidthreads.model.Postagem;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitPatchActivity extends AppCompatActivity {

    private Button btnPatch;
    private TextView txtPatch;
    private Retrofit retrofit;
    private IDataService service;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit_patch);

        btnPatch = findViewById(R.id.btnPatch);
        txtPatch = findViewById(R.id.txtPatch);
        retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(IDataService.class);

        btnPatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Postagem postagem = new Postagem();
                postagem.setBody("Vai atualizar somente o body");


                Call<Postagem> call =  service.atualizarPostagemPatch(2, postagem);

                call.enqueue(new Callback<Postagem>() {
                    @Override
                    public void onResponse(Call<Postagem> call, Response<Postagem> response) {
                        if(response.isSuccessful()){
                            Postagem postagemResposta = response.body();

                            txtPatch.setText("Codigo: " + response.code()
                                    + " id: " + postagemResposta.getId()
                                    + " userId: " + postagemResposta.getUserId()
                                    + " titulo: " + postagemResposta.getTitle()
                                    + " body: " + postagemResposta.getBody());

                        }
                    }

                    @Override
                    public void onFailure(Call<Postagem> call, Throwable t) {

                    }
                });





            }
        });
    }
}
