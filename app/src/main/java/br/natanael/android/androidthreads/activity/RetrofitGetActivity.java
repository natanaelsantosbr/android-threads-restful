package br.natanael.android.androidthreads.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

import br.natanael.android.androidthreads.R;
import br.natanael.android.androidthreads.api.ICEPService;
import br.natanael.android.androidthreads.model.CEP;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitGetActivity extends AppCompatActivity {

    private Button btnProcessar;
    private TextView txtResultado;
    private Retrofit retrofit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);

        btnProcessar  = findViewById(R.id.btnProcessar);
        txtResultado = findViewById(R.id.txtResultado);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://viacep.com.br/ws/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        btnProcessar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recuperarCEPRetrovit();

            }
        });
    }





    private void recuperarCEPRetrovit()
    {
        ICEPService service = retrofit.create(ICEPService.class);
        Call<CEP> call = service.recuperarCep("01310100");

        //Criar tarefa assincrona (Enquiuu)
        call.enqueue(new Callback<CEP>() {
            @Override
            public void onResponse(Call<CEP> call, Response<CEP> response) {
                if(response.isSuccessful())
                {
                    CEP cep = response.body();

                    txtResultado.setText(cep.getLogradouro());

                }

            }

            @Override
            public void onFailure(Call<CEP> call, Throwable t) {

            }
        });

    }
}
