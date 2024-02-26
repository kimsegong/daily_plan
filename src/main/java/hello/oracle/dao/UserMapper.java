package hello.oracle.dao;

import hello.oracle.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface UserMapper {
  public int insertUser(UserDto user);
  public UserDto selectUser(int userId);
}