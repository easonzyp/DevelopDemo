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
        String jsonStr = "{\"reason\":\"查询成功\",\"result\":{\"list\":[{\"name\":\"人民币\",\"code\":\"CNY\"},{\"name\":\"美元\",\"code\":\"USD\"},{\"name\":\"日元\",\"code\":\"JPY\"},{\"name\":\"欧元\",\"code\":\"EUR\"},{\"name\":\"英镑\",\"code\":\"GBP\"},{\"name\":\"韩元\",\"code\":\"KRW\"},{\"name\":\"港币\",\"code\":\"HKD\"},{\"name\":\"澳大利亚元\",\"code\":\"AUD\"},{\"name\":\"加拿大元\",\"code\":\"CAD\"},{\"name\":\"阿尔及利亚第纳尔\",\"code\":\"DZD\"},{\"name\":\"阿根廷比索\",\"code\":\"ARS\"},{\"name\":\"爱尔兰镑\",\"code\":\"IEP\"},{\"name\":\"埃及镑\",\"code\":\"EGP\"},{\"name\":\"阿联酋迪拉姆\",\"code\":\"AED\"},{\"name\":\"阿曼里亚尔\",\"code\":\"OMR\"},{\"name\":\"奥地利先令\",\"code\":\"ATS\"},{\"name\":\"澳门元\",\"code\":\"MOP\"},{\"name\":\"百慕大元\",\"code\":\"BMD\"},{\"name\":\"巴基斯坦卢比\",\"code\":\"PKR\"},{\"name\":\"巴拉圭瓜拉尼\",\"code\":\"PYG\"},{\"name\":\"巴林第纳尔\",\"code\":\"BHD\"},{\"name\":\"巴拿马巴尔博亚\",\"code\":\"PAB\"},{\"name\":\"保加利亚列弗\",\"code\":\"BGN\"},{\"name\":\"巴西雷亚尔\",\"code\":\"BRL\"},{\"name\":\"比利时法郎\",\"code\":\"BEF\"},{\"name\":\"冰岛克朗\",\"code\":\"ISK\"},{\"name\":\"博茨瓦纳普拉\",\"code\":\"BWP\"},{\"name\":\"波兰兹罗提\",\"code\":\"PLN\"},{\"name\":\"玻利维亚诺\",\"code\":\"BOB\"},{\"name\":\"丹麦克朗\",\"code\":\"DKK\"},{\"name\":\"德国马克\",\"code\":\"DEM\"},{\"name\":\"法国法郎\",\"code\":\"FRF\"},{\"name\":\"菲律宾比索\",\"code\":\"PHP\"},{\"name\":\"芬兰马克\",\"code\":\"FIM\"},{\"name\":\"哥伦比亚比索\",\"code\":\"COP\"},{\"name\":\"古巴比索\",\"code\":\"CUP\"},{\"name\":\"哈萨克坚戈\",\"code\":\"KZT\"},{\"name\":\"荷兰盾\",\"code\":\"NLG\"},{\"name\":\"加纳塞地\",\"code\":\"GHC\"},{\"name\":\"捷克克朗\",\"code\":\"CZK\"},{\"name\":\"津巴布韦元\",\"code\":\"ZWD\"},{\"name\":\"卡塔尔里亚尔\",\"code\":\"QAR\"},{\"name\":\"克罗地亚库纳\",\"code\":\"HRK\"},{\"name\":\"肯尼亚先令\",\"code\":\"KES\"},{\"name\":\"科威特第纳尔\",\"code\":\"KWD\"},{\"name\":\"老挝基普\",\"code\":\"LAK\"},{\"name\":\"拉脱维亚拉图\",\"code\":\"LVL\"},{\"name\":\"黎巴嫩镑\",\"code\":\"LBP\"},{\"name\":\"林吉特\",\"code\":\"MYR\"},{\"name\":\"立陶宛立特\",\"code\":\"LTL\"},{\"name\":\"卢布\",\"code\":\"RUB\"},{\"name\":\"罗马尼亚新列伊\",\"code\":\"RON\"},{\"name\":\"毛里求斯卢比\",\"code\":\"MUR\"},{\"name\":\"蒙古图格里克\",\"code\":\"MNT\"},{\"name\":\"孟加拉塔卡\",\"code\":\"BDT\"},{\"name\":\"缅甸缅元\",\"code\":\"BUK\"},{\"name\":\"秘鲁新索尔\",\"code\":\"PEN\"},{\"name\":\"摩洛哥迪拉姆\",\"code\":\"MAD\"},{\"name\":\"墨西哥元\",\"code\":\"MXN\"},{\"name\":\"南非兰特\",\"code\":\"ZAR\"},{\"name\":\"挪威克朗\",\"code\":\"NOK\"},{\"name\":\"葡萄牙埃斯库多\",\"code\":\"PTE\"},{\"name\":\"瑞典克朗\",\"code\":\"SEK\"},{\"name\":\"瑞士法郎\",\"code\":\"CHF\"},{\"name\":\"沙特里亚尔\",\"code\":\"SAR\"},{\"name\":\"斯里兰卡卢比\",\"code\":\"LKR\"},{\"name\":\"索马里先令\",\"code\":\"SOS\"},{\"name\":\"泰国铢\",\"code\":\"THB\"},{\"name\":\"坦桑尼亚先令\",\"code\":\"TZS\"},{\"name\":\"新土耳其里拉\",\"code\":\"TRY\"},{\"name\":\"突尼斯第纳尔\",\"code\":\"TND\"},{\"name\":\"危地马拉格查尔\",\"code\":\"GTQ\"},{\"name\":\"委内瑞拉玻利瓦尔\",\"code\":\"VEB\"},{\"name\":\"乌拉圭新比索\",\"code\":\"UYU\"},{\"name\":\"西班牙比塞塔\",\"code\":\"ESP\"},{\"name\":\"希腊德拉克马\",\"code\":\"GRD\"},{\"name\":\"新加坡元\",\"code\":\"SGD\"},{\"name\":\"新台币\",\"code\":\"TWD\"},{\"name\":\"新西兰元\",\"code\":\"NZD\"},{\"name\":\"匈牙利福林\",\"code\":\"HUF\"},{\"name\":\"牙买加元\",\"code\":\"JMD\"},{\"name\":\"义大利里拉\",\"code\":\"ITL\"},{\"name\":\"印度卢比\",\"code\":\"INR\"},{\"name\":\"印尼盾\",\"code\":\"IDR\"},{\"name\":\"以色列谢克尔\",\"code\":\"ILS\"},{\"name\":\"约旦第纳尔\",\"code\":\"JOD\"},{\"name\":\"越南盾\",\"code\":\"VND\"},{\"name\":\"智利比索\",\"code\":\"CLP\"},{\"name\":\"白俄罗斯卢布\",\"code\":\"BYR\"}]},\"error_code\":0}\n";

        ExchangeListResponse exchangeBean = JSON.parseObject(jsonStr, ExchangeListResponse.class);
        resultList.addAll(exchangeBean.getResult().getList());

        wv_choose_from.setViewAdapter(wheelAdapter);
        wv_choose_to.setViewAdapter(wheelAdapter);

        int itemFrom = wv_choose_from.getCurrentItem();
        exchangeFrom = resultList.get(itemFrom);

        int itemTo = wv_choose_to.getCurrentItem();
        exchangeTo = resultList.get(itemTo);

        setSelectItem(exchangeFrom, exchangeTo);

        /*Map<String,String> param = new HashMap<String, String>();
        param.put("key","e2eb6e7818b4ecf98e8adcf290541a78");
        HttpAction.getInstance().getExchangeList(param).subscribe(new BaseObserver<>(new CallBackListener<ExchangeListResponse>() {
            @Override
            public void onSuccess(ExchangeListResponse response) throws IOException {

            }

            @Override
            public void onError(int code, String message) {

            }
        }));*/
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
                tv_from_result.setText(exchangeCurrencyList.get(0).getResult());
                tv_to_result.setText(exchangeCurrencyList.get(1).getResult());
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
