package com.raot.raotrankings.controller;

import com.raot.raotrankings.dto.PasswordDto;
import com.raot.raotrankings.dto.UserDto;
import com.raot.raotrankings.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public ResponseEntity<Void> createUser(@Valid @RequestBody UserDto userDto){
        userService.create(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/current")
    public Principal getAllUsernames(Principal principal){
        return principal;
    }

    @PutMapping("/{username}")
    public ResponseEntity<Void> changeUsername(@Valid @RequestBody PasswordDto passwordDto, @PathVariable String username){

        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
