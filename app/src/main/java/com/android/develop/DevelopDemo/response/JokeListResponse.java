package com.android.develop.DevelopDemo.response;

import com.android.develop.DevelopDemo.base.BaseResponse;

import java.util.List;

/**
 * Created by zyp on 2019/8/20 0020.
 * class note:
 */

public class JokeListResponse extends BaseResponse {

    /**
     * result : {"data":[{"content":"刚才在QQ上有个还在上大学的姑娘突然跟我讲：\u201c朋友介绍我去打工，今天晚上要到一个酒吧领舞，现在有点不敢去。\u201d我：\u201c有什么不敢去的？\u201d姑娘：\u201c刚才在小摊上只吃了碗牛肉面，怕到时候饿。\u201d──姑娘，你心太宽了！","hashId":"4f3822c322b649ee69f031b5a25fd4b4","unixtime":1554958624,"updatetime":"2019-04-11 12:57:04"},{"content":"为什么古装剧里总是有女人会对恩人说：小女子无以为报，唯有以身相许，古代真的存在这种现象吗？ 扯淡，那是因为她喜欢他，要是不喜欢，她就会说：小女子无以为报，唯有来生再报了。","hashId":"426c58b9639768df62f699963ce00983","unixtime":1554778627,"updatetime":"2019-04-09 10:57:07"}]}
     */

    private ResultBean result;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        private List<DataBean> data;

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * content : 刚才在QQ上有个还在上大学的姑娘突然跟我讲：“朋友介绍我去打工，今天晚上要到一个酒吧领舞，现在有点不敢去。”我：“有什么不敢去的？”姑娘：“刚才在小摊上只吃了碗牛肉面，怕到时候饿。”──姑娘，你心太宽了！
             * hashId : 4f3822c322b649ee69f031b5a25fd4b4
             * unixtime : 1554958624
             * updatetime : 2019-04-11 12:57:04
             */

            private String content;
            private String hashId;
            private int unixtime;
            private String updatetime;

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getHashId() {
                return hashId;
            }

            public void setHashId(String hashId) {
                this.hashId = hashId;
            }

            public int getUnixtime() {
                return unixtime;
            }

            public void setUnixtime(int unixtime) {
                this.unixtime = unixtime;
            }

            public String getUpdatetime() {
                return updatetime;
            }

            public void setUpdatetime(String updatetime) {
                this.updatetime = updatetime;
            }
        }
    }
}
