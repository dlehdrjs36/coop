package com.projectteam.coop.service.board;

import com.projectteam.coop.domain.Board;
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
