package com.project.board.service;

import com.project.board.dto.BoardRequestDto;
import com.project.board.dto.BoardResponseDto;
import com.project.board.dto.BoardDeleteDto;
import com.project.board.entity.Board;
import com.project.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    @Transactional
    public BoardResponseDto createBoard(BoardRequestDto boardRequestDto) {
        Board board = new Board(boardRequestDto);
        boardRepository.save(board);
        return new BoardResponseDto(board);


    }

    public List<BoardResponseDto> getBoards() {
        List<Board> boardList = boardRepository.findAllByOrderByModifiedAtDesc();
        List<BoardResponseDto> boardResponseDto = new ArrayList<>();
        for (Board board : boardList) {
            BoardResponseDto tmpBoardResponseDto = new BoardResponseDto(board);
            boardResponseDto.add(tmpBoardResponseDto);
        }
        return boardResponseDto;
    }

    public BoardResponseDto getBoard(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new RuntimeException("존재하지 않습니다.")
        );
        return new BoardResponseDto(board);
    }

    @Transactional
    public BoardResponseDto updateBoard(Long id, BoardRequestDto boardRequestDto) {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new RuntimeException("존재하지 않습니다.")
        );
        BoardResponseDto boardResponseDto = new BoardResponseDto(board);

        if (boardRequestDto.getPassword().equals(board.getPassword())) {
            board.update(boardRequestDto);
            return boardResponseDto;
        } else {
            return boardResponseDto;
        }
    }

    public BoardDeleteDto deleteBoard(Long id, BoardRequestDto boardRequestDto) {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new RuntimeException("존재하지 않습니다.")
        );
        BoardDeleteDto boardDeleteDto = new BoardDeleteDto();
        if (boardRequestDto.getPassword().equals(board.getPassword())) {
            boardRepository.deleteById(id);
            boardDeleteDto.setMsg("success");
        } else {
            boardDeleteDto.setMsg("fail");
        }
        return boardDeleteDto;
    }
}