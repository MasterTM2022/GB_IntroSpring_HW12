package ru.gb.perov.Part3HW8.Data;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "customer")

public class Customer {
    @Getter
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "customer_names")
    private String customerName;

    @JsonManagedReference
    @OneToMany(mappedBy = "customer")
    private List<Order> orders;
}
