package com.projectteam.coop;

import com.projectteam.coop.domain.board.model.entity.Board;
import com.projectteam.coop.domain.board.model.entity.QBoard;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@ActiveProfiles("local")
@SpringBootTest(
        properties =
                "spring.config.import="
                        + "optional:../properties/local_security/coop_server_local.yml,"
                        + "optional:../properties/coop_server.yml",
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional(transactionManager = "mysqlTxManager")
public class CoopApplicationTests {
    @Autowired
    private EntityManager em;

    @Test
    void contextLoads() {
        Board board = Board.createBoard();
        em.persist(board);

        JPAQueryFactory query = new JPAQueryFactory(em);
        QBoard qBoard = QBoard.board;

        List<Board> result = query.selectFrom(qBoard)
                .fetch();
        Optional<Board> boardOptional = result.stream().filter(i -> i.getId().equals(board.getId()))
                .findFirst();

        Board createdBoard = boardOptional.orElseGet(() -> Board.createBoard());
        System.out.println(board.getId());
        System.out.println(createdBoard.getId());

        Assertions.assertThat(createdBoard).isEqualTo(board);
        Assertions.assertThat(createdBoard.getId()).isEqualTo(board.getId());
    }
}
