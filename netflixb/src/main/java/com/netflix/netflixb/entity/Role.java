package com.netflix.netflixb.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "roles") // tablo adı isteğe bağlı
public class Role {

    @Id
    private Long id;  // Örnek: 1, 2 gibi ID'ler

    private String name; // Örnek: "ROLE_USER", "ROLE_ADMIN"

    // Boş constructor
    public Role() {}

    public Role(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getter ve Setter metodları

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
