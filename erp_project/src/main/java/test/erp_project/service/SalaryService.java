package test.erp_project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import test.erp_project.domain.salary_log.SalaryLog;
import test.erp_project.domain.user.User;
import test.erp_project.dto.salary_dto.AdminSalaryData;
import test.erp_project.repository.SalaryRepository;
import test.erp_project.repository.UserRepository;

import java.time.LocalDate;
import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SalaryService {

    private final SalaryRepository salaryRepository;
    private final UserService userService;
    private final PositionService positionService;

    //매월 10일 9시 정각에 모든 직원의 직급을 저장.
    @Transactional(readOnly = false)
    public void saveSalary() {
        List<User> users = userService.getAllUser();

        // 모든유저를 순회하면서 salary log 저장
        for (User user : users) {
            SalaryLog salaryLog = SalaryLog.builder()
                    .receivedDate(LocalDate.now())
                    .totalSalary(user.getPosition().getBasicSalary())
                    .user(user)
                    .build();
            salaryRepository.save(salaryLog);
        }
    }

    // (관리자) 보너스 추가
    @Transactional(readOnly = false)
    public void addBonus(int bonus, Long salaryNum) {
        SalaryLog salaryLog = salaryRepository.findSalaryLogByNum(salaryNum).orElseThrow(() -> new RuntimeException("급여 내역을 찾을 수 없습니다."));

        salaryLog.setTotalSalary(salaryLog.getTotalSalary() + bonus);
    }

    //(관리자) 모든 급여내역 조회함수
    public List<AdminSalaryData> getAdminSalaryDatas() {
        return salaryRepository.findAdminSalaryData();
    }

    // (관리자) 유저이름과 일치하는 급여내역 조회
    public List<AdminSalaryData> getAdminSalaryDatasByName(String name) {
        return salaryRepository.findAdminSalaryDataByName(name);
    }

}
