package org.example.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
import org.example.Utils;
import org.example.dto.CreateUserRequest;
import org.example.dto.GetUserResponse;
import org.example.models.User;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/user")
    public void createUser(@RequestBody @Valid CreateUserRequest userRequest) throws JsonProcessingException {
        userService.create(Utils.convertUserCreateRequest(userRequest));
    }

    @GetMapping("/user/{userId}")
    public GetUserResponse getUser(@PathVariable("userId") int userId) throws Exception{
        User user=userService.get(userId);
        return Utils.convertToUserResponse(user);
    }

}
