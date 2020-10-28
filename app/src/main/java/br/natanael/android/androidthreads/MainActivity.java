package br.natanael.android.androidthreads;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button buttonIniciar;
    private Button buttonParar;
    private int contador;
    private Handler handler = new Handler();
    private boolean pararExecucao = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonIniciar = findViewById(R.id.buttonIniciar);
        buttonParar = findViewById(R.id.buttonParar);
    }

    public void iniciarThread(View view){
        /*MyThread thread = new MyThread();
        thread.start();*/

        pararExecucao = false;
        MyRunnable runnable = new MyRunnable();
        new Thread(runnable).start();
    }

    public  void pararThread(View view){
        pararExecucao = true;

    }


    class MyRunnable implements Runnable{

        @Override
        public void run() {


            for (int i = 0; i <= 15;i++){

                if(pararExecucao)
                    return;

                contador = i;
                Log.d("Thread", "contador: " + i);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                runOnUiThread(new MyRunnable() {
                    @Override
                    public void run() {
                        buttonIniciar.setText("contador: " + contador);
                    }
                });


/*                handler.post(new MyRunnable() {
                    @Override
                    public void run() {
                        buttonIniciar.setText("contador: " + contador);
                    }
                });*/
            }
        }
    }


    class MyThread extends  Thread {
        @Override
        public void run() {
            for (int i = 0; i <= 15;i++){
                Log.d("Thread", "contador: " + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
