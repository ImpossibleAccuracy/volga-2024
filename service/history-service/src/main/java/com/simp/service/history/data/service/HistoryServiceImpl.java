package com.simp.service.history.data.service;

import com.simp.service.history.data.model.HistoryEntity;
import com.simp.service.history.data.repository.HistoryRepository;
import com.simp.service.history.domain.service.LocalHistoryService;
import com.simp.service.shared.domain.model.Account;
import com.simp.service.shared.domain.model.Caller;
import com.simp.service.shared.domain.model.History;
import com.simp.service.shared.domain.model.Room;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class HistoryServiceImpl implements LocalHistoryService {
    private final HistoryRepository historyRepository;

    @Override
    public Mono<? extends History> create(Caller caller, Instant date, Account patient, Account doctor, Room room, String data) {
        return Mono.just(HistoryEntity.builder()
                        .date(date)
                        .patient(patient.id())
                        .doctor(doctor.id())
                        .room(room.id())
                        .data(data)
                        .build())
                .flatMap(historyRepository::save);
    }

    @Override
    public Mono<? extends History> get(Caller caller, long id) {
        return historyRepository.findById(id);
    }

    @Override
    public Flux<? extends History> getByAccount(Caller caller, Account account) {
        return historyRepository.findByPatient(account.id());
    }

    @Override
    public Mono<? extends History> update(Caller caller, History target, Instant date, Account patient, Account doctor, Room room, String data) {
        var entity = (HistoryEntity) target;

        return Mono.just(entity.toBuilder()
                        .date(date)
                        .patient(patient.id())
                        .doctor(doctor.id())
                        .room(room.id())
                        .data(data)
                        .build())
                .flatMap(historyRepository::save);
    }
}
