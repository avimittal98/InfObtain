package com.example.advait.infobtain;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.cardview.widget.CardView;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {
    CardView create;
    EditText email;
    EditText pass;
    EditText c_pass;
    String email1,pass1,c_pass1;
    DatabaseHelper myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        create=(CardView) findViewById(R.id.cardView);
        email=(EditText)findViewById(R.id.editText21);
        pass=(EditText)findViewById(R.id.editText22);
        c_pass=(EditText)findViewById(R.id.editText23);
        email.setText("");
        pass.setText("");
        c_pass.setText("");
        final Context c=this;
        myDb=new DatabaseHelper(this);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email1=email.getText().toString();
                pass1=pass.getText().toString();
                c_pass1=c_pass.getText().toString();
                Intent i=new Intent(c,Createinfo1Activity.class);
                if(pass1.equals(c_pass1)&&(!email1.equals(""))&&(!pass1.equals(""))&&isValid(pass1)) {
                    Cursor res1 = myDb.getAllData1(email1,pass1);
                    if(res1.getCount()!=0) {
                        showMessage("User Already Exists!", "Please use another Email ID since this one is already used.");
                        return;
                    }

                    else {
                        Session_Variables.s_email = email1;
                        Session_Variables.s_pass = pass1;
                        startActivity(i);
                    }
                }
                else if(! isValid(pass1))
                    Toast.makeText(SignupActivity.this, "Password must have 8 letters and 2 digits!", Toast.LENGTH_LONG).show();
                else if(!pass1.equals(c_pass1))
                    Toast.makeText(SignupActivity.this, "Both passwords do not match!", Toast.LENGTH_LONG).show();



            }
        });
    }
    public static boolean isValid(String password) {
        //return true if and only if password:
        //1. have at least eight characters.
        //2. must contain at least two digits.
        if (password.length() < 8) {
            return false;
        } else {
            char c;
            int count = 0;
            for (int i = 0; i < password.length(); i++) {
                c = password.charAt(i);
                if (Character.isDigit(c))
                    count++;
            }
            if (count < 2)
                return false;

        }
        return true;
    }
    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}
