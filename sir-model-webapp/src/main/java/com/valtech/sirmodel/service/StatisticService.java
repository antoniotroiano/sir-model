package com.valtech.sirmodel.service;

import com.valtech.sirmodel.model.DataWorld;
import com.valtech.sirmodel.repository.CoronaWorldRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class StatisticService {

    private final CoronaWorldRepository coronaWorldRepository;

    public DataWorld saveDataWorld(DataWorld dataWorld) {
        log.info("Save data of world.");
        return coronaWorldRepository.save(dataWorld);
    }

    public List<DataWorld> getAllData() {
        return coronaWorldRepository.findAll();
    }

    public Optional<DataWorld> findDataWorldById(long id) {
        return coronaWorldRepository.findById(id);
    }

    public Optional<DataWorld> findDataWorldByLastUpdate(String lastUpdate) {
        log.info("Find last update: {}", lastUpdate);
        return coronaWorldRepository.findDataWorldByLastUpdate(lastUpdate);
    }

    public void deleteDataWorld(DataWorld dataWorld) {
        coronaWorldRepository.delete(dataWorld);
    }
}