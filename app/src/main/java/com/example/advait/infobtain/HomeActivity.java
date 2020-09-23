package com.example.advait.infobtain;


import android.content.ContentValues;
import android.graphics.Bitmap;
import android.content.Context;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.content.Intent;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.cardview.widget.CardView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import net.glxn.qrgen.android.QRCode;
import net.glxn.qrgen.core.scheme.VCard;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    CardView scan;
    TextView n;
    TextView e;
    TextView p;
    TextView w;
    TextView c;
    TextView d;
    Session_Variables s;
    private IntentIntegrator qrScan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        s=new Session_Variables();
        String s1 = Session_Variables.s_email;
        String s3 = Session_Variables.s_name;
        String s4 = Session_Variables.s_phone;
        String s5 = Session_Variables.s_web;
        String s6 = Session_Variables.s_comp;
        String s7 = Session_Variables.s_desi;
        n = (TextView) findViewById(R.id.textView9);
        p = (TextView) findViewById(R.id.textView10);
        e = (TextView) findViewById(R.id.textView11);
        w = (TextView) findViewById(R.id.textView17);
        c = (TextView) findViewById(R.id.textView18);
        d = (TextView) findViewById(R.id.textView19);
        scan = (CardView) findViewById(R.id.cardView1);
        n.setText(s3);
        p.setText(s4);
        e.setText(s1);
        if(s5.equals(""))
            w.setText("Not Set");
        else
            w.setText(s5);
        if(s6.equals(""))
            c.setText("Not Set");
        else
            c.setText(s6);
        if(s7.equals(""))
            d.setText("Not Set");
        else
            d.setText(s7);

        qrScan = new IntentIntegrator(this);

        //attaching onclick listener
        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            qrScan.initiateScan();
            }
        });




        ImageView qrcode2 = (ImageView) findViewById(R.id.qrcode2);

        VCard lVCard=new VCard(s3)
                .setEmail(s1)
                .setAddress("India")
                .setTitle(s7)
                .setCompany(s6)
                .setPhoneNumber(s4)
                .setWebsite(s5);
        Bitmap lBitmap1=QRCode.from(lVCard).bitmap();
        qrcode2.setImageBitmap(lBitmap1);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater =getMenuInflater();
        inflater.inflate(R.menu.options,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final Context c=this;
        Intent i1=new Intent(c,LoginActivity.class);
        Intent i2=new Intent(c,EditInfo.class);
        switch(item.getItemId()){
            case R.id.item1:
                startActivity(i2);
                return true;
            case R.id.item2:
                Toast.makeText(HomeActivity.this, "Logged Out!", Toast.LENGTH_LONG).show();
                s.empty_Session_Variables();
                startActivity(i1);
                return true;
            default: return super.onOptionsItemSelected(item);

        }

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode,data);
        if (result != null) {
            //if qrcode has nothing in it
            if (result.getContents() == null) {
                Toast.makeText(this, "Result Not Found", Toast.LENGTH_LONG).show();
            } else {
                //if qr contains data
                try {
                    String s=result.getContents();
                    //setting values to <></>extviews
                   // Toast.makeText(this, "" + s + "", Toast.LENGTH_LONG).show();
                    VCard vCard=VCard.parse(s);
                    Intent a2c=new Intent(ContactsContract.Intents.Insert.ACTION);
                    a2c.setType(ContactsContract.RawContacts.CONTENT_TYPE);
                    a2c.putExtra(ContactsContract.Intents.Insert.NAME,vCard.getName());
                    a2c.putExtra(ContactsContract.Intents.Insert.PHONE,vCard.getPhoneNumber());
                    a2c.putExtra(ContactsContract.Intents.Insert.EMAIL,vCard.getEmail());
                    a2c.putExtra(ContactsContract.Intents.Insert.JOB_TITLE,vCard.getTitle());
                    a2c.putExtra(ContactsContract.Intents.Insert.COMPANY,vCard.getCompany());
                    a2c.putExtra(ContactsContract.Intents.Insert.FULL_MODE,true);
                    ArrayList<ContentValues> data1 = new ArrayList<ContentValues>();
                    ContentValues row1 = new ContentValues();
                    row1.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Website.CONTENT_ITEM_TYPE);
                    row1.put(ContactsContract.CommonDataKinds.Website.URL,vCard.getWebsite() );
                    row1.put(ContactsContract.CommonDataKinds.Website.LABEL, "Website");
                    row1.put(ContactsContract.CommonDataKinds.Website.TYPE, ContactsContract.CommonDataKinds.Website.TYPE_WORK);
                    data1.add(row1);
                    a2c.putExtra(ContactsContract.Intents.Insert.DATA, data1);
                    startActivity(a2c);
                } catch (Exception e) {
                    e.printStackTrace();
                    //if control comes here
                    //that means the encoded format not matches
                    //in this case you can display whatever data is available on the qrcode
                    //to a toast
                    Toast.makeText(this, "" + e + "", Toast.LENGTH_LONG).show();
                }
                }
        } else {
            super.onActivityResult(requestCode, resultCode,data);
        }
    }


}
