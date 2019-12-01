package com.example.aorms;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Pakistani extends AppCompatActivity {
    EditText b,a;
    String s,t,c,d;
    Button btn1;
    DatabaseReference dishDatabase;
    Spinner dishName; //ctype
    Spinner dishType; //ctype2
    EditText price; //price
    EditText expTime; //time
    EditText descp; //Description
    String  name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pakistani);
        Spinner x;
        String UTypes[]={"Chicken Bombay Biryani",
                "Chicken Daleem",
                "Chinioti Mutton Kunna",
                "Chicken Achar Gosht",
                "Mutton Taka Tak" +
                "Chicken Frontier Karahi",
                "Daal Gosht (Mutton)",
                "Batair Karahi",
                "Aloo Ki Bhujia",
                "Shahi Mutton Kofta",
                "Channa Curry",
                "Suji Ka Halwa with Puri"};
        ArrayAdapter<String> ad= new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,UTypes);
        x=findViewById(R.id.ctype);
        x.setAdapter(ad);
        b=findViewById(R.id.Dname);

        dishDatabase = FirebaseDatabase.getInstance().getReference().child("Dish");
        dishName = (Spinner) findViewById(R.id.ctype);
        dishType = (Spinner) findViewById(R.id.ctype2);
        price = (EditText) findViewById(R.id.price);
        expTime = (EditText) findViewById(R.id.time);
        descp = (EditText) findViewById(R.id.Description);

         name = dishName.getSelectedItem().toString();

        x.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                s= (String) parent.getItemAtPosition(position).toString();
                b.setText(s);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        b.setText("Please Type");

        Spinner y;
        String Types[]={"Please Select","Pakistani", "Chinese", "Continental", "Other"};
        ArrayAdapter<String> ad1= new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,Types);
        y=findViewById(R.id.ctype2);
        y.setAdapter(ad1);


        a=findViewById(R.id.Dtype);

        y.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                t= (String) parent.getItemAtPosition(position).toString();
                a.setText(t);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        a.setText("Please Type");

        btn1 = findViewById(R.id.next);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Pakistani.this, AddIngrediante.class);
                i.putExtra("DishName",name);
                startActivity(i);
            }
        });

    }
    public void AddDish(View v)
    {

        String type = dishType.getSelectedItem().toString();
        String pri = price.getText().toString();
        String time = expTime.getText().toString();

        int val = Integer.parseInt(pri);
        int val2= Integer.parseInt(time);

        Dish dish =  new Dish(name,type,val,val2);

        String id = dishDatabase.push().getKey();
        dishDatabase.child(id).setValue(dish);
    }
}
