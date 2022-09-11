package ro.giohnnysoftware.ssc10.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

    @GetMapping("/")
    public String main() { //Spring MVC -> Spring will look to find main.html
        return "main.html";
    }

    @PostMapping("/test")
    @ResponseBody
   // @CrossOrigin("*") //all origins - NEVER DO THIS
    public String test() { //Spring knows it doesn't need to find a page
        System.out.println(":(");
        return "TEST!";
    }
}
