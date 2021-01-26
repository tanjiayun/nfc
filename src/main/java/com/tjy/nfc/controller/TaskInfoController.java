package com.tjy.nfc.controller;

import com.tjy.nfc.entity.TaskInfo;
import com.tjy.nfc.service.TaskInfoService;
import com.tjy.nfc.utils.ResponseCode;
import com.tjy.nfc.utils.ResultModel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 任务信息表(TaskInfo)表控制层
 *
 * @author makejava
 * @since 2020-12-14 14:13:05
 */
@RestController
@RequestMapping("taskInfo")
public class TaskInfoController {
    /**
     * 服务对象
     */
    @Resource
    private TaskInfoService taskInfoService;

    @GetMapping("/getList/{companyId}/{checkPeople}/{status}")
    public ResultModel getList(@PathVariable String companyId,@PathVariable String checkPeople, @PathVariable String status) {
        try {
            List<TaskInfo> list = taskInfoService.getList(companyId,checkPeople,Integer.parseInt(status));
            return ResultModel.success(list);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultModel.failure(ResponseCode.ERROR_999,"系统异常");
        }
    }

}