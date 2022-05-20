package kz.iitu.itse1909r.mukhitdinov.core.controller.models.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateRequestModel {
    private String firstName;
    private String lastName;
}
