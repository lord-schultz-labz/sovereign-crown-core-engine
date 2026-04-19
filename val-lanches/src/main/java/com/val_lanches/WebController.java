package com.val_lanches;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/")
    public String home() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    // MAIN MENU
    @GetMapping("/menu")
    public String menu() {
        return "menu";
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }

    @GetMapping("/orders-page")
    public String orders() {
        return "orders";
    }

    @GetMapping("/products-page")
    public String products() {
        return "products";
    }

    @GetMapping("/customers-page")
    public String customers() {
        return "customers";
    }
}
