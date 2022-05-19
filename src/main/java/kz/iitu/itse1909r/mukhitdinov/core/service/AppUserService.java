package kz.iitu.itse1909r.mukhitdinov.core.service;

import kz.iitu.itse1909r.mukhitdinov.core.controller.models.UserCreateRequestModel;

public interface AppUserService {
    String signin(String username, String password);
    String signup(UserCreateRequestModel appUser);
}
