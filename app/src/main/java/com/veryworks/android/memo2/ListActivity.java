package com.veryworks.android.memo2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.veryworks.android.memo2.domain.Loader;
import com.veryworks.android.memo2.domain.Memo;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    RecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        // 1. 데이터
        ArrayList<Memo> datas = Loader.getData(this);
        // 2. 아답터
        adapter = new RecyclerAdapter(datas);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setAdapter(adapter);
        // 3. 레이아웃 매니저
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.btnNew);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListActivity.this, DetailActivity.class);
                startActivity(intent);
            }
        });
    }

    // 디테일 액티비티를 열었다가 종료하면 호출될때
    // 데이터를 갱신하고, 목록을 다시 그려준다.
    @Override
    protected void onResume() {
        super.onResume();
        /* 아래 코드는 최초 두번 호출되는 문제점이 있다.
          - 각자 해결 */
        // 데이터를 갱신
        Loader.getData(this);
        // 아답터를 갱신
        adapter.notifyDataSetChanged();
    }
}
