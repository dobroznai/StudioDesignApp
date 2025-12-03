package de.ait.training.service;

import de.ait.training.model.User;
import de.ait.training.repository.SDUserRepository;
import de.ait.training.security.SDUserSecurity;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SDUserDetailsService implements UserDetailsService {

    private final SDUserRepository userRepository;


    public SDUserDetailsService(SDUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername (String username) throws UsernameNotFoundException {
        User user = userRepository.findByName(username).orElseThrow(() ->
                new UsernameNotFoundException("User not found " + username));
        return new SDUserSecurity(user);
    }
}
