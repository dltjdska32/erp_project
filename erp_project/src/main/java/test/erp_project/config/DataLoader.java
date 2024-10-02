package test.erp_project.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import test.erp_project.domain.dept.Dept;
import test.erp_project.domain.position.Position;
import test.erp_project.repository.DeptRepository;
import test.erp_project.repository.PositionRepository;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner{

    private final DeptRepository deptRepository;
    private final PositionRepository positionRepository;

    @Override
    public void run(String... args) throws Exception {

        //부서 초기 데이터 설정
        deptRepository.setDept(new Dept(1L, "임시부서"));
        deptRepository.setDept(new Dept(2L, "인사부서"));
        deptRepository.setDept(new Dept(3L, "재무부서"));
        deptRepository.setDept(new Dept(4L, "영업부서"));
        deptRepository.setDept(new Dept(5L, "IT부서"));
        deptRepository.setDept(new Dept(6L, "법무부서"));

        // 직위 초기 데이터 설정.
        positionRepository.setPosition(new Position(1L, "사원", 15, 3000000, 30000));
        positionRepository.setPosition(new Position(2L, "대리", 16, 3500000, 35000));
        positionRepository.setPosition(new Position(3L, "주임", 17, 4000000, 40000));
        positionRepository.setPosition(new Position(4L, "팀장", 18, 4500000, 45000));
        positionRepository.setPosition(new Position(5L, "부장", 19, 5000000, 50000));
        positionRepository.setPosition(new Position(6L, "부사장", 20, 5500000, 60000));
        positionRepository.setPosition(new Position(7L, "사장", 21, 6000000, 65000));
    }

}
