package hello.oracle.service;

import hello.oracle.dto.UserDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Map;

public interface UserService {
    public void join(HttpServletRequest request, HttpServletResponse response);
    public UserDto getUserById(int userId);

}
