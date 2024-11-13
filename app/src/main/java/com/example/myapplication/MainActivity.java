package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button bt0 = (Button) findViewById(R.id.bt0);
        bt0.setOnClickListener(this);

        Button bt1 = (Button) findViewById(R.id.bt1);
        bt1.setOnClickListener(this);

        Button bt2 = (Button) findViewById(R.id.bt2);
        bt2.setOnClickListener(this);

        Button bt3 = (Button) findViewById(R.id.bt3);
        bt3.setOnClickListener(this);

        Button bt4 = (Button) findViewById(R.id.bt4);
        bt4.setOnClickListener(this);

        Button bt5 = (Button) findViewById(R.id.bt5);
        bt5.setOnClickListener(this);

        Button bt6 = (Button) findViewById(R.id.bt6);
        bt6.setOnClickListener(this);

        Button bt7 = (Button) findViewById(R.id.bt7);
        bt7.setOnClickListener(this);

        Button bt8 = (Button) findViewById(R.id.bt8);
        bt8.setOnClickListener(this);

        Button bt9 = (Button) findViewById(R.id.bt9);
        bt9.setOnClickListener(this);

    }

    @Override
    public void onClick(View view){
        Button boton = (Button) view;
        String valor = boton.getText().toString();
        List<String> operaciones = new ArrayList<>();
        operaciones.add("+");
        operaciones.add("-");
        operaciones.add("*");
        operaciones.add("/");
        operaciones.add("%");
        //if(boton.getId() = (Button) findViewById(R.id.bt1).getId())
        if (isNumber(valor)) {
            TextView res = (TextView) findViewById(R.id.tvResultado);
            valor = res.getText().toString() + valor;
            res.setText(valor);
        } else if(operaciones.contains(valor)){
            TextView res = (TextView) findViewById(R.id.tvResultado);
            String valres = res.getText().toString();
            if(isNumber(Character.toString(valres.charAt(valres.length()-1)))) {
                valor = valres + valor;
            } else {
                valor = valres.substring(0, valres.length()-2) + valor;
            }
        }
    }

    public boolean isNumber(String num){
        try{
            int valor = Integer.parseInt(num);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
