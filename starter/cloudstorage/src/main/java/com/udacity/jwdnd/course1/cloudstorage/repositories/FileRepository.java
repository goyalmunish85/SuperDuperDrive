package com.udacity.jwdnd.course1.cloudstorage.repositories;

import com.udacity.jwdnd.course1.cloudstorage.entities.File;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileRepository {

    @Select("SELECT * FROM Files WHERE userid = #{userid}")
    public List<File> get(String userid);

    @Select("SELECT * FROM Files WHERE userid = #{userid} and fileId = #{fileId}")
    public File findById(String fileId, String userid);

    @Select("SELECT * FROM Files WHERE userid = #{userid} and filename = #{filename}")
    public File findByFileName(String filename, String userid);
 
    @Delete("DELETE FROM Files WHERE userid = #{userid} AND fileId = #{fileId}")
    public int deleteById(String fileId, String userid);

    @Insert("INSERT INTO Files (userid, filename, contenttype, filesize, filedata) VALUES (#{userid}, #{file.filename}, #{file.contenttype}, #{file.filesize}, #{file.filedata})")
    public int insert(File file, String userid);

}
