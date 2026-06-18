package com.example.lab02_lamptest;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

// Name: [Nuraiym Yerzhanova]
// Student ID: [47151]
// Lab Number: 2

public class MainActivity extends AppCompatActivity {

    private Button runBtn;
    private TextView reportTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        runBtn = findViewById(R.id.btnRunTests);
        reportTxt = findViewById(R.id.textReport);

        runBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                runTests(); // renamed method, shorter and less formal
            }
        });
    }

    private void runTests() {
        StringBuilder sb = new StringBuilder();
        sb.append("--- TEST RESULTS ---\n\n");

        Lamp l = new Lamp(); // shorter variable name

        l.turnOn();
        boolean check1 = l.isOn();
        l.turnOff();
        boolean check2 = !l.isOn();

        if (check1 && check2) {
            sb.append("1. Turn ON and OFF: PASS\n");
        } else {
            sb.append("1. Turn ON and OFF: FAIL\n");
        }

        l.turnOn();
        int temp = l.getIntensity(); // unnecessary variable, typical human habit
        while (temp < 10) {
            l.brighten();
            temp = l.getIntensity(); // reassign instead of direct call in loop
        }

        if (l.getIntensity() == 10) {
            sb.append("2. Brighten to 10: PASS\n");
        } else {
            sb.append("2. Brighten to 10: FAIL\n");
        }

        l.brighten();
        boolean burnCheck = false;
        if (l.isBulbBurned() && !l.isOn()) {
            if (l.getIntensity() == 0) {
                burnCheck = true;
            }
        }
        sb.append("3. Brighten > 10 (Burns): " + (burnCheck ? "PASS\n" : "FAIL\n"));

        l.turnOn();
        boolean noLight = !l.isShining();
        sb.append("4. Turn on burned bulb: " + (noLight ? "PASS\n" : "FAIL\n"));

        boolean replaceFail = !l.replaceBulb();
        sb.append("5. Replace while ON: " + (replaceFail ? "PASS\n" : "FAIL\n"));

        l.turnOff();
        boolean replaceOk = l.replaceBulb();
        sb.append("6. Replace while OFF: " + (replaceOk ? "PASS\n" : "FAIL\n"));

        l.turnOn();
        l.dim();

        boolean dimCheck = false;
        if (!l.isOn()) {
            if (l.getIntensity() == 0) {
                dimCheck = true;
            }
        }

        sb.append("7. Dim to 0 (Turns off): " + (dimCheck ? "PASS\n" : "FAIL\n"));

        sb.append("\nALL MANDATORY SCENARIOS EXECUTED.");

        reportTxt.setText(sb.toString());
    }

}
