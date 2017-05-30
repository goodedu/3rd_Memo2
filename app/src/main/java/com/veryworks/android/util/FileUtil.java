package com.veryworks.android.util;

import android.content.Context;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by pc on 5/30/2017.
 */
public class FileUtil {
    private static final String TAG = "FileUtil";

    // 파일읽기
    public static String read(Context context,  String filename){
        String result = "";
        try {
            // 1. 스트림을 열고
            FileInputStream fis = context.openFileInput(filename);
            // 2. 래퍼가 필요할 경우는 사용 - 문자열 캐릭터셋을 변환해 주는 역할
            // 생략가능
            // 3. 버퍼를 씌워서 속도를 향상시킨후 한줄씩 읽어서 result 결과값에 계속 더해준다.
            BufferedInputStream bis = new BufferedInputStream(fis);
            // 4. 내가 한번 읽어올 단위를 설정
            byte buffer[] = new byte[1024];
            int count = 0;
            // 5. 버퍼로 읽은 후에 몇글자가 들어있는지를 count 에 담아준다.
            while((count = bis.read(buffer)) != -1  ){
                // 5.1 버퍼에 담긴 글자만큼만 문자열로 변환하고
                String data = new String(buffer, 0, count);
                // 5.2 결과값 변수에 더해준다.
                result = result + data;
            }
            // 6. 스트림을 닫는다.
            bis.close();
            fis.close();
        }catch(Exception e){
            Log.e(TAG, e.toString());
        }
        return result;
    }

    // 파일쓰기
    public static void write(Context context, String value){
        try {
            // 1. 스트림을 열어야 됩니다.
            FileOutputStream fos = context.openFileOutput("filename.txt", MODE_PRIVATE);
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
