package com.projectteam.coop.repository.board;

import com.projectteam.coop.domain.board.model.entity.Board;
import com.projectteam.coop.domain.board.model.entity.QBoard;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class BoardRepository {

    @PersistenceContext(unitName = "mysqlJpa")
    private EntityManager em;

    //등록
    public Long addBoard(Board board) {
        em.persist(board);
        return board.getId();
    }

    //단건조회
    public Board findBoard(Long boardId) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        Board board = queryFactory
                .selectFrom(QBoard.board)
                .where(
                        QBoard.board.id.eq(boardId)
                ).fetchOne();

        return board;
    }

}
