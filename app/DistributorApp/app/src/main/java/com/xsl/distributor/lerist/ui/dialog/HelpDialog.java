package com.xsl.distributor.lerist.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xsl.distributor.R;
import com.xsl.lerist.llibrarys.utils.StringUtils;
import com.xsl.lerist.llibrarys.widget.LToast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by Lerist on 2016/7/11, 001.
 */

public class HelpDialog extends Dialog {


    @BindView(R.id.d_help_tv_phone)
    TextView tv_phone;
    private String mPhone;

    public HelpDialog(Context context) {
        super(context, R.style.TranslucentAlertDialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootView = View.inflate(getContext(), R.layout.dialog_help, null);
        setContentView(rootView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        ButterKnife.bind(this);

        if (!StringUtils.isEmpty(mPhone)) {
            tv_phone.setText(mPhone);
        }

    }

    public HelpDialog setPhone(String phone) {
        this.mPhone = phone;
        return this;
    }

    @OnClick({R.id.d_help_btn_cancel, R.id.d_help_btn_ok})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.d_help_btn_cancel:
                dismiss();
                break;
            case R.id.d_help_btn_ok:
                Intent intent = new Intent(Intent.ACTION_DIAL);
                if (StringUtils.isEmpty(mPhone)) {
                    LToast.show(getContext(), "号码有误, 请刷新再试");
                    return;
                }
                Uri data = Uri.parse("tel:" + mPhone);
                intent.setData(data);
                getContext().startActivity(intent);
                dismiss();
                break;
        }
    }

}
