package cn.jianke.ageselector;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.ArrayList;

/**
 * @className: AgeSelectorDialog
 * @classDescription: 年龄选择器
 * @author: leibing
 * @createTime: 2016/09/09
 */
public class AgeSelectorDialog extends Dialog implements View.OnClickListener{
    // 年龄选择列表
    private ListView mAgeSeletorLv;
    // 适配器
    private AgeSelectorAdapter mAdapter;
    // 数据源
    private ArrayList<String> mData;

    public AgeSelectorDialog(Context context) {
        super(context,R.style.Dialog_FS);
        // 设置窗体无标题样式d
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 隐藏状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // 设定布局
        setContentView(R.layout.dialog_age_selector);
        // 获取屏幕宽、高用
        WindowManager m = ((Activity)context).getWindowManager();
        Display d = m.getDefaultDisplay();
        // 设置Dialog底部以及宽高
        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        dialogWindow.setGravity(Gravity.BOTTOM);
        lp.height = (int)(d.getHeight() * 0.65);
        dialogWindow.setAttributes(lp);
        // findView
        mAgeSeletorLv = (ListView) findViewById(R.id.lv_age_selector);
        // 初始化数据源
        initData();
        // 初始化适配
        mAdapter = new AgeSelectorAdapter(context, mData);
        // onClick
        findViewById(R.id.tv_age_selector).setOnClickListener(this);
        mAgeSeletorLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (mDialogCallBack != null && mData != null && i < mData.size()){
                    mDialogCallBack.getAge(mData.get(i));
                    dismiss();
                }
            }
        });
        // 设置对话框的外面点击不让对话框消失
        setCanceledOnTouchOutside(false);
        // 设置适配器
        mAgeSeletorLv.setAdapter(mAdapter);
    }

    /**
     * 初始化数据源
     * @author leibing
     * @createTime 2016/09/09
     * @lastModify 2016/09/09
     * @param
     * @return
     */
    private void initData(){
        mData = new ArrayList<>();
        for (int i = 0;i<= 100;i++){
            mData.add(i + "");
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_age_selector:
                this.dismiss();
                break;
            default:
                break;
        }
    }

    // Dialog回调
    private DialogCallBack mDialogCallBack;

    /**
     * 设置Dialog回调
     * @author leibing
     * @createTime 2016/09/09
     * @lastModify 2016/09/09
     * @param mDialogCallBack dialog回调
     * @return
     */
    public void setDialogCallBack(DialogCallBack mDialogCallBack){
        this.mDialogCallBack = mDialogCallBack;
    }

    /**
     * @interfaceName: DialogCallBack
     * @interfaceDescription: Dialog回调
     * @author: leibing
     * @createTime: 2016/09/09
     */
    public interface DialogCallBack{
        void getAge(String age);
    }
}
