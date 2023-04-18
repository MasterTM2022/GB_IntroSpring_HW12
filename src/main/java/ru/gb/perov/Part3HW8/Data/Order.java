package ru.gb.perov.Part3HW8.Data;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.Calendar;
import java.util.List;

@Component
@Getter
@Setter
//@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @Autowired
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "order_date")
    private Calendar date;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @JsonBackReference
    @OneToMany(mappedBy = "order")
    private List<OrderString> orderString;

//    public Order() {
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public Customer getCustomer() {
//        return customer;
//    }
//
//    public Calendar getDate() {
//        return date;
//    }
//
//    public void setDate(Calendar date) {
//        this.date = date;
//    }
//
    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", customerId=" + customer +
                ", date=" + date +
                '}';
    }
}