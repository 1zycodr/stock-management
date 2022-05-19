package kz.iitu.itse1909r.mukhitdinov.core.repository;

import kz.iitu.itse1909r.mukhitdinov.core.entity.Privilege;

public interface PrivilegeRepository {
    Privilege findPrivilegeByName(String name);
    void save(Privilege privilege);
}
