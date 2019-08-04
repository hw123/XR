package com.jacob.xrsample;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_ID = "com.jacob.xrsample.ID";

    private Button loginBtn;
    private EditText idInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginBtn = findViewById(R.id.login_btn);
        idInput = findViewById(R.id.input_txt);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = idInput.getText().toString();
                if(str.isEmpty()) {
                    Toast.makeText(MainActivity.this, R.string.input_your_id_noty, Toast.LENGTH_SHORT);
                    return;
                }

                Intent intent = new Intent(MainActivity.this, SplashActivity.class);
                intent.putExtra(EXTRA_ID, str);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        if(AuthenticationUtil.getAuthenticationUtil().isReady() == false) {
            if(AuthenticationUtil.getAuthenticationUtil().loadSavedAvailableActorsInfo() == false) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(R.string.alert_dlg_title_error);
                builder.setMessage(R.string.should_check_actors_info_base_data);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        }
        super.onResume();
    }
}
