package br.natanael.android.androidthreads;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

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
                ConsumirListaDeBlockchain task = new ConsumirListaDeBlockchain();
                String urlApi = "https://blockchain.info/ticker";
                String urlCep = "https://viacep.com.br/ws/72304052/json/";

                task.execute(urlApi);


            }
        });
    }

    class ConsultarCep extends AsyncTask<String, Void, String>
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

            JSONObject jsonObject = null;
            String cep = "";
            String logradouro = "";
            String complemento = "";
            String bairro  = "";
            String localidade = "";
            String uf = "";



            try {
                jsonObject = new JSONObject(s);
                cep = jsonObject.getString("cep");
                logradouro = jsonObject.getString("logradouro");
                complemento = jsonObject.getString("complemento");
                bairro = jsonObject.getString("bairro");
                localidade = jsonObject.getString("localidade");
                uf = jsonObject.getString("uf");



            } catch (JSONException e) {
                e.printStackTrace();
            }

//            textResultado.setText(s);
            textResultado.setText("cep: " +  cep + " \n" + "logradouro: " + logradouro + "\n complemento: " + complemento + "\n bairro: " + bairro + "\n localidade: " + localidade + "\n uf:" + uf);
        }
    }


    class ConsumirListaDeBlockchain extends AsyncTask<String, Void, String>
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

            JSONObject jsonObject = null;
            String cep = "";
            String logradouro = "";
            String complemento = "";
            String bairro  = "";
            String localidade = "";
            String uf = "";



            try {
                jsonObject = new JSONObject(s);
                cep = jsonObject.getString("cep");
                logradouro = jsonObject.getString("logradouro");
                complemento = jsonObject.getString("complemento");
                bairro = jsonObject.getString("bairro");
                localidade = jsonObject.getString("localidade");
                uf = jsonObject.getString("uf");



            } catch (JSONException e) {
                e.printStackTrace();
            }

//            textResultado.setText(s);
            textResultado.setText("cep: " +  cep + " \n" + "logradouro: " + logradouro + "\n complemento: " + complemento + "\n bairro: " + bairro + "\n localidade: " + localidade + "\n uf:" + uf);
        }
    }
}
