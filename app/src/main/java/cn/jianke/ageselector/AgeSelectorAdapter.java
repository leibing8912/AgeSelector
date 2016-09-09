package cn.jianke.ageselector;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * @className: AgeSelectorAdapter
 * @classDescription: 年龄选择适配器
 * @author: leibing
 * @createTime: 2016/09/09
 */
public class AgeSelectorAdapter extends BaseAdapter{
    // 数据源
    private ArrayList<String>  mData;
    // 布局
    private LayoutInflater mLayoutInflater;

    /**
     *
     * @author leibing
     * @createTime 2016/09/09
     * @lastModify 2016/09/09
     * @param context 上下文
     * @param mData 数据源
     * @return
     */
    public AgeSelectorAdapter(Context context,ArrayList<String>  mData){
        mLayoutInflater = LayoutInflater.from(context);
        this.mData = mData;
    }

    @Override
    public int getCount() {
        return mData != null ? mData.size():0;
    }

    @Override
    public Object getItem(int i) {
        return mData != null ? mData.get(i):null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null){
            view = mLayoutInflater.inflate(R.layout.adapter_age_seletor_item, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }

        if (i < mData.size())
            viewHolder.updateUI(mData.get(i));

        return view;
    }

    static class ViewHolder{
        // 年龄
        private TextView mAgeSeletorItemTv;

        public ViewHolder(View view){
            mAgeSeletorItemTv = (TextView) view.findViewById(R.id.tv_age_selector_item);
        }

        public void updateUI(String age){
            if (age != null && !age.equals(""))
                mAgeSeletorItemTv.setText(age + " 岁");
        }
    }
}
