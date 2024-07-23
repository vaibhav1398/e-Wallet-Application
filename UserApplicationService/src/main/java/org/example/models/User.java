package org.example.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements Serializable, UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @Column(unique = true,nullable = false)
    private String phone;

    @Column(unique = true)
    private String email;

    private int age;

    @CreationTimestamp
    private Date createdOn;

    @UpdateTimestamp
    private Date updatedOn;

    private String password;

    private String authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String[] authorities=this.authorities.split(":");
        return Arrays.stream(authorities).map(x -> new SimpleGrantedAuthority(x))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Column(unique = true,nullable = false)
    private String username;        //provide phone number here

    @Override
    public String getUsername() {
        return this.username;
    }
}
