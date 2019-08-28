package com.zhangyp.develop.HappyTools.response;

import com.zhangyp.develop.HappyTools.base.BaseResponse;

import java.util.List;

/**
 * Created by zyp on 2019/8/20 0020.
 * class note:
 */

public class SearchCityResponse extends BaseResponse {


    private List<HeWeather6Bean> HeWeather6;

    public List<HeWeather6Bean> getHeWeather6() {
        return HeWeather6;
    }

    public void setHeWeather6(List<HeWeather6Bean> HeWeather6) {
        this.HeWeather6 = HeWeather6;
    }

    public static class HeWeather6Bean {
        /**
         * basic : [{"cid":"CN101010100","location":"北京","parent_city":"北京","admin_area":"北京","cnty":"中国","lat":"39.90498734","lon":"116.4052887","tz":"+8.00","type":"city"},{"cid":"CN101010300","location":"朝阳","parent_city":"北京","admin_area":"北京","cnty":"中国","lat":"39.92148972","lon":"116.48641205","tz":"+8.00","type":"city"},{"cid":"CN101010200","location":"海淀","parent_city":"北京","admin_area":"北京","cnty":"中国","lat":"39.95607376","lon":"116.31031799","tz":"+8.00","type":"city"},{"cid":"CN101010900","location":"丰台","parent_city":"北京","admin_area":"北京","cnty":"中国","lat":"39.86364365","lon":"116.28696442","tz":"+8.00","type":"city"},{"cid":"CN101010700","location":"昌平","parent_city":"北京","admin_area":"北京","cnty":"中国","lat":"40.21808624","lon":"116.23590851","tz":"+8.00","type":"city"},{"cid":"CN101011200","location":"房山","parent_city":"北京","admin_area":"北京","cnty":"中国","lat":"39.73553467","lon":"116.13916016","tz":"+8.00","type":"city"},{"cid":"CN101011100","location":"大兴","parent_city":"北京","admin_area":"北京","cnty":"中国","lat":"39.72890854","lon":"116.33803558","tz":"+8.00","type":"city"},{"cid":"CN101010600","location":"通州","parent_city":"北京","admin_area":"北京","cnty":"中国","lat":"39.90248489","lon":"116.65859985","tz":"+8.00","type":"city"},{"cid":"CN101011700","location":"西城","parent_city":"北京","admin_area":"北京","cnty":"中国","lat":"39.91530991","lon":"116.36679077","tz":"+8.00","type":"city"},{"cid":"CN101010400","location":"顺义","parent_city":"北京","admin_area":"北京","cnty":"中国","lat":"40.12893677","lon":"116.65352631","tz":"+8.00","type":"city"}]
         * status : ok
         */

        private String status;
        private List<BasicBean> basic;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public List<BasicBean> getBasic() {
            return basic;
        }

        public void setBasic(List<BasicBean> basic) {
            this.basic = basic;
        }

        public static class BasicBean {
            /**
             * cid : CN101010100
             * location : 北京
             * parent_city : 北京
             * admin_area : 北京
             * cnty : 中国
             * lat : 39.90498734
             * lon : 116.4052887
             * tz : +8.00
             * type : city
             */

            private String cid;
            private String location;
            private String parent_city;
            private String admin_area;
            private String cnty;
            private String lat;
            private String lon;
            private String tz;
            private String type;

            public String getCid() {
                return cid;
            }

            public void setCid(String cid) {
                this.cid = cid;
            }

            public String getLocation() {
                return location;
            }

            public void setLocation(String location) {
                this.location = location;
            }

            public String getParent_city() {
                return parent_city;
            }

            public void setParent_city(String parent_city) {
                this.parent_city = parent_city;
            }

            public String getAdmin_area() {
                return admin_area;
            }

            public void setAdmin_area(String admin_area) {
                this.admin_area = admin_area;
            }

            public String getCnty() {
                return cnty;
            }

            public void setCnty(String cnty) {
                this.cnty = cnty;
            }

            public String getLat() {
                return lat;
            }

            public void setLat(String lat) {
                this.lat = lat;
            }

            public String getLon() {
                return lon;
            }

            public void setLon(String lon) {
                this.lon = lon;
            }

            public String getTz() {
                return tz;
            }

            public void setTz(String tz) {
                this.tz = tz;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }
    }
}
