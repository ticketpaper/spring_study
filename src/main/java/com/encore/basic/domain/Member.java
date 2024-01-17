package com.encore.basic.domain;


import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

// Setter를 둬서 데이터 바인딩을 시키면 상관없지만 setter를 두는건 불안해서 DTO를 둔다

@Getter
//모든 매개변수를 넣은 생성자
@Entity
//entity어노테이션을 통해 mariadb의 테이블 및 컬럼을 자동생성
//class명은 테이블명, 변수명은 컬럼명
@NoArgsConstructor
public class Member {
    @Setter
    @Id //pk 설정
    // IDENTITY = auto_increment 설정, auto=JPA 구현체가 자동으로 적절한 키생성 전략 선택
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
//    String은 DB의 varchar로 변환

    private String name;
    @Column(nullable = false, length = 50) // name 옵션을 통해 DB의 컬럼명 별도 지정 가능
    private String email;
    private String password;
    @Setter
    @Column(name="created_time")
    @CreationTimestamp
    private LocalDateTime create_time;
    @UpdateTimestamp
    private LocalDateTime updated_time;

    public Member(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public void UpdateUserPassword(String name, String password) {
        this.name = name;
        this.password = password;
    }
}
