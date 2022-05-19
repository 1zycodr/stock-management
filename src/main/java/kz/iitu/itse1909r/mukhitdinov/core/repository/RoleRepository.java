package kz.iitu.itse1909r.mukhitdinov.core.repository;

import kz.iitu.itse1909r.mukhitdinov.core.entity.Role;

import java.util.List;

public interface RoleRepository {
    List<Role> findAll();
    Role findByName(String name);
    void save(Role role);
}
