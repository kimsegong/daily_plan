package hello.oracle.service;

import hello.oracle.dao.UserMapper;
import hello.oracle.dto.UserDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.PrintWriter;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService{

    private final UserMapper userMapper;

    @Override
    public void join(HttpServletRequest request, HttpServletResponse response) {
        String userName = request.getParameter("userName");
        String address = request.getParameter("address");
        Integer phoneNo = Integer.valueOf(request.getParameter("phoneNo"));

       UserDto user = UserDto.builder()
               .userName(userName)
               .address(address)
               .phoneNo(phoneNo).build();

        int joinResult = userMapper.insertUser(user);

    }
    @Override
    public UserDto getUserById(int userId) {
        // 사용자 조회 로직
        return userMapper.selectUser(userId);
    }


}
