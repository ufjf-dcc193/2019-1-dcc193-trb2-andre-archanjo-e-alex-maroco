package br.ufjf.dcc193.trab2.controller;

import java.util.HashMap;

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

import br.ufjf.dcc193.trab2.model.Avaliador;
import br.ufjf.dcc193.trab2.model.Revisao;
import br.ufjf.dcc193.trab2.model.Trabalho;
import br.ufjf.dcc193.trab2.repository.AvaliadorRepository;
import br.ufjf.dcc193.trab2.repository.RevisaoRepository;
import br.ufjf.dcc193.trab2.repository.TrabalhoRepository;
import br.ufjf.dcc193.trab2.service.LoginService;

@Controller
@RequestMapping("/revisao")
public class RevisaoController {

    @Autowired
    LoginService ls;

    @Autowired
    AvaliadorRepository aRepo;

    @Autowired
    TrabalhoRepository tRepo;
    
    @Autowired
    RevisaoRepository rRepo;

    @GetMapping("/cadastrar.html")
    public ModelAndView cadastroRevisao(@RequestParam Long id) {
        ModelAndView mv = new ModelAndView();

        System.err.println(System.lineSeparator());
        System.err.println(aRepo.findAll());
        System.err.println(System.lineSeparator());
        System.err.println(tRepo.findAll());
        System.err.println(System.lineSeparator());
        System.err.println(rRepo.findAll());
        mv.addObject("revisao", new Revisao());
        //System.err.println(id);
        Trabalho tr = tRepo.findById(id).get();
        mv.addObject("trabalho", tr);
        mv.setViewName("form-cadastro-revisao");
        return mv;
    }

    @PostMapping(value={"/cadastrar.html" })
    public ModelAndView cadastroRevisao(@Valid Revisao revisao, @RequestParam Long idTrabalho, @RequestParam String buttonName, BindingResult binding) {
        ModelAndView mv = new ModelAndView();
            if(binding.hasErrors()){
                mv.setViewName("form-cadastro-revisao");
                mv.addObject("revisao", revisao);
                mv.addObject("idTrabalho", idTrabalho);
                return mv;
            }
            Boolean isNew = false;
            if(revisao.getId() == null) {
                isNew = true;
            }
            Avaliador a = ls.getUser();
            Trabalho tr = tRepo.findById(idTrabalho).get();
            revisao.setAvaliador(a);
            revisao.setTrabalho(tr);
            System.err.println(buttonName);
            HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
            hashMap.put("Revisar Depois", 0);
            hashMap.put("Revisar Agora", 1);
            hashMap.put("Pular", 2);
            
            Integer val = (Integer)hashMap.get(buttonName);
            System.err.println(val);
            revisao.setStatus(val);

            System.err.println(revisao.toString());
            rRepo.save(revisao);
            if(isNew) {
                a.getListRevisaoAvaliador().add(revisao);
                tr.getListRevisaoTrabalho().add(revisao);
                a = aRepo.save(a);
                //System.err.println(a);
                tRepo.save(tr);
                ls.login(a);
            }
            //tRepo.save(tr);
            //rRepo.save(revisao);
            //revisao.setAvaliador(a);
            //revisao.setTrabalho(tr);
            //rRepo.save(revisao);
            //System.err.println(revisao.toString());
            //System.err.println(tr.toString());
            //tr.addRevisao(revisao);
            // tr.setTitulo(trabalho.getTitulo());
            // tr.setDescricao(trabalho.getDescricao());
            // tr.setUrl(trabalho.getUrl());
            // tr.setAreaConhecimento(trabalho.getAreaConhecimento());
            //tRepo.save(tr);

            
            mv.setViewName("redirect:/index.html");
            return mv;
    }

    /*@GetMapping(value={"/listarArea.html"})
    public ModelAndView listarArea(@RequestParam int area) {
        ModelAndView mv = new ModelAndView();
        List<Trabalho> tr = tRepo.findAllByAreaConhecimento(area);
        for (Trabalho t : tr) {
            System.err.println(t.toString());
        }
        mv.addObject("trabalhos", tr);
        mv.addObject("area", area);
        mv.setViewName("list-trabalhos-area");
        return mv;
    }*/

}