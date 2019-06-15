package br.ufjf.dcc193.trab2.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.ufjf.dcc193.trab2.model.Avaliador;
import br.ufjf.dcc193.trab2.repository.AvaliadorRepository;

/**
 * HomeController
 */
@Controller
public class HomeController {

    @Autowired
    AvaliadorRepository aRepo;

    @GetMapping({ "", "/", "/index.html" })
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");
        return mv;
    }

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
                mv.setViewName("redirect:cadastroavaliador.html");
                return mv;
            }
            mv.setViewName("redirect:index.html");
            return mv;
    }

    @GetMapping(value={"/cadastroavaliador.html" })
    public ModelAndView cadastroavaliador() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("avaliador", new Avaliador());
        mv.setViewName("form-cadastro-avaliador");
        return mv;
    }

    @PostMapping(value="/cadastroavaliador.html")
    public ModelAndView cadastroavaliador(@Valid Avaliador avaliador, BindingResult binding){
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
    /*@GetMapping({"index.html"})
    public ModelAndView homeGet(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");
        return mv;
    }

    @PostMapping({"index.html"})
    public ModelAndView homePost(@RequestParam Avaliador avaliador){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");
        mv.addObject("avaliador", avaliador);
        return mv;
    }*/
    
}