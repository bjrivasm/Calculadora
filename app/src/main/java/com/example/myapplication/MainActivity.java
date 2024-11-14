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

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Double valorAnterior;
    private String operador = null;
    private Boolean entradaNueva = true;
    private TextView tvPantalla;

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

        tvPantalla = findViewById(R.id.tvResultado);
        tvPantalla.setText("0");

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
        Button btAc = (Button) findViewById(R.id.btAC);
        btAc.setOnClickListener(this);
        Button btC = (Button) findViewById(R.id.btC);
        btC.setOnClickListener(this);
        Button btnMasMenos = (Button) findViewById(R.id.btMasMenos);
        btnMasMenos.setOnClickListener(this);
        Button btComa = (Button) findViewById(R.id.btComa);
        btComa.setOnClickListener(this);
        Button btIgual = (Button) findViewById(R.id.btIgual);
        btIgual.setOnClickListener(this);
        Button btBorrar = (Button) findViewById(R.id.btBorrar);
        btBorrar.setOnClickListener(this);
        Button btRaiz = (Button) findViewById(R.id.btRaiz);
        btRaiz.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Button boton = (Button) view;
        String valorActual = boton.getText().toString();

        // Primero verificamos si el valor es un número
        if (isNumber(valorActual)) {
            // Si es un número, procedemos con la lógica correspondiente
            if (entradaNueva || tvPantalla.getText().toString().equals("0")) {
                tvPantalla.setText(valorActual);
                entradaNueva = false;
            } else {
                tvPantalla.append(valorActual);
            }
        } else {
            switch (valorActual) {
                case "+":
                case "-":
                case "x":
                case "/":
                case "%":
                    procesarOperador(valorActual);
                    break;
                case "=":
                    calcularResultado();
                    break;
                case "+/-":
                    double valor = Double.parseDouble(tvPantalla.getText().toString()) * -1;
                    tvPantalla.setText(String.valueOf(valor));
                    break;
                case "C":
                    limpiarValorActual();
                    break;
                case "AC":
                    limpiarCalculadora();
                    break;
                case ",":
                    if (!tvPantalla.getText().toString().contains(".")) {
                        tvPantalla.append(".");
                    }
                    break;
                case "D":
                    if (!entradaNueva) {
                        borrarUltimoCaracter();
                    }
                    break;
                case "√":
                    calcularRaizCuadrada();
                    break;
                default:
                    break;
            }
        }
    }


    private void procesarOperador(String valorActual) {
        String valorPantalla = tvPantalla.getText().toString();
        if (isNumber(Character.toString(valorPantalla.charAt(valorPantalla.length() - 1)))) {
            valorAnterior = Double.valueOf(valorPantalla);
            operador = valorActual;
            entradaNueva = true;
        } else {
            operador = valorActual;
        }
    }

    private void calcularResultado() {
        if (operador != null) {
            try {
                double valorActual = Double.parseDouble(tvPantalla.getText().toString());
                double resultado = calcularOperacion(valorActual);
                tvPantalla.setText(String.valueOf(resultado));
                operador = null;
                valorAnterior = resultado;
                entradaNueva = true;
            } catch (NumberFormatException e) {
                tvPantalla.setText("Error");
            }
        }
    }

    private double calcularOperacion(Double valorActual) {
        double resultado = 0;
        if (valorAnterior != null) {
            switch (operador) {
                case "+":
                    resultado = valorAnterior + valorActual;
                    break;
                case "-":
                    resultado = valorAnterior - valorActual;
                    break;
                case "x":
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
        }
        return resultado;
    }

    private boolean isNumber(String num) {
        try {
            Integer.parseInt(num);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void calcularRaizCuadrada() {
        try {
            double valorActual = Double.parseDouble(tvPantalla.getText().toString());
            if (valorActual >= 0) {
                double resultado = Math.sqrt(valorActual);
                tvPantalla.setText(String.valueOf(resultado));
                entradaNueva = true;
            } else {
                tvPantalla.setText("Error");
            }
        } catch (NumberFormatException e) {
            tvPantalla.setText("Error");
        }
    }


    private void borrarUltimoCaracter() {
        String pantallaTexto = tvPantalla.getText().toString();
        if (pantallaTexto.length() > 1) {
            tvPantalla.setText(pantallaTexto.substring(0, pantallaTexto.length() - 1));
        } else {
            tvPantalla.setText("0");
            entradaNueva = true;
        }
    }

    private void limpiarValorActual() {
        tvPantalla.setText("0");
        entradaNueva = true;
    }

    private void limpiarCalculadora() {
        tvPantalla.setText("0");
        valorAnterior = null;
        operador = null;
        entradaNueva = true;
    }
}