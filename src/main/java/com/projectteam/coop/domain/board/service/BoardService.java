package com.projectteam.coop.domain.board.service;


import com.projectteam.coop.domain.board.model.entity.Board;
import com.projectteam.coop.repository.board.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(transactionManager = "mysqlTxManager")
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public Long addBoard(Board board) {
        return boardRepository.addBoard(board);
    }
}
