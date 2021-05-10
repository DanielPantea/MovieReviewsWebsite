package com.panteapaliuc.movie.reviews.repository;

import com.panteapaliuc.movie.reviews.model.Watchlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WatchlistRepository extends JpaRepository<Watchlist, Long> {
}
