package sisepe.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/menu")
    public String menu() {
        return "menu";
    }

    @GetMapping("/menu_interno")
    public String menuInterno() {
        return "menu_interno";
    }

    @GetMapping("/pages/usuarios")
    public String usuarios() {
        return "pages/usuarios";
    }

    @GetMapping("/pages/zonas")
    public String zonas() {
        return "pages/zonas";
    }

    @GetMapping("/pages/municipios")
    public String municipios() {
        return "pages/municipios";
    }

    @GetMapping("/pages/polos")
    public String polos() {
        return "pages/polos";
    }

    @GetMapping("/pages/secoes")
    public String secoes() {
        return "pages/secoes";
    }
}
