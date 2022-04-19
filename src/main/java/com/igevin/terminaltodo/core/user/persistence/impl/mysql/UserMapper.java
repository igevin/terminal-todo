package com.igevin.terminaltodo.core.user.persistence.impl.mysql;

import com.igevin.terminaltodo.core.user.persistence.UserEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
    @Insert("INSERT INTO todo_user (id, username, password) VALUES (#{id}, #{username}, #{password});")
    Integer saveUser(UserEntity user);
    @Select("SELECT * FROM todo_user WHERE username=#{username};")
    UserEntity getUser(String username);
    @Select("SELECT * FROM todo_user;")
    List<UserEntity> listUser();
}
