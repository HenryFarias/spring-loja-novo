package br.edu.ulbra.gestaoloja.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.edu.ulbra.gestaoloja.input.UserInput;
import br.edu.ulbra.gestaoloja.model.User;
import br.edu.ulbra.gestaoloja.repository.UserRepository;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    private ModelMapper mapper = new ModelMapper();

    @GetMapping()
    public ModelAndView listUserDemo(){
        ModelAndView mv = new ModelAndView("user/list");
        List<User> usuarios = (List<User>) userRepository.findAll();
        mv.addObject("users", usuarios);
        return mv;
    }

    private ModelAndView userForm(UserInput userInput){
        ModelAndView mv = new ModelAndView("user/new");
        mv.addObject("user", userInput);
        return mv;
    }

    @GetMapping("/new")
    public ModelAndView newUser(){
        return this.userForm(new UserInput());
    }

    @PostMapping(value="/new")
    public ModelAndView newUser(UserInput userInput) {
        if (userInput.getPassword() != null && userInput.getPassword().length() > 0 && !userInput.getPassword().equals(userInput.getPasswordConfirm())){
            ModelAndView mv = this.userForm(userInput);
            mv.addObject("error", "Senha inválida!");
            return mv;
        }
        User user = mapper.map(userInput, User.class);
        userRepository.save(user);
        return new ModelAndView("redirect:/user/?usercreated=true");
    }
    
    @PostMapping(value="/login")
    public ModelAndView login(UserInput userInput) {
        return new ModelAndView("redirect:/user/?usercreated=true");
    }

    @GetMapping("/{id}")
    public ModelAndView viewUserDemo(@PathVariable(name="id") Long id){
        User usuario = userRepository.findOne(id);
        UserInput userInput = mapper.map(usuario, UserInput.class);
        ModelAndView mv = this.userForm(userInput);
        mv.setViewName("user/update");
        return mv;
    }

    @PostMapping("/{id}")
    public ModelAndView updateUserDemo(@PathVariable(name="id") Long id, UserInput userInput){
        User usuario = userRepository.findOne(id);
        if (userInput.getPassword() != null && userInput.getPassword().length() > 0 && !userInput.getPassword().equals(userInput.getPasswordConfirm())){
            ModelAndView mv = this.userForm(userInput);
            mv.addObject("error", "Password don't match!");
            return mv;
        }
        usuario.setUsername(userInput.getUsername());
       // usuario.setPassword(userInput.getPassword());
        usuario.setName(userInput.getName());
        userRepository.save(usuario);
        return new ModelAndView("redirect:/user/?usercreated=true");
    }
    
    @GetMapping("/{id}/senha")
    public ModelAndView viewUserSenha(@PathVariable(name="id") Long id){
        User usuario = userRepository.findOne(id);
        UserInput userInput = mapper.map(usuario, UserInput.class);
        ModelAndView mv = this.userForm(userInput);
        mv.setViewName("user/updateSenha");
        return mv;
    }
    
    //Update senha
    @PostMapping("/{id}/senha")
    public ModelAndView updateUserSenha(@PathVariable(name="id") Long id, UserInput userInput){
        User usuario = userRepository.findOne(id);
        if (userInput.getPassword() != null && userInput.getPassword().length() > 0 && !userInput.getPassword().equals(userInput.getPasswordConfirm())){
            ModelAndView mv = this.userForm(userInput);
            mv.addObject("error", "Password don't match!");
            return mv;
        }
        usuario.setPassword(userInput.getPassword());
        userRepository.save(usuario);
        return new ModelAndView("redirect:/user/?usercreated=true");
    }

    @GetMapping("/{id}/delete")
    public ModelAndView deleteUserDemo(@PathVariable(name="id") Long id){
        User usuario = userRepository.findOne(id);
        userRepository.delete(usuario);
        return new ModelAndView("redirect:/user/?usercreated=true");

    }

}
