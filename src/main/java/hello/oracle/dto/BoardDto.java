package hello.oracle.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardDto {
    private int boardNo;
    private int userNo;
    private String title;
    private String content;
    private String createAt;
    private String updateAt;
}
