package org.example;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.example.dto.CreateUserRequest;
import org.example.dto.GetUserResponse;
import org.example.models.User;


public class Utils {

    public static User convertUserCreateRequest(CreateUserRequest request){
        //it will rreceive dto and will convert it into user object
        return User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .age(request.getAge())
                .build();
    }

    public static GetUserResponse convertToUserResponse(User user){
        return GetUserResponse.builder()
                .name(user.getName())
                .phone(user.getPhone())
                .age(user.getAge())
                .email(user.getEmail())
                .updatedOn(user.getUpdatedOn())
                .createdOn(user.getCreatedOn())
                .build();
    }
}
