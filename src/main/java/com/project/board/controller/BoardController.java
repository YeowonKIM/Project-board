package com.project.board.controller;

import com.project.board.dto.BoardResponseDto;
import com.project.board.dto.BoardRequestDto;
import com.project.board.dto.BoardDeleteDto;
import com.project.board.entity.Board;
import com.project.board.service.BoardService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/")
    public ModelAndView home() {
        return new ModelAndView("index");
    }

    @Transactional
    @PostMapping("/api/post")
    public BoardResponseDto createBoard(@RequestBody BoardRequestDto boardRequestDto) {
        return boardService.createBoard(boardRequestDto);
    }
    @Transactional
    @GetMapping("/api/posts")
    public List<BoardResponseDto> getBoards() {
        return boardService.getBoards();
    }

    @Transactional
    @GetMapping("/api/post/{id}")
    public BoardResponseDto getBoard(@PathVariable Long id) {
        return boardService.getBoard(id);
    }

    @Transactional
    @PutMapping("/api/post/{id}")
    public BoardResponseDto updateBoard(@PathVariable Long id, @RequestBody BoardRequestDto boardRequestDto) {
        return boardService.updateBoard(id, boardRequestDto);
    }

    @Transactional
    @DeleteMapping("/api/post/{id}")
    public BoardDeleteDto deleteBoard(@PathVariable Long id, @RequestBody BoardRequestDto boardRequestDto){
        return boardService.deleteBoard(id, boardRequestDto);
    }
}
