package br.ufjf.dcc193.trab2.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.ufjf.dcc193.trab2.model.Avaliador;
import br.ufjf.dcc193.trab2.repository.AvaliadorRepository;

/**
 * AvaliadorController
 */
@Controller
public class AvaliadorController {

    @Autowired
    AvaliadorRepository aRepo;

    @GetMapping(value={"/login.html" })
    public ModelAndView login() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("avaliador", new Avaliador());
        mv.setViewName("login");
        return mv;
    }

    @PostMapping(value="/login.html")
    public ModelAndView criar(@Valid Avaliador avaliador, BindingResult binding){
            ModelAndView mv = new ModelAndView();
            if(binding.hasErrors()){
                mv.setViewName("login");
                mv.addObject("avaliador", avaliador);
                return mv;
            }
            Avaliador a = aRepo.findOneByEmailAndCodigoAcesso(avaliador.getEmail(), avaliador.getCodigoAcesso());
            System.err.println(a);
            if(a != null){
                mv.setViewName("redirect:CadastroAvaliador.html");
                return mv;
            }
            mv.setViewName("redirect:index.html");
            return mv;
    }

    @GetMapping(value={"/CadastroAvaliador.html" })
    public ModelAndView cadastroAvaliador() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("avaliador", new Avaliador());
        mv.setViewName("form-cadastro-avaliador");
        return mv;
    }

    @PostMapping(value="/CadastroAvaliador.html")
    public ModelAndView cadastroAvaliador(@Valid Avaliador avaliador, BindingResult binding){
            ModelAndView mv = new ModelAndView();
            if(binding.hasErrors()){
                mv.setViewName("form-cadastro-avaliador");
                mv.addObject("avaliador", avaliador);
                return mv;
            }
            System.err.println(avaliador.toString());
            aRepo.save(avaliador);
            System.err.println(aRepo.findAll());
            mv.setViewName("redirect:index.html");
            return mv;
    }
}