package br.ufjf.dcc193.trab2.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


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
    
    @GetMapping(value={"/listar.html"})
    public ModelAndView listarTodos() {
        ModelAndView mv = new ModelAndView();
        List<Trabalho> tr = tRepo.findAll();
        mv.addObject("trabalhos", tr);
        if(ls.getUser() != null) {
            mv.addObject("username", ls.getUser().getNome());
        } else {
            mv.addObject("username", "-");
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
            String[] ignorar = {"id", "listRevisaoTrabalho"};
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
    
    @GetMapping(value={"/listar.html"}, params = {"area"})
    public ModelAndView listarArea(@RequestParam int area) {
        ModelAndView mv = new ModelAndView();
        List<Trabalho> tbr = tRepo.findAllByAreaConhecimento(area);
        HashMap<Trabalho, Integer> contador = new HashMap<>();
        int qtd = 0;
        for (Trabalho t : tbr) {
            qtd = 0;
            for(Revisao r: t.getListRevisaoTrabalho()) {
                if(r.getStatus() == 1) {
                    qtd++;
                }
            }
            contador.put(t, qtd);
        }
        Map<Trabalho, Integer> sorted = sortByValue(contador);
        mv.addObject("hashmap", sorted);
        mv.addObject("area", area);
        mv.setViewName("list-trabalhos-area");
        return mv;
    }

    private HashMap<Trabalho, Integer> sortByValue(HashMap<Trabalho, Integer> hm) 
    { 
        // Create a list from elements of HashMap 
        List<Map.Entry<Trabalho, Integer> > list = 
               new LinkedList<Map.Entry<Trabalho, Integer>>(hm.entrySet()); 
  
        // Sort the list 
        Collections.sort(list, new Comparator<Map.Entry<Trabalho, Integer> >() { 
            public int compare(Map.Entry<Trabalho, Integer> o1,  
                               Map.Entry<Trabalho, Integer> o2) 
            { 
                return (o1.getValue()).compareTo(o2.getValue()); 
            } 
        }); 
          
        // put data from sorted list to hashmap  
        HashMap<Trabalho, Integer> temp = new LinkedHashMap<Trabalho, Integer>(); 
        for (Map.Entry<Trabalho, Integer> aa : list) { 
            temp.put(aa.getKey(), aa.getValue()); 
        } 
        return temp; 
    }
    
}