package com.example.advait.infobtain;

import android.content.Context;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.cardview.widget.CardView;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EditInfo extends AppCompatActivity {
    CardView edit1;
    EditText e_name;
    EditText e_phone;
    EditText e_web;
    EditText e_comp;
    EditText e_desi;
    DatabaseHelper myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_info);
        e_name=(EditText)findViewById(R.id.editText41);
        e_phone=(EditText)findViewById(R.id.editText42);
        e_web=(EditText)findViewById(R.id.editText43);
        e_comp=(EditText)findViewById(R.id.editText44);
        e_desi=(EditText)findViewById(R.id.editText45);
        e_name.setText(Session_Variables.s_name);
        e_phone.setText(Session_Variables.s_phone);
        e_web.setText(Session_Variables.s_web);
        e_comp.setText(Session_Variables.s_comp);
        e_desi.setText(Session_Variables.s_desi);
        edit1=(CardView)findViewById(R.id.cardView_edit);
        myDb=new DatabaseHelper(this);
        final Context c=this;
        edit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(c,HomeActivity.class);
                String s1=Session_Variables.s_email;
                String s2=Session_Variables.s_pass;
                Session_Variables.s_name=e_name.getText().toString();
                Session_Variables.s_phone=e_phone.getText().toString();
                Session_Variables.s_web=e_web.getText().toString();
                Session_Variables.s_comp=e_comp.getText().toString();
                Session_Variables.s_desi=e_desi.getText().toString();
                String s3=Session_Variables.s_name;
                String s4=Session_Variables.s_phone;
                String s5=Session_Variables.s_web;
                String s6=Session_Variables.s_comp;
                String s7=Session_Variables.s_desi;
                if((!s3.equals(""))&&(!s4.equals(""))&&(s4.length()==10)) {
                    try {
                        boolean updateData1 = myDb.updateData(s1, s3, s4, s5, s6, s7);
                        if (updateData1 == true) {
                            Toast.makeText(EditInfo.this, "Account Details Updated!", Toast.LENGTH_LONG).show();
                            startActivity(i);
                        } else
                            Toast.makeText(EditInfo.this, "Error in updating account.", Toast.LENGTH_LONG).show();
                    }
                    catch(Exception e)
                    {
                        Toast.makeText(EditInfo.this,""+ e+"", Toast.LENGTH_LONG).show();
                    }
                }
                else {
                    if(s4.length()!=10)
                        Toast.makeText(EditInfo.this, "Please enter a valid number!", Toast.LENGTH_LONG).show();
                    else if (s3.equals("")&&s4.length()==10)
                        Toast.makeText(EditInfo.this, "Please enter a valid name!.", Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(EditInfo.this, "Please enter a valid name and number!.", Toast.LENGTH_LONG).show();

            }
            }
        });
    }
}
