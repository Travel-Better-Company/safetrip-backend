package com.safetripbackend.PlannerItinerarie.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.catalina.User;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User_Preferences {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne
    @JoinColumn(name = "id_user")
    private Users users;
    @ManyToOne
    @JoinColumn(name = "id_city")
    private Cities cities;

}
