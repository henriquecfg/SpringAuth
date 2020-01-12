package com.henrique.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Entity
public class Client implements Serializable {


	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;

    @JsonIgnore
    private String password;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @ElementCollection(fetch=FetchType.EAGER)
    @CollectionTable(name="profiles")
    private Set<Integer> profiles = new HashSet<>();

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Car> carSet = new ArrayList<>();

    public Client() {
        addProfile(Role.USER);
    }

    public Client(Long id, String name, String email, String password, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super();
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        addProfile(Role.USER);
    }

    public Set<Role> getProfiles(){
        return profiles.stream().map(x -> Role.toEnum(x)).collect(Collectors.toSet());
    }

    public void addProfile(Role role){
        profiles.add(role.getCod());
    }
}
