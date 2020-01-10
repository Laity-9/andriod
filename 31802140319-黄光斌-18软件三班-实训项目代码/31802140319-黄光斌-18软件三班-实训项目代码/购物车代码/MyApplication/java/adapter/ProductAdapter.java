package com.example.administrator.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.bean.ProductBean;

import java.util.List;


public class ProductAdapter extends BaseAdapter {

    private LayoutInflater layoutInflater;
    private List list;
    private  Context mContext;

    public ProductAdapter(Context context, List list){
        this.mContext = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.list = list;
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        if (convertView == null){
            convertView = layoutInflater.inflate(R.layout.list_item,null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        ProductBean noteInfo = (ProductBean) getItem(position);
        viewHolder.tvproductName.setText(noteInfo.getName());
        viewHolder.tvproductPrice.setText("￥"+noteInfo.getPrice()+"已购买数量"+noteInfo.getIsShop());
        viewHolder.tvproductSrc.setImageResource(mContext.getResources().getIdentifier(noteInfo.getSrc(),"drawable", mContext.getPackageName()));
        return convertView;
    }

    class ViewHolder{
        TextView tvproductName;
        TextView tvproductPrice;
        ImageView tvproductSrc;

        public ViewHolder (View view){
            tvproductName = (TextView) view.findViewById(R.id.product_name);
            tvproductPrice = (TextView) view.findViewById(R.id.product_price);
            tvproductSrc = (ImageView) view.findViewById(R.id.product_src);
        }
    }

}
