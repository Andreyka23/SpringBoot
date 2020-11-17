package com.geekbrains.myboot.market.dto;

import com.geekbrains.myboot.market.models.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private Integer birthday_year;
    private Integer gender;
    private String city;

    public UserDto(User u) {
        this.id = u.getId();
        this.name = u.getName();
        this.surname = u.getSurname();
        this.email = u.getEmail();
        this.phone = u.getPhone();
        this.birthday_year = u.getBirthday_year();
        this.gender = u.getGender();
        this.city = u.getCity();
    }
}
