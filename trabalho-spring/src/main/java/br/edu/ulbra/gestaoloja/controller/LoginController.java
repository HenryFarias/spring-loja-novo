package br.edu.ulbra.gestaoloja.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.edu.ulbra.gestaoloja.input.UserInput;
import br.edu.ulbra.gestaoloja.model.User;
import br.edu.ulbra.gestaoloja.service.interfaces.SecurityService;
import br.edu.ulbra.gestaoloja.service.interfaces.UserService;

@Controller
public class LoginController {

    @Autowired
    private SecurityService securityService;
    
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }
    
    public ModelAndView redirecionaAposLogin() {
    	ModelAndView mv = new ModelAndView();
    	User userLogado = this.securityService.findLoggedInUser();
    	
    	if (this.userService.isAdmin(userLogado)) {
    		mv.setViewName("redirect:/user");
    	} else {
    		mv.setViewName("redirect:/produto");
    	}
    	
    	return mv;
    }
    
    @PostMapping("/login")
    public ModelAndView doLogin(UserInput userInput) {
        ModelAndView mv = new ModelAndView();
        
        try {
	        if (userInput.getUsername() == null || userInput.getPassword() == null) {
	            mv.addObject("loginError", true);
	            mv.setViewName("index");
	        } else {
				this.securityService.autologin(userInput.getUsername(), userInput.getPassword());
	            mv = this.redirecionaAposLogin();
	        }	
        } catch (Exception e) {
			e.printStackTrace();
		}
        
        return mv;
    }
}
