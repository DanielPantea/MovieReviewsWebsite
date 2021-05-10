package com.panteapaliuc.movie.reviews.service;

import com.panteapaliuc.movie.reviews.model.Watchlist;
import com.panteapaliuc.movie.reviews.repository.WatchlistRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class WatchlistService {

    private final WatchlistRepository watchlistRepository;

    public Watchlist addWatchlist (Watchlist watchlist)
    {
        return watchlistRepository.save(watchlist);
    }

}
