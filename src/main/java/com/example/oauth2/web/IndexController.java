package com.example.oauth2.web;

import com.example.oauth2.config.auth.LoginUser;
import com.example.oauth2.config.auth.SessionUser;
import com.example.oauth2.service.LoginTestService;
import com.example.oauth2.service.PostsService;
import com.example.oauth2.web.Dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Mono;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.LinkedHashMap;

@RequiredArgsConstructor
@Controller
@Slf4j
public class IndexController {

    private final PostsService postsService;
    private final LoginTestService loginTestService;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        model.addAttribute("posts", postsService.findAllDesc());

        if (user != null) {
            model.addAttribute("sUserName", user.getName());
        }

        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave(Model model, @LoginUser SessionUser user) {
        if (user != null) {
            model.addAttribute("sUserName", user.getName());
        }

        return "write";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model, @LoginUser SessionUser user) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        if(dto.getAuthor().equals(user.getName())) {
            model.addAttribute("author", true);
        }

        return "update";
    }

    /**
     * 2020.07.26.Mon
     * Test login api
     */
    @PostMapping("/login2")
    public String index2(Model model, HttpServletRequest request, HttpSession session) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Emp api auth-token
        HashMap<String, Object> authToken = loginTestService.getAuthToken(session, username, password);

        /*LinkedHashMap authToken = (LinkedHashMap) obj;*/

        model.addAttribute("posts", postsService.findAllDesc());

        if (authToken.get("loginId").toString().length() > 0) {
            String loginId = authToken.get("loginId").toString();
            String userName = authToken.get("userName").toString();
            String token = authToken.get("token").toString();

            model.addAttribute("sUserName", userName);
            model.addAttribute("token", token);
        }

        return "index";
    }
}
