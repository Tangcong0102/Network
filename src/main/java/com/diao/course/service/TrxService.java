package com.diao.course.service;

import java.util.List;

import com.diao.course.bean.Product;

/**
 * 鍜屼氦鏄撶浉鍏虫湇鍔＄殑瀹氫箟
 */
public interface TrxService {

    /**
     * 杩斿洖鐢ㄦ埛id涓篿d鐨勭敤鎴疯喘涔扮殑鍟嗗搧鍒楄〃
     * @param userId 鐢ㄦ埛鐨刬d
     * @return 璐拱鐨勫晢鍝佸垪琛�
     */
    List<Product> getBuyList(String userId);

    /**
     * 鐢ㄦ埛璐拱鍟嗗搧鐨勬湇鍔�
     * @param userId 鐢ㄦ埛鐨刬d
     * @param productId 棣栬璐拱鐨勫晢鍝乮d
     * @param currentTimeMillis 璐拱鐨勬椂闂�
     * @return 鏄惁璐拱鎴愬姛
     */
    boolean buy(String userId, int productId, long currentTimeMillis);
}
