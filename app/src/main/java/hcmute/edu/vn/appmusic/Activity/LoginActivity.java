package hcmute.edu.vn.appmusic.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import hcmute.edu.vn.appmusic.R;

public class LoginActivity extends AppCompatActivity {
    EditText txtUserName, txtPassword;
    Button btnSignIn;
    private Button btnfacebook, btngmail;

    private FirebaseAuth mAuth;


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
        Intent intent =new Intent(this, GoogleAccount.class);
        intent.putExtra("getToken",false);
        startActivityForResult(intent,10);
    }

    private void facebook() {
        Intent intent =new Intent(this, FacebookAccount.class);
        intent.putExtra("getToken",false);
        startActivityForResult(intent,10);
    }

    private void forgetPass() {
    }
    //đăng nhập thường
    private void login() {
        //final để đánh dấu đây là biến hằng có giá trị không thay đổi sau khi khởi tạo
        final String UserName = txtUserName.getText().toString();
        String PassWord = txtPassword.getText().toString();

        //kiểm tra dữ liệu người dùng nhập vào
        if(UserName.isEmpty()){
            txtUserName.setError("Vui lòng nhập tên đăng nhập!");
            txtUserName.requestFocus(); // đưa trỏ tới ô cần nhập
        }else if(PassWord.isEmpty()){
            txtPassword.setError("Vui lòng nhập password");
            txtPassword.requestFocus();
        }else if(UserName.isEmpty() && PassWord.isEmpty()){
            Toast.makeText(LoginActivity.this, "Các ô không được để trống!", Toast.LENGTH_SHORT).show();
        }
        //khi nhập đầy đủ thông tin
        else if (!(UserName.isEmpty() && PassWord.isEmpty())){
            mAuth.signInWithEmailAndPassword(UserName,PassWord).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(!task.isSuccessful()){
                        Toast.makeText(LoginActivity.this, "Đăng nhập thất bại, vui lòng kiểm tra lại!", Toast.LENGTH_SHORT).show();
                    }
                    //đăng nhập thành công
                    else{
                        Toast.makeText(LoginActivity.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        else{
            Toast.makeText(LoginActivity.this, "Có lỗi xảy ra!", Toast.LENGTH_SHORT).show();
        }
    }
    //Hàm đăng ký
    private void signUp() {
        startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));
        overridePendingTransition(R.anim.slide_out_left, R.anim.slide_in_right);
        finish();
    }

}
