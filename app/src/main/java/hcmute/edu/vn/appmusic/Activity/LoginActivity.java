package hcmute.edu.vn.appmusic.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import hcmute.edu.vn.appmusic.R;

public class LoginActivity extends AppCompatActivity {
    EditText txtUserName, txtPassword;
    Button btnSignIn;
    private Button btnfacebook, btngmail;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Ánh xạ
        txtUserName = findViewById(R.id.edUserName);
        txtPassword = findViewById(R.id.edPassword);
        btnSignIn = findViewById(R.id.btnSignIn);
        btnfacebook = findViewById(R.id.btnfacebook);
        btngmail = findViewById(R.id.sign_in_button);

        TextView signUp_text = findViewById(R.id.tvSignUp);
        TextView Forgetpass = findViewById(R.id.tvForgetpass);

        //Khi thao tác với các nút
        //Đăng ký
        signUp_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });
        //quên mật khẩu
        Forgetpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forgetPass();
            }
        });
        //đăng nhập thường
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
        //đăng nhập bằng facebook
        btnfacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                facebook();
            }
        });
        //đăng nhập bằng gmail
        btngmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gmail();
            }
        });
    }

    private void gmail() {
    }

    private void facebook() {
    }

    private void forgetPass() {
    }

    private void login() {
    }

    private void signUp() {
    }


}
