package com.zhangyp.develop.HappyTools.response;

import com.zhangyp.develop.HappyTools.base.BaseResponse;

import java.util.List;

/**
 * Created by zyp on 2019/8/22 0022.
 * class note:
 */

public class ExchangeCurrencyResponse extends BaseResponse {

    private List<ResultBean> result;

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * currencyF : CNY
         * currencyF_Name : 人民币
         * currencyT : AED
         * currencyT_Name : 阿联酋迪拉姆
         * currencyFD : 1
         * exchange : 0.5185
         * result : 0.5185
         * updateTime : 2019-08-23 09:24:09
         */

        private String currencyF;
        private String currencyF_Name;
        private String currencyT;
        private String currencyT_Name;
        private String currencyFD;
        private String exchange;
        private String result;
        private String updateTime;

        public String getCurrencyF() {
            return currencyF;
        }

        public void setCurrencyF(String currencyF) {
            this.currencyF = currencyF;
        }

        public String getCurrencyF_Name() {
            return currencyF_Name;
        }

        public void setCurrencyF_Name(String currencyF_Name) {
            this.currencyF_Name = currencyF_Name;
        }

        public String getCurrencyT() {
            return currencyT;
        }

        public void setCurrencyT(String currencyT) {
            this.currencyT = currencyT;
        }

        public String getCurrencyT_Name() {
            return currencyT_Name;
        }

        public void setCurrencyT_Name(String currencyT_Name) {
            this.currencyT_Name = currencyT_Name;
        }

        public String getCurrencyFD() {
            return currencyFD;
        }

        public void setCurrencyFD(String currencyFD) {
            this.currencyFD = currencyFD;
        }

        public String getExchange() {
            return exchange;
        }

        public void setExchange(String exchange) {
            this.exchange = exchange;
        }

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }
    }
}
