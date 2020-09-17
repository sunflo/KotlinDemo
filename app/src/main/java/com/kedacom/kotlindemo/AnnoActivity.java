package com.kedacom.kotlindemo;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.kedacom.flo.anno.AutoBind;
import com.kedacom.flo.anno.BindView;

public class AnnoActivity extends AppCompatActivity {
    @BindView(R.id.tv_anno)
    public TextView tvAnno;
    @BindView(R.id.btn_anno)
    public Button btnAnno;
    @BindView(R.id.iv_anno)
    public ImageView ivAnno;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anno);
        AutoBind.getInstance().inject(this);
        tvAnno.setText("111111111111111111111");
    }
}