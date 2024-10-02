package test.erp_project.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import test.erp_project.domain.dept.Dept;

@Repository
@RequiredArgsConstructor
public class DeptRepository {

    private final EntityManager em;

    public void setDept(Dept dept) {
        em.persist(dept);
    }

}
