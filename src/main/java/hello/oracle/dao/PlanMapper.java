package hello.oracle.dao;

import hello.oracle.dto.PlanDto;
import hello.oracle.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;


@Mapper
public interface PlanMapper {
    public List<PlanDto> selectPlan(Map<String, Object> map);
    public PlanDto selectPlanModal(Map<String, Object> map);
    public int insertPlan(PlanDto plan);
    public int modifyPlan(PlanDto plan);
    public int deletePlan(int PlanNo);


}