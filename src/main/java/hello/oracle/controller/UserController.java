package hello.oracle.controller;

import hello.oracle.dto.UserDto;
import hello.oracle.service.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class UserController {

  private final UserServiceImpl userService;

  @GetMapping(value={"/", "/main/user.do"})
  public String main() {
    return "main/user";
  }

  @PostMapping("/main/userImpo.do")
  public String join(HttpServletRequest request, HttpServletResponse response) {
    userService.join(request, response);
    return "main/userImpo";
  }
  @GetMapping("main/mypage.form")
  public String mypageForm(Model model, int userId) {
    UserDto user = userService.getUserById(userId);
    model.addAttribute("user", user);
    return "main/mypage";
  }
}