package com.veryworks.android.memo2;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import static com.veryworks.android.memo2.R.id.fab;

public class DetailActivity extends AppCompatActivity {
    private static final String TAG = "DetailActivity";
    FloatingActionButton btnSave; // 버튼
    EditText editText; // 입력 위젯

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        editText = (EditText) findViewById(R.id.editText);
        btnSave = (FloatingActionButton) findViewById(fab);

        // 파일의 내용을 불러와서 화면에 뿌려준다.
        String memo = read("filename.txt");
        editText.setText(memo);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 1. 컨텐츠를 가져온다
                String content = editText.getText().toString();
                // 2. 컨텐츠를 파일에 저장한다.
                write(content);
            }
        });
    }

    // 파일읽기
    private String read(String filename){
        String result = "";
        try {
            // 1. 스트림을 열고
            FileInputStream fis = openFileInput(filename);
            // 2. 래퍼가 필요할 경우는 사용 - 문자열 캐릭터셋을 변환해 주는 역할
            // 생략가능
            // 3. 버퍼를 씌워서 속도를 향상시킨후 한줄씩 읽어서 result 결과값에 계속 더해준다.
            BufferedInputStream bis = new BufferedInputStream(fis);
            // 4. 내가 한번 읽어올 단위를 설정
            byte buffer[] = new byte[1024];

            int count = 0;
                   // 버퍼로 떴는데 몇글자가 들어있는지를 count 에 담아준다.
            while((count = bis.read(buffer)) != -1  ){

                String data = new String(buffer, 0, count);

                result = result + data;
            }
            // 4. 스트림을 닫는다.
            bis.close();
            fis.close();

        }catch(Exception e){
            Log.e(TAG, e.toString());
        }
        return result;
    }


    // 파일쓰기
    private void write(String value){
        try {
            // 1. 스트림을 열어야 됩니다.
            FileOutputStream fos = openFileOutput("filename.txt", MODE_PRIVATE);
            // 2. 스트림을 통해서 데이터를 쓰고
            fos.write(value.getBytes());
            fos.close();
            Log.i(TAG, "=============================== 파일 생성된거여~");
            // 3. 스트림을 닫아준다.
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
    }
}
