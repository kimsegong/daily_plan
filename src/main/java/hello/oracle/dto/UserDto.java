package hello.oracle.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private int userNo;
    private String userEmail;
    private String userPw;
    private String userName;
    private String userPhone;
    private int userRole;
    private String createAt;
    private String updateAt;
}
