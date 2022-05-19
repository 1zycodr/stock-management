package kz.iitu.itse1909r.mukhitdinov.core.service.impl;

import kz.iitu.itse1909r.mukhitdinov.core.entity.AppUser;
import kz.iitu.itse1909r.mukhitdinov.core.entity.Privilege;
import kz.iitu.itse1909r.mukhitdinov.core.entity.Role;
import kz.iitu.itse1909r.mukhitdinov.core.repository.AppUserRepository;
import kz.iitu.itse1909r.mukhitdinov.core.repository.RoleRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class AppUserDetailsService implements UserDetailsService {
    private AppUserRepository userRepository;
    private RoleRepository roleRepository;

    public AppUserDetailsService(AppUserRepository userRepository,
                                 RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = userRepository.findUserByUsername(username);
        if (user == null) { throw new UsernameNotFoundException("User not found"); }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),true,true,true,true,getAuthorities( roleRepository.findAll()));
    }

    public Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles) {
        return getGrantedAuthorities(getPrivileges(roles));
    }

    public List<String> getPrivileges(Collection<Role> roles) {
        List<String> privileges = new ArrayList<>();
        List<Privilege> collection = new ArrayList<>();
        for (Role role : roles) { collection.addAll(role.getPrivileges()); }
        for (Privilege item : collection) { privileges.add(item.getName()); }

        roles.forEach(role -> { privileges.add(role.getName()); });

        return privileges;
    }

    private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String privilege : privileges) { authorities.add(new SimpleGrantedAuthority(privilege)); }
        return authorities;
    }
}
