package com.example.firstapp.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.firstapp.MainActivity;
import com.example.firstapp.R;
import com.example.firstapp.join.RegisterActivity;

import org.json.JSONException;
import org.json.JSONObject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG="LoginActivity";

    private EditText login_email,login_password;
    private Button login_button,join_button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login_email = findViewById(R.id.login_email);
        login_password = findViewById(R.id.login_password);

        join_button = findViewById(R.id.join_button);
        join_button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        login_button = findViewById(R.id.login_button);
        login_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String UserEmail = login_email.getText().toString();
                String UserPwd = login_password.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>(){

                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");

                            if(success){

                                String UserEmail = jsonObject.getString("UserEmail");
                                String UserPwd = jsonObject.getString("UserPwd");
                                String UserName = jsonObject.getString("UserName");

                                Toast.makeText(getApplicationContext(), String.format("%s님 환영합니다.", UserName), Toast.LENGTH_SHORT).show();
                                Intent intent= new Intent(LoginActivity.this,MainActivity.class);

                                intent.putExtra("UserEmail", UserEmail);
                                intent.putExtra("UserPwd", UserPwd);
                                intent.putExtra("UserName", UserName);

                                startActivity(intent);

                            }else{
                                Toast.makeText(getApplicationContext(), "로그인에 실패하셨습니다.",Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                LoginRequest loginRequest = new LoginRequest(UserEmail, UserPwd, responseListener);
                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                queue.add(loginRequest);
            }
        });

        Log.e(TAG,"Log.e - LoginActivity");

    }
}
