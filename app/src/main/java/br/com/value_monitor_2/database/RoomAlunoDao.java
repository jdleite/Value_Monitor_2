package br.com.value_monitor_2.database;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import br.com.value_monitor_2.model.ValueGroup;

public interface RoomAlunoDao {

    @Insert
    void save(ValueGroup valueGroup);

    @Query("SELECT * FROM ValueGroup")
    List<ValueGroup> findAll();

    @Delete
    void remove();

    @Update
    void update();
}
