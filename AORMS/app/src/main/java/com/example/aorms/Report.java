package com.example.aorms;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.example.aorms.ui.main.ReportData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class Report extends AppCompatActivity {

    GraphView graph;
    DatabaseReference dbRef, rep;
    float bill_value;
    Date date;
    DataPoint[] dp;
    ;
    List<Date> D = new ArrayList<Date>();
    List<Integer> B = new ArrayList<>();
    List<ReportData> list= new ArrayList<ReportData>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report);
        Toolbar myToolbar = findViewById(R.id.tToolbar);
        setSupportActionBar(myToolbar);
        myToolbar.setTitle("                REPORTS");
        graph = (GraphView) findViewById(R.id.graph);
        //Button button = findViewById(R.id.addButton);
        graph.setVisibility(View.VISIBLE);

        dbRef = FirebaseDatabase.getInstance().getReference().child("Orders");
        rep = FirebaseDatabase.getInstance().getReference().child("Report");

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
                OrderModel m = dataSnapshot.getValue(OrderModel.class);
                bill_value= m.getBill();
                date= Calendar.getInstance().getTime();
                addvalues();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        LineGraphSeries <DataPoint> series;
        DataPoint[] dparray= getvalues();
        if(dparray!=null){
            graph = (GraphView) findViewById(R.id.graph);
            //Button button = findViewById(R.id.addButton);
            graph.setVisibility(View.VISIBLE);
            series = new LineGraphSeries< >(dparray);
            graph.addSeries(series);
        }

    }
    public  void addvalues(){
        ReportData d= new ReportData(bill_value, date);
        String id = rep.push().getKey();
        rep.child(id).setValue(d);
    }
    public DataPoint[] getvalues(){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                DataSnapshot needRef = dataSnapshot.child("Report");
                int i=0;
                for (DataSnapshot snap : needRef.getChildren())
                {
                    String temptime = snap.child("Time").getValue().toString();
                    int b = Integer.valueOf(snap.child("Bill").getValue().toString());
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                    Date d = null;
                    try {
                        d = dateFormat.parse(temptime);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    D.add(d);
                    B.add(b);
                    i++;
                }
                dp = new DataPoint[i];
                for (int j = 0; j < i; i++) {
                    dp[i]= new DataPoint(D.get(j),B.get(j));
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });







        /*
        rep.orderByChild("Bill");
        rep.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    ReportData r1 = postSnapshot.getValue(ReportData.class);
                    list.add(r1);
                }

            }
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        int j= list.size();
        float b;
        Date dc;
        dp = new DataPoint[j];
        for (int i = 0; i < list.size(); i++) {
            b= list.get(i).getbill();
            System.out.println(b);
            dc= list.get(i).getdate();
            System.out.println(dc);
            dp[i]= new DataPoint(dc,b);
        }

         */
        return dp;
    }
    public void btn(View view) {
        Intent i = new Intent(this, Login.class);
        startActivity(i);
    }
}
