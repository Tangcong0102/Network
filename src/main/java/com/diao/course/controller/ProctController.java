package com.diao.course.controller;

import com.diao.course.bean.Product;
import com.diao.course.bean.User;
import com.diao.course.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;

/**
 * 鍝嶅簲鍜孭roduct鐩稿叧璇锋眰
 *
 */
@Controller
public class ProctController {

    @Autowired
    private ProductService productService;

    /**
     * 鍝嶅簲index鎴栬��/璇锋眰
     *
     * @param modelMap
     * @param user
     * @param userName
     * @param usertype
     * @return
     */
    @RequestMapping(value = {"/index", "/index.html", "/"})
    public String index(ModelMap modelMap,
                        @SessionAttribute(name = "user", required = false) User user,
                        @SessionAttribute(name = "username", required = false) String userName,
                        @SessionAttribute(name = "usertype", required = false) String usertype,
                        @RequestParam(name = "type", required = false) String type) {
        //浼犲叆userName鍜宼ype锛堟槸鍚︾瓫閫夊凡璐拱鐨勫晢鍝侊級鑾峰彇鍟嗗搧鍒楄〃
        List<Product> list = productService.listProducts(type, userName);
        modelMap.addAttribute("productList", list);
        return "index";
    }

    /**
     * 灞曠幇浜у搧鐨勪俊鎭�
     *
     * @param modelMap
     * @param user
     * @param userName
     * @param usertype
     * @param id
     * @return
     */
    @RequestMapping(value = {"/show"})
    public String show(ModelMap modelMap,
                       @SessionAttribute(name = "user", required = false) User user,
                       @SessionAttribute(name = "username", required = false) String userName,
                       @SessionAttribute(name = "usertype", required = false) String usertype,
                       @RequestParam("id") int id) {
        Product p = productService.get(id, userName);
        if (p != null) {
            modelMap.addAttribute(p);
        }
        return "show";
    }

    /**
     * 璇锋眰鍙戝竷鐨勭晫闈�
     *
     * @param user
     * @param userName
     * @param usertype
     * @return
     */
    @RequestMapping(value = {"/public"})
    public String publicSome(
            @SessionAttribute(name = "user") User user,
            @SessionAttribute(name = "username") String userName,
            @SessionAttribute(name = "usertype") String usertype) {
        if ("0".equals(usertype)) {
            throw new IllegalStateException("涔板鏃犳硶璁块棶");
        }
        return "public";
    }


    /**
     * 璇锋眰缂栬緫鐨勭晫闈�
     *
     * @param modelMap
     * @param user
     * @param userName
     * @param usertype
     * @param id
     * @return
     */
    @RequestMapping(value = {"/edit"})
    public String edit(ModelMap modelMap,
                       @SessionAttribute(name = "user") User user,
                       @SessionAttribute(name = "username") String userName,
                       @SessionAttribute(name = "usertype") String usertype,
                       @RequestParam("id") int id) {
        if ("0".equals(usertype)) {
            throw new IllegalStateException("涔板鏃犳硶璁块棶");
        }
        Product p = productService.get(id, userName);
        modelMap.addAttribute(p);
        return "edit";
    }

    /**
     * @param modelMap
     * @param user
     * @param userName
     * @param usertype
     * @param data
     * @return
     */
    @RequestMapping(value = "/publicSubmit", method = RequestMethod.POST)
    public String publicSubmit(ModelMap modelMap,
                               @SessionAttribute(name = "user") User user,
                               @SessionAttribute(name = "username") String userName,
                               @SessionAttribute(name = "usertype") String usertype,
                               @Validated Product data, Errors errors) {
        if ("0".equals(usertype)) {
            throw new IllegalStateException("涔板鏃犳硶璁块棶");
        }
        int count = productService.getCount();
        if (!errors.hasErrors() && count < 1000) {
            productService.submitProduct(data);
            modelMap.addAttribute("product", data);
        } else {
            modelMap.addAttribute("product", null);
        }
        return "publicSubmit";
    }

    @RequestMapping(value = {"/editSubmit"}, method = RequestMethod.POST)
    public String editSubmit(ModelMap modelMap,
                             @SessionAttribute(name = "user") User user,
                             @SessionAttribute(name = "username") String userName,
                             @SessionAttribute(name = "usertype") String usertype,
                             @Validated Product data, Errors errors, @RequestParam("id") int id) {
        if ("0".equals(usertype)) {
            throw new IllegalStateException("涔板鏃犳硶璁块棶");
        }
        int count = productService.getCount();
        if (!errors.hasErrors() && count < 1000
                && productService.updateProduct(data)) {
            modelMap.addAttribute("product", data);
        } else {
            modelMap.addAttribute("product", null);
        }
        return "editSubmit";
    }
}