package test.erp_project.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import test.erp_project.domain.position.Position;

@Repository
@RequiredArgsConstructor
@Slf4j
public class PositionRepository {

    private final EntityManager em;

    public void setPosition(Position p) {
        log.info("setPosition num = {}, name = {}, leaveday = {} basic_salary = {}, leave_pay {}",
                p.getPositionNum(), p.getPositionName(), p.getLeaveDay(), p.getBasicSalary(), p.getLeavePay());

        em.merge(p);
    }

}
