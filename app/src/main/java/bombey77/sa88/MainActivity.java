package bombey77.sa88;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    TextView tvResult;
    ProgressBar progressBar;
    MyTask myTask;

    private static final String LOG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvResult = (TextView) findViewById(R.id.tvResult);
        progressBar = (ProgressBar) findViewById(R.id.pb);


    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnStart:
                myTask = new MyTask();
                myTask.execute();
                break;
            case R.id.btnResult:
                if (myTask == null) return;
                showResult();
                break;
        }
    }

    class MyTask extends AsyncTask<Void, Void, Integer> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            tvResult.setText("Begin");
        }

        @Override
        protected Integer doInBackground(Void... params) {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 100500;
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            tvResult.setText("Result is " + integer);
        }

    }


    public void showResult() {
        int result = -1;
        try {
            result = myTask.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.d(LOG, "Result is " + result);
        tvResult.setText("Result in showResult is " + result);
        Toast.makeText(getBaseContext(), "showResult is " + result, Toast.LENGTH_LONG).show();

    }


}
