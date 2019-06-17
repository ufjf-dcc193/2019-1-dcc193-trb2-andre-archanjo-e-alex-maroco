package br.ufjf.dcc193.trab2.controller;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.ufjf.dcc193.trab2.model.Revisao;
import br.ufjf.dcc193.trab2.model.Trabalho;
import br.ufjf.dcc193.trab2.repository.RevisaoRepository;
import br.ufjf.dcc193.trab2.repository.TrabalhoRepository;

@Controller
@RequestMapping("/revisao")
public class RevisaoController {

    @Autowired
    TrabalhoRepository tRepo;
    
    @Autowired
    RevisaoRepository rRepo;

    @GetMapping("/cadastrar.html")
    public ModelAndView cadastroRevisao(@RequestParam Long id) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("revisao", new Revisao());
        System.err.println(id);
        mv.addObject("idTrabalho", id);
        mv.setViewName("form-cadastro-revisao");
        return mv;
    }

    @PostMapping(value={"/cadastrar.html" })
    public ModelAndView cadastroRevisao(@Valid Revisao revisao, @RequestParam Long idTrabalho, BindingResult binding) {
        ModelAndView mv = new ModelAndView();
            if(binding.hasErrors()){
                mv.setViewName("form-cadastro-revisao");
                mv.addObject("revisao", revisao);
                mv.addObject("idTrabalho", idTrabalho);
                return mv;
            }
            rRepo.save(revisao);
            System.err.println(revisao.toString());
            Trabalho tr = tRepo.findById(idTrabalho).get();
            System.err.println(tr.toString());
            tr.addRevisao(revisao);
            // tr.setTitulo(trabalho.getTitulo());
            // tr.setDescricao(trabalho.getDescricao());
            // tr.setUrl(trabalho.getUrl());
            // tr.setAreaConhecimento(trabalho.getAreaConhecimento());
            tRepo.save(tr);
            mv.setViewName("redirect:/index.html");
            return mv;
    }

}