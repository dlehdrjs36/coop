package com.projectteam.coop.repository.log;

import com.projectteam.coop.domain.log.LoginLog;
import com.projectteam.coop.domain.member.model.entity.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public class LoginRepository {

    @PersistenceContext(unitName = "mysqlJpa")
    private EntityManager em;

    //등록
    public Long addLoginLog(LoginLog login) {
        em.persist(login);
        return login.getId();
    }

    //로그인 이력 조회
    public Long findLoginLog(Member member) {
        Long findLoginLog = em.createQuery("SELECT count(l) FROM LoginLog l WHERE substring(function('DATE_FORMAT', l.createDate, '%Y-%m-%d') , 1, 10) = :date AND l.email = :email", Long.class)
                .setParameter("date", LocalDate.now().toString())
                .setParameter("email", member.getEmail())
                .getResultList()
                .stream()
                .findAny()
                .orElseGet(() -> null);

        return findLoginLog;
    }

}
