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

public class RetrofitPostActivity extends AppCompatActivity {

    private Button btnSalvarPostagem;
    private TextView txtSalvarPostagem;
    private Retrofit retrofit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit_post);

        btnSalvarPostagem = findViewById(R.id.btnSalvarPostagem);
        txtSalvarPostagem = findViewById(R.id.txtSalvarPostagem);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        btnSalvarPostagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarPostagem();
            }
        });
    }

    private void salvarPostagem() {
        Postagem postagem = new Postagem("1234",  "Titulo postagem", "Corpo da mensagem");

        IDataService service = retrofit.create(IDataService.class);
        Call<Postagem>  call = service.salvarPostagem(postagem.getUserId(), postagem.getTitle(), postagem.getBody());

        call.enqueue(new Callback<Postagem>() {
            @Override
            public void onResponse(Call<Postagem> call, Response<Postagem> response) {
                if(response.isSuccessful()){
                    Postagem postagemResultado = response.body();

                    txtSalvarPostagem.setText("Codigo: " + response.code()
                            + "id: " + postagemResultado.getId()
                            + "titulo: " + postagemResultado.getTitle());
                }
            }

            @Override
            public void onFailure(Call<Postagem> call, Throwable t) {

            }
        });



    }
}
