package com.tjy.nfc.controller;

import com.tjy.nfc.entity.CheckLog;
import com.tjy.nfc.service.CheckLogService;
import com.tjy.nfc.utils.ResponseCode;
import com.tjy.nfc.utils.ResultModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 巡检日志表(CheckLog)表控制层
 *
 * @author makejava
 * @since 2020-11-30 16:48:37
 */
@Slf4j
@RestController
@RequestMapping("checkLog")
public class CheckLogController {
    /**
     * 服务对象
     */
    @Resource
    private CheckLogService checkLogService;


    @GetMapping("/search/{companyId}/{planNo}/{shebeiNo}/{dataTime}")
    public ResultModel search(@PathVariable String companyId, @PathVariable String planNo,
                              @PathVariable String shebeiNo,@PathVariable String dataTime) {
        try {
            if(StringUtils.equals("all",dataTime)){
                dataTime = "";
            }
            List<CheckLog> checkLogList = checkLogService.search(companyId,planNo,shebeiNo,dataTime);
            return ResultModel.success(checkLogList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultModel.failure(ResponseCode.ERROR_999,"系统异常");
        }
    }

}