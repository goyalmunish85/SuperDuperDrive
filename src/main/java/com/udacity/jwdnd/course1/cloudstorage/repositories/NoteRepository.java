package com.udacity.jwdnd.course1.cloudstorage.repositories;

import com.udacity.jwdnd.course1.cloudstorage.entities.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoteRepository {

    @Select("SELECT * FROM Notes WHERE userid = #{userid}")
    public List<Note> get(String userid);

    @Select("SELECT * FROM Notes WHERE userid = #{userid} AND noteid = #{noteid} ")
    public Note findById(String noteid, String userid);

    @Delete("DELETE FROM Notes WHERE userid = #{userid} AND noteid = #{noteid}")
    public int deleteById(String noteid, String userid);

    @Insert("INSERT INTO Notes(userid, notetitle, notedescription) VALUES (#{userid}, #{note.notetitle}, #{note.notedescription})")
    public int insert(Note note, String userid);

    @Update("UPDATE Notes SET noteid = #{note.noteid}, notetitle = #{note.notetitle}, notedescription = #{note.notedescription} WHERE userid = #{userid} AND noteid = #{note.noteid}")
    public int updateById(Note note, String userid);
}