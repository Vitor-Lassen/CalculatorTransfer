package com.example.vitor.calculatortransfer;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MainActivity extends AppCompatActivity {

    EditText _stateCodeEditText;
    EditText _loadCodEditText;
    EditText _weightLoadEditText;
    Button _calculateButton;
    TextView _taxAmountTextView;
    TextView _loadValueTextView;
    TextView _weightLoadKgTextView;
    NumberFormat _format = NumberFormat.getCurrencyInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _stateCodeEditText = findViewById(R.id.stateCodEditText);
        _loadCodEditText = findViewById(R.id.loadCodEditText);
        _weightLoadEditText = findViewById(R.id.weightLoadEditText);
        _calculateButton =findViewById(R.id.calculateButton);
        _taxAmountTextView = findViewById(R.id.taxAmountTextView);
        _loadValueTextView = findViewById(R.id.loadValueTextView);
        _weightLoadKgTextView = findViewById(R.id.weightLoadKgTextView);




        _calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double weghtLoadKg = Double.valueOf(_weightLoadEditText.getText().toString()) * 1000;
                int loadCod = Integer.valueOf(_loadCodEditText.getText().toString());
                int stateCod = Integer.valueOf(_stateCodeEditText.getText().toString());

                double totalAmount = getValueLoadFofKilo(loadCod) * weghtLoadKg;
                double tax = getTax(stateCod) * totalAmount;
                double amount = totalAmount - tax;
                _weightLoadKgTextView.setText(String.valueOf( weghtLoadKg));
                _taxAmountTextView.setText(_format.format(tax));
                _loadValueTextView.setText(_format.format(amount));
            }
        });
    }
    private int getValueLoadFofKilo (int cod){
        if (cod >=10 && cod <=20){
            return  100;
        }
        if (cod >20 && cod <=30){
            return  250;
        }
        if (cod >30 && cod <=40){
            return  340;
        }
        return  0;
    }

    private double getTax (int cod){
        switch (cod){
            case 1 : return .35;
            case 2 : return .25;
            case 3 : return .15;
            case 4 : return .5;
            default:return 0;
        }
    }

}
