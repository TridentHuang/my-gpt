package fun.fanyu.mygpt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @version V3.0
 * @Title: PageController
 * @Description: 描述
 * @author: Trident
 * @date 2023/4/13 5:08 PM
 */
@Controller
public class PageController {

    @RequestMapping("/index")
    public String getDemoHtml() {
        return "index";
    }
}
