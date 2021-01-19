package com.example.schedule.taskdao;



import com.example.schedule.po.Task;

import java.util.List;
import java.util.Map;

public interface TaskDao {

    public void add(Task task);
    public int remove(int id);
    public int update(Task user);
    public Task findById(int id);
    public List<Task> findByName(Task user_name);
    public List<Map> findAll(int user_id);
    public int updateImportance(int id, int importance);
    public int updateFinish(int id, int finish);

}
