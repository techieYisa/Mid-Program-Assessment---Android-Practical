package nyc.c4q.loopactivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.CheckBox;

/**
 * Created by c4q on 12/6/17.
 */

public class loginActivity extends AppCompatActivity {
    private static final String SHARED_PREFS_KEY = "sharedPrefsTesting";
    private EditText username;
    private EditText password;
    private Checkbox checkbox;
    private Button submit;
    private SharedPreferences login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_intent);

        username = (EditText) findViewById(R.id.email_edittext);
        password = (EditText) findViewById(R.id.password_edittext);
        checkbox = (Checkbox) findViewById(R.id.remember_me_checkbox);
        submit = (Button) findViewById(R.id.enter_button);

        login = getApplicationContext().getSharedPreferences(SHARED_PREFS_KEY, MODE_PRIVATE);


        if (login.getBoolean("isChecked", false)) {
            username.setText(login.getString("username", null));
            password.setText(login.getString("password", null));
            checkBox.setChecked(login.getBoolean("isChecked", false));
        }
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = login.edit();
                if (checkBox.isChecked()) {
                    editor.putString("username", username.getText().toString());
                    editor.putString("password", password.getText().toString());
                    editor.putBoolean("isChecked", checkBox.isChecked());
                    editor.commit();
                } else {
                    editor.putBoolean("isChecked", checkBox.isChecked());
                    editor.commit();
                }

                String checkUser = "user" + username.getText().toString();
                String checkPassword = "password" + username.getText().toString();

                if (username.getText().toString().equalsIgnoreCase(login.getString(checkUser, null))
                        && password.getText().toString().equals(login.getString(checkPassword, null))) {

                    Intent intent = new Intent(loginActivity.this, listActivity.class);
                    intent.putExtra("currentUser", username.getText().toString());
                    startActivity(intent);
                }

            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(loginActivity.this, listActivity.class);
                intent.putExtra("testKey", SHARED_PREFS_KEY);
                startActivity(intent);


            }
        });

    }
}

