package com.project.board.dto;

import com.project.board.entity.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardDeleteDto {
    private String message;

    public void setMsg(String message) {
        this.message = message;
    }
}