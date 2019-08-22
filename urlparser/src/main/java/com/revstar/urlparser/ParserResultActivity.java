package com.revstar.urlparser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.revstar.urlparser.adapter.ResultAdapter;

import java.util.ArrayList;

public class ParserResultActivity extends AppCompatActivity {

    private ArrayList<String>urlList;
    private RecyclerView rvResult;
    private ResultAdapter mResultAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parser_result);
        rvResult=findViewById(R.id.rv_result);
        urlList=getIntent().getStringArrayListExtra("urlList");

        if (urlList!=null){
            mResultAdapter=new ResultAdapter(this,R.layout.result_item,urlList);
            GridLayoutManager manager=new GridLayoutManager(this,3,RecyclerView.VERTICAL,false);
            rvResult.setLayoutManager(manager);
            rvResult.setAdapter(mResultAdapter);
        }

    }
}
