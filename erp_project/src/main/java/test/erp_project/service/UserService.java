package test.erp_project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import test.erp_project.domain.dept.Dept;
import test.erp_project.domain.position.Position;
import test.erp_project.domain.user.Role;
import test.erp_project.domain.user.User;
import test.erp_project.dto.user_dto.UserJoinDto;

import test.erp_project.dto.user_dto.UserLoginDto;
import test.erp_project.dto.user_dto.UserSearchDto;
import test.erp_project.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final DeptService deptService;
    private final PositionService positionService;

    @Transactional
    // 유저만 사용할 것이기 때문에 Role 은 USER로 설정
    // 유저아 아이디를 생성할때 기본적으로 임시부서와 사원으로 저장됨.
    public void saveUser(UserJoinDto userJoinDto) {
        Dept dept = deptService.getDept("임시부서"); // user객체에 넣어줄 dept -> 임시부서
        Position position = positionService.getPosition("사원"); // user객체에 넣어줄 position -> 사원

        //userJoinDto를 통해 가져온 정보로 user Entity를 만들고 저장.
        User user = User.builder()
                .userId(userJoinDto.getUserId())
                .password(userJoinDto.getPassword())
                .name(userJoinDto.getName())
                .email(userJoinDto.getEmail())
                .role(Role.USER)
                .remainedLeave(position.getLeaveDay())
                .position(position)
                .dept(dept)
                .tel(userJoinDto.getTel())
                .build();

        userRepository.save(user);
    }


    //유저의 모든 정보를 가져오는 메서드
    public List<UserSearchDto> getAllUserInfo() {
        List<UserSearchDto> userSearchDtos = getUserSearchDtos();

        return userSearchDtos;
    }


    // 유저 아이디 패스워드 확인 함수
    public boolean checkUser(UserLoginDto userLoginDto) {

        boolean checkInfo = false;

        checkInfo = isCheckInfo(userLoginDto, checkInfo);

        return checkInfo;
    }


    //유저 아이디 패스워드 확인 비즈니스로직
    private boolean isCheckInfo(UserLoginDto userLoginDto, boolean checkInfo) {
        // 받은 정보로 user 엔터티변환
        User user = User.builder()
                .userId(userLoginDto.getUserId())
                .password(userLoginDto.getPassword())
                .build();

        Optional<User> userInfo = userRepository.findById(user.getUserId());

        if (userInfo.isPresent()) {
            String password = userInfo.get().getPassword();
            if (password.equals(userLoginDto.getPassword())) {
                checkInfo = true;
            }
        }
        return checkInfo;
    }

    // 모든 유저 정보를 가져오는 비즈니스 로직.
    private List<UserSearchDto> getUserSearchDtos() {

        List<User> userInfos = userRepository.findAllUser();
        List<UserSearchDto> userSearchDtos = new ArrayList<>();

        //user도메인 userSearchDto로 변환
        for (User userInfo : userInfos) {
            UserSearchDto userSearchDto = UserSearchDto.builder()
                    .userId(userInfo.getUserId())
                    .name(userInfo.getName())
                    .email(userInfo.getEmail())
                    .role(userInfo.getRole())
                    .remainedLeave(userInfo.getRemainedLeave())
                    .position(userInfo.getPosition())
                    .dept(userInfo.getDept())
                    .tel(userInfo.getTel())
                    .userNum(userInfo.getUserNum())
                    .build();
            userSearchDtos.add(userSearchDto);
        }
        return userSearchDtos;
    }



}
