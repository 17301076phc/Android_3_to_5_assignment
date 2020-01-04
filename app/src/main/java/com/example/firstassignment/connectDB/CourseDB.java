package com.example.firstassignment.connectDB;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.firstassignment.CourseIndro;

@Database(entities = {CourseIndro.class},version = 1)
public abstract class CourseDB extends RoomDatabase {

    private static final String DB_NAME = "CourseDatabase.db";
    private static volatile CourseDB instance;

    public static synchronized CourseDB getInstance(Context context) {
        if (instance == null) {
            instance = create(context);
        }
        return instance;
    }

    private static CourseDB create(final Context context) {
        return Room.databaseBuilder(
                context,
                CourseDB.class,
                DB_NAME)
                .allowMainThreadQueries()
                .build();
    }

    public abstract CourseDao getCourseDao();

}
