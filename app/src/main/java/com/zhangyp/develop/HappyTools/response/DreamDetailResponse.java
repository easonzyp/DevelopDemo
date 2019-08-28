package com.zhangyp.develop.HappyTools.response;

import com.zhangyp.develop.HappyTools.base.BaseResponse;

import java.util.List;

/**
 * Created by zyp on 2019/8/20 0020.
 * class note:
 */

public class DreamDetailResponse extends BaseResponse {

    /**
     * result : {"id":"6ae5bb58d0c09bf65ae2d7786d286aff","title":"大火灾","des":"梦见一场大火灾，如果没有人被烧死的话，表示未来发生的某些变化，将有益于你的利益和幸福。","list":["梦见一场大火灾，如果没有人被烧死的话，表示未来发生的某些变化，将有益于你的利益和幸福。"]}
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
         * id : 6ae5bb58d0c09bf65ae2d7786d286aff
         * title : 大火灾
         * des : 梦见一场大火灾，如果没有人被烧死的话，表示未来发生的某些变化，将有益于你的利益和幸福。
         * list : ["梦见一场大火灾，如果没有人被烧死的话，表示未来发生的某些变化，将有益于你的利益和幸福。"]
         */

        private String id;
        private String title;
        private String des;
        private List<String> list;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDes() {
            return des;
        }

        public void setDes(String des) {
            this.des = des;
        }

        public List<String> getList() {
            return list;
        }

        public void setList(List<String> list) {
            this.list = list;
        }
    }
}
