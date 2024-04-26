package hello.oracle.controller;

import hello.oracle.dto.UserDto;
import hello.oracle.service.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@RequestMapping(value="/user")
@RequiredArgsConstructor
@Controller
public class UserController {

  private final UserServiceImpl userService;

  @GetMapping("/login.do")
  public String loginForm() {
    return "user/login";
  }

  @PostMapping("/login.do")
  public void login(HttpServletRequest request, HttpServletResponse response) throws Exception {
    userService.login(request, response);
  }
  @GetMapping("/logout.do")
  public void logout(HttpServletRequest request, HttpServletResponse response) {
    userService.logout(request, response);
  }

  @GetMapping("/join.do")
  public String joinForm() {
    return "user/join";
  }

  @PostMapping("/join.do")
  public void join(HttpServletRequest request, HttpServletResponse response){
    userService.join(request, response);
  }

  @GetMapping(value="/checkEmail.do", produces= MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Map<String, Object>> checkEmail(@RequestParam String userEmail) {
    return userService.checkEmail(userEmail);
  }

  @GetMapping("/mypage.form")
  public String mypageForm(Model model, @RequestParam(value="userNo", required=false, defaultValue="0") int userNo) {
    UserDto user = userService.getUserById(userNo);
    model.addAttribute("user", user);
    return "user/mypage";
  }
  @PostMapping("/mypage.form")
  public void modifyUser(HttpServletRequest request){
    userService.modify(request);

  }

}