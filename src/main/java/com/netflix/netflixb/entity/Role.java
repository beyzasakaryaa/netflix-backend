package com.netflix.netflixb.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Otomatik artan ID, pratikte daha rahat olur

    @Column(nullable = false, unique = true, length = 50)
    private String name; // "ROLE_USER", "ROLE_ADMIN" gibi

    // Boş constructor
    public Role() {}

    public Role(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    // Sadece isimle de oluşturmak için ek constructor (opsiyonel)
    public Role(String name) {
        this.name = name;
    }

    // Getter ve Setter’lar
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
