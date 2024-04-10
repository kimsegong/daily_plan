package hello.oracle.dao;

import hello.oracle.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;
import java.util.Objects;

@Mapper
public interface UserMapper {

  public UserDto getUser(Map<String, Object> map);
  public int insertUser(UserDto user);
  public UserDto selectUser(int userNo);
  public Map<String, Objects> updateUser(int userNo);
  public int deleteUser(int userNo);

}