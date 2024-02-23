package proyecton.com.Proyecton7.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class homeController {
    @GetMapping("/")
    public String index(){
        return "index";
    }
    @GetMapping("/auth/login")
    public String login(){
        return "login";
    }
    @GetMapping("/auth/register")
    public String register(){return "register"; }
}
