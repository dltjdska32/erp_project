package test.erp_project.component;


import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import test.erp_project.service.SalaryService;
import test.erp_project.service.WorkService;

@RequiredArgsConstructor
@Component
public class SchedulingComponent {

    private final SalaryService salaryService;
    private final WorkService workService;


    // 매월 10일 9시에 호출
    @Scheduled(cron = "0 0 9 10 * ?")
    public void saveSalarylog() {

        salaryService.saveSalary();
    }

    // 매일 8시에 호출
    @Scheduled(cron = "0 0 8 * * ?")
    public void saveWorklog() {
        workService.saveWork();
    }
}
