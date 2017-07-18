package com.example.suraj.orhrdso;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.HashMap;

public class restHouseForm extends AppCompatActivity implements View.OnClickListener {
    private Spinner spinner;
    private Spinner spinner1;
    private Spinner spinner2;
    private Spinner spinner3;
    private Spinner spinner4;
    private RadioGroup r;
    private RadioGroup r1;
    private EditText OfficerMobileEt;
    private EditText OfficeMobileEt;
    private EditText EmailEdt;
    private EditText PersonEt;
    private Button Submitbtn;
    private Button CancleBtn;
    private static final String Form_URL = "http://192.168.43.106/finalRHForm.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest_house_form);

        CancleBtn = (Button) findViewById(R.id.btnCancle);
        Submitbtn = (Button) findViewById(R.id.btnSubmit);
        r = (RadioGroup) findViewById(R.id.R);
        r1 = (RadioGroup) findViewById(R.id.R1);
        OfficerMobileEt = (EditText) findViewById(R.id.etOfficerMobile);
        OfficeMobileEt = (EditText) findViewById(R.id.etOfficeMobile);
        EmailEdt = (EditText) findViewById(R.id.edtEmail);
        PersonEt = (EditText) findViewById(R.id.edtPersons);


        String arr[] = {"Select", "ORH-RDSO", "X", "Y", "Z"};
        String arr2[] = {"Select", "Official A/C-Officer on official duty", "Private A/C-Officer on leave OR for family members/dependents as per pass rule", "Guest A/C Guest of Rly. officer"};
        String arr3[] = {"Select", "Dhiraj Srivastava", "X", "Y", "Z"};
        String arr4[] = {"Select", "EDRT", "Director/HQ", "Director/Lab", "Director/OT-III"};
        String arr5[] = {"Select", "Carriage", "X", "Y/Lab", "Z"};

        spinner = (Spinner) findViewById(R.id.s1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(restHouseForm.this, android.R.layout.simple_list_item_1, arr);
        spinner.setAdapter(adapter);
        spinner1 = (Spinner) findViewById(R.id.s2);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(restHouseForm.this, android.R.layout.simple_list_item_1, arr2);
        spinner1.setAdapter(adapter1);
        spinner2 = (Spinner) findViewById(R.id.s3);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(restHouseForm.this, android.R.layout.simple_list_item_1, arr3);
        spinner2.setAdapter(adapter2);
        spinner3 = (Spinner) findViewById(R.id.s4);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(restHouseForm.this, android.R.layout.simple_list_item_1, arr4);
        spinner3.setAdapter(adapter3);
        spinner4 = (Spinner) findViewById(R.id.s5);
        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(restHouseForm.this, android.R.layout.simple_list_item_1, arr5);
        spinner4.setAdapter(adapter4);


        r.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()

        {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.sound) {
                    Toast.makeText(getApplicationContext(), "Self",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Other Guest",
                            Toast.LENGTH_SHORT).show();
                }
            }

        });
        r1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                if (null != rb && checkedId > -1) {
                    Toast.makeText(restHouseForm.this, rb.getText(), Toast.LENGTH_SHORT).show();
                }

            }
        });
        Submitbtn.setOnClickListener(this);
        CancleBtn.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if(v == Submitbtn){
            restHouseForm();
        }
        switch (v.getId()){
            case R.id.btnCancle:
                startActivity(new Intent(this,RestOrVehi.class));
                break;
        }

    }
    private void restHouseForm() {
        String BookedFor=spinner.getSelectedItem().toString();
        String BookedOn=spinner1.getSelectedItem().toString();
        String Official=spinner2.getSelectedItem().toString();
        String Odesignation=spinner3.getSelectedItem().toString();
        String officerMobile=OfficerMobileEt.getText().toString();
        String officeMobile=OfficeMobileEt.getText().toString();
        String Org=spinner4.getSelectedItem().toString();
        String Email=EmailEdt.getText().toString();
        String personNo=PersonEt.getText().toString();

        restHouse(BookedFor,BookedOn,Official,Odesignation,officerMobile,officeMobile,Org,Email,personNo);
    }

    private void restHouse(String BookedFor, String BookedOn, String Official, String Odesignation,
                          String officerMobile,String officeMobile,String Org,String Email,String personNo ) {
        class Restform extends AsyncTask<String, Void, String> {
            ProgressDialog loading;
            RegisterUserClass ruc = new RegisterUserClass();


            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(restHouseForm.this, "Please Wait",null, true, true);
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
                data.put("BookedFor",params[0]);
                data.put("BookedOn",params[1]);
                data.put("Official",params[2]);
                data.put("OfficialDesignation",params[3]);
                data.put("officerMobile",params[4]);
                data.put("officeMobile",params[5]);
                data.put("Orginization",params[6]);
                data.put("Email",params[7]);
                data.put("personNo",params[8]);

                String result = ruc.sendPostRequest(Form_URL,data);

                return  result;
            }
        }

        Restform ru = new Restform();
        ru.execute(BookedFor,BookedOn,Official,Odesignation,officerMobile,officeMobile,Org,Email,personNo);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("Logout");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        Toast.makeText(this, "User Logout", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this,MainActivity.class));
        return super.onOptionsItemSelected(item);
    }
}