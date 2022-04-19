package com.igevin.terminaltodo.core.todo.persistence.impl.mysql;

import com.igevin.terminaltodo.core.todo.persistence.TodoListEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TodoListMapper {
    @Insert("INSERT INTO todo_list (id, username, createTime) VALUES (#{id}, #{username}, #{createTime});")
    Integer createTodoList(TodoListEntity todoListEntity);
    @Select("SELECT * FROM todo_list where username=#{username} LIMIT 1;")
    TodoListEntity getDefaultUserTodoList(String username);
}
