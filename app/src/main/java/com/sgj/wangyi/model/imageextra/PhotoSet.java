package com.sgj.wangyi.model.imageextra;
import java.io.Serializable;
import java.util.List;

/**
 * Created by HHX on 15/9/12.
 *
 * {"postid":"PHOT3IP1000100AP",
 * "series":"",
 * "clientadurl":"",
 * "desc":"五一期间及小长假结束后，有不少人（其中绝大多数为老年人）赶到迪士尼乐园外，但是，迪士尼乐园的外墙被用绿色塑料布围得严严实实，
 *      “庐山不露真面目”，生怕游人窥视内部风景。图为有一些老人凑到围布的空隙缝缝处往里张望，也立刻被保安及乐园员工劝走。Huaren/视觉中国",
 * "datatime":"2016-05-04 07:49:20",
 * "createdate":"2016-05-04 07:42:13",
 * "relatedids":[],
 * "scover":"http://img4.cache.netease.com/photo/0001/2016-05-04/s_BM75EPFH00AP0001.jpg",
 * "autoid":"",
 * "url":"http://news.163.com/photoview/00AP0001/117537.html",
 * "creator":"朱玥珂",
 * "reporter":"",
 * "photos":[
 * {"timgurl":"http://img3.cache.netease.com/photo/0001/2016-05-04/t_BM75ETHD00AP0001.jpg",
 * "photohtml":"http://news.163.com/photoview/00AP0001/117537.html#p=BM75ETHD00AP0001",
 * "newsurl":"#",
 * "squareimgurl":"http://img3.cache.netease.com/photo/0001/2016-05-04/400x400_BM75ETHD00AP0001.jpg",
 * "cimgurl":"http://img4.cache.netease.com/photo/0001/2016-05-04/c_BM75ETHD00AP0001.jpg",
 * "imgtitle":"",
 * "simgurl":"http://img4.cache.netease.com/photo/0001/2016-05-04/s_BM75ETHD00AP0001.jpg",
 * "note":"五一期间及小长假结束后，有不少人（其中绝大多数为老年人）赶到迪士尼乐园外，但是，迪士尼乐园的外墙被用绿色塑料布围得严严实实，
 *      “庐山不露真面目”，生怕游人窥视内部风景。图为有一些老人凑到围布的空隙缝缝处往里张望，也立刻被保安及乐园员工劝走。Huaren/视觉中国（来源：网易综合）",
 *  "photoid":"BM75ETHD00AP0001",
 *  "imgurl":"http://img4.cache.netease.com/photo/0001/2016-05-04/BM75ETHD00AP0001.jpg"},
 *  ...
 *  ],
 *  "setname":"上海迪士尼外墙包裹严密","cover":"http://img4.cache.netease.com/photo/0001/2016-05-04/BM75EPFH00AP0001.jpg",
 *  "commenturl":"http://comment.news.163.com/photoview_bbs/PHOT3IP1000100AP.html","source":"网易综合","settag":"上海迪士尼，踩点，窥探","boardid":"photoview_bbs",
 *  "tcover":"http://img3.cache.netease.com/photo/0001/2016-05-04/t_BM75EPFH00AP0001.jpg","imgsum":"5"}
 */

public class PhotoSet implements Serializable {
    private String postid;

    private String series;

    private String clientadurl;

    private String desc;

    private String datatime;

    private String createdate;

//    private List<relatedids> relatedidss ;

    private String scover;

    private String autoid;

    private String url;

    private String creator;

    private String reporter;

    private List<photos> photos;

    private String setname;

    private String cover;

    private String commenturl;

    private String source;

    private String settag;

    private String boardid;

    private String tcover;

    private String imgsum;

    public void setPostid(String postid){
        this.postid = postid;
    }
    public String getPostid(){
        return this.postid;
    }
    public void setSeries(String series){
        this.series = series;
    }
    public String getSeries(){
        return this.series;
    }
    public void setClientadurl(String clientadurl){
        this.clientadurl = clientadurl;
    }
    public String getClientadurl(){
        return this.clientadurl;
    }
    public void setDesc(String desc){
        this.desc = desc;
    }
    public String getDesc(){
        return this.desc;
    }
    public void setDatatime(String datatime){
        this.datatime = datatime;
    }
    public String getDatatime(){
        return this.datatime;
    }
    public void setCreatedate(String createdate){
        this.createdate = createdate;
    }
    public String getCreatedate(){
        return this.createdate;
    }
//    public void setRelatedids(List<relatedids> relatedids){
//        this.relatedidss = relatedids;
//    }
//    public List<relatedids> getRelatedids(){
//        return this.relatedidss;
//    }
    public void setScover(String scover){
        this.scover = scover;
    }
    public String getScover(){
        return this.scover;
    }
    public void setAutoid(String autoid){
        this.autoid = autoid;
    }
    public String getAutoid(){
        return this.autoid;
    }
    public void setUrl(String url){
        this.url = url;
    }
    public String getUrl(){
        return this.url;
    }
    public void setCreator(String creator){
        this.creator = creator;
    }
    public String getCreator(){
        return this.creator;
    }
    public void setReporter(String reporter){
        this.reporter = reporter;
    }
    public String getReporter(){
        return this.reporter;
    }
    public void setPhotos(List<photos> photos){
        this.photos = photos;
    }
    public List<photos> getPhotos(){
        return this.photos;
    }
    public void setSetname(String setname){
        this.setname = setname;
    }
    public String getSetname(){
        return this.setname;
    }
    public void setCover(String cover){
        this.cover = cover;
    }
    public String getCover(){
        return this.cover;
    }
    public void setCommenturl(String commenturl){
        this.commenturl = commenturl;
    }
    public String getCommenturl(){
        return this.commenturl;
    }
    public void setSource(String source){
        this.source = source;
    }
    public String getSource(){
        return this.source;
    }
    public void setSettag(String settag){
        this.settag = settag;
    }
    public String getSettag(){
        return this.settag;
    }
    public void setBoardid(String boardid){
        this.boardid = boardid;
    }
    public String getBoardid(){
        return this.boardid;
    }
    public void setTcover(String tcover){
        this.tcover = tcover;
    }
    public String getTcover(){
        return this.tcover;
    }
    public void setImgsum(String imgsum){
        this.imgsum = imgsum;
    }
    public String getImgsum(){
        return this.imgsum;
    }

    @Override
    public String toString() {
        return "PhotoSet{" +
                "autoid='" + autoid + '\'' +
                ", postid='" + postid + '\'' +
                ", series='" + series + '\'' +
                ", clientadurl='" + clientadurl + '\'' +
                ", desc='" + desc + '\'' +
                ", datatime='" + datatime + '\'' +
                ", createdate='" + createdate + '\'' +
                ", scover='" + scover + '\'' +
                ", url='" + url + '\'' +
                ", creator='" + creator + '\'' +
                ", reporter='" + reporter + '\'' +
                ", photos=" + photos +
                ", setname='" + setname + '\'' +
                ", cover='" + cover + '\'' +
                ", commenturl='" + commenturl + '\'' +
                ", source='" + source + '\'' +
                ", settag='" + settag + '\'' +
                ", boardid='" + boardid + '\'' +
                ", tcover='" + tcover + '\'' +
                ", imgsum='" + imgsum + '\'' +
                '}';
    }
}