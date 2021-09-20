package com.example.caner.advancedcalculator;

import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Scanner;
import java.util.Stack;

public class MainActivity extends AppCompatActivity {

    TextView txt_Postfix,txt_Infix,txt_Sonuc;
    EditText edt_Input;
    Button btn_Hesapla;
    String postfix_output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt_Infix=(TextView)findViewById(R.id.txtInfix);
        txt_Postfix=(TextView)findViewById(R.id.txtPostfix);
        txt_Sonuc=(TextView)findViewById(R.id.txtSonuc);
        edt_Input=(EditText) findViewById(R.id.edtInput);
        btn_Hesapla=(Button) findViewById(R.id.btnHesapla);

        btn_Hesapla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try
                {
                    txt_Infix.setText(edt_Input.getText().toString());
                    PostFixCalculator calculator=new PostFixCalculator();
                    //infix convert postfix
                    txt_Postfix.setText("" + calculator.ConvertToPostfix(edt_Input.getText().toString()));
                    //postfix calculate
                    txt_Sonuc.setText("" + calculator.Evaluate(calculator.ConvertToPostfix( edt_Input.getText().toString())));

                }
                catch(Exception e)
                {
                    Toast.makeText(MainActivity.this,e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

}
