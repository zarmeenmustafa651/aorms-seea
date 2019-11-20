package com.example.aorms;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class Report extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report);
        Toolbar myToolbar = findViewById(R.id.tToolbar);
        setSupportActionBar(myToolbar);
        myToolbar.setTitle("                REPORTS");
        final GraphView graph = (GraphView) findViewById(R.id.graph);
        //Button button = findViewById(R.id.addButton);
        graph.setVisibility(View.VISIBLE);
        Integer firstInput_1=1, secondInput_1=2,firstInput_2=3, secondInput_2=4,firstInput_3=5, secondInput_3=6,firstInput_4=7, secondInput_4=8;

        try {
            LineGraphSeries <DataPoint> series = new LineGraphSeries< >(new DataPoint[] {
                    new DataPoint(0, 1),
                    new DataPoint(firstInput_1,secondInput_1),
                    new DataPoint(firstInput_2,secondInput_2),
                    new DataPoint(firstInput_3,secondInput_3),
                    new DataPoint(firstInput_4,secondInput_4)
            });
            graph.addSeries(series);
        } catch (IllegalArgumentException e) {
            Toast.makeText(Report.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu2,menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.item1:

                return true;
            case R.id.item2:

                return true;
            case R.id.item3:

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
