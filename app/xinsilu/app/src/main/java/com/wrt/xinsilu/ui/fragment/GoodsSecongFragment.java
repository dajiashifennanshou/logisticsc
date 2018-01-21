package com.wrt.xinsilu.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.socks.library.KLog;
import com.wrt.xinsilu.R;
import com.wrt.xinsilu.bean.Cargo;
import com.wrt.xinsilu.bean.ResultCode;
import com.wrt.xinsilu.lerist.client.OrderClient;
import com.wrt.xinsilu.lerist.interfaces.InputKeyValue;
import com.wrt.xinsilu.lerist.model.CargoPackType;
import com.wrt.xinsilu.lerist.ui.dialog.WheelPopupWindow;
import com.xsl.lerist.llibrarys.fragments.LFragment;
import com.xsl.lerist.llibrarys.model.ResultInfo;
import com.xsl.lerist.llibrarys.utils.StringUtils;
import com.xsl.lerist.llibrarys.widget.LProgressDialog;
import com.xsl.lerist.llibrarys.widget.LToast;

import org.xutils.common.Callback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/6/27 0027.
 */
public class GoodsSecongFragment extends LFragment implements View.OnClickListener, InputKeyValue {

    @BindView(R.id.goods_second_ll_goods)
    LinearLayout ll_goods;

    /**
     * 添加货物按钮
     */
    private LinearLayout add_goods;
    /**
     * fragment加载的view
     */
    private View v;
    /**
     * 这是一个测试
     */
    private List<Cargo> cargos = new ArrayList<>();
    private OrderClient orderClient;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.goods_second_fragment, null);
        ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initData();
        initViews();

    }

    private void initData() {
        orderClient = new OrderClient();
    }

    private void initViews() {
        addGoodsInfoItemView();
        add_goods = (LinearLayout) v.findViewById(R.id.add_goods);
        add_goods.setOnClickListener(this);

    }

    private void addGoodsInfoItemView() {
        final View itemGoodsInfoView = View.inflate(getContext(), R.layout.goods_layout, null);
        ll_goods.addView(itemGoodsInfoView);

        final TextView tv_pack_type = (TextView) itemGoodsInfoView.findViewById(R.id.goods_pack_type);
        ((View) tv_pack_type.getParent()).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectPackType(tv_pack_type);
            }
        });
        View btn_delete = itemGoodsInfoView.findViewById(R.id.goods_btn_delete);
        if (ll_goods.getChildCount() == 1) {
            btn_delete.setVisibility(View.GONE);
        } else {
            btn_delete.setVisibility(View.VISIBLE);
        }
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ll_goods.removeView(itemGoodsInfoView);
            }
        });
    }

    /**
     * 选择包装信息
     *
     * @param tv_pack_type
     */
    private void selectPackType(final TextView tv_pack_type) {
        LProgressDialog.show(context, "");
        orderClient.getPackType(new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                KLog.i(result);
                ResultInfo resultInfo = ResultInfo.toResultInfo(result);
                if (resultInfo == null) {
                    return;
                }
                switch (resultInfo.getCode()) {
                    case ResultCode.SUCCEED:
                        final List<CargoPackType> cargoPackTypes = JSON.parseArray(resultInfo.getData(), CargoPackType.class);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                WheelPopupWindow<CargoPackType> wheelPopupWindow = new WheelPopupWindow<>(context, cargoPackTypes.toArray(new CargoPackType[]{}),
                                        new WheelPopupWindow.OnSelectListener<CargoPackType>() {
                                            @Override
                                            public void onCancel() {

                                            }

                                            @Override
                                            public void onOk(CargoPackType selectedData) {
                                                tv_pack_type.setText(selectedData.getName());
                                                tv_pack_type.setTag(selectedData);
                                            }
                                        });
                                wheelPopupWindow.showAtLocation(((View) tv_pack_type.getParent()), Gravity.BOTTOM, 0, 0);
                            }
                        });
                        break;
                    case ResultCode.FAILURE:
                        LToast.show(context, StringUtils.isEmpty(resultInfo.getMsg()) ? "获取包装类型失败, 请稍后再试" : resultInfo.getMsg());
                        break;
                    case ResultCode.NOPERMISSION:

                        break;
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                ex.printStackTrace();
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                LProgressDialog.dismiss();
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (add_goods == view) {
            View v = ll_goods.getChildAt(ll_goods.getChildCount() - 1);
            if (!getCargo(v)) {
                return;
            }

            //禁止再次编辑
//            View overlay = v.findViewById(R.id.goods_layout_ll_overlay);
//            overlay.setVisibility(View.VISIBLE);
            addGoodsInfoItemView();
        }
    }

    private boolean getCargo(View v) {
        String name = ((EditText) v.findViewById(R.id.goods_name)).getText().toString();
        String brand = ((EditText) v.findViewById(R.id.goods_brand)).getText().toString();
        String model = ((EditText) v.findViewById(R.id.goods_size)).getText().toString();
        String num = ((EditText) v.findViewById(R.id.goods_num)).getText().toString();
        String set_num = ((EditText) v.findViewById(R.id.goods_set_num)).getText().toString();
        String weight = ((EditText) v.findViewById(R.id.goods_weight)).getText().toString();
        String volume = ((EditText) v.findViewById(R.id.goods_volume)).getText().toString();
        TextView tv_packType = (TextView) v.findViewById(R.id.goods_pack_type);
        String goodsPackType = tv_packType.getTag() == null ? "" : ((CargoPackType) tv_packType.getTag()).getId() + "";

        if (StringUtils.isExistEmpty(name,
                brand,
                model,
                num,
                set_num,
                weight,
                volume,
                goodsPackType)) {
            LToast.show(getActivity(), "请完善货物信息");
            return false;
        }

        Cargo cargo = new Cargo();
        cargo.setName(name);
        cargo.setBrand(brand);
        cargo.setModel(model);
        cargo.setNumber(num);
        cargo.setSet_number(set_num);
        double weightValue = 0;
        double volumeValue = 0;
        try {
            weightValue = Double.parseDouble(weight);
        } catch (NumberFormatException e) {
            LToast.show(context,"货物重量输入有误");
            return false;
        }
        try {
            volumeValue = Double.parseDouble(volume);
        } catch (NumberFormatException e) {
            LToast.show(context,"货物体积输入有误");
            return false;
        }
        cargo.setSingle_weight(weightValue);
        cargo.setSingle_volume(volumeValue);
        cargo.setPackage_type(goodsPackType);

        cargos.add(cargo);
        return true;
    }

    @Override
    public HashMap<String, Object> getInputData() {
        HashMap<String, Object> inputData = new HashMap<>();
        if (!getCargo(ll_goods.getChildAt(ll_goods.getChildCount() - 1))) {
            return null;
        }
        inputData.put("cargos", cargos);
        double estimate_weight = 0;
        double estimate_volume = 0;
        for (Cargo cargo : cargos) {
            estimate_volume += cargo.getSingle_volume();
            estimate_weight += cargo.getSingle_weight();
        }
        inputData.put("estimate_weight", estimate_weight);
        inputData.put("estimate_volume", estimate_volume);
        return inputData;
    }
}
