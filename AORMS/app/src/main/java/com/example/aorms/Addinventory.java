package com.example.aorms;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Addinventory extends AppCompatActivity {
    EditText b;
    String s;
    Spinner ingredientName; // addinventory
    EditText quantity;  // Q
    EditText threshold; // T
    DatabaseReference ingredientsDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addinventory);
        Spinner x;
        String UTypes[]={"Chili",
                "Pepper",
                "Salt",
                "Rice",
                "Chicken",
                "Beans",
                "Coconut",
                "Sweeteners",
                "Fruits",
                "Vegetables","Other"};
        ArrayAdapter<String> ad= new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,UTypes);
        x=findViewById(R.id.addinventory);
        x.setAdapter(ad);

        ingredientsDatabase = FirebaseDatabase.getInstance().getReference().child("Ingredients");
        ingredientName = (Spinner) findViewById(R.id.addinventory);
        quantity = (EditText) findViewById(R.id.Q);
        threshold = (EditText) findViewById(R.id.T);


        b=findViewById(R.id.i);
        b.setText("Please Type");
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
        if(b.equals("Other"))
        {
            b.setText("TypeHere");
        }
    }

    public void addIngredientInDatabase(View v)
    {
        String IngredientName = ingredientName.getSelectedItem().toString();
        String Quantity = quantity.getText().toString();
        String Threshold = threshold.getText().toString();
        int val = 0;
        val = Integer.parseInt(Quantity);
        int val2 = 0;
        val2 = Integer.parseInt(Threshold);

        Ingredient ingredient = new Ingredient(IngredientName,val,val2);
        String id = ingredientsDatabase.push().getKey();
        ingredientsDatabase.child(id).setValue(ingredient);

    }

}
