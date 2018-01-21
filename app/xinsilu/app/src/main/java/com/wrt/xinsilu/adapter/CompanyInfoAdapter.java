package com.wrt.xinsilu.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.wrt.xinsilu.R;
import com.wrt.xinsilu.basic.BasicAdapter;
import com.wrt.xinsilu.bean.CompanyInfoBean;
import com.wrt.xinsilu.lerist.interfaces.Selectable;

import java.util.List;

/**
 * Created by Administrator on 2016/8/10 0010.
 */
public class CompanyInfoAdapter extends BasicAdapter<CompanyInfoBean> implements Selectable {
    CompanyInfoBean bean;
    private boolean isSelect;

    public CompanyInfoAdapter(Context context, List<CompanyInfoBean> list) {
        super(context, list);
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null) {
            holder = new ViewHolder();
            view = getConvertView(R.layout.company_adapter);
            holder.text_name = (TextView) view.findViewById(R.id.company_text);
            holder.company_info = (EditText) view.findViewById(R.id.company_info);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.text_name.setText(list.get(i).getText_first());
//        holder.company_info.setHint(list.get(i).getHint());
//        holder.company_info.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int oisition, int i1, int i2) {
//                list.get(i).setCompany_info(charSequence.toString());
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//
//            }
//        });
//        holder.company_info.setInputType(InputType.TYPE_CLASS_TEXT);
//        holder.company_info.setFilters(new InputFilter[]{new InputFilter.LengthFilter(Integer.MAX_VALUE)});
//        if(i == 3 || i == 5 || i == 7|| i == 8 || i == 9 || i == 12){
//            holder.company_info.setInputType(InputType.TYPE_CLASS_NUMBER);
//            holder.company_info.setFilters(new InputFilter[]{new InputFilter.LengthFilter(11)});
//        }
//        if(i == 6 || i == 10){
//            holder.company_info.setKeyListener(new NumberKeyListener() {
//                @Override
//                protected char[] getAcceptedChars() {
//                char[] chr = new char[]{'a','b','P','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','0','1','2','3','4','5','6','7','8','9'};
//                    return chr;
//                }
//
//                @Override
//                public int getInputType() {
//                    return InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS;
//                }
//            });
//        }
        holder.company_info.setText(list.get(i).getCompany_info());
        holder.company_info.setEnabled(false);
//        holder.company_info.setEnabled(isSelect);
        return view;
    }

    @Override
    public boolean isSelectable() {
        return isSelect;
    }

    @Override
    public void setSelectable(boolean isSelectable) {
        isSelect = isSelectable;
    }

    class ViewHolder {
        /**
         * 公司信息第一个
         */
        TextView text_name;
        /**
         * 公司信息填写
         */
        EditText company_info;
    }
}
