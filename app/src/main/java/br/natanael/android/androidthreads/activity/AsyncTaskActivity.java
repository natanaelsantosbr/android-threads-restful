package br.natanael.android.androidthreads.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import br.natanael.android.androidthreads.R;

public class AsyncTaskActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);

        progressBar = findViewById(R.id.progressBar);
    }

    public void iniciarAsyncTask(View view)
    {
        MyAsyncTask task = new MyAsyncTask();
        task.execute(10);

    }

    /*
    * 1 - Parametro a ser passado para a classe
    * 2 - Tipo de valor que sera utilizado para o progresso da tarefa
    * 3 - Retorno apos tarefa finalizada*/

    class MyAsyncTask extends AsyncTask<Integer,  Integer, String > {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(Integer... integers) {
            int numero = integers[0];

            for (int i = 0; i <= numero; i++)
            {
                publishProgress(i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


            return "Finalizado";
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressBar.setProgress(values[0]);
        }
        private void Teste() {

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(AsyncTaskActivity.this, s, Toast.LENGTH_SHORT).show();
            progressBar.setProgress(0);
            progressBar.setVisibility(View.INVISIBLE);
        }


    }
}
