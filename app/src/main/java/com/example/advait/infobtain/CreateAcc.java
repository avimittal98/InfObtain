package com.example.advait.infobtain;

import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.cardview.widget.CardView;
import android.view.View;

public class CreateAcc extends AppCompatActivity {
    CardView signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_acc);
        signup=(CardView)findViewById(R.id.cardView);
        final Context c=this;
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(c,HomeActivity.class);
                startActivity(i);
            }
        });
    }
}
