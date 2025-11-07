package com.example.first;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "BaiTap45_Log";

    // Khai báo các View cho Bài 4
    private EditText editText_Numbers;
    private Button button_Filter;
    private TextView textView_Task4_Result;

    // Khai báo các View cho Bài 5
    private EditText editText_Input;
    private Button button_Process;
    private TextView textView_Result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // === Ánh xạ View BÀI 4 ===
        editText_Numbers = findViewById(R.id.editText_Numbers);
        button_Filter = findViewById(R.id.button_Filter);
        textView_Task4_Result = findViewById(R.id.textView_Task4_Result);

        // === Ánh xạ View BÀI 5 ===
        editText_Input = findViewById(R.id.editText_Input);
        button_Process = findViewById(R.id.button_Process);
        textView_Result = findViewById(R.id.textView_Result);

        // === Thiết lập sự kiện click ===
        setupTask4_Button();
        setupTask5_Button();
    }

    /**
     * BÀI 4: Thiết lập sự kiện click cho nút Lọc Số
     */
    private void setupTask4_Button() {
        button_Filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 1. Lấy chuỗi số từ EditText
                String inputNumbersString = editText_Numbers.getText().toString();

                if (inputNumbersString.trim().isEmpty()) {
                    textView_Task4_Result.setText("Vui lòng nhập mảng số!");
                    return;
                }

                // 2. Tách chuỗi thành mảng các chuỗi số (dựa trên dấu phẩy)
                String[] numberStrings = inputNumbersString.split(",");

                ArrayList<Integer> numbers = new ArrayList<>();
                ArrayList<Integer> evenNumbers = new ArrayList<>(); // Số chẵn
                ArrayList<Integer> oddNumbers = new ArrayList<>();  // Số lẻ

                // 3. Chuyển đổi chuỗi sang số và phân loại
                try {
                    for (String numStr : numberStrings) {
                        // Xóa khoảng trắng thừa
                        int number = Integer.parseInt(numStr.trim());
                        numbers.add(number);

                        // Phân loại
                        if (number % 2 == 0) {
                            evenNumbers.add(number);
                        } else {
                            oddNumbers.add(number);
                        }
                    }
                } catch (NumberFormatException e) {
                    // Xử lý nếu người dùng nhập sai (ví dụ: "1,2,abc,4")
                    textView_Task4_Result.setText("Lỗi: Vui lòng chỉ nhập số và dấu phẩy.");
                    return;
                }

                // 4. Hiển thị kết quả ra TextView
                String resultText = "Mảng ban đầu: " + numbers.toString() + "\n" +
                        "Số chẵn: " + evenNumbers.toString() + "\n" +
                        "Số lẻ: " + oddNumbers.toString();
                textView_Task4_Result.setText(resultText);

                // 5. In ra Log.d (vẫn giữ như yêu cầu ban đầu)
                Log.d(TAG, "Mảng ban đầu: " + numbers.toString());
                Log.d(TAG, "CÁC SỐ CHẴN: " + evenNumbers.toString());
                Log.d(TAG, "CÁC SỐ LẺ: " + oddNumbers.toString());
            }
        });
    }

    /**
     * BÀI 5: Thiết lập sự kiện click cho nút Đảo Chuỗi
     */
    private void setupTask5_Button() {
        button_Process.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputString = editText_Input.getText().toString();
                if (inputString.trim().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập chuỗi!", Toast.LENGTH_SHORT).show();
                    return;
                }
                String[] words = inputString.split(" ");
                StringBuilder reversedStringBuilder = new StringBuilder();
                for (int i = words.length - 1; i >= 0; i--) {
                    reversedStringBuilder.append(words[i]);
                    if (i > 0) {
                        reversedStringBuilder.append(" ");
                    }
                }
                String result = reversedStringBuilder.toString().toUpperCase();
                textView_Result.setText(result);
                Toast.makeText(MainActivity.this, result, Toast.LENGTH_LONG).show();
            }
        });
    }
    
}