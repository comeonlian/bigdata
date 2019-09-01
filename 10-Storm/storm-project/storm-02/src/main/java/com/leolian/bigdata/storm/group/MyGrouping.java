package com.leolian.bigdata.storm.group;

import org.apache.storm.generated.GlobalStreamId;
import org.apache.storm.grouping.CustomStreamGrouping;
import org.apache.storm.task.WorkerTopologyContext;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 
 * @author lianliang
 * @date 2019/9/1 17:24
 */
public class MyGrouping implements CustomStreamGrouping {

    private List<Integer> targetTasks;

    public void prepare(WorkerTopologyContext context, GlobalStreamId stream, List<Integer> targetTasks) {
        this.targetTasks = targetTasks;
    }

    public List<Integer> chooseTasks(int taskId, List<Object> values) {
        List<Integer> resultTasks = new ArrayList<Integer>();
        for (Integer targetTask : targetTasks) {
            if (targetTask % 2 == 0) {
                resultTasks.add(targetTask);
            }
        }
        return resultTasks;
    }
    
}
