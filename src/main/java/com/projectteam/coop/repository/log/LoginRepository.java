package com.projectteam.coop.repository.log;

import com.projectteam.coop.domain.Member;
import com.projectteam.coop.domain.log.LoginLog;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;

@Repository
public class LoginRepository {

    @PersistenceContext(unitName = "h2Jpa")
    private EntityManager em;

    //등록
    public Long addLoginLog(LoginLog login) {
        em.persist(login);
        return login.getId();
    }

    //로그인 이력 조회
    public Long findLoginLog(Member member) {
        Long findLoginLog = em.createQuery("SELECT count(l) FROM LoginLog l WHERE substring(l.createDate, 0, 10) = :date AND l.email = :email", Long.class)
                .setParameter("date", LocalDate.now().toString())
                .setParameter("email", member.getEmail())
                .getResultList()
                .stream()
                .findAny()
                .orElseGet(() -> null);

        return findLoginLog;
    }

}
