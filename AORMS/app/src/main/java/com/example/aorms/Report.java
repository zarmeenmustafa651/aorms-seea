package com.example.aorms;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.Random;

public class Report extends AppCompatActivity {
    TextView textview;
    GraphView graph;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report);
        Toolbar myToolbar = findViewById(R.id.tToolbar);
         textview = findViewById(R.id.title);
        setSupportActionBar(myToolbar);
        myToolbar.setTitle("                REPORTS");
        graph = (GraphView) findViewById(R.id.graph);
        //Button button = findViewById(R.id.addButton);
        graph.setVisibility(View.VISIBLE);

        try {
            DataPoint[] dp;
            LineGraphSeries <DataPoint> series;
            Random r = new Random();
            dp=new DataPoint[4];
            for(int i=2016,j=0;i<2020;i++){
                dp[j]=new DataPoint(i,r.nextInt((100000 - 20000) + 1) + 20000);
                j++;
            }
            series = new LineGraphSeries< >(dp);
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
        DataPoint[] dp;
        LineGraphSeries <DataPoint> series;
        Random r = new Random();
        switch(item.getItemId()){
            case R.id.item1:
                graph = (GraphView) findViewById(R.id.graph);
                //Button button = findViewById(R.id.addButton);
                graph.setVisibility(View.VISIBLE);
                dp=new DataPoint[30];
                for(int i=1,j=0;i<31;i++){
                    dp[j]=new DataPoint(i,r.nextInt((10000 - 2000) + 1) + 2000);
                    j++;
                }
                series = new LineGraphSeries< >(dp);
                graph.addSeries(series);
                textview.setText("Daily Sales Report");
                return true;
            case R.id.item2:
                graph = (GraphView) findViewById(R.id.graph);
                //Button button = findViewById(R.id.addButton);
                graph.setVisibility(View.VISIBLE);
                dp=new DataPoint[12];
                for(int i=1,j=0;i<13;i++){
                    dp[j]=new DataPoint(i,r.nextInt((100000 - 20000) + 1) + 20000);
                    j++;
                }
                series = new LineGraphSeries< >(dp);
                graph.addSeries(series);
                textview.setText("Monthly Sales Report");
                return true;
            case R.id.item3:
                graph = (GraphView) findViewById(R.id.graph);
                //Button button = findViewById(R.id.addButton);
                graph.setVisibility(View.VISIBLE);
                dp=new DataPoint[4];
                for(int i=2016,j=0;i<2020;i++){
                    dp[j]=new DataPoint(i,r.nextInt((100000 - 20000) + 1) + 20000);
                    j++;
                }
                series = new LineGraphSeries< >(dp);
                graph.addSeries(series);
                textview.setText("Yearly Sales Report");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
