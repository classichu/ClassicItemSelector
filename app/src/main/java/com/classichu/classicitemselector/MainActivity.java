package com.classichu.classicitemselector;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.classichu.classichu.basic.listener.OnNotFastClickListener;
import com.classichu.itemselector.ClassicItemSelectorDataHelper;
import com.classichu.itemselector.bean.ItemSelectBean;
import com.classichu.itemselector.model.ItemSelectorModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView id_gotoselet;
    TextView id_gotoselet2;
    Button id_gotoselet3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        id_gotoselet = (TextView) findViewById(R.id.id_gotoselet);
        id_gotoselet.setOnClickListener(new OnNotFastClickListener() {
            @Override
            protected void onNotFastClick(View view) {
                //
                ClassicItemSelectorDataHelper.setDataAndToItemSelect(id_gotoselet, MainActivity.this,
                        new ItemSelectorModel().gainData("学校"), 3);
            }
        });
        id_gotoselet2 = (TextView) findViewById(R.id.id_gotoselet_2);
        id_gotoselet2.setOnClickListener(new OnNotFastClickListener() {
            @Override
            protected void onNotFastClick(View view) {
                //
                ClassicItemSelectorDataHelper.setDataAndToItemSelect(id_gotoselet2, MainActivity.this,
                        new ItemSelectorModel().gainData("性别"), 1);
            }
        });

        id_gotoselet3 = (Button) findViewById(R.id.id_gotoselet_3);
        id_gotoselet3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                ClassicItemSelectorDataHelper.setDataAndToItemSelect(id_gotoselet3, MainActivity.this,
                        new ItemSelectorModel().gainData("性别"), 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //
        ClassicItemSelectorDataHelper.callAtOnActivityResult(requestCode, resultCode, data, new ClassicItemSelectorDataHelper.ItemSelectBackData() {

            @Override
            public void backData(View clickView, List<ItemSelectBean> itemSelectBeanList) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < itemSelectBeanList.size(); i++) {
                    if (itemSelectBeanList.get(i).isSelected()) {
                        sb.append(itemSelectBeanList.get(i).getItemTitle());
                    }
                }
                if (clickView instanceof Button) {
                    ((Button) clickView).setText("Button:" + sb.toString());
                } else if (clickView instanceof TextView) {
                    ((TextView) clickView).setText(sb.toString());
                }
            }
        });


    }
}
