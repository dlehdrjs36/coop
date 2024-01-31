package com.projectteam.coop;

import com.projectteam.coop.domain.Board;
import com.projectteam.coop.domain.QBoard;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@SpringBootTest
@Transactional
public class CoopApplicationTests {
    @Autowired
    private EntityManager em;

    @Test
    void contextLoads() {
        Board board = Board.createBoard();
        em.persist(board);

        JPAQueryFactory query = new JPAQueryFactory(em);
        QBoard qBoard = QBoard.board;

        Board result = query.selectFrom(qBoard).fetchOne();
        Assertions.assertThat(result).isEqualTo(board);
        Assertions.assertThat(result.getId()).isEqualTo(board.getId());
    }
}
