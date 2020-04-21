package com.udacity.jwdnd.course1.cloudstorage.repositories;

import com.udacity.jwdnd.course1.cloudstorage.entities.Credential;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialRepository {

    @Select("SELECT * FROM Credentials WHERE userid = #{userid}")
    public List<Credential> get(String userid);

    @Select("SELECT * FROM CREDENTIALS WHERE userid = #{userid} AND credentialid = #{credentialid} ")
    public Credential findById(String credentialid, String userid);

    @Delete("DELETE FROM CREDENTIALS WHERE userid = #{userid} AND credentialid = #{credentialid}")
    public int deleteById(String credentialid, String userid);

    @Insert("INSERT INTO CREDENTIALS(userid, url, username, key, password) VALUES (#{userid}, #{credential.url}, #{credential.username}, #{credential.key}, #{credential.password})")
    public int insert(Credential credential, String userid);

    @Update("UPDATE CREDENTIALS SET credentialid = #{credential.credentialid}, url = #{credential.url}, username = #{credential.username}, key = #{credential.key}, password = #{credential.password} WHERE userid = #{userid} AND credentialID = #{credential.credentialid}")
    public int updateById(Credential credential, String userid);
}