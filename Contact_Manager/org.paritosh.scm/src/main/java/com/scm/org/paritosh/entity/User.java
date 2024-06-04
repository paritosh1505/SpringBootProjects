package com.scm.org.paritosh.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity(name="User")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements UserDetails {
    @Id
    private String Id;
    @Getter(value=AccessLevel.NONE)
    private String password;
    @Getter(value=AccessLevel.NONE)
    public String username;
    private String phoneNumber;
    @Column(nullable = false,unique = true)
    private String email;
    @Column(length=1000)
    private String about;
    private String profilePic;
    private String websiteLink;
    private String linkedLink;
    private boolean phoneVerified=false;
    private boolean emailVerified=false;
    @Getter(value=AccessLevel.NONE)
    private boolean enabled=true;
    @Enumerated(value=EnumType.STRING)
    private Provider provider = Provider.SELF;
    private String providerUserid;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL,fetch=FetchType.LAZY,orphanRemoval = true)
    private List<Contact> contacts = new ArrayList<>();

    @ElementCollection(fetch=FetchType.EAGER)
    private List<String> roles = new ArrayList<>();
   
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
     Collection<SimpleGrantedAuthority> roleval=  roles.stream().map(role->new SimpleGrantedAuthority(role)).collect(Collectors.toList());
     return roleval;
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
    public String getUsername() {
        return this.username;
    }
    @Override
    public boolean isEnabled(){
        return this.enabled;
    }
    @Override
    public String getPassword() {
       return this.password;
    }
   
}
