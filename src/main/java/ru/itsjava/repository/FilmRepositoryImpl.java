package ru.itsjava.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.itsjava.domain.Film;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Transactional
@Repository
@RequiredArgsConstructor
public class FilmRepositoryImpl implements FilmRepository {
    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public List<Film> findAll() {
        return entityManager
                .createQuery("select distinct f from films f join fetch f.genre join fetch f.places", Film.class)
                .getResultList();
    }

    @Override
    public Film getById(long id) {
        return entityManager.find(Film.class, id);
    }


}
