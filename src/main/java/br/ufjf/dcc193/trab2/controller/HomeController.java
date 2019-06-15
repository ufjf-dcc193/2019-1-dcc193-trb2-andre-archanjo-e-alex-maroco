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

    @GetMapping({ "", "/", "/index.html" })
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");
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