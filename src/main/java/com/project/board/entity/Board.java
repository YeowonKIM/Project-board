package com.project.board.entity;

import com.project.board.dto.BoardRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity                                                   //클래스를 테이블과 매핑시키기 위해 필수로 사용되는 어노테이션
@Getter
@NoArgsConstructor                                        //기본 생성자를 생성해줌
public class Board extends TimeStamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private String author;
    private String password;

    public Board(BoardRequestDto boardRequestDto){
        this.author=boardRequestDto.getAuthor();
        this.title=boardRequestDto.getTitle();
        this.content=boardRequestDto.getContent();
        this.password=boardRequestDto.getPassword();
    }

    public void update(BoardRequestDto boardRequestDto) {
        this.author = boardRequestDto.getAuthor();
        this.content = boardRequestDto.getContent();
        this.title = boardRequestDto.getTitle();
    }
}