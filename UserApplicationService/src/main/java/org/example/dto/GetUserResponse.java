package org.example.dto;

import lombok.*;

import java.util.Date;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetUserResponse {

    //data that we have to keep visible to user
    private String name;

    private String phone;

    private String email;

    private int age;

    private Date createdOn;

    private Date updatedOn;
}
