package com.br.sorteio.web.controller;

import com.br.sorteio.util.PaginacaoUtil;
import com.br.sorteio.domain.Participante;
import com.br.sorteio.service.ParticipanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

import java.util.Optional;

@Controller
@RequestMapping("/participantes")
public class ParticipanteController {

    @Autowired
    private ParticipanteService participanteService;

    @GetMapping("/cadastrar")
    public String cadastrar(Participante participante){
        return "participante/cadastro";
    }

    @GetMapping("/listar")
    public String listar(ModelMap model,
                         @RequestParam("page") Optional<Integer> page,
                         @RequestParam("dir") Optional<String> dir){

        int paginaAtual = page.orElse(1);
        String ordem = dir.orElse("asc");

        PaginacaoUtil<Participante> lista = participanteService.buscarPorPagina(paginaAtual,ordem);

        model.addAttribute("page", lista);
        return "participante/lista";
    }

    @PostMapping("/salvar")
    public String salvar(Participante participante, BindingResult result, RedirectAttributes attr){

        if (result.hasErrors()) {
            return "participantes/cadastro";
        }

        participanteService.salvar(participante);
        attr.addFlashAttribute("success", "Participante cadastrado com sucesso.");
        return "redirect:/participantes/listar";
    }

    @GetMapping("/editar/{id}")
    public String preEditar(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("participante", participanteService.findById(id));
        return "participante/cadastro";
    }

    @PostMapping("/editar")
    public String editar(@Valid Participante participante, BindingResult result, RedirectAttributes attr) {

        if (result.hasErrors()) {
            return "participante/cadastro";
        }

        participanteService.editar(participante);
        attr.addFlashAttribute("success", "Participante editado com sucesso.");
        return "redirect:/participantes/listar";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id,ModelMap model) {
        participanteService.excluir(id);
        model.addAttribute("success", "Participante excluído com sucesso.");
        return "redirect:/participantes/listar";
    }

    /**
     *
     * rebece o form da página cadastrar-se
     *
     */
    @PostMapping("/cadastrar-se")
    public String salvarCadastroPaciente(Participante participante, BindingResult result, RedirectAttributes attr)  {
        try {
            participanteService.salvarCadastroPaciente(participante);
            attr.addFlashAttribute("success", "Participante cadastrado com sucesso.");
        } catch (RuntimeException ex) {
            result.reject("email", "Participante já cadastrado na base de dados.");
            return "cadastrar-se";
        }
        return "redirect:/home";
    }

}
