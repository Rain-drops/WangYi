package com.sgj.wangyi.model.newstext;

/**
 * Created by John on 2016/5/27.
 */
public class Img {
    private String ref;

    private String pixel;

    private String alt;

    private String src;

    public void setRef(String ref){
        this.ref = ref;
    }
    public String getRef(){
        return this.ref;
    }
    public void setPixel(String pixel){
        this.pixel = pixel;
    }
    public String getPixel(){
        return this.pixel;
    }
    public void setAlt(String alt){
        this.alt = alt;
    }
    public String getAlt(){
        return this.alt;
    }
    public void setSrc(String src){
        this.src = src;
    }
    public String getSrc(){
        return this.src;
    }

    @Override
    public String toString() {
        return "Img{" +
                "ref='" + ref + '\'' +
                ", pixel='" + pixel + '\'' +
                ", alt='" + alt + '\'' +
                ", src='" + src + '\'' +
                '}';
    }

}
