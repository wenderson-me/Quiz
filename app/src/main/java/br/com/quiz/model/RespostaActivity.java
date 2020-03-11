package br.com.quiz.model;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import br.com.quiz.R;
import br.com.quiz.controller.QuizActivity;



public class RespostaActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_resposta);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        ImageView imgResposta = (ImageView)findViewById(R.id.imgResposta);
        TextView resposta = (TextView)findViewById(R.id.resposta);
        Button btnJogarNovamente = (Button)findViewById(R.id.btnJogarNovamente);

        Intent intent = getIntent();
        int pontos = intent.getIntExtra("pontos", 0);

        if(intent.hasExtra("acertou")) {
            btnJogarNovamente.setVisibility(View.INVISIBLE);
            boolean acertou = intent.getBooleanExtra("acertou", false);
            if (acertou) {
                imgResposta.setImageResource(R.drawable.acertou);
                resposta.setText("Acertou :) \n Sua pontuação atual é: " + pontos);
            } else {
                imgResposta.setImageResource(R.drawable.errou);
                resposta.setText("Errou :( \n Sua pontuação atual é: " + pontos);
            }

            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    finish();
                }
            });
            thread.start();
        }
        else{
            btnJogarNovamente.setVisibility(View.VISIBLE);
            resposta.setText("Você fez " + pontos + " ponto(s) de 10");

            if(pontos >= 10)
                imgResposta.setImageResource(R.drawable.acertoutudo);
            else if(pontos >= 7)
                imgResposta.setImageResource(R.drawable.acertou7);
            else if(pontos >= 5)
                imgResposta.setImageResource(R.drawable.errou5);
            else
                imgResposta.setImageResource(R.drawable.erroutudo);
        }
    }

    public void btnJogarNovamenteOnClick(View v){
        Intent intent = new Intent(this, QuizActivity.class);
        startActivity(intent);
        finish();
    }
}
