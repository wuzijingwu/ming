package com.example.my407f;

import java.util.List;

/**
 * Created by dell on 2017/7/25.
 */

public class MenuInfo {

    /**
     * array : [{"title":"你买过的最失败的东西是什么？","content":"你买过的最失败的东西是什么你买过的最失败的东西是什么你买过的最失败的东西是什么你买过的最失败的东西是什么","number":"123","pic":"http://zxpic.gtimg.com/infonew/0/wechat_pics_-214279.jpg/168","link":"https://www.zhihu.com/question/39004511","source":"生活方式"},{"title":"【夜读】梁晓声：你追求的，就是你人生的意义？","content":"【夜读】梁晓声：你追求的，就是你人生的意义【夜读】梁晓声：你追求的，就是你人生的意义【夜读】梁晓声：你追求的，就是你人生的意义【夜读】梁晓声：你追求的，就是你人生的意义【夜读】梁晓声：你追求的，就是你人生的意义【夜读】梁晓声：你追求的，就是你人生的意义","number":"169","pic":"http://zxpic.gtimg.com/infonew/0/wechat_pics_-214521.jpg/168","link":"http://v.juhe.cn/weixin/redirect?wid=wechat_20150402028462","source":"生活方式"},{"title":"孩子们喜欢怎样的房间 你不能装作不知道？","content":"孩子们喜欢怎样的房间 你不能装作不知道孩子们喜欢怎样的房间 你不能装作不知道孩子们喜欢怎样的房间 你不能装作不知道孩子们喜欢怎样的房间 你不能装作不知道孩子们喜欢怎样的房间 你不能装作不知道孩子们喜欢怎样的房间 你不能装作不知道","number":"1903","pic":"http://zxpic.gtimg.com/infonew/0/wechat_pics_-214277.jpg/168","link":"http://v.juhe.cn/weixin/redirect?wid=wechat_20150401071583","source":"生活方式"},{"title":"国家电网员工竟然这样办婚礼？","content":"国家电网员工竟然这样办婚礼国家电网员工竟然这样办婚礼国家电网员工竟然这样办婚礼国家电网员工竟然这样办婚礼国家电网员工竟然这样办婚礼国家电网员工竟然这样办婚礼国家电网员工竟然这样办婚礼","number":"184","pic":"http://zxpic.gtimg.com/infonew/0/wechat_pics_-214267.jpg/168","link":"http://v.juhe.cn/weixin/redirect?wid=wechat_20150401070488","source":"生活方式"},{"title":"【独家】2015年室内色彩搭配指南？","content":"【独家】2015年室内色彩搭配指南【独家】2015年室内色彩搭配指南【独家】2015年室内色彩搭配指南【独家】2015年室内色彩搭配指南【独家】2015年室内色彩搭配指南【独家】2015年室内色彩搭配指南","number":"73","pic":"http://zxpic.gtimg.com/infonew/0/wechat_pics_-214270.jpg/168","link":"http://v.juhe.cn/weixin/redirect?wid=wechat_20150401070491","source":"生活方式"},{"title":"德国人的严谨出了名 那家具设计呢？","content":"德国人的严谨出了名 那家具设计呢德国人的严谨出了名 那家具设计呢德国人的严谨出了名 那家具设计呢德国人的严谨出了名 那家具设计呢德国人的严谨出了名 那家具设计呢德国人的严谨出了名 那家具设计呢德国人的严谨出了名 那家具设计呢德国人的严谨出了名 那家具设计呢","number":"634","pic":"http://zxpic.gtimg.com/infonew/0/wechat_pics_-214269.jpg/168","link":"http://v.juhe.cn/weixin/redirect?wid=wechat_20150401070489","source":"生活方式"}]
     * string : Hello World
     */

    private String string;
    private List<ArrayBean> array;

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public List<ArrayBean> getArray() {
        return array;
    }

    public void setArray(List<ArrayBean> array) {
        this.array = array;
    }

    public static class ArrayBean {
        /**
         * title : 你买过的最失败的东西是什么？
         * content : 你买过的最失败的东西是什么你买过的最失败的东西是什么你买过的最失败的东西是什么你买过的最失败的东西是什么
         * number : 123
         * pic : http://zxpic.gtimg.com/infonew/0/wechat_pics_-214279.jpg/168
         * link : https://www.zhihu.com/question/39004511
         * source : 生活方式
         */

        private String title;
        private String content;
        private String number;
        private String pic;
        private String link;
        private String source;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }
    }
}
