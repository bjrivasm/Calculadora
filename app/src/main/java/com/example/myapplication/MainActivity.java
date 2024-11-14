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

    private List<String> operaciones = List.of("+","-","*","/","%");
    private Double valorAnterior;
    private String operador = null;
    private Boolean entradaNueva = true;
    private final TextView tvPantalla = (TextView) findViewById(R.id.tvResultado);

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

        //Numeros
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

        //Operaciones
        Button btSuma = (Button) findViewById(R.id.btSuma);
        btSuma.setOnClickListener(this);
        Button btResta = (Button) findViewById(R.id.btResta);
        btResta.setOnClickListener(this);
        Button btMultiplicar = (Button) findViewById(R.id.btMultiplicar);
        btMultiplicar.setOnClickListener(this);
        Button btDividir = (Button) findViewById(R.id.btDividir);
        btDividir.setOnClickListener(this);
        Button btPorcentaje = (Button) findViewById(R.id.btPorcentaje);
        btPorcentaje.setOnClickListener(this);

        //Botones de control
        Button btCe = (Button) findViewById(R.id.btCE);
        btCe.setOnClickListener(this);
        Button btC = (Button) findViewById(R.id.btC);
        btC.setOnClickListener(this);
        Button btnMasMenos = (Button) findViewById(R.id.btMasMenos);
        btnMasMenos.setOnClickListener(this);
        Button btComa = (Button) findViewById(R.id.btComa);
        btComa.setOnClickListener(this);
        Button btIgual = (Button) findViewById(R.id.btIgual);
        btIgual.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Button boton = (Button) view;
        String valorActual = boton.getText().toString();

        if (entradaNueva || tvPantalla.getText().toString().equals("0")) {
            tvPantalla.setText(valorActual);
            entradaNueva = false;
        } else if (isNumber(valorActual)) {
            valorActual = tvPantalla.getText().toString() + valorActual;
            tvPantalla.setText(valorActual);
        } else if (operaciones.contains(valorActual)) {
            String valres = tvPantalla.getText().toString();
            if (isNumber(Character.toString(valres.charAt(valres.length() - 1)))) {
                valorAnterior = Double.valueOf(valres);
                operador = valorActual;
            } else {
                valorAnterior = Double.valueOf(valres.substring(0, valres.length() - 2));
                operador = valorActual;
            }
            entradaNueva = true;
        } else {
            switch (valorActual) {
                case "=":
                    double resultado = calcularOperacion(Double.parseDouble(tvPantalla.getText().toString()));
                    tvPantalla.setText(String.valueOf(resultado));
                    entradaNueva = true;
                    break;

                case "+/-":
                    double valor = Double.parseDouble(tvPantalla.getText().toString()) * -1;
                    tvPantalla.setText(String.valueOf(valor));
                    break;

                case "C":
                    limpiarValorActual();
                    break;

                case "CE":
                    limpiarCalculadora();
                    break;

                case ",":
                    if (!tvPantalla.getText().toString().contains(".")) {
                        tvPantalla.append(".");
                    }
                    break;

                default:
                    break;
            }
        }
    }


    private void limpiarValorActual() {
        tvPantalla.setText("0");
        entradaNueva = true;
    }


    /*public void onClick(View view){
        Button boton = (Button) view;
        String valorActual = boton.getText().toString();

        if(entradaNueva || tvPantalla.getText().toString().equals("0")) {
            tvPantalla.setText(valorActual);
            entradaNueva = false;
        } else if (isNumber(valorActual)) {
            valorActual = tvPantalla.getText().toString() + valorActual;
            tvPantalla.setText(valorActual);
        } else if (operaciones.contains(valorActual)) {
            String valres = tvPantalla.getText().toString();
            if(isNumber(Character.toString(valres.charAt(valres.length()-1)))) {
                valorAnterior = Double.valueOf(valres + valorActual);
                operador = valorActual;
            } else {
                valorAnterior = Double.valueOf(valres.substring(0, valres.length()-2) + valorActual);
                operador = valorActual;
            }
            entradaNueva = true;
        } else if (valorActual.equals("=")) {
            calcularOperacion(Double.parseDouble(valorActual));
        }

    }*/

    private double calcularOperacion(Double valorActual) {
        double resultado = 0;

        switch (operador) {
            case "+":
                resultado = valorAnterior + valorActual;
                break;
            case "-":
                resultado = valorAnterior - valorActual;
                break;
            case "*":
                resultado = valorAnterior * valorActual;
                break;
            case "/":
                if (valorActual != 0) {
                    resultado = valorAnterior / valorActual;
                } else {
                    tvPantalla.setText("Error");
                }
                break;
        }

        return resultado;
    }

    private boolean isNumber(String num){
        try{
            int valor = Integer.parseInt(num);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    private void limpiarCalculadora(){
        tvPantalla.setText("0");
        valorAnterior = 0.0;
        operador = "";
        entradaNueva = true;
    }
}
