package hcmute.edu.vn.appmusic.Activity;

import hcmute.edu.vn.appmusic.R;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class RegistrationActivity extends AppCompatActivity {
    EditText ed_name, ed_email, ed_pass;
    Button bt_register;
    TextView tv_login;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        //ánh xạ
        ed_name = findViewById(R.id.name);
        ed_email = findViewById(R.id.email1);
        ed_pass = findViewById(R.id.pass1);
        bt_register = findViewById(R.id.btnSignUp);
        tv_login = findViewById(R.id.tvSignIn);

        // Khi thao tác với các nút
        bt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
