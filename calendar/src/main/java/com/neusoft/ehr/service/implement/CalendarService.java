package com.neusoft.ehr.service.implement;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.neusoft.ehr.entity.po.CalendarPo;
import com.neusoft.ehr.mapper.CalendarMapper;
import com.neusoft.ehr.service.ICalendarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CalendarService implements ICalendarService {
    private final CalendarMapper calendarMapper;

    @Override
    public List<CalendarPo> getCalendar(String start, String end) {
        return calendarMapper.selectList(
                Wrappers.<CalendarPo>lambdaQuery()
                        .between(CalendarPo::getId, start, end)
        );
    }

    @Override
    public void addCalendar(LocalDate date) {
        List<CalendarPo> list = new ArrayList<>();
        for (LocalDate localDate = Optional.ofNullable(
                calendarMapper.selectOne(Wrappers.<CalendarPo>lambdaQuery().orderByDesc(CalendarPo::getId))
        ).map(CalendarPo::getId).orElse(LocalDate.now()); localDate.isBefore(date); localDate = localDate.plusDays(1)) {
            CalendarPo po = new CalendarPo();
            po.setId(localDate);
            po.setType((byte) (localDate.getDayOfWeek() == DayOfWeek.SATURDAY || localDate.getDayOfWeek() == DayOfWeek.SUNDAY ? 3 : 4));
            list.add(po);
        }
        calendarMapper.insert(list);
    }

    @Override
    public void updateCalendar(CalendarPo calendarPo) {
        calendarMapper.insertOrUpdate(calendarPo);
    }
}
