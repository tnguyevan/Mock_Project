package com.vti.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CreatorProduct")
public class CreatorProduct implements Serializable {

    private static final long serialVersionUID = 1L;


    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "staffId")
    private User user;

    @OneToOne
    @JoinColumn(name = "productId", referencedColumnName = "productId", nullable = false)
    private Product product;

    @CreatedDate
    @Column(name = "createDate", insertable = false)
    @CreationTimestamp
    private LocalDateTime createDate;



}