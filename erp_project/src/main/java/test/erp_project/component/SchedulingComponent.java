package test.erp_project.component;


import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import test.erp_project.service.SalaryService;
import test.erp_project.service.WorkService;

import java.time.DayOfWeek;
import java.time.LocalDate;

@RequiredArgsConstructor
@Component
public class SchedulingComponent {

    private final SalaryService salaryService;
    private final WorkService workService;


    // 매월 10일 9시에 호출 월급 지급
    @Scheduled(cron = "0 0 9 10 * ?")
    public void saveSalarylog() {

        salaryService.saveSalary();
    }

    // 매일 6시에 호출
    @Scheduled(cron = "0 0 6 * * ?")
    public void saveWorklog() {
        LocalDate  now = LocalDate.now();
        DayOfWeek dayOfWeek = now.getDayOfWeek();

        ///주말일 경우 실행 x
        if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
            return;
        }

        workService.saveWork();
    }


}
