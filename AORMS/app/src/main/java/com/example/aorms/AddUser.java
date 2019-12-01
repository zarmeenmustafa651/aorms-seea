package com.example.aorms;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.appcompat.app.AppCompatActivity;

public class AddUser extends AppCompatActivity {
    EditText b;
    String s;
    FirebaseAuth auth;
    DatabaseReference addWaiter, addInventoryManager, addHallManager, addChef;
    Button add;
    Spinner designation;
    EditText name, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        Spinner x;
        String UTypes[]={"Inventory Manager", "Hall Manager", "Waiter", "Pakistani Chef", "Chinese Chef", "Continental Chef"};
        ArrayAdapter<String> ad= new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,UTypes);
        x=findViewById(R.id.type);
        x.setAdapter(ad);
        addChef = FirebaseDatabase.getInstance().getReference("Chef");
        addWaiter = FirebaseDatabase.getInstance().getReference("Waiter");
        addHallManager = FirebaseDatabase.getInstance().getReference("Hall Manger");
        addInventoryManager = FirebaseDatabase.getInstance().getReference("Inventory Manger");
        add = (Button) findViewById(R.id.aduser);  // Add button
        designation  = (Spinner) findViewById(R.id.type); // Designation
        name = (EditText) findViewById(R.id.userName); // Name of Employee
        password = (EditText) findViewById(R.id.Password); // password of Employee


        b=findViewById(R.id.UserName);
        b.setText("Designation");
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
    }

    public void AddUserDetails(View view) {
        String Name = name.getText().toString();
        String Password = password.getText().toString();
        String Desig = designation.getSelectedItem().toString();


        if(!TextUtils.isEmpty(Name) && !TextUtils.isEmpty(Password) && !TextUtils.isEmpty(Desig))
        {
            Employee employee = new Employee(Name,Password,Desig);

            if(Desig.equals("Waiter"))
            {
                String id = addWaiter.push().getKey();
                addWaiter.child(id).setValue(employee);

            }

            if(Desig.equals("Hall Manager"))
            {
                String id = addHallManager.push().getKey();
                addHallManager.child(id).setValue(employee);

            }

            if(Desig.equals("Inventory Manager"))
            {
                String id = addInventoryManager.push().getKey();
                addInventoryManager.child(id).setValue(employee);

            }

            if(Desig.equals("Chef"))
            {
                String id = addChef.push().getKey();
                addChef.child(id).setValue(employee);

            }
            Toast.makeText(this, "Employee Added", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this,"Fill are Spaces",Toast.LENGTH_SHORT).show();
        }
    }
}
