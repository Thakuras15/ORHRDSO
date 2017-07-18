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
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.HashMap;

public class VehicalForm extends AppCompatActivity implements View.OnClickListener{

    private Spinner spinner2;
    private Spinner spinner3;
    private RadioGroup r2;
    private RadioGroup r3;
    private RadioGroup r4;
    private EditText OfficerNameEt;
    private EditText MobileEt;
    private EditText EmailEt;
    private EditText PurposeEt;
    private EditText VisitPlaceEt;
    private EditText TimeEt;
    private EditText AddressEt;
    private EditText DateEt;

    private Button Submitbtn;
    private Button CancleBtn;
    private static final String VForm_URL = "http://192.168.43.106/finalVehicalForm.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehical_form);
        r2 = (RadioGroup) findViewById(R.id.R3);
        r3 = (RadioGroup) findViewById(R.id.R4);
        r4 = (RadioGroup) findViewById(R.id.R5);
        CancleBtn=(Button)findViewById(R.id.btnCancleV) ;
        Submitbtn=(Button)findViewById(R.id.btnSubmitV) ;
        OfficerNameEt=(EditText)findViewById(R.id.etOfficerName);
        MobileEt=(EditText)findViewById(R.id.etmobile);
        EmailEt=(EditText)findViewById(R.id.etemail);
        PurposeEt=(EditText)findViewById(R.id.etPurpose);
        VisitPlaceEt=(EditText)findViewById(R.id.etVisitPlace);
        TimeEt=(EditText)findViewById(R.id.ettime);
        AddressEt=(EditText)findViewById(R.id.etAddres);
        DateEt=(EditText)findViewById(R.id.etDate);



        String arr[] = {"Select","Testing", "QA/Civil", "QA/Mech", "B&S", "Psycho-Tech", "QA/S&T/LKO","Stores","Wagon","TMM","Track"};
        String arr2[] = {"Select","EDRT", "Director/HQ", "Director/Lab", "Director/OT-III","Joint Director","ED/QA/Civil","Dir/QAC/Tk","DD/P&C","DD/EF","XEN"};



        spinner2 = (Spinner) findViewById(R.id.sp3);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(VehicalForm.this, android.R.layout.simple_list_item_1, arr);
        spinner2.setAdapter(adapter);
        spinner3 = (Spinner) findViewById(R.id.sp4);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(VehicalForm.this, android.R.layout.simple_list_item_1, arr2);
        spinner3.setAdapter(adapter1);

        r2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()

        {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.self) {
                    Toast.makeText(getApplicationContext(), "Self",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Other Guest",
                            Toast.LENGTH_SHORT).show();
                }
            }

        });
        r3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()

        {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.selfBooking) {
                    Toast.makeText(getApplicationContext(), "Self Booking",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Other Vehical Booking",
                            Toast.LENGTH_SHORT).show();
                }
            }

        });
        r4.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()

        {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.yes) {
                    Toast.makeText(getApplicationContext(), "Yes",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "No",
                            Toast.LENGTH_SHORT).show();
                }
            }

        });

        Submitbtn.setOnClickListener(this);
        CancleBtn.setOnClickListener(this);
    }



    @Override
    public void onClick(View view) {
        if(view==Submitbtn){
            VehicalForm();
        }
        switch (view.getId()){
            case R.id.btnCancleV:
                startActivity(new Intent(this,RestOrVehi.class));
        }

    }

    private void VehicalForm() {
        String OfficerName=OfficerNameEt.getText().toString();
        String directorate=spinner2.getSelectedItem().toString();
        String designation=spinner3.getSelectedItem().toString();
        String mobile=MobileEt.getText().toString();
        String Email=EmailEt.getText().toString();
        String PurposeOfVisit=PurposeEt.getText().toString();
        String PlaceForVisit=VisitPlaceEt.getText().toString();
        String Time=TimeEt.getText().toString();
        String Address=AddressEt.getText().toString();
        String Date=DateEt.getText().toString();
        Vehical(OfficerName,directorate,designation,mobile,Email, PurposeOfVisit,PlaceForVisit, Time,Address,Date);
    }

    private void Vehical(String OfficerName, String directorate, String designation, String mobile,
                           String Email,String PurposeOfVisit,String PlaceForVisit,String Time,String Address,String Date ) {
        class Vehiform extends AsyncTask<String, Void, String> {
            ProgressDialog loading;
            RegisterUserClass ruc = new RegisterUserClass();


            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(VehicalForm.this, "Please Wait",null, true, true);
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
                data.put("OfficerName",params[0]);
                data.put("directorate",params[1]);
                data.put("designation",params[2]);
                data.put("mobile",params[3]);
                data.put("Email",params[4]);
                data.put("PurposeOfVisit",params[5]);
                data.put("PlaceForVisit",params[6]);
                data.put("Time",params[7]);
                data.put("Address",params[8]);
                data.put("Date",params[9]);

                String result = ruc.sendPostRequest(VForm_URL,data);

                return  result;
            }
        }

        Vehiform ru = new Vehiform();
        ru.execute(OfficerName,directorate,designation,mobile, Email,PurposeOfVisit,PlaceForVisit,Time,Address,Date);
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
