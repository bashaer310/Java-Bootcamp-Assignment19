package com.example.assignment17.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Username must not be empty")
    @Size(min = 3,message = "Username length must be more than 2")
    @Column(columnDefinition = "varchar(30) unique not null check(LENGTH(username)>=3)")
    private String username;


    @NotEmpty(message = "Password must not be empty")
    @Size(min = 8,message = "Password length must be more than 7")
    @Column(columnDefinition = "varchar(150) not null check(LENGTH(password)>=8)")
    private String password;

    @NotEmpty(message = "Role must not be empty")
    @Pattern(regexp = "CUSTOMER|EXPERT",message = "Role must be CUSTOMER or EXPERT")
    @Column(columnDefinition = "varchar(8) not null check(role='CUSTOMER' or role='EXPERT')")
    private String role;

    @OneToOne(cascade = CascadeType.ALL,mappedBy = "user")
    @PrimaryKeyJoinColumn
    private Customer customer;

    @OneToOne(cascade = CascadeType.ALL,mappedBy = "user")
    @PrimaryKeyJoinColumn
    private Expert expert;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(this.role));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
