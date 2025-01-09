package com.neusoft.ehr.controller;

import com.neusoft.ehr.DTO.PositionsDTO;
import com.neusoft.ehr.entity.Request;
import com.neusoft.ehr.entity.Response;
import com.neusoft.ehr.entity.po.PositionsPo;
import com.neusoft.ehr.service.PositionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PstnController extends BaseController {
    /**
     * BaseController构造方法
     *
     * @param messageSource 国际化组件
     */
    protected PstnController(MessageSource messageSource) {
        super(messageSource);
    }

    @Autowired
    private PositionsService positionsService;

    @GetMapping("/positions")
    public Response<List<PositionsPo>> getPositions(@RequestParam(required = false) Integer limit,
                                                      @RequestParam(required = false) Integer page) {
        List<PositionsPo> positionsList = positionsService.pagePositions(limit, page);
        return success(positionsList);
    }

    @PostMapping("/positions")
    public Response<Void> addPositions(@RequestBody Request<PositionsDTO> positionsDTO) {
        positionsService.insertPositions(positionsDTO.getData());
        return success();
    }

    @PutMapping("/positions")
    public Response<PositionsPo> updateDepartment(@RequestBody Request<PositionsDTO> positionsDTO) {
        PositionsPo updatedposition = positionsService.updatePositions(positionsDTO.getData());
        return success(updatedposition);
    }
}
