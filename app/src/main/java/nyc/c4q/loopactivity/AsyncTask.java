package nyc.c4q.loopactivity;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by c4q on 12/6/17.
 */

public class AsyncTask extends android.os.AsyncTask<Integer, Integer, Void> {
    public TextView display;

    @Override
    protected void onPreExecute() {
        display = (TextView) display.findViewById(R.id.async_textView);
    }

    @Override
    protected Void doInBackground(Integer... value) {
        for (int i = 0; i < 100000; i++) {
            publishProgress();

        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... progress) {
        display.setText("Loops completed: ");
    }

    @Override
    protected void onPostExecute(Void result) {
        display.setText("Loops completed: ");
    }

    public void openExplicitIntent(View view){
        Intent intent = new Intent(AsyncTask.this, loginActivity.class);
        startActivity(intent);
    }

}