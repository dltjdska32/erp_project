package test.erp_project.dto.board_dto;


import test.erp_project.dto.answer_dto.AnswerInBoardDto;

import java.util.List;

public class BoardAndAnswer {



    private BoardInfoDto2 boardInfoDto;
    private List<AnswerInBoardDto> answerInBoard;

    public BoardInfoDto2 getBoardInfoDto() {
        return boardInfoDto;
    }

    public List<AnswerInBoardDto> getAnswerInBoard() {
        return answerInBoard;
    }



    public BoardAndAnswer(BoardInfoDto2 boardInfoDto, List<AnswerInBoardDto> answerInBoard) {
        this.boardInfoDto = boardInfoDto;
        this.answerInBoard = answerInBoard;
    }








}
