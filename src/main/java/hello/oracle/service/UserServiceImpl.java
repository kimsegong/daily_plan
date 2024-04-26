package hello.oracle.service;

import hello.oracle.dao.UserMapper;
import hello.oracle.dto.UserDto;
import hello.oracle.util.SecurityUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService{

    private final UserMapper userMapper;
    private final SecurityUtil securityUtil;

    @Override
    public void login(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String userEmail = request.getParameter("userEmail");
        String userPw = request.getParameter("userPw");

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
            out.println("location.href='" + request.getContextPath() + "/layout/main.do'");
            out.println("</script>");
            out.flush();
            out.close();

        } else {
            // 로그인 실패 처리
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>");
            out.println("alert('일치하는 회원 정보가 없습니다.')");
            out.println("location.href='" + request.getContextPath() + "/user/login.do'");
            out.println("</script>");
            out.flush();
            out.close();
        }
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();
        session.invalidate();

        try {
            response.sendRedirect(request.getContextPath() + "/layout/main.do");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void join(HttpServletRequest request, HttpServletResponse response) {
        String userEmail = request.getParameter("userEmail");
        String userPw = request.getParameter("userPw");
        String userName = request.getParameter("userName");
        String userPhone = request.getParameter("userPhone");


       UserDto user = UserDto.builder()
               .userEmail(userEmail)
               .userPw(userPw)
               .userName(userName)
               .userPhone(userPhone).build();


        int joinResult = userMapper.insertUser(user);

    }


    @Transactional(readOnly=true)
    @Override
    public ResponseEntity<Map<String, Object>> checkEmail(String userEmail) {

        Map<String, Object> map = new HashMap<>();
        map.put("userEmail", userEmail);

        boolean enableEmail = userMapper.getUser(map) == null;

        return new ResponseEntity<>(Map.of("enableEmail", enableEmail), HttpStatus.OK);

    }

    @Transactional(readOnly=true)
    @Override
    public UserDto getUserById(int userNo) {
        // 사용자 조회 로직
        return userMapper.selectUser(userNo);
    }
    @Override
    public ResponseEntity<Map<String, Object>> modify(HttpServletRequest request) {

        String userName = request.getParameter("userName");
        String userPhone = request.getParameter("userPhone");
        String userPw = request.getParameter("userPw");
        int userNo = Integer.parseInt(request.getParameter("userNo"));

        UserDto user = UserDto.builder()
                .userName(userName)
                .userPhone(userPhone)
                .userPw(userPw)
                .userNo(userNo)
                .build();

        int modifyResult = userMapper.updateUser(user);

        if(modifyResult == 1) {
            HttpSession session = request.getSession();
            UserDto sessionUser = (UserDto)session.getAttribute("user");
            sessionUser.setUserName(userName);
            sessionUser.setUserPhone(userPhone);
            sessionUser.setUserPw(userPw);
        }

        return new ResponseEntity<>(Map.of("modifyResult", modifyResult), HttpStatus.OK);

    }


}
