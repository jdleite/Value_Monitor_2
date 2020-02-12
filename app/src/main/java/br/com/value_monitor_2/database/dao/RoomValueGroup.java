package br.com.value_monitor_2.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import br.com.value_monitor_2.model.ValueGroup;

@Dao
public interface RoomValueGroup {

    @Insert
    void save(ValueGroup valueGroup);

    @Query("SELECT * FROM ValueGroup ORDER BY ID DESC")
    List<ValueGroup> findAll();

    @Delete
    void remove(ValueGroup valueGroup);

    @Update
    void update(ValueGroup valueGroup);

    @Query("DELETE FROM ValueGroup")
    void removeAll();


}
