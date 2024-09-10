package com.pitercapistrano.calculadoradenotas;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText n1, n2, n3, n4, numeroFaltas;
    private Button btCalcular;
    private TextView txtResultado;

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
        n1 = findViewById(R.id.nota1);
        n2 = findViewById(R.id.nota2);
        n3 = findViewById(R.id.nota3);
        n4 = findViewById(R.id.nota4);
        numeroFaltas = findViewById(R.id.numeroFaltas);
        btCalcular = findViewById(R.id.btCalcular);
        txtResultado = findViewById(R.id.txt_resultado);

        btCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                double nota1 = Double.parseDouble(n1.getText().toString());
                double nota2 = Double.parseDouble(n2.getText().toString());
                double nota3 = Double.parseDouble(n3.getText().toString());
                double nota4 = Double.parseDouble(n4.getText().toString());
                int faltas = Integer.parseInt(numeroFaltas.getText().toString());

                double media = (nota1 + nota2 + nota3 + nota4) / 4;

                if (faltas <= 20 && media > 5){
                    txtResultado.setText("Aluno foi aprovado! \n" + "Média Final: " + media);
                    txtResultado.setTextColor(getColor(R.color.verde));
                } else if (faltas > 20) {
                    txtResultado.setText("Aluno reprovado por faltas \n" + "Média Final: " + media + "\nTotal de faltas: " + faltas);
                    txtResultado.setTextColor(getColor(R.color.vermelho));
                } else if (faltas <= 20 && media >=4 && media <= 5) {
                    txtResultado.setText("Aluno de recuperação \n" + "Média Final: " + media);
                    txtResultado.setTextColor(getColor(R.color.amarelo));
                } else {
                    txtResultado.setText("Aluno foi reprovado por nota \n" + "Média Final: " + media);
                    txtResultado.setTextColor(getColor(R.color.vermelho));
                }
            }
        });
    }
}