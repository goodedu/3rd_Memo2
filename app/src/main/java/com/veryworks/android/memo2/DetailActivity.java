package com.veryworks.android.memo2;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.veryworks.android.util.FileUtil;

public class DetailActivity extends AppCompatActivity {
    private static final String TAG = "DetailActivity";
    FloatingActionButton btnSave; // 버튼
    EditText editText; // 입력 위젯

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        editText = (EditText) findViewById(R.id.editText);
        btnSave = (FloatingActionButton) findViewById(R.id.fab);

        // 가. 액티비티가 실행되면 파일의 내용을 불러와서 화면에 뿌려준다.
        String memo = FileUtil.read(getBaseContext(), "filename.txt");
        editText.setText(memo);

        // 나. 버튼이 클릭되면 메모를 저장한다.
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 1. 메모의 내용을 가져온다
                String content = editText.getText().toString();
                // 2. 파일이름을 생성한다
                String filename = "MEMO"+System.currentTimeMillis()+".txt";
                // 3. 메모를 파일에 저장한다.
                FileUtil.write(getBaseContext(), filename, content);
            }
        });
    }
}
