package com.utp.teamwork.service;

import com.utp.teamwork.model.Employees;
import com.utp.teamwork.model.Roles;
import com.utp.teamwork.repository.EmployeesRepository;
import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


@Component
public class UserDetailsServiceImpl implements UserDetailsService {
	
        @Autowired
	EmployeesRepository employeesRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Employees employee = employeesRepository.findByUsername(username);
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		
                Roles role = employee.getRole();
		grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
		

		return new org.springframework.security.core.userdetails.User(employee.getUsername(), 
                                                                              employee.getPassword(),
                                                                              grantedAuthorities);
	}
}
