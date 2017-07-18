package com.example.suraj.orhrdso;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class RestOrVehi extends AppCompatActivity implements View.OnClickListener{
    Button RestHousebt,VehicalBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest_or_vehi);
        RestHousebt=(Button)findViewById(R.id.btnRestHouse);
        VehicalBt=(Button)findViewById(R.id.btnVehical);



        RestHousebt.setOnClickListener(this);
        VehicalBt.setOnClickListener(this);



    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnRestHouse:
                startActivity(new Intent(this,restHouseForm.class));
                break;
            case R.id.btnVehical:
                startActivity(new Intent(this,VehicalForm.class));
                break;

        }

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
