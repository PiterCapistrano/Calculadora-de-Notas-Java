package com.pitercapistrano.calculadoradenotas;

import android.os.Bundle;  // Importa a classe Bundle para salvar e restaurar o estado da atividade
import android.view.View;  // Importa a classe View para manipular as interações da interface do usuário
import android.widget.Button;  // Importa a classe Button para representar botões de ação
import android.widget.EditText;  // Importa a classe EditText para entradas de texto
import android.widget.TextView;  // Importa a classe TextView para exibir texto na tela
import androidx.activity.EdgeToEdge;  // Importa EdgeToEdge para uso otimizado da tela completa
import androidx.appcompat.app.AppCompatActivity;  // Importa a classe base AppCompatActivity para atividades
import androidx.core.graphics.Insets;  // Importa a classe Insets para gerenciar margens
import androidx.core.view.ViewCompat;  // Importa ViewCompat para compatibilidade de versões anteriores do Android
import androidx.core.view.WindowInsetsCompat;  // Importa WindowInsetsCompat para manipular margens do sistema

public class MainActivity extends AppCompatActivity {

    // Declaração dos campos de entrada de texto para as notas e faltas
    private EditText n1, n2, n3, n4, numeroFaltas;

    // Declaração do botão que será clicado para calcular o resultado
    private Button btCalcular;

    // Declaração do TextView que exibirá o resultado final
    private TextView txtResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Ativa o modo EdgeToEdge para melhor uso da tela no Android
        EdgeToEdge.enable(this);

        // Define o layout principal da atividade a partir do arquivo XML
        setContentView(R.layout.activity_main);

        // Configura o layout para ajustar as margens conforme as barras do sistema (status e navegação)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            // Ajusta o padding para evitar sobreposição com barras do sistema
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Associa as variáveis aos componentes do layout, vinculando os campos de texto para as notas
        n1 = findViewById(R.id.nota1);
        n2 = findViewById(R.id.nota2);
        n3 = findViewById(R.id.nota3);
        n4 = findViewById(R.id.nota4);
        numeroFaltas = findViewById(R.id.numeroFaltas);

        // Associa o botão de calcular ao componente do layout
        btCalcular = findViewById(R.id.btCalcular);

        // Associa o TextView para exibir o resultado final
        txtResultado = findViewById(R.id.txt_resultado);

        // Define o evento de clique para o botão de calcular
        btCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Converte os valores inseridos pelo usuário (notas e faltas) de texto para números
                double nota1 = Double.parseDouble(n1.getText().toString());
                double nota2 = Double.parseDouble(n2.getText().toString());
                double nota3 = Double.parseDouble(n3.getText().toString());
                double nota4 = Double.parseDouble(n4.getText().toString());
                int faltas = Integer.parseInt(numeroFaltas.getText().toString());

                // Calcula a média das notas inseridas
                double media = (nota1 + nota2 + nota3 + nota4) / 4;

                // Condições para determinar o resultado final baseado na média e no número de faltas
                if (faltas <= 20 && media > 5) {
                    // Se o número de faltas for aceitável e a média for maior que 5, o aluno é aprovado
                    txtResultado.setText("Aluno foi aprovado! \n" + "Média Final: " + media);
                    txtResultado.setTextColor(getColor(R.color.verde));  // Muda a cor do texto para verde
                } else if (faltas > 20) {
                    // Se o número de faltas for maior que 20, o aluno é reprovado por faltas
                    txtResultado.setText("Aluno reprovado por faltas \n" + "Média Final: " + media + "\nTotal de faltas: " + faltas);
                    txtResultado.setTextColor(getColor(R.color.vermelho));  // Muda a cor do texto para vermelho
                } else if (faltas <= 20 && media >= 4 && media <= 5) {
                    // Se a média estiver entre 4 e 5 e as faltas estiverem dentro do limite, o aluno está em recuperação
                    txtResultado.setText("Aluno de recuperação \n" + "Média Final: " + media);
                    txtResultado.setTextColor(getColor(R.color.amarelo));  // Muda a cor do texto para amarelo
                } else {
                    // Se nenhuma das condições anteriores forem atendidas, o aluno é reprovado por nota
                    txtResultado.setText("Aluno foi reprovado por nota \n" + "Média Final: " + media);
                    txtResultado.setTextColor(getColor(R.color.vermelho));  // Muda a cor do texto para vermelho
                }
            }
        });
    }
}
