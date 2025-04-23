package com.example.mathandroid;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private LinearLayout inputContainer;
    private TextView resultView;
    private Button btnLiniowe, btnKwadratowe, btnLogarytmiczne, btnUklad;
    private EditText aInput, bInput, cInput, a1Input, b1Input, c1Input, a2Input, b2Input, c2Input;
    private Button solveBtn;

    private String currentType = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputContainer = findViewById(R.id.input_container);
        resultView = findViewById(R.id.result_view);

        btnLiniowe = findViewById(R.id.btn_liniowe);
        btnKwadratowe = findViewById(R.id.btn_kwadratowe);
        btnLogarytmiczne = findViewById(R.id.btn_logarytmiczne);
        btnUklad = findViewById(R.id.btn_uklad);

        btnLiniowe.setOnClickListener(v -> showLiniowe());
        btnKwadratowe.setOnClickListener(v -> showKwadratowe());
        btnLogarytmiczne.setOnClickListener(v -> showLogarytmiczne());
        btnUklad.setOnClickListener(v -> showUklad());
    }

    private void clearInputs() {
        inputContainer.removeAllViews();
        resultView.setText("");
    }

    private void showLiniowe() {
        clearInputs();
        currentType = "liniowe";
        aInput = new EditText(this); aInput.setHint("A");
        bInput = new EditText(this); bInput.setHint("B");
        solveBtn = new Button(this); solveBtn.setText("Rozwiąż");
        inputContainer.addView(aInput);
        inputContainer.addView(bInput);
        inputContainer.addView(solveBtn);

        solveBtn.setOnClickListener(v -> {
            try {
                double a = Double.parseDouble(aInput.getText().toString());
                double b = Double.parseDouble(bInput.getText().toString());
                if (a == 0) {
                    resultView.setText("Równanie sprzeczne (brak rozwiązań)");
                } else {
                    double x = -b / a;
                    resultView.setText("x = " + x);
                }
            } catch (Exception e) {
                resultView.setText("Błędne dane");
            }
        });
    }

    private void showKwadratowe() {
        clearInputs();
        currentType = "kwadratowe";
        aInput = new EditText(this); aInput.setHint("a");
        bInput = new EditText(this); bInput.setHint("b");
        cInput = new EditText(this); cInput.setHint("c");
        solveBtn = new Button(this); solveBtn.setText("Rozwiąż");
        inputContainer.addView(aInput);
        inputContainer.addView(bInput);
        inputContainer.addView(cInput);
        inputContainer.addView(solveBtn);

        solveBtn.setOnClickListener(v -> {
            try {
                double a = Double.parseDouble(aInput.getText().toString());
                double b = Double.parseDouble(bInput.getText().toString());
                double c = Double.parseDouble(cInput.getText().toString());
                double delta = b * b - 4 * a * c;
                if (delta < 0) {
                    resultView.setText("Brak rozwiązań (delta < 0)");
                } else if (delta == 0) {
                    double x = -b / (2 * a);
                    resultView.setText("x = " + x);
                } else {
                    double x1 = (-b + Math.sqrt(delta)) / (2 * a);
                    double x2 = (-b - Math.sqrt(delta)) / (2 * a);
                    resultView.setText("x₁ = " + x1 + ", x₂ = " + x2);
                }
            } catch (Exception e) {
                resultView.setText("Błędne dane");
            }
        });
    }

    private void showLogarytmiczne() {
        clearInputs();
        currentType = "logarytmiczne";
        aInput = new EditText(this); aInput.setHint("Podstawa (a)");
        bInput = new EditText(this); bInput.setHint("Liczba (b)");
        solveBtn = new Button(this); solveBtn.setText("Rozwiąż");
        inputContainer.addView(aInput);
        inputContainer.addView(bInput);
        inputContainer.addView(solveBtn);

        solveBtn.setOnClickListener(v -> {
            try {
                double a = Double.parseDouble(aInput.getText().toString());
                double b = Double.parseDouble(bInput.getText().toString());
                if (a <= 0 || a == 1 || b <= 0) {
                    resultView.setText("Błędne dane (a ≤ 0 lub a = 1 lub b ≤ 0)");
                } else {
                    double x = Math.log(b) / Math.log(a);
                    resultView.setText("x = " + x);
                }
            } catch (Exception e) {
                resultView.setText("Błędne dane");
            }
        });
    }

    private void showUklad() {
        clearInputs();
        currentType = "uklad";
        a1Input = new EditText(this); a1Input.setHint("a1");
        b1Input = new EditText(this); b1Input.setHint("b1");
        c1Input = new EditText(this); c1Input.setHint("c1");
        a2Input = new EditText(this); a2Input.setHint("a2");
        b2Input = new EditText(this); b2Input.setHint("b2");
        c2Input = new EditText(this); c2Input.setHint("c2");
        solveBtn = new Button(this); solveBtn.setText("Rozwiąż");
        inputContainer.addView(a1Input);
        inputContainer.addView(b1Input);
        inputContainer.addView(c1Input);
        inputContainer.addView(a2Input);
        inputContainer.addView(b2Input);
        inputContainer.addView(c2Input);
        inputContainer.addView(solveBtn);

        solveBtn.setOnClickListener(v -> {
            try {
                double a1 = Double.parseDouble(a1Input.getText().toString());
                double b1 = Double.parseDouble(b1Input.getText().toString());
                double c1 = Double.parseDouble(c1Input.getText().toString());
                double a2 = Double.parseDouble(a2Input.getText().toString());
                double b2 = Double.parseDouble(b2Input.getText().toString());
                double c2 = Double.parseDouble(c2Input.getText().toString());

                double D = a1 * b2 - b1 * a2;
                double Dx = c1 * b2 - c2 * b1;
                double Dy = a1 * c2 - a2 * c1;

                if (D == 0) {
                    if (Dx == 0 && Dy == 0) {
                        resultView.setText("Układ ma nieskończenie wiele rozwiązań");
                    } else {
                        resultView.setText("Układ jest sprzeczny - brak rozwiązań");
                    }
                } else {
                    double x = Dx / D;
                    double y = Dy / D;
                    resultView.setText("x = " + x + ", y = " + y);
                }
            } catch (Exception e) {
                resultView.setText("Błędne dane");
            }
        });
    }
}
