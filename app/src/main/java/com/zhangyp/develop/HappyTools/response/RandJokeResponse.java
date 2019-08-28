package com.zhangyp.develop.HappyTools.response;

import com.zhangyp.develop.HappyTools.base.BaseResponse;

import java.util.List;

/**
 * Created by zyp on 2019/8/20 0020.
 * class note:
 */

public class RandJokeResponse extends BaseResponse {


    private List<ResultBean> result;

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * content : 前女友打电话来：“说话方便吗？” 我看了一眼旁边的女友说：“哦，还没吃呢。。” 对方又说：“那我等你方便了，再打过来。” 我笑了笑：“做好饭也得八点了！” 挂掉电话后现女友问我谁的电话，我说：“我前女友的！” 她瞪了我一眼：“讨厌，又逗我，肯定是你妈！”
         * hashId : D37C84735C74BB1D3829499DD15BCEC5
         * unixtime : 1428730371
         */

        private String content;
        private String hashId;
        private int unixtime;

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
    }
}
