package com.raot.raotrankings.service;

import com.raot.raotrankings.dto.UserDto;
import com.raot.raotrankings.entity.User;
import com.raot.raotrankings.exceptions.UserAlreadyExistsException;
import com.raot.raotrankings.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean existsUsersByUsername(String username){
        return userRepository.existsUsersByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    public void create(UserDto userDto){
        Optional<User> existing = userRepository.findUserByUsername(userDto.getUsername());

        existing.ifPresent(user -> {
            throw new UserAlreadyExistsException("User already exists");
        });

        User user = User.builder()
                        .username(userDto.getUsername())
                        .password(passwordEncoder.encode(userDto.getPassword()))
                        .build();

        userRepository.save(user);
    }

}
