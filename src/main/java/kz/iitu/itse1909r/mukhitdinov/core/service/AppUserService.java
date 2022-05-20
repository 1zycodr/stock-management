package kz.iitu.itse1909r.mukhitdinov.core.service;

import kz.iitu.itse1909r.mukhitdinov.core.controller.models.user.UserCreateRequestModel;
import kz.iitu.itse1909r.mukhitdinov.core.controller.models.user.UserUpdateRequestModel;
import kz.iitu.itse1909r.mukhitdinov.core.entity.AppUser;

public interface AppUserService {
    String signin(String username, String password);
    String signup(UserCreateRequestModel appUser);
    AppUser getById(Long id);
    AppUser updateUser(Long id, UserUpdateRequestModel user);
    void deleteUser(Long id);
}
