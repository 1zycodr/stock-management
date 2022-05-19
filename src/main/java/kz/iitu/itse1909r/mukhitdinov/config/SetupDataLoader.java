package kz.iitu.itse1909r.mukhitdinov.config;

import kz.iitu.itse1909r.mukhitdinov.core.entity.AppUser;
import kz.iitu.itse1909r.mukhitdinov.core.entity.Privilege;
import kz.iitu.itse1909r.mukhitdinov.core.entity.Role;
import kz.iitu.itse1909r.mukhitdinov.core.repository.AppUserRepository;
import kz.iitu.itse1909r.mukhitdinov.core.repository.PrivilegeRepository;
import kz.iitu.itse1909r.mukhitdinov.core.repository.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collection;

//@Component
//@SuppressWarnings("unchecked")
//@Slf4j
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {
    boolean alreadySetup = false;

    private final AppUserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PrivilegeRepository privilegeRepository;
    private final PasswordEncoder passwordEncoder;

    public SetupDataLoader(
            AppUserRepository userRepository, RoleRepository roleRepository,
            PrivilegeRepository privilegeRepository, PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.privilegeRepository = privilegeRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        Privilege readPrivilege = createPrivilegeIfNotFound("READ_PRIVILEGE");
        Privilege writePrivilege = createPrivilegeIfNotFound("WRITE_PRIVILEGE");
        Privilege deletePrivilege = createPrivilegeIfNotFound("DELETE_PRIVILEGE");

        Role adminRole = createRoleIfNotFound(
                "ROLE_ADMIN", Arrays.asList(readPrivilege, writePrivilege, deletePrivilege)
        );
        createRoleIfNotFound("ROLE_STAFF", Arrays.asList(readPrivilege, writePrivilege));
        createRoleIfNotFound("ROLE_USER", Arrays.asList(readPrivilege));

        AppUser user = AppUser
                .builder()
                .username("admin")
                .password(passwordEncoder.encode("admin"))
                .roles(Arrays.asList(adminRole))
                .build();
        userRepository.save(user);
        alreadySetup = true;
    }

    Privilege createPrivilegeIfNotFound(String name) {
        Privilege privilege = privilegeRepository.findPrivilegeByName(name);
        if (privilege == null) { privilege = new Privilege(name); privilegeRepository.save(privilege); }
        return privilege;
    }

    Role createRoleIfNotFound(String name, Collection<Privilege> privileges) {
        Role role = roleRepository.findByName(name);
        if (role == null) { role = new Role(name); role.setPrivileges(privileges); roleRepository.save(role); }
        return role;
    }
}
