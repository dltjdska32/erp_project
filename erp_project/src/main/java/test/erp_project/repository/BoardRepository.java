package test.erp_project.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import test.erp_project.domain.board.Board;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardRepository {

    private final EntityManager em;

    // 게시글 저장
    public void save(Board board) {
        em.persist(board);
    }


    // 모든 게시글 조회
    public List<Board> findAllBoard() {

        List<Board> boards = em.createQuery("select b from Board b" , Board.class).getResultList();
        return boards;
    }

    // 제목으로 게시글 조회
   

}
