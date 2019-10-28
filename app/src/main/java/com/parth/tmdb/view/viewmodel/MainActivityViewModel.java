package com.parth.tmdb.view.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.parth.tmdb.view.model.MovieDataSource;
import com.parth.tmdb.view.model.MovieDataSourceFactory;
import com.parth.tmdb.view.model.entity.Movie;
import com.parth.tmdb.view.model.repository.MovieRepository;
import com.parth.tmdb.view.service.MovieDataService;
import com.parth.tmdb.view.service.RetrofitInstance;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MainActivityViewModel extends AndroidViewModel {

    private MovieRepository movieRepository;
    private Executor executor;
    private LiveData<PagedList<Movie>> pagedListLiveData;

    LiveData<MovieDataSource> movieDataSourceLiveData;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);

        movieRepository = new MovieRepository(application);

        MovieDataService movieDataService = RetrofitInstance.getService();
        MovieDataSourceFactory factory = new MovieDataSourceFactory(movieDataService,application);
        movieDataSourceLiveData = factory.getMovieDataSourceMutableLiveData();

        PagedList.Config config = (new PagedList.Config.Builder())
                                    .setEnablePlaceholders(true)
                                    .setInitialLoadSizeHint(10)
                                    .setPageSize(20)
                                    .setPrefetchDistance(4)
                                    .build();

        executor = Executors.newFixedThreadPool(5);

        pagedListLiveData = (new LivePagedListBuilder<Long,Movie>(factory,config))
                .setFetchExecutor(executor)
                .build();

    }

    public LiveData<List<Movie>> getAllMovies()
    {
        return movieRepository.getMutableLiveData();
    }

    public LiveData<PagedList<Movie>> getPagedListLiveData() {
        return pagedListLiveData;
    }
}
