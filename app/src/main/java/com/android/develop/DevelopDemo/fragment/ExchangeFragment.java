package com.android.develop.DevelopDemo.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.android.develop.DevelopDemo.R;
import com.android.develop.DevelopDemo.base.BaseFragment;
import com.android.develop.DevelopDemo.base.BaseObserver;
import com.android.develop.DevelopDemo.http.HttpAction;
import com.android.develop.DevelopDemo.listener.CallBackListener;
import com.android.develop.DevelopDemo.response.ExchangeCurrencyResponse;
import com.android.develop.DevelopDemo.response.ExchangeListResponse;
import com.android.develop.DevelopDemo.util.ToastUtil;
import com.android.develop.DevelopDemo.wight.customVheelView.AbstractWheel;
import com.android.develop.DevelopDemo.wight.customVheelView.ListWheelAdapter;
import com.android.develop.DevelopDemo.wight.customVheelView.OnWheelScrollListener;
import com.android.develop.DevelopDemo.wight.customVheelView.WheelVerticalView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zyp on 2019/7/19 0019.
 * class note:
 */

public class ExchangeFragment extends BaseFragment {

    private TextView tv_from_name;
    private TextView tv_from_code;
    private TextView tv_from_result;
    private WheelVerticalView wv_choose_from;
    private TextView tv_to_name;
    private TextView tv_to_code;
    private TextView tv_to_result;
    private WheelVerticalView wv_choose_to;

    private List<ExchangeListResponse.ResultBean.ListBean> resultList;
    private ListWheelAdapter wheelAdapter;

    private ExchangeListResponse.ResultBean.ListBean exchangeFrom;
    private ExchangeListResponse.ResultBean.ListBean exchangeTo;

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        tv_from_name = view.findViewById(R.id.tv_from_name);
        tv_from_code = view.findViewById(R.id.tv_from_code);
        tv_from_result = view.findViewById(R.id.tv_from_result);
        wv_choose_from = view.findViewById(R.id.wv_choose_from);
        tv_to_name = view.findViewById(R.id.tv_to_name);
        tv_to_code = view.findViewById(R.id.tv_to_code);
        tv_to_result = view.findViewById(R.id.tv_to_result);
        wv_choose_to = view.findViewById(R.id.wv_choose_to);

        resultList = new ArrayList<>();
        wheelAdapter = new ListWheelAdapter(getContext(), resultList);
        wheelAdapter.setItemResource(R.layout.item_wheel_choose_list);
        wheelAdapter.setItemTextResource(R.id.tv_item);
    }

    @Override
    protected void initData() {
        //获取汇率列表数据
        String jsonStr = getString(R.string.exchange_list_response);

        ExchangeListResponse exchangeBean = JSON.parseObject(jsonStr, ExchangeListResponse.class);
        resultList.addAll(exchangeBean.getResult().getList());

        wv_choose_from.setViewAdapter(wheelAdapter);
        wv_choose_to.setViewAdapter(wheelAdapter);

        int itemFrom = wv_choose_from.getCurrentItem();
        exchangeFrom = resultList.get(itemFrom);

        int itemTo = wv_choose_to.getCurrentItem();
        exchangeTo = resultList.get(itemTo);

        setSelectItem(exchangeFrom, exchangeTo);
    }

    @Override
    protected void initClick() {
        wv_choose_from.addScrollingListener(new OnWheelScrollListener() {
            @Override
            public void onScrollingStarted(AbstractWheel wheel) {

            }

            @Override
            public void onScrollingFinished(AbstractWheel wheel) {
                int item = wheel.getCurrentItem();
                exchangeFrom = resultList.get(item);
                setSelectItem(exchangeFrom, exchangeTo);
            }
        });

        wv_choose_to.addScrollingListener(new OnWheelScrollListener() {
            @Override
            public void onScrollingStarted(AbstractWheel wheel) {

            }

            @Override
            public void onScrollingFinished(AbstractWheel wheel) {
                int item = wheel.getCurrentItem();
                exchangeTo = resultList.get(item);
                setSelectItem(exchangeFrom, exchangeTo);
            }
        });
    }

    private void setSelectItem(ExchangeListResponse.ResultBean.ListBean exchangeFrom, ExchangeListResponse.ResultBean.ListBean exchangeTo) {

        if (exchangeFrom == null || exchangeTo == null) {
            ToastUtil.showShortToast(getActivity(), "请选择货币");
            return;
        }

        String codeFrom = exchangeFrom.getCode();
        String codeTo = exchangeTo.getCode();

        if (codeFrom.equals(codeTo)) {
            //选择了相同的货币
            setExchangeInfo(exchangeFrom, exchangeTo);
            tv_from_result.setText("1");
            tv_to_result.setText("1");
            return;
        }

        setExchangeInfo(exchangeFrom, exchangeTo);

        Map<String, String> param = new HashMap<>();
        param.put("from", codeFrom);
        param.put("to", codeTo);
        param.put("key", "e2eb6e7818b4ecf98e8adcf290541a78");
        HttpAction.getInstance().getExchangeCurrency(param).subscribe(new BaseObserver<>(new CallBackListener<ExchangeCurrencyResponse>() {
            @Override
            public void onSuccess(ExchangeCurrencyResponse response) throws IOException {

                if (response.getError_code() != 0) {
                    ToastUtil.showShortToast(getActivity(), response.getReason());
                    return;
                }

                List<ExchangeCurrencyResponse.ResultBean> exchangeCurrencyList = response.getResult();
                tv_from_result.setText(exchangeCurrencyList.get(1).getResult());
                tv_to_result.setText(exchangeCurrencyList.get(0).getResult());
            }

            @Override
            public void onError(int code, String message) {
                ToastUtil.showShortToast(getActivity(), message);
            }
        }));
    }

    private void setExchangeInfo(ExchangeListResponse.ResultBean.ListBean exchangeFrom, ExchangeListResponse.ResultBean.ListBean exchangeTo) {
        tv_from_name.setText(exchangeFrom.getName());
        tv_from_code.setText(exchangeFrom.getCode());
        tv_to_name.setText(exchangeTo.getName());
        tv_to_code.setText(exchangeTo.getCode());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_exchange;
    }
}
