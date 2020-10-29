package br.natanael.android.androidthreads;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class RestFulActivity extends AppCompatActivity {

    private Button buttonRecuperar;
    private TextView textResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest_ful);

        buttonRecuperar = findViewById(R.id.buttonRecuperar);
        textResultado = findViewById(R.id.textResultado);

        buttonRecuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyTask task = new MyTask();
                String urlApi = "https://blockchain.info/ticker";
                task.execute(urlApi);


            }
        });
    }

    class MyTask extends AsyncTask<String, Void, String>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            String stringUrl = strings[0];
            StringBuffer buffer = new StringBuffer();

            try {
                URL url = new URL(stringUrl);

                HttpURLConnection conexao = (HttpURLConnection)  url.openConnection();

                //Recupera os dados em bytes
                InputStream inputStream =  conexao.getInputStream();

                //converter bytes em caracteres normais
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

                //leitura dos caracteres normais
                BufferedReader reader = new BufferedReader(inputStreamReader);

                String linha  = "";

                while((linha = reader.readLine()) != null){
                    buffer.append(linha);
                }

                return  buffer.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            textResultado.setText(s);
        }
    }
}
