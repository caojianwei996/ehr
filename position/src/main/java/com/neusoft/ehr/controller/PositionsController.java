package com.neusoft.ehr.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.neusoft.ehr.entity.InsertPositionsDto;
import com.neusoft.ehr.entity.UpdatePositionsDto;
import com.neusoft.ehr.entity.Request;
import com.neusoft.ehr.entity.Response;
import com.neusoft.ehr.entity.po.PositionsPo;
import com.neusoft.ehr.service.PositionsService;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/positions")
@RestController
public class PositionsController extends BaseController {
    private final PositionsService positionsService;
    /**
     * BaseController构造方法
     *
     * @param messageSource 国际化组件
     */
    protected PositionsController(MessageSource messageSource, PositionsService positionsService) {
        super(messageSource);
        this.positionsService = positionsService;
    }

    @GetMapping
    public Response<IPage<PositionsPo>> getPositions(@RequestParam(defaultValue = "1") Integer page,
                                                           @RequestParam(defaultValue = "10") Integer limit) {
        return success(positionsService.selectPositions(limit, page));
    }

    @PostMapping
    public Response<Void> addPositions(@RequestBody @Valid Request<InsertPositionsDto> insertPositionsDto) {
        positionsService.insertPositions(insertPositionsDto.getData());
        return success();
    }

    @PutMapping
    public Response<Void> updateDepartment(@RequestBody @Valid Request<UpdatePositionsDto> positionsDto) {
        positionsService.updatePositions(positionsDto.getData());
        return success();
    }
}
