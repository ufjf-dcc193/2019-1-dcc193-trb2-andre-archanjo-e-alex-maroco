package br.ufjf.dcc193.trab2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import br.ufjf.dcc193.trab2.model.Avaliador;
import br.ufjf.dcc193.trab2.service.LoginService;


/**
 * HomeController
 */
@Controller
public class HomeController {

    @Autowired
    private LoginService ls;

    @GetMapping({ "", "/", "/index.html" })
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");
        Avaliador a = ls.getUser();
        if(a != null) {
            mv.addObject("username", a.getNome());
        } else {
            mv.addObject("username", "Sem user");
        }
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