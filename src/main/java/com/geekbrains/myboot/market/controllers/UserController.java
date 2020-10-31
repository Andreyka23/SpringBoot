package com.geekbrains.myboot.market.controllers;

import com.geekbrains.myboot.market.dto.JwtResponse;
import com.geekbrains.myboot.market.dto.UserDto;
import com.geekbrains.myboot.market.exceptions.MarketError;
import com.geekbrains.myboot.market.exceptions.ResourceNotFoundException;
import com.geekbrains.myboot.market.models.Order;
import com.geekbrains.myboot.market.models.User;
import com.geekbrains.myboot.market.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    @GetMapping
    public UserDto getUser(Principal principal) {
        return new UserDto(
                userService.findByUsername(principal.getName()).orElseThrow(() -> new ResourceNotFoundException("Unable to create order for user: " + principal.getName() + ". User doesn't exist"))
        );
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> updateUserProfile( Principal principal, @RequestParam(defaultValue = "") String name, @RequestParam(defaultValue = "") String surname, @RequestParam(defaultValue = "") String phone,
                                   @RequestParam(defaultValue = "") String email, @RequestParam(defaultValue = "") Integer birthday_year, @RequestParam(defaultValue = "") Integer gender,
                                   @RequestParam(defaultValue = "") String city, @RequestParam(defaultValue = "") String password ) {
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new ResourceNotFoundException("Unable to create order for user: " + principal.getName() + ". User doesn't exist"));

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), password));
            userService.updateUser(user, name, surname, phone, email, birthday_year, gender, city);
            return ResponseEntity.ok("Okay");
        } catch (BadCredentialsException ex) {
            return new ResponseEntity<>(new MarketError(HttpStatus.BAD_REQUEST.value(), "Incorrect password"), HttpStatus.BAD_REQUEST);
        }
    }

}
