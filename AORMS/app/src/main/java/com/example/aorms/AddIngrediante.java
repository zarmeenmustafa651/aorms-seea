package com.example.aorms;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddIngrediante extends AppCompatActivity {
    EditText a,b;
    String s;
    Button Btn, goToMainPage;
    Spinner ingredientName; // addinventory
    EditText quantity;  // Q
    EditText threshold; // T
    DatabaseReference ingredientsDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ingrediante);
        Spinner x;
        String UTypes[]={"Chicken",
                "Spices",
                "Pepper",
                "Salt",
                "Tomato",
                "Pulses",
                "Rice",
                "Oil",
                "Fruits",
                "Vegetables","Other"};
        ArrayAdapter<String> ad= new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,UTypes);
        x=findViewById(R.id.IngName);
        x.setAdapter(ad);

        Intent i = getIntent();
        String DishName = i.getStringExtra("DishName"); // getting dish name from previous intent

        ingredientsDatabase = FirebaseDatabase.getInstance().getReference().child(DishName);  // will make branch according to dish name
        ingredientName = (Spinner) findViewById(R.id.addinventory);
        quantity = (EditText) findViewById(R.id.Q);
        threshold = (EditText) findViewById(R.id.T);

        x.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                s= (String) parent.getItemAtPosition(position).toString();
                b=findViewById(R.id.IngrediantName);
                b.setText(s);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        Btn=findViewById(R.id.Back);
        Btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AddIngrediante.this, Pakistani.class);
                startActivity(i);
            }
        });

    }
    public void addIngredientInDatabase(View v)
    {
        String IngredientName = ingredientName.getSelectedItem().toString();
        String Quantity = quantity.getText().toString();

        int val = 0;
        val = Integer.parseInt(Quantity);

        AddDishIngredients addDishIngredients = new AddDishIngredients(IngredientName,val);
        String id = ingredientsDatabase.push().getKey();
        ingredientsDatabase.child(id).setValue(addDishIngredients);
        Toast.makeText(this,"Ingredient Added",Toast.LENGTH_SHORT).show();

    }

    public void GOBackToAdderPage(View v)
    {
        Intent myIntent =  new Intent(AddIngrediante.this,Adder.class);
        startActivity(myIntent);
        finish();
    }
}

