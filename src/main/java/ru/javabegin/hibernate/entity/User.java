package ru.javabegin.hibernate.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@ToString(exclude = {"categories", "priorities", "roles", "tasks"})
@ToString(exclude = "roles")
//@EqualsAndHashCode(exclude = {"categories", "priorities", "roles", "tasks"})
@EqualsAndHashCode(exclude = "roles")
@Entity
@Table(name = "user_data", schema = "public", catalog = "task_planner")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String username;

    @Column(name = "userpassword")
    private String password;

    @Builder.Default
    @ManyToMany(mappedBy = "users", fetch = FetchType.EAGER)
    private Set<Role> roles = new HashSet<>();

//    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
//    private List<Task> tasks;

//    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
//    private List<Category> categories;

//    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
//    private List<Priority> priorities;

//    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY, optional = false)
//    private Activity activity;

//    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY, optional = false)
//    private Stat stat;

}
