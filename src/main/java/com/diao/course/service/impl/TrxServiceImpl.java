package com.diao.course.service.impl;

import com.diao.course.bean.Product;
import com.diao.course.dao.ProductDao;
import com.diao.course.dao.TrxDao;
import com.diao.course.service.TrxService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 鍜屼氦鏄撶浉鍏虫湇鍔＄殑瀹炵幇
 *
 */
@Service
public class TrxServiceImpl implements TrxService {
    @Autowired
    private TrxDao trxDao;
    @Autowired
    private ProductDao productDao;

    /**
     * 杩斿洖鐢ㄦ埛id涓篿d鐨勭敤鎴疯喘涔扮殑鍟嗗搧鍒楄〃
     *
     * @param userId 鐢ㄦ埛鐨刬d
     * @return 璐拱鐨勫晢鍝佸垪琛�
     */
    @Override
    public List<Product> getBuyList(String userId) {
        return trxDao.getBuyList(userId);
    }

    /**
     * 鐢ㄦ埛璐拱鍟嗗搧鐨勬湇鍔�
     *
     * @param userId            鐢ㄦ埛鐨刬d
     * @param productId         棣栬璐拱鐨勫晢鍝乮d
     * @param currentTimeMillis 璐拱鐨勬椂闂�
     * @return 鏄惁璐拱鎴愬姛
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public boolean buy(String userId, int productId, long currentTimeMillis) {
        Product product = productDao.get(productId);
        boolean buy = false;
        if (!trxDao.isSell(product)) {
            buy = trxDao.buy(userId, product, System.currentTimeMillis());
        }
        return buy;
    }
}
