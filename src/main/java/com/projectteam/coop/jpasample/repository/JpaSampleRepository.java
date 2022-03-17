package com.projectteam.coop.jpasample.repository;

import com.projectteam.coop.jpasample.domain.Member;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
@Transactional(transactionManager = "hibernateTxManager")
public class JpaSampleRepository {

    @PersistenceContext(unitName = "mysqlJpa")
    EntityManager em;
    //EntityManagerFactory emf = Persistence.createEntityManagerFactory("memoryJpa"); //스프링 없이 사용하려면 직접 팩토리 만들어서 생성필요

    public Long join(Member member) {
        em.persist(member);
        return member.getSampleSeq();
    }

    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    public void sample() {
        //Jpa에서 데이터를 변경하는 모든 작업은 트랜잭션 안에서 진행되어야 한다.
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member findMember = em.find(Member.class, 1L);
            System.out.println(findMember.getId());
            System.out.println(findMember.getName());

            /* 기존 데이터 수정*/
            findMember.setName("sampleB");

            /* 회원 여러 명 조회 */
            em.createQuery("select m from Member m", Member.class)
                    .setFirstResult(1)
                    .setMaxResults(5)
                    .getResultList();

            tx.commit();

        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
    }

    public void sample2() {
        Member member = new Member();
        member.setId("test"+member.getId());
        member.setName("sample"+member.getId());
        member.setAge("20"+member.getId());

        em.persist(member);
    }
}
