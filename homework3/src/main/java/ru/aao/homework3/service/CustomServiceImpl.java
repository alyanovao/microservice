package ru.aao.homework3.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.aao.homework3.dto.ServiceResponse;

@Service
@RequiredArgsConstructor
public class CustomServiceImpl implements CustomService {

    @Override
    public ServiceResponse getHello() {
        return new ServiceResponse("Hello");
    }
}
