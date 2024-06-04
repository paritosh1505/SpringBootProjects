package com.scm.org.paritosh.entity;


import java.util.ArrayList;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util. *;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Contact {
    @Id
    private String Id;
    private String name;
    private String email;
    private String phoneno;
    private String address;
    private String picture;
    @Column(length=1000)
    private String desc_val;
    private boolean fav = false;
    private String websiteLink;
    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "contact",cascade = CascadeType.ALL,fetch=FetchType.EAGER,orphanRemoval = true)
    private List<SocialLink> socialLinks = new ArrayList<>();


}
