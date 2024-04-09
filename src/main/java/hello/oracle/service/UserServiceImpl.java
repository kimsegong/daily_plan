package hello.oracle.service;

import hello.oracle.dao.UserMapper;
import hello.oracle.dto.UserDto;
import hello.oracle.util.SecurityUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.PrintWriter;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService{

    private final UserMapper userMapper;
    private final SecurityUtil securityUtil;

    @Override
    public void login(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String userEmail = request.getParameter("userEmail");
        String userPw = securityUtil.getSHA256(request.getParameter("userPw"));

        Map<String, Object> map = Map.of("userEmail", userEmail
                , "userPw", userPw);

        HttpSession session = request.getSession();

         UserDto user = userMapper.getUser(map);

        if (user != null) {
            // 로그인 성공 처리
            request.getSession().setAttribute("user", user);
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>");
            out.println("location.href='" + request.getContextPath() + "/main.do'");
            out.println("</script>");
            out.flush();
            out.close();

        } else {
            // 로그인 실패 처리
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>");
            out.println("alert('일치하는 회원 정보가 없습니다.')");
            out.println("location.href='" + request.getContextPath() + "/login.do'");
            out.println("</script>");
            out.flush();
            out.close();
        }
    }

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
