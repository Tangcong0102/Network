package com.diao.course.service;

import java.util.List;

import com.diao.course.bean.Product;

/**
 */
public interface ProductService {

    /**
     * 灞曠ず鍟嗗搧鍒楄〃
     *
     * @param type     type type涓�1鏃讹紝绛涢�夌敤鎴锋湭璐拱鐨勫晢鍝�
     * @param userName
     * @return
     */
    List<Product> listProducts(String type, String userName);

    /**
     * 鑾峰彇鍟嗗搧鐨勪俊鎭�
     *
     * @param id 鍟嗗搧鐨刬d
     * @return
     */
    Product get(int id);

    /**
     * 鑾峰彇鍟嗗搧鐨勪俊鎭�
     *
     * @param id       鍟嗗搧鐨刬d
     * @param userName 褰撳墠鐢ㄦ埛鐨剈sername锛岀敤浜庡尯鍒嗘槸鍚︽湁鐢ㄦ埛鐧诲綍
     * @return
     */
    Product get(int id, String userName);

    /**
     * 鎻愪氦鍙戝竷鍟嗗搧
     *
     * @param data 鎻愪氦鐨勫晢鍝佷俊鎭�
     */
    void submitProduct(Product data);

    /**
     * 鏇存柊鍟嗗搧鐨勬暟鎹�
     *
     * @param data 鎻愪氦鐨勫晢鍝佷俊鎭�
     * @return 鏄惁鎴愬姛
     */
    boolean updateProduct(Product data);

    /**
     * 鍒犻櫎鍟嗗搧淇℃伅
     *
     * @param id 鍟嗗搧id
     * @return 鏄惁鎴愬姛
     */
    boolean delete(int id);

    /**
     * 鑾峰彇鍟嗗搧鐨勬�绘暟
     *
     * @return 鍟嗗搧鐨勬�绘暟
     */
    int getCount();
}
