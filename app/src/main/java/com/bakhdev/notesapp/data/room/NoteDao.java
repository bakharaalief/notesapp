package com.bakhdev.notesapp.data.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

@Dao
public interface NoteDao {
    @Query("SELECT * FROM notes")
    Flowable<List<NoteEntity>> getNotes();


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Completable insert(NoteEntity noteEntity);

    @Update
    Completable update(NoteEntity noteEntity);

    @Delete
    Completable delete(NoteEntity noteEntity);
}
