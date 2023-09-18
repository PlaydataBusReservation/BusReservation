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
//public class Company {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    private String name;
//
//    @OneToMany(mappedBy = "company")
//    private List<Driver> drivers;
//
//    @OneToMany(mappedBy = "company")
//    private List<Manager> managers;
//
//}
