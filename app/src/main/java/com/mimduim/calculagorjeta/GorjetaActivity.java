package com.mimduim.calculagorjeta;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class GorjetaActivity extends AppCompatActivity {

    private CalculadoraGorjeta calculadora;

    private EditText etGorjeta10;
    private EditText etGorjeta15;
    private EditText etGorjeta20;
    private EditText etTotal10;
    private EditText etTotal15;
    private EditText etTotal20;
    private EditText etPessoas;
    private EditText etTotalDivisaoGorjeta;
    private EditText etValorConta;
    private EditText etGorjetaPersonalizada;
    private EditText etTotalPersonalizado;
    private SeekBar sbPersonalizado;
    private TextView tvPersonalizado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gorjeta);

        calculadora = new CalculadoraGorjeta();

        etGorjeta10 = (EditText) findViewById(R.id.etGorjeta10);
        etGorjeta15 = (EditText) findViewById(R.id.etGorjeta15);
        etGorjeta20 = (EditText) findViewById(R.id.etGorjeta20);
        etTotal10 = (EditText) findViewById(R.id.etTotal10);
        etTotal15 = (EditText) findViewById(R.id.etTotal15);
        etTotal20 = (EditText) findViewById(R.id.etTotal20);
        etValorConta = (EditText) findViewById(R.id.etValorConta);
        etPessoas = (EditText) findViewById(R.id.etPessoas);
        etTotalDivisaoGorjeta = (EditText) findViewById(R.id.etTotalDivisaoGorjeta);
        sbPersonalizado = (SeekBar) findViewById(R.id.sbPersonalizado);
        tvPersonalizado = (TextView) findViewById(R.id.tvPersonalizado);
        etGorjetaPersonalizada = (EditText) findViewById(R.id.etGorjetaPersonalizada);
        etTotalPersonalizado = (EditText) findViewById(R.id.etTotalPersonalizado);

        etValorConta.addTextChangedListener(new ContaTextWatcher());

        sbPersonalizado.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                atualizarTela();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }


    class ContaTextWatcher implements TextWatcher {


        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            if (charSequence.length() > 0){
                float valor = Float.parseFloat(charSequence.toString());
                // atualizar o objeto modelo
                calculadora.setValorConta(valor);
                // atualizar tela
                GorjetaActivity.this.atualizarTela();
            }else{
                // atualizar o objeto modelo
                calculadora.setValorConta(0);
                // atualizar tela
                GorjetaActivity.this.atualizarTela();
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    }

    class PessoaTextWatcher implements TextWatcher{

        @Override
        public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {

        }
        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            // atualizar tela
            GorjetaActivity.this.atualizarTela();
        }
        @Override
        public void afterTextChanged(Editable editable) {

        }
    }

    private void atualizarTela() {
        float gorj10 = calculadora.getGorjeta(0.1f);
        float gorj15 = calculadora.getGorjeta(0.15f);
        float gorj20 = calculadora.getGorjeta(0.2f);

        float total10 = calculadora.getValorConta()
                + gorj10;
        float total15 = calculadora.getValorConta()
                + gorj15;
        float total20 = calculadora.getValorConta()
                + gorj20;

        // calcular personalizado
        float gorjP = sbPersonalizado.getProgress() / 100f;
        gorjP = calculadora.getValorConta() * gorjP;

        float totalP = calculadora.getValorConta() +
                gorjP;

        etGorjetaPersonalizada.setText("" + gorjP);
        etTotalPersonalizado.setText("" + totalP);
        etTotalDivisaoGorjeta.setText("" + totalP/Float.parseFloat(etPessoas.getText().toString()));
        tvPersonalizado.setText("" +
                sbPersonalizado.getProgress() + "%");

        etGorjeta10.setText("" + gorj10);
        etGorjeta15.setText("" + gorj15);
        etGorjeta20.setText("" + gorj20);

        etTotal10.setText("" + total10);
        etTotal15.setText("" + total15);
        etTotal20.setText("" + total20);
    }
}
