package hello.oracle.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlanDto {
    private int planNo;
    private int userNo;
    private String startAt;
    private String endAt;
    private String insertAt;
    private String updateAt;
    private String firstPlan;
    private String firstComment;
    private String secondPlan;
    private String secondComment;
    private String thirdPlan;
    private String thirdComment;
}
