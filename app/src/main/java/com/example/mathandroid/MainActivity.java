package com.example.mathandroid;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private LinearLayout formLiniowe, formKwadratowe, formLogarytmiczne, formUklad;
    private TextView textResult, titleView;
    private EditText inputA, inputB, inputC, inputKA, inputKB, inputKC;
    private EditText inputUA1, inputUB1, inputUC1, inputUA2, inputUB2, inputUC2;
    private Button btnSolve, btnLiniowe, btnKwadratowe, btnLogarytmiczne, btnUklad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        formLiniowe = findViewById(R.id.form_liniowe);
        formKwadratowe = findViewById(R.id.form_kwadratowe);
        formLogarytmiczne = findViewById(R.id.form_logarytmiczne);
        formUklad = findViewById(R.id.form_uklad);
        textResult = findViewById(R.id.text_result);
        btnSolve = findViewById(R.id.btn_solve);

        btnLiniowe = findViewById(R.id.btn_liniowe);
        btnKwadratowe = findViewById(R.id.btn_kwadratowe);
        btnLogarytmiczne = findViewById(R.id.btn_logarytmiczne);
        btnUklad = findViewById(R.id.btn_uklad);

        // Set button listeners
        btnLiniowe.setOnClickListener(v -> showForm("liniowe"));
        btnKwadratowe.setOnClickListener(v -> showForm("kwadratowe"));
        btnLogarytmiczne.setOnClickListener(v -> showForm("logarytmiczne"));
        btnUklad.setOnClickListener(v -> showForm("uklad"));

        btnSolve.setOnClickListener(v -> solveEquation());
    }

    private void showForm(String type) {
        // Hide all forms
        formLiniowe.setVisibility(View.GONE);
        formKwadratowe.setVisibility(View.GONE);
        formLogarytmiczne.setVisibility(View.GONE);
        formUklad.setVisibility(View.GONE);
        textResult.setVisibility(View.GONE);
        btnSolve.setVisibility(View.VISIBLE);

        // Show the selected form
        switch (type) {
            case "liniowe":
                formLiniowe.setVisibility(View.VISIBLE);
                break;
            case "kwadratowe":
                formKwadratowe.setVisibility(View.VISIBLE);
                break;
            case "logarytmiczne":
                formLogarytmiczne.setVisibility(View.VISIBLE);
                break;
            case "uklad":
                formUklad.setVisibility(View.VISIBLE);
                break;
        }
    }

    private void solveEquation() {
        try {
            textResult.setVisibility(View.VISIBLE);
            if (formLiniowe.getVisibility() == View.VISIBLE) {
                // Solve linear equation
                inputA = findViewById(R.id.input_a);
                inputB = findViewById(R.id.input_b);
                double a = Double.parseDouble(inputA.getText().toString());
                double b = Double.parseDouble(inputB.getText().toString());
                if (a == 0) {
                    textResult.setText("Równanie sprzeczne (brak rozwiązań)");
                } else {
                    double x = -b / a;
                    textResult.setText("x = " + x);
                }
            } else if (formKwadratowe.getVisibility() == View.VISIBLE) {
                // Solve quadratic equation
                inputKA = findViewById(R.id.input_ka);
                inputKB = findViewById(R.id.input_kb);
                inputKC = findViewById(R.id.input_kc);
                double a = Double.parseDouble(inputKA.getText().toString());
                double b = Double.parseDouble(inputKB.getText().toString());
                double c = Double.parseDouble(inputKC.getText().toString());
                double delta = b * b - 4 * a * c;
                if (delta < 0) {
                    textResult.setText("Brak rozwiązań (delta < 0)");
                } else if (delta == 0) {
                    double x = -b / (2 * a);
                    textResult.setText("x = " + x);
                } else {
                    double x1 = (-b + Math.sqrt(delta)) / (2 * a);
                    double x2 = (-b - Math.sqrt(delta)) / (2 * a);
                    textResult.setText("x₁ = " + x1 + ", x₂ = " + x2);
                }
            } else if (formLogarytmiczne.getVisibility() == View.VISIBLE) {
                // Solve logarithmic equation
                inputA = findViewById(R.id.input_log_a);
                inputB = findViewById(R.id.input_log_b);
                double a = Double.parseDouble(inputA.getText().toString());
                double b = Double.parseDouble(inputB.getText().toString());
                if (a <= 0 || a == 1 || b <= 0) {
                    textResult.setText("Błędne dane (a ≤ 0 lub a = 1 lub b ≤ 0)");
                } else {
                    double x = Math.log(b) / Math.log(a);
                    textResult.setText("x = " + x);
                }
            } else if (formUklad.getVisibility() == View.VISIBLE) {
                // Solve system of equations
                inputUA1 = findViewById(R.id.input_u_a1);
                inputUB1 = findViewById(R.id.input_u_b1);
                inputUC1 = findViewById(R.id.input_u_c1);
                inputUA2 = findViewById(R.id.input_u_a2);
                inputUB2 = findViewById(R.id.input_u_b2);
                inputUC2 = findViewById(R.id.input_u_c2);

                double a1 = Double.parseDouble(inputUA1.getText().toString());
                double b1 = Double.parseDouble(inputUB1.getText().toString());
                double c1 = Double.parseDouble(inputUC1.getText().toString());
                double a2 = Double.parseDouble(inputUA2.getText().toString());
                double b2 = Double.parseDouble(inputUB2.getText().toString());
                double c2 = Double.parseDouble(inputUC2.getText().toString());

                double D = a1 * b2 - b1 * a2;
                double Dx = c1 * b2 - c2 * b1;
                double Dy = a1 * c2 - a2 * c1;

                if (D == 0) {
                    if (Dx == 0 && Dy == 0) {
                        textResult.setText("Układ ma nieskończenie wiele rozwiązań");
                    } else {
                        textResult.setText("Układ jest sprzeczny - brak rozwiązań");
                    }
                } else {
                    double x = Dx / D;
                    double y = Dy / D;
                    textResult.setText("x = " + x + ", y = " + y);
                }
            }
        } catch (Exception e) {
            textResult.setText("Błędne dane");
        }
    }
}