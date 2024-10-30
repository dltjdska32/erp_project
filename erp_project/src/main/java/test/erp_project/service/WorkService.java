package test.erp_project.service;


import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import test.erp_project.domain.user.User;
import test.erp_project.domain.work_log.Status;
import test.erp_project.domain.work_log.WorkLog;
import test.erp_project.repository.WorkRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class WorkService {

    private final WorkRepository workRepository;
    private final UserService userService;

    //매일 8시에 모든 직원들 결근으로 데이터생성.
    public void saveWork() {

        List<User> users = userService.getAllUser();

        for(User user : users) {
            WorkLog workLog = WorkLog.builder()
                    .user(user)
                    .startTime(null)
                    .endTime(null)
                    .workDate(LocalDate.now())
                    .status(Status.ABSENCE)
                    .build();
            workRepository.save(workLog);
        }
    }

    // 출근 기록 (사용자 출근 클릭)
    public void updateAttendance(Long userNum) {
        LocalTime now = LocalTime.now();
        // 사용자를 찾는다.
        User user= userService.findUserByUserNum(userNum);
        WorkLog workLog = workRepository.findWorkLog(user);

        // 9시 이후일 경우 지각처리
        if(now.isAfter(LocalTime.of(9, 0, 59))) {

            workLog.setStartTime(LocalTime.now());
            workLog.setStatus(Status.ABSENCE);
        }

        // 9시 이전일 경우 출석처리
        if(now.isBefore(LocalTime.of(9, 0, 59))) {

            workLog.setStartTime(LocalTime.now());
            workLog.setStatus(Status.ATTENDANCE);
        }
    }


    // 퇴근 기록 (사용자 퇴근 클릭)
    //6시 10분 이후에도 출석을 하지않는다면, 결석처리로 변경 
    // 5시 50분부터 6시 10분 사이에 퇴근을 클릭한다면 출석유지 후, 퇴근시간 설정.



}
