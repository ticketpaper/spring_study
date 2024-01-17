package com.encore.basic.repository;

import com.encore.basic.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public class JpaMemberRepository implements MemberRepository {
    //    Entity Manager는 JPA의 핵심 클래스 (객체)
//    Entity의 생명주기(CRUD)를 관리, 데이터베이스와의 모든 상호작용을 책임진다.
//    엔티티를 대상으로 CRUD하는 기능을 제공한다.
    private final EntityManager entityManager;

    public JpaMemberRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Member> findAll() {
//        jpql : jpa의 객체지향 쿼리문법
//        장점: DB에 따라 문법이 달라지지않는 객체지향 언어, 컴파일 타임에서 check (SpringDataJpa의 @Query 기능)
//        단점: DB고유의 기능과 성능을 극데ㅐ화하기는 어려움
        List<Member> members = entityManager.createQuery("select m from Member m", Member.class).getResultList();
        return members;
    }

    @Override
    public Member save(Member member) {
//        persist : 전달된 엔티티(Member)가 EntityManager의 관리상태가 되도록 만들어주고,
//        트랜잭션이 커밋될 때에 데이터베이스에 저장한다. insert, update포함한다.
//        = insert, update 해주는 메서드
        entityManager.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(int id) {
//        find메서드는 pk를 매개변수로 준다.
        Member member = entityManager.find(Member.class, id);

        return Optional.ofNullable(member);
    }

    @Override
    public void delete(Member member) {

    }

////       pk가아닌 그외 조회 (name으로 조회할때)
//    public List<Member> findByName(String name) {
//        List<Member> memebers = entityManager
//                                .createQuery("select m from Member m where m.name = :name", Member.class)
//                                .getResultList();
//        return memebers;
//     }

}
