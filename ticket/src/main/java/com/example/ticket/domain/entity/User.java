//package com.example.ticket.domain.entity;
//
//import jakarta.persistence.*;
//import lombok.*;
//
//import java.util.List;
//import java.util.UUID;
//
//@Table(name = "users")
//@Entity
//@AllArgsConstructor
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@Builder
//@Getter
//public class User {
//    @Id
//    @GeneratedValue(strategy = GenerationType.UUID)
//    private UUID id;
//    private String name;
//    private String email;
//    private String password;
//    private String number;
//    private Integer age;
//    @Enumerated(EnumType.STRING)
//    private Role role;
//
//    @OneToMany(mappedBy = "user")
//    private List<Ticket> tickets;
//
//}
