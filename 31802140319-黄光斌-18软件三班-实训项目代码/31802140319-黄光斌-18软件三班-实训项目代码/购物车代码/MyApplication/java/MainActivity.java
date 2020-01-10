package com.example.administrator.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myapplication.adapter.ProductAdapter;
import com.example.administrator.myapplication.bean.ProductBean;
import com.example.administrator.myapplication.database.SQLiteHelper;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    SQLiteHelper sh;
    Button btn;
    ListView lv;
    List list;
    String state;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sh = new SQLiteHelper(MainActivity.this);
        btn = (Button) findViewById(R.id.add);
        lv = (ListView) findViewById(R.id.listview);
        tv = (TextView) findViewById(R.id.note_name);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                change("0".equals(state)?0:1);
            }
        });

        sh.clear();
        sh.add("猫",12.12,"cat",0);
        sh.add("狗",11.11,"dog",0);
        sh.add("袋鼠",123,"daishu",0);
        sh.add("波斯猫",200,"bosimao",0);
        sh.add("仓鼠",20,"cangshu",0);
        sh.add("柴犬",40,"chaiquan",0);
        sh.add("布偶猫",90,"buoumao",0);
        sh.add("土拨鼠",25,"tuboshu",0);
        sh.add("松鼠",20,"songshu",0);
        sh.add("秋田犬",40,"qiutianquan",0);




        change(0);
    }

    public void change(final int isShop) {
        list = sh.query(isShop);
        lv.setAdapter(new ProductAdapter(MainActivity.this,list));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ProductBean productBean = (ProductBean)list.get(position);
                sh.update(productBean.getId(),isShop==1?(Integer.parseInt(productBean.getIsShop())-1):(Integer.parseInt(productBean.getIsShop())+1));
                list = sh.query(isShop);
                lv.setAdapter(new ProductAdapter(MainActivity.this,list));
                String message = isShop==0?"添加购物车成功":"移出购物车成功";
                Toast.makeText(MainActivity.this,message,Toast.LENGTH_LONG).show();
            }
        });
        tv.setText(isShop==0?"购物商城":"购物车");
        btn.setText(isShop==0?"购物车":"购物商城");
        state = isShop==1?"0":"1";
    }
}
