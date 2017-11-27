package com.diao.course.service.impl;
import com.diao.course.bean.Product;
import com.diao.course.dao.ProductDao;
import com.diao.course.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;

/**
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    /**
     * 灞曠ず鍟嗗搧鍒楄〃
     *
     * @param type     type type涓�1鏃讹紝绛涢�夌敤鎴锋湭璐拱鐨勫晢鍝�
     * @param userName
     * @return
     */
    @Override
    public List<Product> listProducts(String type, String userName) {
        List<Product> products = productDao.listProducts();
        Iterator<Product> iterator = products.iterator();
        while (iterator.hasNext()) {
            Product p = iterator.next();
            //鍦ㄧ敤鎴风櫥褰曠殑鎯呭喌涓�,鏌ョ湅鐢氳嚦buy鍜宻ell瀛楁锛屾樉绀鸿喘涔颁俊鎭�
            if (userName != null) {
                p.setBuy(p.getTrxCount() > 0);
                p.setSell(p.getTrxCount() > 0);
                //褰搕ype涓�1鏃讹紝杩囨护鐢ㄦ埛宸茬粡璐拱鐨勫晢鍝�
                if ("1".equals(type)) {
                    if (p.getIsBuy()) {
                        iterator.remove();
                    }
                }
            }
        }
        return products;
    }

    /**
     * 鑾峰彇鍟嗗搧鐨勪俊鎭�
     *
     * @param id 鍟嗗搧鐨刬d
     * @return
     */
    @Override
    public Product get(int id) {
        Product p = productDao.get(id);
        return p;
    }

    /**
     * 鑾峰彇鍟嗗搧鐨勪俊鎭�
     *
     * @param id       鍟嗗搧鐨刬d
     * @param userName 褰撳墠鐢ㄦ埛鐨剈sername锛岀敤浜庡尯鍒嗘槸鍚︽湁鐢ㄦ埛鐧诲綍
     * @return
     */
    @Override
    public Product get(int id, String userName) {
        Product p = productDao.get(id);
        //褰撶敤鎴风櫥褰曟椂璁剧疆buy鍜宻ell
        if (p != null && userName != null) {
            p.setBuy(p.getTrxCount() > 0);
            p.setSell(p.getTrxCount() > 0);
        }
        return p;
    }

    /**
     * 鎻愪氦鍙戝竷鍟嗗搧
     *
     * @param data 鎻愪氦鐨勫晢鍝佷俊鎭�
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void submitProduct(Product data) {
        productDao.submit(data);
    }

    /**
     * 鏇存柊鍟嗗搧鐨勬暟鎹�
     *
     * @param data 鎻愪氦鐨勫晢鍝佷俊鎭�
     * @return 鏄惁鎴愬姛
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public boolean updateProduct(Product data) {
        return productDao.update(data);
    }

    /**
     * 鍒犻櫎鍟嗗搧淇℃伅
     *
     * @param id 鍟嗗搧id
     * @return 鏄惁鎴愬姛
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public boolean delete(int id) {
        return productDao.delete(id);
    }


    /**
     * 鑾峰彇鍟嗗搧鐨勬�绘暟
     *
     * @return 鍟嗗搧鐨勬�绘暟
     */
    @Override
    public int getCount() {
        return productDao.getCount();
    }
}
