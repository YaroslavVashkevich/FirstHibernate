package ru.javabegin.hibernate.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = {"category", "priority", "user"})
@EqualsAndHashCode(exclude = {"category", "priority", "user"})
@Entity
@Table(name = "task", schema = "public", catalog = "task_planner")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Convert(converter = org.hibernate.type.NumericBooleanConverter.class)
    private Boolean completed;

    @Column(name = "task_date")
    private LocalDateTime taskDate;

    @ManyToOne
    @JoinColumn(name = "priority_id", referencedColumnName = "id")
    private Priority priority;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

}
