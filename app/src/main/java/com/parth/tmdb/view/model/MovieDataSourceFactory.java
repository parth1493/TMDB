package com.parth.tmdb.view.model;


import android.app.Application;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.parth.tmdb.view.service.MovieDataService;

public class MovieDataSourceFactory extends DataSource.Factory {
    private MovieDataService movieDataService;
    private MovieDataSource movieDataSource;
    private Application application;
    private MutableLiveData<MovieDataSource> movieDataSourceMutableLiveData;

    public MovieDataSourceFactory(MovieDataService movieDataService, Application application) {
        this.movieDataService = movieDataService;
        this.application = application;

        movieDataSourceMutableLiveData = new MutableLiveData<>();
    }

    @Override
    public DataSource create() {

        movieDataSource = new MovieDataSource(movieDataService,application);
        movieDataSourceMutableLiveData.postValue(movieDataSource);
        return movieDataSource;
    }

    public MutableLiveData<MovieDataSource> getMovieDataSourceMutableLiveData() {
        return movieDataSourceMutableLiveData;
    }
}
