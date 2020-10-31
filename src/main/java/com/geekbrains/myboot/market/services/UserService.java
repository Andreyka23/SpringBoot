package com.geekbrains.myboot.market.services;


import com.geekbrains.myboot.market.models.Role;
import com.geekbrains.myboot.market.models.User;
import com.geekbrains.myboot.market.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(String.format("User '%s' not found", username)));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    public User updateUser(User user, String name, String surname, String phone, String email, Integer birthday_year, Integer gender, String city) {
        user.setName(name);
        user.setSurname(surname);
        user.setPhone(phone);
        user.setEmail(email);
        user.setBirthday_year(birthday_year);
        user.setGender(gender);
        user.setCity(city);
        return userRepository.save(user);
    }


    public boolean checkPassword(User user, String tryPassword) {
        System.out.println(BCrypt.hashpw(tryPassword, BCrypt.gensalt()));
        System.out.println(tryPassword);
        System.out.println(user.getPassword());

        return BCrypt.hashpw(tryPassword, BCrypt.gensalt()).equals(user.getPassword());
    }

    public User registerUser(String username, String email, String password) {
        User new_user = new User();
        new_user.setUsername(username);
        new_user.setEmail(email);
        new_user.setPassword(BCrypt.hashpw(password, BCrypt.gensalt()));
        return userRepository.save(new_user);
    }

}