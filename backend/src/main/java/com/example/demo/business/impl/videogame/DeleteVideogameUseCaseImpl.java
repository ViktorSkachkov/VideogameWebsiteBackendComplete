package com.example.demo.business.impl.videogame;

import com.example.demo.business.cases.videogame.DeleteVideogameUseCase;
import com.example.demo.domain.Videogame;
import com.example.demo.persistence.entity.*;
import com.example.demo.persistence.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DeleteVideogameUseCaseImpl implements DeleteVideogameUseCase {
    private final VideogameRepository videogameRepository;
    private final AdditionRepository additionRepository;
    private final NewsRepository newsRepository;
    private final ReviewRepository reviewRepository;

    /**
     * @param id
     * @return
     */
    @Override
    public Videogame deleteVideogame(int id) {
        Optional<VideogamePersistence> vp = videogameRepository.findById(Long.valueOf(id));
        if (vp.isEmpty()) {

        }
        videogameRepository.deleteById(Long.valueOf(id));

        List<NewsPersistence> newsArticles = newsRepository.findAll();

        List<AdditionPersistence> additions = additionRepository.findAll();

        List<ReviewPersistence> reviews = reviewRepository.findAll();

        List<Integer> additionsIds = this.deleteAdditions(additions, id);

        this.deleteReviews(reviews, additionsIds, id);

        this.deleteNews(newsArticles, id);

        return Videogame.builder()
                .id(Math.toIntExact(vp.get().getId()))
                .featured(vp.get().getFeatured())
                .description(vp.get().getDescription())
                .image(vp.get().getImage())
                .name(vp.get().getName())
                .price(vp.get().getPrice())
                .build();
    }

    @Override
    public List<ReviewPersistence> deleteReviews(List<ReviewPersistence> reviewsList, List<Integer> additionsIds, int id) {
        for(ReviewPersistence rp : reviewsList) {
            if((rp.getReviewed_item_id() == id && rp.getType_of_reviewed_item().equals("game")) ||
                    (additionsIds.contains(rp.getReviewed_item_id()) && rp.getType_of_reviewed_item().equals("addition"))) {
                reviewRepository.deleteById(rp.getId());
            }
        }
        return reviewsList;
    }

    @Override
    public List<Integer> deleteAdditions(List<AdditionPersistence> additionsList, int id) {
        List<Integer> additionsIds = new ArrayList<>();
        for(AdditionPersistence ap : additionsList) {
            if(ap.getGame_id() == id) {
                additionsIds.add(Math.toIntExact(ap.getId()));
                additionRepository.deleteById(ap.getId());
            }
        }
        return additionsIds;
    }

    @Override
    public List<NewsPersistence> deleteNews(List<NewsPersistence> newsList, int id) {
        for(NewsPersistence np : newsList) {
            if(np.getGame_id() == id) {
                newsRepository.deleteById(np.getId());
            }
        }
        return newsList;
    }
}
