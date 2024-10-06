package com.simp.service.hospital.data.service;

import com.simp.service.hospital.data.model.RoomEntity;
import com.simp.service.hospital.data.repository.RoomRepository;
import com.simp.service.hospital.domain.service.LocalRoomService;
import com.simp.service.shared.domain.exception.ResourceNotFoundException;
import com.simp.service.shared.domain.model.Caller;
import com.simp.service.shared.domain.model.Hospital;
import com.simp.service.shared.domain.model.Room;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements LocalRoomService {
    private final RoomRepository roomRepository;

    @Override
    public Mono<Void> createHospitalRooms(Caller caller, Hospital hospital, List<String> rooms) {
        // TODO: check caller

        return roomRepository
                .findByHospital(hospital.id())
                .collectList()
                .flatMap(savedRooms -> {
                    var newRooms = rooms.stream()
                            .filter(r -> savedRooms
                                    .stream()
                                    .noneMatch(r2 -> r.equalsIgnoreCase(r2.name())))
                            .map(r -> RoomEntity.builder()
                                    .name(r)
                                    .hospital(hospital.id())
                                    .build())
                            .toList();

                    var deletedRooms = savedRooms
                            .stream()
                            .filter(r -> rooms
                                    .stream()
                                    .noneMatch(r2 -> r.name().equalsIgnoreCase(r2)))
                            .toList();

                    return roomRepository
                            .saveAll(newRooms)
                            .collectList()
                            .zipWhen(a -> roomRepository.deleteAll(deletedRooms))
                            .then();
                });
    }

    @Override
    public Mono<? extends Room> get(Caller caller, Hospital hospital, long id) {
        return roomRepository
                .findByIdAndHospital(id, hospital.id())
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Room not found")));
    }

    @Override
    public Mono<? extends Room> getByName(Caller caller, Hospital hospital, String name) {
        return roomRepository
                .findByNameAndHospital(name, hospital.id())
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Room not found")));
    }

    @Override
    public Flux<? extends Room> getHospitalRooms(Caller caller, Hospital hospital) {
        return roomRepository.findByHospital(hospital.id());
    }
}
