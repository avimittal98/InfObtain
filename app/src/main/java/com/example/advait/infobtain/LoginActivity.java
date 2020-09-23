package com.example.advait.infobtain;

import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.database.Cursor;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.app.AlertDialog;

public class LoginActivity extends AppCompatActivity {
    EditText username,password1;
    TextView register;
    Button login;
    DatabaseHelper mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username=(EditText) findViewById(R.id.email);
        password1=(EditText)findViewById(R.id.password);
        register=(TextView)findViewById(R.id.textView14);
        login=(Button)findViewById(R.id.email_sign_in_button);
        mydb=new DatabaseHelper(this);
        Session_Variables s=new Session_Variables();
        s.empty_Session_Variables();
        final Context c=this;
        login.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                String user=username.getText().toString();
                String pass=password1.getText().toString();
                Intent i=new Intent(c,HomeActivity.class);
                Cursor res = mydb.getAllData(user,pass);
                if(res.getCount() == 0) {
                    // show message
                    showMessage("Login Failed.","Please fill in correct details");
                    return;
                }
                else
                {
                    while(res.moveToNext()) {
                        Session_Variables.s_email = res.getString(1);
                        Session_Variables.s_name = res.getString(3);
                        Session_Variables.s_phone = res.getString(4);
                        Session_Variables.s_web = res.getString(5);
                        Session_Variables.s_comp = res.getString(6);
                        Session_Variables.s_desi = res.getString(7);
                        startActivity(i);
                    }
                }




            }
        });
        addListenerOnRegister();
    }

    private void addListenerOnRegister() {
        final Context c=this;
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(c,SignupActivity.class);
                startActivity(i);
            }
        });
    }
    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

}
