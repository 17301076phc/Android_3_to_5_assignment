package com.example.firstassignment.connectDB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.firstassignment.Course;
import com.example.firstassignment.CourseIndro;

import java.util.List;

@Dao
public interface CourseDao {

    @Insert
    void insert(CourseIndro... users);

    //删
    @Delete
    void delete(CourseIndro... users);

    //改
    @Update
    void update(CourseIndro... users);

    @Query("SELECT * from CourseIndro")
    List<CourseIndro> getCourseIndro();

}
