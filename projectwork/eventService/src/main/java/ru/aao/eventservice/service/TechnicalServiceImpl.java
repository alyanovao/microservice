package ru.aao.eventservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.info.BuildProperties;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TechnicalServiceImpl implements TechnicalService {

    private final BuildProperties properties;

    @Override
    public String echo(String param) {
        return param;
    }

    @Override
    public String getVersion() {
        return properties.getVersion();
    }
}
