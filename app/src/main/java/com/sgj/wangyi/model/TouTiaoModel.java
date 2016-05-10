package com.sgj.wangyi.model;

import java.util.ArrayList;

/**
 * Created by John on 2016/5/4.
 * {"T1348647853363":[
 * {"postid":"PHOT3IP1000100AP",
 "hasCover":false, "hasHead":1, "replyCount":17179, "hasImg":1, "digest":"", "hasIcon":true, "docid":"9IG74V5H00963VRO_BM75TTC4bjzhuyuekeupdateDoc",
 "title":"上海迪士尼外墙包裹严密 游人踩点窥探", "order":1, "priority":253, "lmodify":"2016-05-04 08:09:06", "boardid":"photoview_bbs",
 "ads":[{"title":"8级阵风袭击大连 风雨过后樱花铺满地","tag":"photoset","imgsrc":"http://img4.cache.netease.com/3g/2016/5/4/201605040916212bbce.jpg",
 "subtitle":"","url":"00AP0001|117544"},
 {"title":"矿工拍下井前后夫妻照 外貌判若两人","tag":"photoset","imgsrc":"http://img5.cache.netease.com/3g/2016/5/4/2016050407561874867.jpg",
 "subtitle":"","url":"00AP0001|117530"},
 {"title":"美旅馆厕所标志不限性别 抗议\"厕所法\"","tag":"photoset","imgsrc":"http://img4.cache.netease.com/3g/2016/5/4/2016050410235465528.jpg",
 "subtitle":"","url":"00AO0001|117552"},
 {"title":"韩国总统朴槿惠访问伊朗 戴头巾亮相","tag":"photoset","imgsrc":"http://img4.cache.netease.com/3g/2016/5/4/20160504030406cb28e.jpg",
 "subtitle":"","url":"54GI0096|92966"},
 {"title":"巴拉圭学生占领一学校 抗议教育部长","tag":"photoset","imgsrc":"http://img4.cache.netease.com/3g/2016/5/4/20160504081027e63ff.jpg",
 "subtitle":"","url":"00AO0001|117538"}
 ],
 "photosetID":"00AP0001|117537", "template":"manual", "votecount":16068, "skipID":"00AP0001|117537", "alias":"Top News", "skipType":"photoset",
 "cid":"C1348646712614", "hasAD":1,
 "imgextra":[{"imgsrc":"http://img5.cache.netease.com/3g/2016/5/4/201605040811061a9d2.jpg"},
 {"imgsrc":"http://img5.cache.netease.com/3g/2016/5/4/2016050408110830272.jpg"}],
 "source":"网易综合", "ename":"iosnews", "tname":"头条", "imgsrc":"http://img6.cache.netease.com/3g/2016/5/4/201605040811032410c.jpg",
 "ptime":"2016-05-04 08:09:06"}]}
 *
 */

public class TouTiaoModel {

    public ArrayList<TouTiao> T1348647853363;

    @Override
    public String toString() {
        return "TouTiaoMouble [T1348647853363=" + T1348647853363.get(0).toString() + "]";
    }

    public class TouTiao{

        public String postid;
        public String url_3w;
        public String votecount;
        public String replyCount; // 跟帖人数?
        public String skipID;
        public String ltitle;
        public String digest;
        public String skipType; // special：专题； photoset：照片组；
        public String url;
        public String specialID;
        public String docid;
        public String title;
        public String source;
        public String priority;
        public String lmodify;
        public String boardid;
        public String subtitle;
        public String imgsrc;
        public String ptime;// 2016-05-04 08:09:06
        public boolean hasCover;
        public int hasHead;
        public int hasImg;
        public int hasAD;
        public boolean hasIcon;
        public int order;
        public String photosetID;
        public String template;
        public String alias;
        public String cid;
        public String tags;

        public String ename;// iconnews
        public String tname;// 头条

        public ArrayList<myAds> ads;

        public class myAds{
            public String title;
            public String tag;
            public String imgsrc;
            public String subtitle;
            public String url;
        }

        public ArrayList<myImgextra> imgextra;
        public class myImgextra{
            public String imgsrc;
        }
        @Override
        public String toString() {
            return "TouTiao [postid=" + postid + ", url_3w=" + url_3w
                    + ", votecount=" + votecount + ", replyCount=" + replyCount
                    + ", skipID=" + skipID + ", ltitle=" + ltitle + ", digest="
                    + digest + ", skipType=" + skipType + ", url=" + url
                    + ", specialID=" + specialID + ", docid=" + docid
                    + ", title=" + title + ", source=" + source + ", priority="
                    + priority + ", lmodify=" + lmodify + ", boardid="
                    + boardid + ", subtitle=" + subtitle + ", imgsrc=" + imgsrc
                    + ", ptime=" + ptime + ", hasCover=" + hasCover
                    + ", hasHead=" + hasHead + ", hasImg=" + hasImg
                    + ", hasAD=" + hasAD + ", hasIcon=" + hasIcon + ", order="
                    + order + ", photosetID=" + photosetID + ", template="
                    + template + ", alias=" + alias + ", cid=" + cid
                    + ", ename=" + ename + ", tname=" + tname + ", ads=" + (ads==null?null: ads.size())
                    + ", imgextra=" + (imgextra==null?null: imgextra.size()) + "]";
        }

    }
}
