package com.projectteam.coop.jpasample.repository;

import com.projectteam.coop.jpasample.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class JpaSampleRepository {

    @PersistenceContext(unitName = "h2Jpa")
    private EntityManager em;
    //EntityManagerFactory emf = Persistence.createEntityManagerFactory("memoryJpa"); //스프링 없이 사용하려면 직접 팩토리 만들어서 생성필요

    public Long addMember(Member member) {
        em.persist(member);
        return member.getSampleSeq();
    }

    public Long deleteMember(Long id) {
        Member findMember = em.find(Member.class, id);
        if(findMember != null) {
            em.remove(findMember);
            return findMember.getSampleSeq();
        }
        throw new IllegalArgumentException("해당하는 회원이 존재하지 않습니다.");
    }

    public Long updateMember(Long id, Member member) {
        Member findMember = em.find(Member.class, id);
        findMember.setId(member.getId());
        findMember.setName(member.getName());
        findMember.setAge(member.getAge());
        return findMember.getSampleSeq();
    }

    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
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

}
