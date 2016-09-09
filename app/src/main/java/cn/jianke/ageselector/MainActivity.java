package cn.jianke.ageselector;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;
/**
 * @className: MainActivity
 * @classDescription: 首页
 * @author: leibing
 * @createTime: 2016/09/09
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // onClick
        findViewById(R.id.btn_age_selector).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AgeSelectorAlterDialog.show(MainActivity.this, new AgeSelectorDialog.DialogCallBack() {
                    @Override
                    public void getAge(String age) {
                        Toast.makeText(MainActivity.this, age + "岁", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
