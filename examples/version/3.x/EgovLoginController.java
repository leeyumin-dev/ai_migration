package egovframework.com.uss.umt.web;

import java.util.Map;
import javax.annotation.Resource;

import egovframework.com.uss.umt.service.EgovLoginService;
import egovframework.com.uss.umt.service.LoginVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EgovLoginController {

    @Resource(name = "loginService")
    private EgovLoginService loginService;

    @RequestMapping("/login.do")
    public String login(LoginVO loginVO, Model model) throws Exception {
        boolean result = loginService.loginProcess(loginVO);
        if (result) {
            return "main";
        } else {
            model.addAttribute("message", "Login Failed");
            return "login";
        }
    }
}
