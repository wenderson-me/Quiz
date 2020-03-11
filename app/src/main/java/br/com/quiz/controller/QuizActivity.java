package br.com.quiz.controller;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import br.com.quiz.model.Questao;
import br.com.quiz.model.RespostaActivity;
import br.com.quiz.R;


public class QuizActivity extends AppCompatActivity {

    private TextView pergunta;
    private RadioButton rbResposta1, rbResposta2, rbResposta3, rbResposta4;
    private ImageView imgPergunta;
    private RadioGroup rgRespostas;
    private int respostaCerta;
    private int pontos;
    private int colorFlag = 0;
    List<Questao> questoes = new ArrayList<Questao>(){
        {
            add(new Questao(R.drawable.vinte, "Qual é o antecessor do número da figura?", R.id.rbResposta1, "A) 19 (Dezenove)", "B) 21 (Vinte e um)", "C) 22 (Vinte e dois)", "D) 23 (Vinte e três)"));
            add(new Questao(R.drawable.vinteenove, "Qual é o número que vem depois do número da figura?", R.id.rbResposta3, "A) 27 (Vinte e sete)", "B) 26 (Vinte e seis)", "C) 30 (Trinta)", "D) 28 (Vinte e oito)"));
            add(new Questao(R.drawable.vinteecinco, "A grafia correta do número da figura é?", R.id.rbResposta2, "A) Dois e cinco", "B) Vinte e cinco", "C) Vinte e um", "D) Dois mais cinco"));
            add(new Questao(R.drawable.peteca, "Qual é o esporte que usa o objeto da imagem?", R.id.rbResposta3,"A) Futebol", "B) Basquete", "C) Peteca", "D) Tênis"));
            add(new Questao(R.drawable.arvore, "Que elemento da flora brasileira está na imagem?", R.id.rbResposta4, "A) Flor ", "B) Grama ", "C) Arbusto ", "D) Árvore "));
            add(new Questao(R.drawable.estrela, "O que é mostrado na imagem?", R.id.rbResposta2, "A) Escada", "B) Estrela", "C) Espelho", "D) Escova"));
            add(new Questao(R.drawable.bota, "Para caminhar sobre a lama posso usar uma?", R.id.rbResposta4, "A) Foca", "B) Toca", "C) Bola", "C) Bota"));
            add(new Questao(R.drawable.boneca, "Que objeto é este?", R.id.rbResposta1, "A) Boneca", "B) Caneta", "C) Panela", "D) Peteca"));
            add(new Questao(R.drawable.noel, "O Papai Noel está com os braços...", R.id.rbResposta3, "A) Cruzados", "B) Para baixo", "C) Para cima", "D) Para traz"));
            add(new Questao(R.drawable.presente, "Quantos presentes estão são mostrados?", R.id.rbResposta3, "A) 4 (Quatro)", "B) 2 (Dois)","C) 1 (Um)", "D) 3 (Três)"));

            }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_quiz);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        imgPergunta = (ImageView)findViewById(R.id.imgPergunta);
        pergunta = (TextView)findViewById(R.id.pergunta);
        rgRespostas = (RadioGroup)findViewById(R.id.rgRespostas);
        rbResposta1 = (RadioButton) findViewById(R.id.rbResposta1);
        rbResposta2 = (RadioButton)findViewById(R.id.rbResposta2);
        rbResposta3 = (RadioButton)findViewById(R.id.rbResposta3);
        rbResposta4 = (RadioButton)findViewById(R.id.rbResposta4);
        carregarQuestao();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        carregarQuestao();
    }

    public void btnResponderOnClick(View v){
        if (rgRespostas.getCheckedRadioButtonId() != -1) {

            final RadioButton rb = (RadioButton) findViewById(rgRespostas.getCheckedRadioButtonId());
            /*rb.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    RadioButton button = (RadioButton) group.findViewById(checkedId);
                    String resposta = button.getText().toString();
                    button.setBackgroundColor(Color.GREEN);
                }
            }); */

            Intent intent = new Intent(this, RespostaActivity.class);
            if(rgRespostas.getCheckedRadioButtonId() == respostaCerta) {
                intent.putExtra("acertou", true);
                pontos++;
            } else
                intent.putExtra("acertou", false);
            intent.putExtra("pontos", pontos);
            startActivity(intent);

            rgRespostas.clearCheck();
        } else {
            AlertDialog alertDialog = new AlertDialog.Builder(QuizActivity.this).create();
            alertDialog.setTitle("Ooops!");
            alertDialog.setMessage("Você precisa selecionar uma alternativa :)");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();

        }

    }

    private void carregarQuestao(){

        embaralharQuestao(questoes);

        if(questoes.size() > 0) {
            Questao q = questoes.remove(0);
            pergunta.setText(q.getPergunta());
            imgPergunta.setImageResource(q.getImgPergunta());
            List<String> resposta = q.getRespostas();
            rbResposta1.setText(resposta.get(0));
            rbResposta2.setText(resposta.get(1));
            rbResposta3.setText(resposta.get(2));
            rbResposta4.setText(resposta.get(3));
            respostaCerta = q.getRespostaCerta();
            rgRespostas.setSelected(false);
        }
        else{ //acabaram as questões
            Intent intent = new Intent(this, RespostaActivity.class);
            intent.putExtra("pontos", pontos);
            startActivity(intent);
            finish();
        }
    }
    public void embaralharQuestao(List<Questao> questoes){
        Collections.shuffle(questoes);
    }

    
}
