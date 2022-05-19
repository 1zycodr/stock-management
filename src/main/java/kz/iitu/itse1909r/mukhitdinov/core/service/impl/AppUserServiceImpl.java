package kz.iitu.itse1909r.mukhitdinov.core.service.impl;

import kz.iitu.itse1909r.mukhitdinov.core.controller.models.UserCreateRequestModel;
import kz.iitu.itse1909r.mukhitdinov.core.entity.AppUser;
import kz.iitu.itse1909r.mukhitdinov.core.repository.AppUserRepository;
import kz.iitu.itse1909r.mukhitdinov.core.service.AppUserService;
import kz.iitu.itse1909r.mukhitdinov.security.jwt.JwtTokenProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AppUserServiceImpl implements AppUserService {

    private final AppUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;


    public AppUserServiceImpl(PasswordEncoder passwordEncoder,
                              JwtTokenProvider jwtTokenProvider,
                              AuthenticationManager authenticationManager,
                              AppUserRepository userRepository
    ) {
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
    }

    @Override
    public String signin(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            return jwtTokenProvider.createToken(username);
        } catch (AuthenticationException e) {
            throw new RuntimeException("Invalid username/password supplied", e);
        }
    }

    @Override
    public String signup(UserCreateRequestModel appUser) {
        if (userRepository.findUserByUsername(appUser.getUsername()) == null) {
            appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
            AppUser user = AppUser.builder()
                    .username(appUser.getUsername())
                    .password(appUser.getPassword())
                    .build();
            userRepository.save(user);
            return jwtTokenProvider.createToken(user.getUsername());
        } else {
            throw new RuntimeException("User already exists");
        }
    }
}
