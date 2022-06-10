package ru.coursework.eco_market.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // Автоматическая гинерация ID для пользователей
    private Long id;
    private String username;
    private String password;
    private boolean active; // Признак активности

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER) // Жадная подгрузка(при запросе пользователя все его роли сразу подгружаются)
    // Для малого числа данных - ускоряет работу, но подъедает памать
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id")) // Данное поле хранится в отдельной табице, для которой не описывался mapping
    @Enumerated(EnumType.STRING)
    private Set<Role> roles; // Поле для ролей одного пользователя, тк их может быть несколько

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
