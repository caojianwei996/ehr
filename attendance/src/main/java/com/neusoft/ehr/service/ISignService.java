package com.neusoft.ehr.service;

import java.time.LocalDateTime;

public interface ISignService {
    void on(LocalDateTime time);

    void off(LocalDateTime time);
}
