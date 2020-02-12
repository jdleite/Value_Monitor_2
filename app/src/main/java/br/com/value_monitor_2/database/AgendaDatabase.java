package br.com.value_monitor_2.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import br.com.value_monitor_2.database.dao.RoomValueGroup;
import br.com.value_monitor_2.model.ValueGroup;

@Database(entities = {ValueGroup.class}, version = 1, exportSchema = false)
public abstract class AgendaDatabase extends RoomDatabase {

    public abstract RoomValueGroup getRoomValueGroup();

    private static final String NOME_BANCO_DE_DADOS = "value.db";

    public static AgendaDatabase getInstance(Context context){
        return Room.databaseBuilder(context,AgendaDatabase.class,NOME_BANCO_DE_DADOS)
                .allowMainThreadQueries()
                .build();

    }


}
