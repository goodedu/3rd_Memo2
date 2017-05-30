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

        // 파일의 내용을 불러와서 화면에 뿌려준다.
        String memo = FileUtil.read(getBaseContext(), "filename.txt");
        editText.setText(memo);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 1. 컨텐츠를 가져온다
                String content = editText.getText().toString();
                // 2. 컨텐츠를 파일에 저장한다.
                FileUtil.write(getBaseContext(), content);
            }
        });
    }
}
