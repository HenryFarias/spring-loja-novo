package br.edu.ulbra.gestaoloja.service;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ulbra.gestaoloja.model.Role;
import br.edu.ulbra.gestaoloja.model.User;
import br.edu.ulbra.gestaoloja.repository.RoleRepository;
import br.edu.ulbra.gestaoloja.repository.UserRepository;
import br.edu.ulbra.gestaoloja.service.interfaces.SecurityService;
import br.edu.ulbra.gestaoloja.service.interfaces.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private SecurityService security;

    @Override
    public void save(User user) {
    	HashSet<Role> roles = new HashSet<Role>(0);
    	
    	roles.add(this.roleRepository.findByName("ROLE_USER"));
    	user.setRoles(roles);
    	
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    
    @Override
    public Boolean isAdmin(User user) {
    	for (Role role : user.getRoles()) {
    		if (role.getName().equals("ROLE_ADMIN")) {
    			return true;
    		}
    	}
    	
        return false;
    }

	@Override
	public String encoder(String password) {
		// TODO Auto-generated method stub
		return null;
	}
}
