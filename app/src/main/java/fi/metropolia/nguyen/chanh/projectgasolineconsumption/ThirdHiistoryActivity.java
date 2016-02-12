package fi.metropolia.nguyen.chanh.projectgasolineconsumption;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ThirdHiistoryActivity extends AppCompatActivity implements View.OnClickListener{
    List<String> gasList = new ArrayList<String>();

    ListView lv;
    Button backMain;
    Button cleearData;
    Button updateBut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_hiistory);

        backMain = (Button) findViewById(R.id.backMain);
        cleearData = (Button) findViewById(R.id.clearHistory);
        updateBut = (Button) findViewById(R.id.updateData);
        backMain.setOnClickListener(this);
        cleearData.setOnClickListener(this);
        updateBut.setOnClickListener(this);

        lv = (ListView) findViewById(R.id.historyListView);
    }

    @Override
    public void onClick(View v) {
        SharedPreferences prefGasoline = getSharedPreferences("gasolineData", Activity.MODE_PRIVATE);
        if(v.getId()==backMain.getId()) {
            finish();
        }else if(v.getId()==cleearData.getId()){
            SharedPreferences.Editor gasEdit = prefGasoline.edit();
            gasEdit.clear();
            gasEdit.apply();
        }else{
            gasList.clear();
            Map<String, ?> mapGasoline = prefGasoline.getAll();
            for(String st: mapGasoline.keySet()){
                String gotoList = st + " " + mapGasoline.get(st);
                gasList.add(gotoList);
            }
            ArrayAdapter<String> gasAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, gasList);
            lv.setAdapter(gasAdapter);
        }
    }
}
