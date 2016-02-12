package fi.metropolia.nguyen.chanh.projectgasolineconsumption;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class SecondDataActivity extends AppCompatActivity implements View.OnClickListener{
    Button back;
    Button update;

    EditText kiloMeter;
    EditText fuelAdd;

    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy HH:mm");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_data);

        back = (Button) findViewById(R.id.back);
        update = (Button) findViewById(R.id.update);

        kiloMeter = (EditText) findViewById(R.id.kiloMeter);
        fuelAdd = (EditText) findViewById(R.id.fuelAdd);

        back.setOnClickListener(this);
        update.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==back.getId()){
            finish();
        }else{
            try {

                SharedPreferences gasolineData = getSharedPreferences("gasolineData", Activity.MODE_PRIVATE);
                SharedPreferences.Editor gasEdit = gasolineData.edit();

                LinkedHashSet<String> date = new LinkedHashSet<>();
                date.add("km:" + Double.parseDouble(kiloMeter.getText().toString()));
                date.add("litre:" + Double.parseDouble(fuelAdd.getText().toString()));

                gasEdit.putStringSet(sdf.format(new Date()), date);

                gasEdit.apply();
            }catch (Exception e){
                Toast.makeText(this,"Fuel and Kilometer can be NUMBER only!", Toast.LENGTH_LONG).show();
            }
        }
    }
}
