package com.diao.course.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * 闈欐�侀〉闈㈢殑璺宠浆锛屽寘鎷櫥闄嗛〉銆侀��鍑�
 *
 */
@Controller
public class DisprController {

    /**
     * 鍝嶅簲login璇锋眰
     *
     * @return login锛岃〃绀簂ogin瑙嗗浘
     */
    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * 鍝嶅簲logout璇锋眰锛屽悓鏃朵娇session澶辨晥
     *
     * @return 閲嶅畾鍚憀ogin锛岃〃绀簂ogin瑙嗗浘
     */
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
