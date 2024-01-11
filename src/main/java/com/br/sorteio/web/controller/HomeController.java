package com.br.sorteio.web.controller;

import com.br.sorteio.domain.Participante;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping({"/", "/home"})
    public String home(){
        return "/home";
    }


    @GetMapping({"/login"})
    public String login() {
        return "login";
    }

    @GetMapping("/novo/cadastro/Participante")
    public String cadastroPorAdminParaAdminParticipante(Participante Participante) {

        return "cadastrar-se";
    }

    @GetMapping({"/login-error"})
    public String loginError(ModelMap model, HttpServletRequest resp) {
        HttpSession session = resp.getSession();
        String lastException = String.valueOf(session.getAttribute("SPRING_SECURITY_LAST_EXCEPTION"));
        if (lastException.contains(SessionAuthenticationException.class.getName())) {
            model.addAttribute("alerta", "erro");
            model.addAttribute("titulo", "Acesso recusado!");
            model.addAttribute("texto", "Você já está logado em outro dispositivo.");
            model.addAttribute("subtexto", "Faça o logout ou espere sua sessão expirar.");
            return "login";
        }
        return "login";
    }
}
