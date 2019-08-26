package com.zhangyp.develop.HappyTools.response;

import com.zhangyp.develop.HappyTools.base.BaseResponse;

import java.util.List;

/**
 * Created by zyp on 2019/8/20 0020.
 * class note:
 */

public class WeatherResponse extends BaseResponse {


    /**
     * result : {"city":"乌鲁木齐","realtime":{"temperature":"25","humidity":"29","info":"阴","wid":"02","direct":"西北风","power":"2级","aqi":"58"},"future":[{"date":"2019-08-21","temperature":"17/25℃","weather":"多云","wid":{"day":"01","night":"01"},"direct":"西北风转持续无风向"},{"date":"2019-08-22","temperature":"18/27℃","weather":"多云","wid":{"day":"01","night":"01"},"direct":"持续无风向"},{"date":"2019-08-23","temperature":"19/25℃","weather":"多云","wid":{"day":"01","night":"01"},"direct":"持续无风向"},{"date":"2019-08-24","temperature":"18/26℃","weather":"多云","wid":{"day":"01","night":"01"},"direct":"持续无风向"},{"date":"2019-08-25","temperature":"17/25℃","weather":"多云","wid":{"day":"01","night":"01"},"direct":"持续无风向"}]}
     */

    private ResultBean result;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * city : 乌鲁木齐
         * realtime : {"temperature":"25","humidity":"29","info":"阴","wid":"02","direct":"西北风","power":"2级","aqi":"58"}
         * future : [{"date":"2019-08-21","temperature":"17/25℃","weather":"多云","wid":{"day":"01","night":"01"},"direct":"西北风转持续无风向"},{"date":"2019-08-22","temperature":"18/27℃","weather":"多云","wid":{"day":"01","night":"01"},"direct":"持续无风向"},{"date":"2019-08-23","temperature":"19/25℃","weather":"多云","wid":{"day":"01","night":"01"},"direct":"持续无风向"},{"date":"2019-08-24","temperature":"18/26℃","weather":"多云","wid":{"day":"01","night":"01"},"direct":"持续无风向"},{"date":"2019-08-25","temperature":"17/25℃","weather":"多云","wid":{"day":"01","night":"01"},"direct":"持续无风向"}]
         */

        private String city;
        private RealtimeBean realtime;
        private List<FutureBean> future;

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public RealtimeBean getRealtime() {
            return realtime;
        }

        public void setRealtime(RealtimeBean realtime) {
            this.realtime = realtime;
        }

        public List<FutureBean> getFuture() {
            return future;
        }

        public void setFuture(List<FutureBean> future) {
            this.future = future;
        }

        public static class RealtimeBean {
            /**
             * temperature : 25
             * humidity : 29
             * info : 阴
             * wid : 02
             * direct : 西北风
             * power : 2级
             * aqi : 58
             */

            private String temperature;
            private String humidity;
            private String info;
            private String wid;
            private String direct;
            private String power;
            private String aqi;

            public String getTemperature() {
                return temperature;
            }

            public void setTemperature(String temperature) {
                this.temperature = temperature;
            }

            public String getHumidity() {
                return humidity;
            }

            public void setHumidity(String humidity) {
                this.humidity = humidity;
            }

            public String getInfo() {
                return info;
            }

            public void setInfo(String info) {
                this.info = info;
            }

            public String getWid() {
                return wid;
            }

            public void setWid(String wid) {
                this.wid = wid;
            }

            public String getDirect() {
                return direct;
            }

            public void setDirect(String direct) {
                this.direct = direct;
            }

            public String getPower() {
                return power;
            }

            public void setPower(String power) {
                this.power = power;
            }

            public String getAqi() {
                return aqi;
            }

            public void setAqi(String aqi) {
                this.aqi = aqi;
            }
        }

        public static class FutureBean {
            /**
             * date : 2019-08-21
             * temperature : 17/25℃
             * weather : 多云
             * wid : {"day":"01","night":"01"}
             * direct : 西北风转持续无风向
             */

            private String date;
            private String temperature;
            private String weather;
            private WidBean wid;
            private String direct;

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getTemperature() {
                return temperature;
            }

            public void setTemperature(String temperature) {
                this.temperature = temperature;
            }

            public String getWeather() {
                return weather;
            }

            public void setWeather(String weather) {
                this.weather = weather;
            }

            public WidBean getWid() {
                return wid;
            }

            public void setWid(WidBean wid) {
                this.wid = wid;
            }

            public String getDirect() {
                return direct;
            }

            public void setDirect(String direct) {
                this.direct = direct;
            }

            public static class WidBean {
                /**
                 * day : 01
                 * night : 01
                 */

                private String day;
                private String night;

                public String getDay() {
                    return day;
                }

                public void setDay(String day) {
                    this.day = day;
                }

                public String getNight() {
                    return night;
                }

                public void setNight(String night) {
                    this.night = night;
                }
            }
        }
    }
}
