package com.example.demo.business.impl.addition;

import com.example.demo.business.cases.addition.GetAdditionsByGameUseCase;
import com.example.demo.domain.Addition;
import com.example.demo.persistence.domain.persistenceClass.AdditionPersistence;
import com.example.demo.persistence.repository.AdditionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAdditionsByGameUseCaseImpl implements GetAdditionsByGameUseCase {
    private final AdditionRepository additionRepository;

    /**
     *
     * @param index
     * @return
     */
    @Override
    public List<Addition> GetAdditionsByGame(int index) {
        List<AdditionPersistence> list = additionRepository.findAll();
        List<Addition> additionsList = new ArrayList<>();
        for(AdditionPersistence ap : list) {
            if(index == -1) {
                Addition addition = Addition.builder()
                        .id(Math.toIntExact(ap.getId()))
                        .image(ap.getImage())
                        .gameId(ap.getGame_id())
                        .price(ap.getPrice())
                        .description(ap.getDescription())
                        .name(ap.getName())
                        .build();
                additionsList.add(addition);
            }
            else {
                if(ap.getGame_id() == index) {
                    Addition addition = Addition.builder()
                            .id(Math.toIntExact(ap.getId()))
                            .image(ap.getImage())
                            .gameId(ap.getGame_id())
                            .price(ap.getPrice())
                            .description(ap.getDescription())
                            .name(ap.getName())
                            .build();
                    additionsList.add(addition);
                }
            }
        }
        return additionsList;
    }
}
