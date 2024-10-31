package test.erp_project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import test.erp_project.domain.dept.Dept;
import test.erp_project.domain.position.Position;
import test.erp_project.domain.user.Role;
import test.erp_project.domain.user.User;
import test.erp_project.dto.user_dto.*;

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


    //관리자 사원관리 유저 정보(부서, 직위) 수정
    @Transactional
    public void updateUser(UserInfo userInfo) {

        Dept dept = deptService.getDept(userInfo.getDeptName());
        Position position = positionService.getPosition(userInfo.getPositionName());

        User user = findUserByUserNum(userInfo.getUserNum());
     
        // 변경감지를 통해서 db 수정
        //  필드하나 set할때마다. 쿼리가 1번 날아감
        user.setDept(dept);
        user.setPosition(position);
    }

    //유저의 번호로 유저를 가져오는 함수.
    public User findUserByUserNum(Long userNum) {
        User user = userRepository.findByUserNum(userNum).orElseThrow(() -> new RuntimeException("회원을 찾을 수 없음"));
        return user;
    }

    // 유저의 아이디로 유저를 가져오는함수
    public User findUserByUserId(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("회원을 찾을 수 없음"));
        return user;
    }

    //유저의 이름으로 정보 가져오는 메서드
    public List<UserInfo> getUserByName(String name) {

        List<UserInfo> userInfoList = new ArrayList<>();

        List<User> userByName = userRepository.findUserByName(name);
        for (User user : userByName) {
            UserInfo userInfo = UserInfo.builder()
                    .userNum(user.getUserNum())
                    .name(user.getName())
                    .email(user.getEmail())
                    .deptName(user.getDept().getDeptName())
                    .positionName(user.getPosition().getPositionName())
                    .tel(user.getTel())
                    .role(user.getRole())
                    .build();
            userInfoList.add(userInfo);
        }

        return userInfoList;
    }


    public List<User> getAllUser() {
        List<User> users = userRepository.findAllUser();
        return users;
    }

    //유저의 특정 정보를 가져오는 메서드
    public List<UserSearchDto> getAllUserInfo() {
        List<UserSearchDto> userSearchDtos = getUserSearchDtos();

        return userSearchDtos;
    }

    //유저의 모든 정보를 가져오는 메서드
    public List<UserAndLeaveInfo> getAllUserAndLeaveInfo() {
        List<UserAndLeaveInfo> users = userRepository.findUserAndLeaveInfo();
        return users;
    }

    // 유저정보를 통해 userInfo로 변환
    public UserInfo getUserInfo(UserLoginDto userLoginDto) {

        Optional<User> user = userRepository.findById(userLoginDto.getUserId());

        if (user.isPresent()) {
            if (userLoginDto.getPassword().equals(user.get().getPassword())) {
                return userToUserInfo(user);
            }
        }

        return null;
    }

    // 유저 번호를 통해서 User 찾는 함수
    public User getUserByUserNum(Long userNum){
        Optional<User> user = userRepository.findByUserNum(userNum);
        if(user.isPresent()) {
            return user.get();
        }

        return null;
    }


    private UserInfo userToUserInfo(Optional<User> user) {
        UserInfo userInfo = UserInfo.builder()
                .userNum(user.get().getUserNum())
                .name(user.get().getName())
                .tel(user.get().getTel())
                .deptName(user.get().getDept().getDeptName())
                .email(user.get().getEmail())
                .positionName(user.get().getPosition().getPositionName())
                .role(user.get().getRole())
                .build();
        return userInfo;
    }


    // 모든 유저 정보를 가져오는 비즈니스 로직.
    private List<UserSearchDto> getUserSearchDtos() {

        List<UserSearchDto> userInfos = userRepository.findAllUserSearchDto();
//        List<UserSearchDto> userSearchDtos = new ArrayList<>();
//
//        //user도메인 userSearchDto로 변환
//        for (User userInfo : userInfos) {
//            UserSearchDto userSearchDto = UserSearchDto.builder()
//                    .userId(userInfo.getUserId())
//                    .name(userInfo.getName())
//                    .email(userInfo.getEmail())
//                    .role(userInfo.getRole())
//                    .remainedLeave(userInfo.getRemainedLeave())
//                    .position(userInfo.getPosition())
//                    .dept(userInfo.getDept())
//                    .tel(userInfo.getTel())
//                    .userNum(userInfo.getUserNum())
//                    .build();
//            userSearchDtos.add(userSearchDto);
//        }
        return userInfos;
    }



}
