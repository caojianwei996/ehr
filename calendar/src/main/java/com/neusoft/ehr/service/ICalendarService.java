package com.neusoft.ehr.service;

import com.neusoft.ehr.entity.po.CalendarPo;

import java.time.LocalDate;
import java.util.List;

public interface ICalendarService {
    List<CalendarPo> getCalendar(String start, String end);

    void addCalendar(LocalDate date);

    void updateCalendar(CalendarPo calendarPo);
}
