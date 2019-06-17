package br.ufjf.dcc193.trab2.controller;

import java.util.List;

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

import br.ufjf.dcc193.trab2.model.Trabalho;
import br.ufjf.dcc193.trab2.repository.TrabalhoRepository;
import br.ufjf.dcc193.trab2.service.LoginService;



@Controller
@RequestMapping("/trabalho")
public class TrabalhoController {

    @Autowired
    TrabalhoRepository tRepo;

    @Autowired
    private LoginService ls;

    @GetMapping("/cadastro.html")
    public ModelAndView cadastroTrabalho() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("trabalho", new Trabalho());
        mv.setViewName("form-cadastro-trabalho");
        return mv;
    }

    @PostMapping("/cadastro.html")
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
            mv.setViewName("redirect:/index.html");
            return mv;
    }
    
    @GetMapping(value={"/listar.html" })
    public ModelAndView listarTodos() {
        ModelAndView mv = new ModelAndView();
        List<Trabalho> tr = tRepo.findAll();
        mv.addObject("trabalhos", tr);
        if(ls.getUser() != null) {
            mv.addObject("username", ls.getUser().getNome());
        } else {
            mv.addObject("username", "Sem user");
        }
        mv.setViewName("list-trabalhos");
        return mv;
    }

    @GetMapping(value={"/editar.html" })
    public ModelAndView editarTrabalho(@RequestParam Long id) {
        ModelAndView mv = new ModelAndView();
        Trabalho tr = tRepo.findById(id).get();
        mv.addObject("trabalho", tr);
        mv.setViewName("form-edit-trabalho");
        return mv;
    }

    @PostMapping(value={"/editar.html" })
    public ModelAndView editarTrabalho(@Valid Trabalho trabalho, BindingResult binding) {
        ModelAndView mv = new ModelAndView();
            if(binding.hasErrors()){
                mv.setViewName("form-edit-trabalho");
                mv.addObject("trabalho", trabalho);
                return mv;
            }
            Trabalho tr = tRepo.getOne(trabalho.getId());
            String[] ignorar = {"id", "revisao"};
            BeanUtils.copyProperties(trabalho, tr, ignorar);
            // tr.setTitulo(trabalho.getTitulo());
            // tr.setDescricao(trabalho.getDescricao());
            // tr.setUrl(trabalho.getUrl());
            // tr.setAreaConhecimento(trabalho.getAreaConhecimento());
            tRepo.save(tr);
            mv.setViewName("redirect:/trabalho/listar.html");
            return mv;
    }

    @GetMapping(value={"/excluir.html" })
    public ModelAndView excluirTrabalho(@RequestParam Long id) {
        ModelAndView mv = new ModelAndView();
        tRepo.deleteById(id);
        mv.setViewName("redirect:/trabalho/listar.html");
        return mv;
    }
    
    @GetMapping(value={"/listarArea.html"})
    public ModelAndView listarArea(@RequestParam int area) {
        ModelAndView mv = new ModelAndView();
        List<Trabalho> tr = tRepo.findAllByAreaConhecimento(area);
        mv.addObject("trabalhos", tr);
        mv.addObject("area", area);
        mv.setViewName("list-trabalhos-area");
        return mv;
    }
    
}