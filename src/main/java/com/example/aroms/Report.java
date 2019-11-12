package com.example.aroms;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class Report extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report);
        Toolbar myToolbar = findViewById(R.id.tToolbar);
        setSupportActionBar(myToolbar);
        myToolbar.setTitle("                REPORTS");
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
