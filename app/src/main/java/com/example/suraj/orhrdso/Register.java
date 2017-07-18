package com.example.suraj.orhrdso;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.HashMap;

public class Register extends AppCompatActivity implements View.OnClickListener{

    private EditText editTextName;
    private EditText editTextPassword;
    private EditText editTextEmail;
    private EditText editTextMobile;
    private Spinner spinner;
    private Spinner spinner1;

    private Button buttonRegister;
    private Button BackPage;



    private static final String REGISTER_URL = "http://192.168.43.106/finalRegistration.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editTextName = (EditText) findViewById(R.id.etOfficerName);
        editTextEmail = (EditText) findViewById(R.id.etEmail);
        editTextPassword = (EditText) findViewById(R.id.etPassword);
        editTextMobile = (EditText) findViewById(R.id.etMobile);

        buttonRegister = (Button) findViewById(R.id.bt_reg);
        BackPage = (Button) findViewById(R.id.btPage);



        String arr[] = {"Select","Testing", "QA/Civil", "QA/Mech", "B&S", "Psycho-Tech", "QA/S&T/LKO","Stores","Wagon","TMM","Track"};
        String arr2[] = {"Select","EDRT", "Director/HQ", "Director/Lab", "Director/OT-III","Joint Director","ED/QA/Civil","Dir/QAC/Tk","DD/P&C","DD/EF","XEN"};



        spinner = (Spinner) findViewById(R.id.sp1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Register.this, android.R.layout.simple_list_item_1, arr);
        spinner.setAdapter(adapter);
        spinner1 = (Spinner) findViewById(R.id.sp2);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(Register.this, android.R.layout.simple_list_item_1, arr2);
        spinner1.setAdapter(adapter1);

        buttonRegister.setOnClickListener(this);
        BackPage.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if(v == buttonRegister){
            registerUser();
        }
        switch (v.getId()){
            case R.id.bt_reg:

                break;
            case R.id.btPage:
                startActivity(new Intent(this,MainActivity.class));

                break;



        }
    }

    private void registerUser() {
        String officerName = editTextName.getText().toString().trim().toLowerCase();
        String directorate=spinner.getSelectedItem().toString();
        String designation=spinner1.getSelectedItem().toString();
        String email = editTextEmail.getText().toString().trim().toLowerCase();
        String password = editTextPassword.getText().toString().trim().toLowerCase();
        String mobile = editTextMobile.getText().toString().trim().toLowerCase();

        register(officerName,directorate,designation,email,password,mobile);
    }

    private void register(String officerName, String directorate, String designation, String email,String password,String mobile) {
        class RegisterUser extends AsyncTask<String, Void, String>{
            ProgressDialog loading;
            RegisterUserClass ruc = new RegisterUserClass();


            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Register.this, "Please Wait",null, true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(String... params) {

                HashMap<String, String> data = new HashMap<String,String>();
                data.put("officerName",params[0]);
                data.put("directorate",params[1]);
                data.put("designation",params[2]);
                data.put("email",params[3]);
                data.put("password",params[4]);
                data.put("mobile",params[5]);

                String result = ruc.sendPostRequest(REGISTER_URL,data);

                return  result;
            }
        }

        RegisterUser ru = new RegisterUser();
        ru.execute(officerName,directorate,designation,email,password,mobile);
    }
}