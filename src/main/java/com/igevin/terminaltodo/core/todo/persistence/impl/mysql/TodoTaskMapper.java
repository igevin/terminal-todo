package com.igevin.terminaltodo.core.todo.persistence.impl.mysql;

import com.igevin.terminaltodo.core.todo.persistence.TodoTaskEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TodoTaskMapper {
    @Insert("INSERT INTO todo_task (id, content, checked, list_id, create_time, update_time)\n" +
            "VALUES (#{id}, #{content}, #{checked}, #{listId}, #{createTime}, #{updateTime});")
    Integer createTodoTask(TodoTaskEntity entity);

    @Update("UPDATE todo_task SET content = #{content}, checked=#{checked}, update_time=#{updateTime}\n" +
            "WHERE id=#{id};")
    Integer updateTodoTask(TodoTaskEntity entity);

    @Select("SELECT * FROM todo_task where id=#{id};")
    TodoTaskEntity getById(long id);

    @Select("<script>" +
            "SELECT * FROM todo_task WHERE list_id=#{listId}" +
            " <if test='checked != null'>\n" +
            "    AND checked=#{checked}\n" +
            " </if>" +
            "</script>")
    List<TodoTaskEntity> listTasks(long listId, Boolean checked);

    @Delete("DELETE FROM todo_task where id=#{id};")
    Integer removeTodoTaskById(long id);


}
