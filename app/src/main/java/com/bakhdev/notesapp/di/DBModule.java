package com.bakhdev.notesapp.di;

import android.content.Context;

import androidx.room.Room;

import com.bakhdev.notesapp.data.room.AppDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class DBModule {

    @Provides
    @Singleton
    AppDatabase provideAppDB(@ApplicationContext Context context) {
        return Room.databaseBuilder(
                context,
                AppDatabase.class,
                "Notes.db"
        ).build();
    }
}
