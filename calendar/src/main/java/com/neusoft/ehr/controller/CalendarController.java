package com.neusoft.ehr.controller;

import com.neusoft.ehr.entity.Request;
import com.neusoft.ehr.entity.Response;
import com.neusoft.ehr.entity.po.CalendarPo;
import com.neusoft.ehr.service.ICalendarService;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RequestMapping("/calendar")
@RestController
public class CalendarController extends BaseController {
    private final ICalendarService calendarService;

    /**
     * BaseController构造方法
     *
     * @param messageSource 国际化组件
     */
    public CalendarController(MessageSource messageSource, ICalendarService calendarService) {
        super(messageSource);
        this.calendarService = calendarService;
    }

    @GetMapping
    public Response<List<CalendarPo>> getCalendar(
            @RequestParam(value = "start", required = false) String start,
            @RequestParam(value = "end", required = false) String end
    ) {
        return success(calendarService.getCalendar(start, end));
    }

    @PostMapping
    public Response<Void> addCalendar(@RequestBody @Valid Request<LocalDate> request) {
        calendarService.addCalendar(request.getData());
        return success();
    }

    @PutMapping
    public Response<Void> updateCalendar(@RequestBody @Valid Request<CalendarPo> request) {
        calendarService.updateCalendar(request.getData());
        return success();
    }
}
