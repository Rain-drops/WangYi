package com.sgj.wangyi.model;

import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by John on 2016/5/6.
 */
public class NewsTouTiaoModel implements Serializable{

    public String postid;
    public String url_3w;
    public String votecount;
    public String replyCount;
    public String skipID;
    public String ltitle;
    public String digest;
    public String skipType;
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

    public String getPostid() {
        return postid;
    }

    public String getUrl_3w() {
        return url_3w;
    }

    public String getVotecount() {
        return votecount;
    }

    public String getReplyCount() {
        return replyCount;
    }

    public String getSkipID() {
        return skipID;
    }

    public String getLtitle() {
        return ltitle;
    }

    public String getDigest() {
        return digest;
    }

    public String getSkipType() {
        return skipType;
    }

    public String getUrl() {
        return url;
    }

    public String getSpecialID() {
        return specialID;
    }

    public String getDocid() {
        return docid;
    }

    public String getTitle() {
        return title;
    }

    public String getSource() {
        return source;
    }

    public String getPriority() {
        return priority;
    }

    public String getLmodify() {
        return lmodify;
    }

    public String getBoardid() {
        return boardid;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getImgsrc() {
        return imgsrc;
    }

    public String getPtime() {
        return ptime;
    }

    public boolean isHasCover() {
        return hasCover;
    }

    public int getHasHead() {
        return hasHead;
    }

    public int getHasImg() {
        return hasImg;
    }

    public int getHasAD() {
        return hasAD;
    }

    public boolean isHasIcon() {
        return hasIcon;
    }

    public int getOrder() {
        return order;
    }

    public String getPhotosetID() {
        return photosetID;
    }

    public String getTemplate() {
        return template;
    }

    public String getAlias() {
        return alias;
    }

    public String getCid() {
        return cid;
    }

    public String getEname() {
        return ename;
    }

    public String getTname() {
        return tname;
    }

    public ArrayList<myAds> getAds() {
        return ads;
    }

    public ArrayList<myImgextra> getImgextra() {
        return imgextra;
    }

    public void setPostid(String postid) {
        this.postid = postid;
    }

    public void setUrl_3w(String url_3w) {
        this.url_3w = url_3w;
    }

    public void setVotecount(String votecount) {
        this.votecount = votecount;
    }

    public void setReplyCount(String replyCount) {
        this.replyCount = replyCount;
    }

    public void setSkipID(String skipID) {
        this.skipID = skipID;
    }

    public void setLtitle(String ltitle) {
        this.ltitle = ltitle;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public void setSkipType(String skipType) {
        this.skipType = skipType;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setSpecialID(String specialID) {
        this.specialID = specialID;
    }

    public void setDocid(String docid) {
        this.docid = docid;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public void setLmodify(String lmodify) {
        this.lmodify = lmodify;
    }

    public void setBoardid(String boardid) {
        this.boardid = boardid;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public void setImgsrc(String imgsrc) {
        this.imgsrc = imgsrc;
    }

    public void setPtime(String ptime) {
        this.ptime = ptime;
    }

    public void setHasCover(boolean hasCover) {
        this.hasCover = hasCover;
    }

    public void setHasHead(int hasHead) {
        this.hasHead = hasHead;
    }

    public void setHasImg(int hasImg) {
        this.hasImg = hasImg;
    }

    public void setHasAD(int hasAD) {
        this.hasAD = hasAD;
    }

    public void setHasIcon(boolean hasIcon) {
        this.hasIcon = hasIcon;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public void setPhotosetID(String photosetID) {
        this.photosetID = photosetID;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public void setAds(ArrayList<myAds> ads) {
        this.ads = ads;
    }

    public void setImgextra(ArrayList<myImgextra> imgextra) {
        this.imgextra = imgextra;
    }
}
