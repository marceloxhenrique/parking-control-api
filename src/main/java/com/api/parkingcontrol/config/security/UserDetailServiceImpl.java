package com.api.parkingcontrol.config.security;

import com.api.parkingcontrol.models.UserModel;
import com.api.parkingcontrol.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserDetailServiceImpl implements UserDetailsService {
    final UserRepository userRepository;

    public UserDetailServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel userModel = userRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username" + username));
        return new User(
                userModel.getUsername(),
                userModel.getPassword(),
                true,
                true,
                true,
                true,
                userModel.getAuthorities());
    }
}
