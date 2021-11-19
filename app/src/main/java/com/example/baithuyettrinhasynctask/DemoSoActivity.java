package com.example.baithuyettrinhasynctask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DemoSoActivity extends AppCompatActivity {

    private Button btn;
    private TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_so);
        btn = (Button) findViewById(R.id.buttonAs);
        txt = (TextView) findViewById(R.id.textviewAs);
        btn.setOnClickListener(v -> {
            new Myasynctask().execute();
            //runNoAsyTask();
        });
    }




    private void runNoAsyTask(){
        for (int i = 0; i < 6; i++) {
            SystemClock.sleep(100);
            txt.setText("Begin" +"\n");
            txt.append("Task" + i + "\n");
            if(i == 5){
                txt.append("done");
            }

        }
    }
    private class Myasynctask extends AsyncTask<Void, String, String> {
        private int a;
        private String str;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            txt.setText("Begin" +"\n");
        }
        @Override
        protected String doInBackground(Void... voids) {
            for(int i = 1 ;i <= 5; i++ ){
                publishProgress("Task" + i + "\n");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return "done";
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            txt.append(s);
        }
        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
//            txt.setText(values[0]);
            txt.append(values[0]);
        }
    }
}