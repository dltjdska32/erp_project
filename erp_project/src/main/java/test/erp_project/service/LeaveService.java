package test.erp_project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import test.erp_project.domain.leave_log.LeaveLog;
import test.erp_project.domain.user.User;
import test.erp_project.dto.leave_dto.ForRequestLeaveDto;
import test.erp_project.dto.leave_dto.LeaveLogOfAdminDto;
import test.erp_project.dto.leave_dto.LeaveLogOfUserDto;
import test.erp_project.repository.LeaveRepository;
import test.erp_project.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly=true)
public class LeaveService {

    private final LeaveRepository leaveRepository;
    private final UserRepository userRepository;

    // 휴가 신청.(사용자)
    @Transactional
    public void saveLeaveLog(ForRequestLeaveDto requestLeaveDto) {
        String userId = requestLeaveDto.getUserId();
        Optional<User> user = userRepository.findById(userId);

        if(user.isPresent()) {

            LeaveLog leaveLog = LeaveLog.builder()
                    .user(user.get())
                    .requestDate(requestLeaveDto.getRequestDate())
                    .endDate(requestLeaveDto.getEndDate())
                    .startDate(requestLeaveDto.getStartDate())
                    .checkStatus(false)
                    .acceptanceStatus(false)
                    .build();

            leaveRepository.save(leaveLog);
        }

    }

    // 휴가 거절 (관리자) -> checkStatus - true , acceptanceStatus - false
    // 휴가번호(pk)를 넘겨받음
    // 변경감지를 통한 update
    @Transactional
    public void rejectLeaveLog(Long leaveNum) {
       LeaveLog leaveLog = leaveRepository.findLeaveLogByNum(leaveNum).orElseThrow(() -> new RuntimeException("회원의 휴가정보 찾을 수 없음."));


        leaveLog.setCheckStatus(true);
        leaveLog.setAcceptanceStatus(false);
      /*
        if(leaveLogByNum.isPresent()) {
            LeaveLog leaveLog = leaveLogByNum.get();
            leaveLog.setCheckStatus(true);
            leaveLog.setAcceptanceStatus(false);
        }
        if(leaveLogByNum.isEmpty()){
            throw new RuntimeException("휴가 정보 찾을수 없음.");
        }*/
    }


    // 휴가 승인 (관리자) -> checkstatus - true, acceptanceStatus - true
    //변경감지를 통한 update
    @Transactional
    public void acceptLeaveLog(Long leaveNum) {
        LeaveLog leaveLog = leaveRepository.findLeaveLogByNum(leaveNum).orElseThrow(() -> new RuntimeException("회원의 휴가정보 찾을 수 없음."));

        leaveLog.setCheckStatus(true);
        leaveLog.setAcceptanceStatus(true);
    }



    //모든 휴가신청기록 찾기
    public List<LeaveLogOfAdminDto> getLeaveLogsOfAdmin() {
        List<LeaveLogOfAdminDto> leaveLogOfAdminList = leaveRepository.findLeaveLogOfAdminList();
        return leaveLogOfAdminList;
    }


    //사용자 이름으로 휴가 기록 찾기( 관리자)
    public List<LeaveLogOfAdminDto> getLeaveLogsOfAdminByName(String userName) {
        List<LeaveLogOfAdminDto> leaveLogOfAdminListByUserName = leaveRepository.findLeaveLogOfAdminListByUserName(userName);
        return leaveLogOfAdminListByUserName;
    }


}
