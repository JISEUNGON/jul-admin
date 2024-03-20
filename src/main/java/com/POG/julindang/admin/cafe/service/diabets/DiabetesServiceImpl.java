package com.POG.julindang.admin.cafe.service.diabets;

import com.POG.julindang.admin.cafe.repository.DiabetesRiskRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class DiabetesServiceImpl implements DiabetesService {
    private final DiabetesRiskRepository diabetesRiskRepository;

}
