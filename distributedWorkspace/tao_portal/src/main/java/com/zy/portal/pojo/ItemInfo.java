package com.zy.portal.pojo;

import com.zy.pojo.TbItem;

/**
 * 扩展TbItem,增加getImages方法
 */
public class ItemInfo extends TbItem {

    //用于jsp展示
    public String[] getImages(){
        String image = this.getImage();
        if (image != null && !"".equals(image)){
            return image.split(",");
        }
        return null;

    }


}
