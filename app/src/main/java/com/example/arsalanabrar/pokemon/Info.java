package com.example.arsalanabrar.pokemon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class Info extends AppCompatActivity {
String name,image,order,hieght,baseexp,weight;
ImageView pic;
TextView ord,hie,base,wt,pname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        pname=(TextView)findViewById(R.id.textView2);
        pic=(ImageView)findViewById(R.id.imageView1);
        ord=(TextView)findViewById(R.id.textView8);
        base=(TextView)findViewById(R.id.textView9);
        hie=(TextView)findViewById(R.id.textView10);
        wt=(TextView)findViewById(R.id.textView11);
        Bundle extras = getIntent().getExtras();
        name=extras.getString("name");
        image=extras.getString("image");
        order=extras.getString("order");
        hieght=extras.getString("hieght");
        baseexp=extras.getString("basexp");
        weight=extras.getString("weight");
        Picasso.with(getApplicationContext()).load(image).into(pic);

        pname.setText(name);
        ord.setText(order);
        base.setText(baseexp);
        hie.setText(hieght);
        wt.setText(weight);
    }
}
