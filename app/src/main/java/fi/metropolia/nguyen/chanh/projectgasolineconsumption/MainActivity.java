package fi.metropolia.nguyen.chanh.projectgasolineconsumption;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button addData;
    Button history;
    Button updateCal;

    TextView averageText;
    TextView minimunText;
    TextView maximunText;
    TextView lastTimeText;

    NumberFormat formatterNum = new DecimalFormat("#0.00");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addData = (Button) findViewById(R.id.addData);
        history = (Button) findViewById(R.id.history);
        updateCal = (Button) findViewById(R.id.updateCal);
        addData.setOnClickListener(this);
        history.setOnClickListener(this);
        updateCal.setOnClickListener(this);

        averageText = (TextView) findViewById(R.id.averageText);
        minimunText = (TextView) findViewById(R.id.minimumText);
        maximunText = (TextView) findViewById(R.id.maximumText);
        lastTimeText = (TextView) findViewById(R.id.lastTimeText);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==addData.getId()){
            Intent secInt = new Intent(this, SecondDataActivity.class);
            startActivity(secInt);
        }else if(v.getId()==history.getId()){
            Intent thiInt = new Intent(this, ThirdHiistoryActivity.class);
            startActivity(thiInt);
        }else{
            SharedPreferences prefGasoline = getSharedPreferences("gasolineData", Activity.MODE_PRIVATE);
            Map<String, ?> mapGasoline = prefGasoline.getAll();

            double average = 0;
            double minimum = 0;
            double maximum = 0;
            double lastTime = 0;

            double sumFuel = 0;
            double sumKm = 0;

            double literTakeOut = 0;
            double kmTakeOut = 1;
            Set<String> fromSt;
            for (String st : mapGasoline.keySet()) {
                fromSt = (Set) mapGasoline.get(st);
                Iterator<String> itr = fromSt.iterator();
                while(itr.hasNext()){
                    String insideSetfromSt = itr.next();
                    if(insideSetfromSt.toLowerCase().contains("litre")){
                        literTakeOut = Double.parseDouble(insideSetfromSt.replaceAll("\\D", ""));
                        sumFuel += literTakeOut;
                    }else if(insideSetfromSt.toLowerCase().contains("km")){
                        kmTakeOut = Double.parseDouble(insideSetfromSt.replaceAll("\\D", ""));
                        sumKm += kmTakeOut;
                    }
                }

                if(minimum <= 0.00001 || minimum > 100*literTakeOut/kmTakeOut){
                    minimum = 100*literTakeOut/kmTakeOut;
                }
                if(maximum < 100*literTakeOut/kmTakeOut){
                    maximum = 100*literTakeOut/kmTakeOut;
                }
                lastTime = 100*literTakeOut/kmTakeOut;
            }
            System.out.println("chanh test");
            average = 100*sumFuel/sumKm;
            averageText.setText(""+formatterNum.format(average));
            minimunText.setText(""+formatterNum.format(minimum));
            maximunText.setText(""+formatterNum.format(maximum));
            lastTimeText.setText(""+formatterNum.format(lastTime));
        }
    }
}
