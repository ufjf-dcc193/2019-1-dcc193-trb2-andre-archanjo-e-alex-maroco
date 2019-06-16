package br.ufjf.dcc193.trab2.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.ufjf.dcc193.trab2.model.Trabalho;
import br.ufjf.dcc193.trab2.repository.TrabalhoRepository;



@Controller
public class TrabalhoController {

    @Autowired
    TrabalhoRepository tRepo;

    @GetMapping("/CadastroTrabalho.html")
    public ModelAndView cadastroTrabalho() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("trabalho", new Trabalho());
        mv.setViewName("form-cadastro-trabalho");
        return mv;
    }

    @PostMapping("/trabalho/cadastro.html")
    public ModelAndView cadastroTrabalho(@Valid Trabalho trab, BindingResult binding) {
        ModelAndView mv = new ModelAndView();
            if(binding.hasErrors()){
                mv.setViewName("form-cadastro-trabalho");
                mv.addObject("trabalho", trab);
                return mv;
            }
            //System.err.println(trab.toString());
            tRepo.save(trab);
            System.err.println(tRepo.findAll());
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