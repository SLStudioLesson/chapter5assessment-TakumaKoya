package com.taskapp.logic;

import java.util.List;

import com.taskapp.dataaccess.LogDataAccess;
import com.taskapp.dataaccess.TaskDataAccess;
import com.taskapp.dataaccess.UserDataAccess;
import com.taskapp.model.Task;
import com.taskapp.model.User;

public class TaskLogic {
    private final TaskDataAccess taskDataAccess;
    private final LogDataAccess logDataAccess;
    private final UserDataAccess userDataAccess;


    public TaskLogic() {
        taskDataAccess = new TaskDataAccess();
        logDataAccess = new LogDataAccess();
        userDataAccess = new UserDataAccess();
    }

    /**
     * 自動採点用に必要なコンストラクタのため、皆さんはこのコンストラクタを利用・削除はしないでください
     * @param taskDataAccess
     * @param logDataAccess
     * @param userDataAccess
     */
    public TaskLogic(TaskDataAccess taskDataAccess, LogDataAccess logDataAccess, UserDataAccess userDataAccess) {
        this.taskDataAccess = taskDataAccess;
        this.logDataAccess = logDataAccess;
        this.userDataAccess = userDataAccess;
    }

    /**
     * 全てのタスクを表示します。
     *
     * @see com.taskapp.dataaccess.TaskDataAccess#findAll()
     * @param loginUser ログインユーザー
     */
    public void showAll(User loginUser) {
        List<Task> tasks = taskDataAccess.findAll();

        for (Task task : tasks) {
            int repUserCode = task.getRepUser().getCode();
            User repUser = userDataAccess.findByCode(repUserCode);

            //担当者判定
            String repUserName;
            if (repUser.equals(loginUser)) {
                repUserName = "あなた";
            } else {
                repUserName = repUser.getName();
            }

            //ステータス判定
            String statusString;
            if (task.getStatus() == 0) {
                statusString = "未着手";
            } else if (task.getStatus() == 1) {
                statusString = "着手";
            } else {
                statusString = "完了";
            }

            System.out.println(task.getCode() + ". タスク名：" + task.getName() + ", 担当者名：" + repUserName + "が担当しています, ステータス：" + statusString);
        }

        // tasks.forEach(task -> {
        //     //担当者コード取得
        //     int repUserCode = task.getRepUser().getCode();
        //     User repUser = userDataAccess.findByCode(repUserCode);

        //     String repUserName;
        //     if (repUser.equals(loginUser)){
        //         repUserName = "あなた";
        //     } else {
        //         repUserName = repUser.getName() ;
        //     }


        //     if (task.getStatus() == 0){
        //         System.out.println(task.getCode() + ". タスク名：" + task.getName() + ", 担当者名：" + repUserName + "が担当しています, ステータス：未着手");
        //     } else if (task.getStatus() == 1) {
        //         System.out.println(task.getCode() + ". タスク名：" + task.getName() + ", 担当者名：" + repUserName + "が担当しています, ステータス：着手");
        //     } else {
        //         System.out.println(task.getCode() + ". タスク名：" + task.getName() + ", 担当者名：" + repUserName + "が担当しています, 完了");
        //     }

        // });
    }

    /**
     * 新しいタスクを保存します。
     *
     * @see com.taskapp.dataaccess.UserDataAccess#findByCode(int)
     * @see com.taskapp.dataaccess.TaskDataAccess#save(com.taskapp.model.Task)
     * @see com.taskapp.dataaccess.LogDataAccess#save(com.taskapp.model.Log)
     * @param code タスクコード
     * @param name タスク名
     * @param repUserCode 担当ユーザーコード
     * @param loginUser ログインユーザー
     * @throws AppException ユーザーコードが存在しない場合にスローされます
     */
    // public void save(int code, String name, int repUserCode,
    //                 User loginUser) throws AppException {
    // }

    /**
     * タスクのステータスを変更します。
     *
     * @see com.taskapp.dataaccess.TaskDataAccess#findByCode(int)
     * @see com.taskapp.dataaccess.TaskDataAccess#update(com.taskapp.model.Task)
     * @see com.taskapp.dataaccess.LogDataAccess#save(com.taskapp.model.Log)
     * @param code タスクコード
     * @param status 新しいステータス
     * @param loginUser ログインユーザー
     * @throws AppException タスクコードが存在しない、またはステータスが前のステータスより1つ先でない場合にスローされます
     */
    // public void changeStatus(int code, int status,
    //                         User loginUser) throws AppException {
    // }

    /**
     * タスクを削除します。
     *
     * @see com.taskapp.dataaccess.TaskDataAccess#findByCode(int)
     * @see com.taskapp.dataaccess.TaskDataAccess#delete(int)
     * @see com.taskapp.dataaccess.LogDataAccess#deleteByTaskCode(int)
     * @param code タスクコード
     * @throws AppException タスクコードが存在しない、またはタスクのステータスが完了でない場合にスローされます
     */
    // public void delete(int code) throws AppException {
    // }
}