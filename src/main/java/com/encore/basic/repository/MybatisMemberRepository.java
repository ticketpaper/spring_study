package com.encore.basic.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

//mybatis 레파지토리로 쓰겠다라는 어노테이션
@Mapper
@Repository
public interface MybatisMemberRepository extends MemberRepository {
//    본문에 MybatisMemberRepository에서 사용할 메서드 명세를정의해야 하나,
//    MemberRepository에서 상속받고 있으므로, 생략가능하다.
//    실질적인 쿼리등 구현은 resources/mapper/MemberMapper.xml 파일에 수행
}
