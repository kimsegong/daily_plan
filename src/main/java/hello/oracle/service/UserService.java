package hello.oracle.service;

import hello.oracle.dto.UserDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface UserService {
    public void login(HttpServletRequest request, HttpServletResponse response) throws Exception;
    public int userPlanCount(int userNo);
    public void logout(HttpServletRequest request, HttpServletResponse response);
    public void join(HttpServletRequest request, HttpServletResponse response);
    public ResponseEntity<Map<String, Object>> checkEmail(String userEmail);
    public UserDto getUserById(int userNo);
    public ResponseEntity<Map<String, Object>> modify(HttpServletRequest request);

}
