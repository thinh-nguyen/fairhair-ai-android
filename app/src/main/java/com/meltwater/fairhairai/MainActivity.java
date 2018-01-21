package com.meltwater.fairhairai;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.meltwater.fairhairai.persistence.AppDatabase;
import com.meltwater.fairhairai.search.view.SearchActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button searchButton = (Button) findViewById(R.id.button);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                //MainActivity.this.overridePendingTransition(R.anim.activity_right_to_left_enter, R.anim.activity_right_to_left_exit);
                MainActivity.this.startActivityForResult(intent, 0);
            }
        });
    }
}
