package kz.iitu.itse1909r.mukhitdinov.core.repository;

import kz.iitu.itse1909r.mukhitdinov.core.entity.AppUser;

import java.util.List;

public interface AppUserRepository {
    List<AppUser> findAll();
    AppUser findById(Long id);
    AppUser findUserByUsername(String username);
    void save(AppUser user);
//    AppUser createUser(AppUser user);
}
