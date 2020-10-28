package br.natanael.android.androidthreads;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button buttonIniciar;
    private int contador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonIniciar = findViewById(R.id.buttonIniciar);
    }

    public void iniciarThread(View view){
        /*MyThread thread = new MyThread();
        thread.start();*/

        MyRunnable runnable = new MyRunnable();
        new Thread(runnable).start();
    }

    class MyRunnable implements Runnable{

        @Override
        public void run() {
            for (int i = 0; i <= 10;i++){
                contador = i;
                Log.d("Thread", "contador: " + i);


                runOnUiThread(new MyRunnable() {
                    @Override
                    public void run() {
                        buttonIniciar.setText("contador: " + contador);
                    }
                });

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
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
