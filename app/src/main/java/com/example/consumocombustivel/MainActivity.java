package com.example.consumocombustivel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText litrosEditText;
    private EditText quilometrosEditText;
    private LinearLayout resultadoLinearLayout;
    private TextView resultadoTextView;
    private Button calcularButton;
    private Button limparButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        litrosEditText = findViewById(R.id.litrosEditText);
        litrosEditText.setOnKeyListener(validarBotoes());
        litrosEditText.requestFocus();

        quilometrosEditText = findViewById(R.id.quilometrosEditText);
        quilometrosEditText.setOnKeyListener(validarBotoes());

        resultadoLinearLayout = findViewById(R.id.resultadoLinearLayout);
        resultadoLinearLayout.setVisibility(View.INVISIBLE);

        resultadoTextView = findViewById(R.id.resultadoTextView);

        calcularButton = findViewById(R.id.calcularButton);
        calcularButton.setEnabled(false);

        limparButton = findViewById(R.id.limparButton);
        limparButton.setEnabled(false);
    }

    private View.OnKeyListener validarBotoes() {
        return new View.OnKeyListener() {

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                String litros = litrosEditText.getText().toString();
                String quilometros = quilometrosEditText.getText().toString();

                if(!litros.isEmpty() && !quilometros.isEmpty()) {
                    calcularButton.setEnabled(true);
                }

                if(!litros.isEmpty() || !quilometros.isEmpty()) {
                    limparButton.setEnabled(true);
                }

                return false;
            }
        };
    }

    public void calcular(View view) {
        double litros = Double.parseDouble(litrosEditText.getText().toString());
        double quilometros = Double.parseDouble(quilometrosEditText.getText().toString());
        double resultado = quilometros/litros;
        quilometrosEditText.onEditorAction(EditorInfo.IME_ACTION_DONE);
        resultadoTextView.setText(String.valueOf(resultado).concat(" km/l"));
        resultadoLinearLayout.setVisibility(View.VISIBLE);
    }

    public void limpar(View view) {
        litrosEditText.requestFocus();
        litrosEditText.setText("");
        quilometrosEditText.setText("");
        resultadoLinearLayout.setVisibility(View.INVISIBLE);
        quilometrosEditText.onEditorAction(EditorInfo.IME_ACTION_DONE);
        calcularButton.setEnabled(false);
        limparButton.setEnabled(false);
    }
}
