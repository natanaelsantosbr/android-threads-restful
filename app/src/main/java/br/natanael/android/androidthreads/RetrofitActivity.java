package br.natanael.android.androidthreads;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitActivity extends AppCompatActivity {

    private Button btnProcessar;
    private TextView txtResultado;
    private Retrofit retrofit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);

        btnProcessar  = findViewById(R.id.btnProcessar);
        txtResultado = findViewById(R.id.txtResultado);

        btnProcessar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retrofit = new Retrofit.Builder()
                        .baseUrl("https://viacep.com.br/ws/72304052/json/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();



            }
        });
    }
}
