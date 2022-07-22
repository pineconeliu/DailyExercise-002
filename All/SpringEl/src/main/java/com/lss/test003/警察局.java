package com.lss.test003;

public class 警察局 {

    static class 普通警察 extends 警察局特有的通讯方式{

        @Override
        public void 通信() {
            System.out.println("正义的警察收到了卧底的消息");
        }
        public void 正义的出击() {
            System.out.println("全军出击，打击罪犯");
        }
    }

    static class 卧底警察 extends 警察局特有的通讯方式{

        @Override
        public void 通信() {
            System.out.println("卧底发出消息，这边犯罪人员要干大事，请求增援");
        }

        public void 捕获犯罪人员() {
            System.out.println("阿祖，收手吧。对不起，我是个警察");
        }
    }
}
