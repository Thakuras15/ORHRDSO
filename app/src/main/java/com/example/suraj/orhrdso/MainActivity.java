package com.example.suraj.orhrdso;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    public static final String Email = "email";

    public static final String Password = "password";

    private static final String LOGIN_URL = "http://192.168.43.106/finalLogin.php";
    private EditText editTextEmail;
    private EditText editTextPassword;

    private Button buttonLogin;
    private Button buttonReg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextEmail = (EditText) findViewById(R.id.Email);
        editTextPassword = (EditText) findViewById(R.id.etPassword);

        buttonLogin = (Button) findViewById(R.id.btnLogin);
        buttonReg = (Button) findViewById(R.id.bt_reg);


        buttonLogin.setOnClickListener(this);
        buttonReg.setOnClickListener(this);
    }


    private void login(){
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        userLogin(email,password);
    }

    private void userLogin(final String email, final String password){
        class UserLoginClass extends AsyncTask<String,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(MainActivity.this,"Please Wait",null,true,true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                if(s.equalsIgnoreCase("success")){
                    Intent intent = new Intent(MainActivity.this,RestOrVehi.class);
                    intent.putExtra(Email,email);
                    startActivity(intent);
                }else{
                    Toast.makeText(MainActivity.this,s,Toast.LENGTH_LONG).show();
                }
            }

            @Override
            protected String doInBackground(String... params) {
                HashMap<String,String> data = new HashMap<>();
                data.put("email",params[0]);
                data.put("password",params[1]);

                RegisterUserClass ruc = new RegisterUserClass();

                String result = ruc.sendPostRequest(LOGIN_URL,data);

                return result;
            }
        }
        UserLoginClass ulc = new UserLoginClass();
        ulc.execute(email,password);
    }

    @Override
    public void onClick(View v) {
        if(v == buttonLogin){
            login();
        }
        switch (v.getId()){
            case R.id.bt_reg:
                startActivity(new Intent(this,Register.class));
                break;
        }
    }
}