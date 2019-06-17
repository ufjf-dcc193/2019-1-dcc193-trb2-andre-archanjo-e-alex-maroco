package br.ufjf.dcc193.trab2.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.ufjf.dcc193.trab2.model.Avaliador;
import br.ufjf.dcc193.trab2.repository.AvaliadorRepository;
import br.ufjf.dcc193.trab2.service.LoginService;

/**
 * AvaliadorController
 */
@Controller
public class AvaliadorController {

    @Autowired
    AvaliadorRepository aRepo;

    @Autowired
    private LoginService ls;

    @GetMapping(value={"/login.html" })
    public ModelAndView login() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("avaliador", new Avaliador());
        mv.setViewName("login");
        return mv;
    }

    @PostMapping(value="/login.html")
    public ModelAndView login(@Valid Avaliador avaliador, BindingResult binding){
            ModelAndView mv = new ModelAndView();
            if(binding.hasErrors()){
                mv.setViewName("login");
                mv.addObject("avaliador", avaliador);
                return mv;
            }
            Avaliador a = aRepo.findOneByEmailAndCodigoAcesso(avaliador.getEmail(), avaliador.getCodigoAcesso());
            System.err.println(a);
            if(a != null){
                ls.login(a);
                mv.setViewName("redirect:/avaliador/home.html");
                return mv;
            }
            mv.setViewName("redirect:login.html");
            return mv;
    }

    @GetMapping(value={"/avaliador/home.html"})
    public ModelAndView home() {
        ModelAndView mv = new ModelAndView();
        Avaliador a = ls.getUser();
        mv.addObject("avaliador", a);
        mv.setViewName("avaliador-logado");
        return mv;
    }

    @GetMapping(value={"/avaliador/cadastro.html" })
    public ModelAndView cadastroAvaliador() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("avaliador", new Avaliador());
        mv.setViewName("form-cadastro-avaliador");
        return mv;
    }

    @PostMapping(value="/avaliador/cadastro.html")
    public ModelAndView cadastroAvaliador(@Valid Avaliador avaliador, BindingResult binding){
            ModelAndView mv = new ModelAndView();
            if(binding.hasErrors()){
                mv.setViewName("form-cadastro-avaliador");
                mv.addObject("avaliador", avaliador);
                return mv;
            }
            //System.err.println(avaliador.toString());
            aRepo.save(avaliador);
            System.err.println(aRepo.findAll());
            mv.setViewName("redirect:/index.html");
            return mv;
    }

    @GetMapping(value={"/avaliador/listar.html" })
    public ModelAndView listarTodos() {
        ModelAndView mv = new ModelAndView();
        List<Avaliador> av = aRepo.findAll();
        mv.addObject("avaliadores", av);
        mv.setViewName("list-avaliadores");
        return mv;
    }

    @GetMapping(value={"/avaliador/editar.html" })
    public ModelAndView editarAvaliador(@RequestParam Long id) {
        ModelAndView mv = new ModelAndView();
        Avaliador av = aRepo.findById(id).get();
        mv.addObject("avaliador", av);
        mv.setViewName("form-edit-avaliador");
        return mv;
    }

    @PostMapping(value={"/avaliador/editar.html" })
    public ModelAndView editarAvaliador(@Valid Avaliador avaliador, BindingResult binding) {
        ModelAndView mv = new ModelAndView();
            if(binding.hasErrors()){
                mv.setViewName("form-edit-avaliador");
                mv.addObject("avaliador", avaliador);
                return mv;
            }
            Avaliador av = aRepo.getOne(avaliador.getId());
            String[] ignorar = {"id", "listRevisao"};
            BeanUtils.copyProperties(avaliador, av, ignorar);
            // av.setNome(avaliador.getNome());
            // av.setEmail(avaliador.getEmail());
            // av.setCodigoAcesso(avaliador.getCodigoAcesso());
            // av.setAreaExatas(avaliador.isAreaExatas());
            // av.setAreaHumanas(avaliador.isAreaHumanas());
            aRepo.save(av);
            mv.setViewName("redirect:/avaliador/listar.html");
            return mv;
    }

    @GetMapping(value={"/avaliador/excluir.html" })
    public ModelAndView excluirAvaliador(@RequestParam Long id) {
        ModelAndView mv = new ModelAndView();
        aRepo.deleteById(id);
        mv.setViewName("redirect:/avaliador/listar.html");
        return mv;
    }

}