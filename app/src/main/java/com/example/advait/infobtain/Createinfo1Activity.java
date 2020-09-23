package com.example.advait.infobtain;

import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.cardview.widget.CardView;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Createinfo1Activity extends AppCompatActivity {
    EditText cr_name;
    EditText cr_phone;
    EditText cr_web;
    EditText cr_comp;
    EditText cr_desi;
    CardView create;
    DatabaseHelper myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createinfo1);
        cr_name=(EditText)findViewById(R.id.editText31);
        cr_phone=(EditText)findViewById(R.id.editText32);
        cr_web=(EditText)findViewById(R.id.editText33);
        cr_comp=(EditText)findViewById(R.id.editText34);
        cr_desi=(EditText)findViewById(R.id.editText35);
        create=(CardView)findViewById(R.id.cardView);
        final Context c=this;
        myDb=new DatabaseHelper(this);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(c,HomeActivity.class);
                String s1=Session_Variables.s_email;
                String s2=Session_Variables.s_pass;
                Session_Variables.s_name=cr_name.getText().toString();
                Session_Variables.s_phone=cr_phone.getText().toString();
                Session_Variables.s_web=cr_web.getText().toString();
                Session_Variables.s_comp=cr_comp.getText().toString();
                Session_Variables.s_desi=cr_desi.getText().toString();
                String s3=Session_Variables.s_name;
                String s4=Session_Variables.s_phone;
                String s5=Session_Variables.s_web;
                String s6=Session_Variables.s_comp;
                String s7=Session_Variables.s_desi;
                if((!s3.equals(""))&&(!s4.equals(""))&&(s4.length()==10)) {
                    boolean isInserted = myDb.insertData(s1, s2, s3, s4, s5, s6, s7);
                    if (isInserted == true) {
                        Toast.makeText(Createinfo1Activity.this, "Account Created!", Toast.LENGTH_LONG).show();
                        startActivity(i);
                    } else
                        Toast.makeText(Createinfo1Activity.this, "Phone Number already exists!", Toast.LENGTH_LONG).show();
                }
                else {

                    if (s3.equals("")&&s4.length()==10)
                        Toast.makeText(Createinfo1Activity.this, "Please enter a valid name!", Toast.LENGTH_LONG).show();

                    else if(s3.equals("")&&s4.length()!=10)
                        Toast.makeText(Createinfo1Activity.this, "Please enter a valid name and number!", Toast.LENGTH_LONG).show();
                     else if(s4.length()!=10)
                        Toast.makeText(Createinfo1Activity.this, "Please enter a valid number!", Toast.LENGTH_LONG).show();

                }
            }
        });
    }

}
