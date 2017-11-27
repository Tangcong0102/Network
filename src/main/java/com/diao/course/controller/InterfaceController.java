package com.diao.course.controller;

import com.diao.course.bean.User;
import com.diao.course.service.ProductService;
import com.diao.course.service.TrxService;
import com.diao.course.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Api鐩稿叧鐨凜ontroller瀹炵幇
 *
 */
@Controller
@RequestMapping("/api")
public class InterfaceController {
    @Autowired
    private UserService userService;
    @Autowired
    private TrxService trxService;
    @Autowired
    private ProductService productService;

    /**
     * 1. 榛樿鏉冮檺涓洪渶瑕佺櫥褰�
     * 2. 杩斿洖缁熶竴涓� json 鏁版嵁锛屾牸寮忎负锛歿code:xxx,message:xxx,result:xxx}
     * 3. code 涓� 200锛岃〃绀鸿姹傛垚鍔燂紝鍏朵粬琛ㄧず寮傚父
     * 4. 褰� code 涓嶆槸 200 鏃讹紝message 涓簲璇存槑鍘熷洜
     *
     * @param request
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(HttpServletRequest request,
                              @RequestParam("userName") String username,
                              @RequestParam("password") String password) {
        User user = userService.login(username, password);
        boolean login = user != null;
        if (login) {
            HttpSession session = request.getSession(true);
            session.setAttribute("user", user);
            session.setAttribute("username", user.getUsername());
            session.setAttribute("usertype", String.valueOf(user.getUsertype()));
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("code", (login ? 200 : 100));
        modelAndView.addObject("message", (login ? "鐧婚檰鎴愬姛" : "鐧婚檰寮傚父"));
        modelAndView.addObject("result", login);
        return modelAndView;
    }

    /**
     * 璐拱鍟嗗搧
     *
     * @param id   鍟嗗搧鐨刬d
     * @param user 鐢ㄦ埛鐨勪俊鎭�
     * @return
     */
    @RequestMapping(value = "/buy", method = RequestMethod.POST)
    public ModelAndView buy(@RequestParam("id") int id,
                            @SessionAttribute("user") User user) {
        boolean buy = trxService.buy(user.getId(), id, System.currentTimeMillis());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("code", (buy ? 200 : 100));
        modelAndView.addObject("message", (buy ? "璐拱鎴愬姛" : "璐拱寮傚父"));
        modelAndView.addObject("result", buy);
        return modelAndView;
    }

    /**
     * 鍒犻櫎鍟嗗搧
     *
     * @param id 鍟嗗搧鐨刬d
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ModelAndView delete(@RequestParam("id") int id) {
        boolean result = productService.delete(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("code", (result ? 200 : 100));
        modelAndView.addObject("message", (result ? "鍒犻櫎鎴愬姛" : "鍒犻櫎澶辫触"));
        modelAndView.addObject("result", result);
        return modelAndView;
    }
}
