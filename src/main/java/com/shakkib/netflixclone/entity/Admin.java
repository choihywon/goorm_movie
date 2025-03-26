package com.shakkib.netflixclone.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Admin {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String name;

    @OneToOne(mappedBy = "admin")
    private AdminAccount adminAccount;

    //기본 생성자 (JPA 사용을 위한 필수)
    protected Admin() {}

    // 테스트를 위한 생성자 추가
    public Admin(String email, String name) {
        this.email = email;
        this.name = name;
    }
}
