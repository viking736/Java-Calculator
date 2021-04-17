package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView tvInput;
    boolean lastNumber = false;
    boolean lastDot = false;
    Button btn0;
    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;
    Button btn5;
    Button btn6;
    Button btn7;
    Button btn8;
    Button btn9;
    Button btnDot;
    Button btnAdd;
    Button btnMult;
    Button btnSub;
    Button btnDiv;
    Button btnEqu;
    Button btnClr;


    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvInput = findViewById(R.id.tvInput);
        Button[] Buttons = new Button[17];
        btn0 = findViewById(R.id.btn_zero);
        Buttons[0] = btn0;
        btn1 = findViewById(R.id.btn_one);
        Buttons[1] = btn1;
        btn2 = findViewById(R.id.btn_two);
        Buttons[2] = btn2;
        btn3 = findViewById(R.id.btn_three);
        Buttons[3] = btn3;
        btn4 = findViewById(R.id.btn_four);
        Buttons[4] = btn4;
        btn5 = findViewById(R.id.btn_five);
        Buttons[5] = btn5;
        btn6 = findViewById(R.id.btn_six);
        Buttons[6] = btn6;
        btn7 = findViewById(R.id.btn_seven);
        Buttons[7] = btn7;
        btn8 = findViewById(R.id.btn_eight);
        Buttons[8] = btn8;
        btn9 = findViewById(R.id.btn_nine);
        Buttons[9] = btn9;
        btnAdd = findViewById(R.id.btn_add);
        Buttons[10] = btnAdd;
        btnSub = findViewById(R.id.btn_subtract);
        Buttons[11] = btnSub;
        btnMult = findViewById(R.id.btn_multiply);
        Buttons[12] = btnMult;
        btnDiv = findViewById(R.id.btn_divide);
        Buttons[13] = btnDiv;
        btnClr = findViewById(R.id.btn_clear);
        Buttons[14] = btnClr;
        btnEqu = findViewById(R.id.btn_equation);
        Buttons[15] = btnEqu;
        btnDot = findViewById(R.id.btn_decimal);
        Buttons[16] = btnDot;

        for(int i = 0; i < 17; i++){
            Buttons[i].setBackgroundColor(R.color.pastel_violet);
        }

    }

    public void onDigit(View view){
        tvInput.append(((Button) view).getText());
        lastNumber = true;
    }

    public void onClear(View view){
        tvInput.setText(" ");
        lastNumber = false;
        lastDot = false;
    }

    public void onDecimalPoint(View view){
        if (lastNumber && !lastDot){
            tvInput.append(".");
            lastNumber = false;
            lastDot = true;
        }
    }


    public void onOperator(View view){
        if (lastNumber && !isOperatorUsed(tvInput.getText().toString())){
            tvInput.append(((Button) view).getText());
            lastNumber = false;
            lastDot = false;
        }
    }

    public void onEqual(View view){
        if (lastNumber){
            String tvValue = tvInput.getText().toString();
            String prefix = "";
            try {
                if(tvValue.startsWith("-")){
                    prefix = "-";
                    tvValue = tvValue.substring(1);
                }
                if (tvValue.contains("-")){
                    String[] splitValue = tvValue.split("-");
                    String one = splitValue[0];
                    String two = splitValue[1];
                    if (!prefix.isEmpty()){
                        one = prefix + one;
                    }
                    double result = Double.parseDouble(one)-Double.parseDouble(two);
                    tvInput.setText(removeZeroAfterDot(Double.toString(result)));
                }else if (tvValue.contains("+")){
                    String[] splitValue = tvValue.split("\\+");
                    String one = splitValue[0];
                    String two = splitValue[1];
                    if (!prefix.isEmpty()){
                        one = prefix + one;
                    }
                    double result = Double.parseDouble(one)+Double.parseDouble(two);
                    tvInput.setText(removeZeroAfterDot(Double.toString(result)));
                }else if (tvValue.contains("*")){
                    String[] splitValue = tvValue.split("\\*");
                    String one = splitValue[0];
                    String two = splitValue[1];
                    if (!prefix.isEmpty()){
                        one = prefix + one;
                    }
                    double result = Double.parseDouble(one)*Double.parseDouble(two);
                    tvInput.setText(removeZeroAfterDot(Double.toString(result)));
                }
                else if (tvValue.contains("/")){
                    String[] splitValue = tvValue.split("/");
                    String one = splitValue[0];
                    String two = splitValue[1];
                    if (!prefix.isEmpty()){
                        one = prefix + one;
                    }
                    double result = Double.parseDouble(one)/Double.parseDouble(two);
                    tvInput.setText(removeZeroAfterDot(Double.toString(result)));
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private boolean isOperatorUsed(String value){
        if(value.startsWith("-")){
            return false;
        }else if (value.contains("/") || value.contains("*") || value.contains("-") || value.contains("+")){
            return true;
        }
        return false;
    }

    private String removeZeroAfterDot(String result){
        String value = result;
        if (result.contains(".0")){
            value = result.substring(0, result.length() - 2);
        }
        return value;
    }
}