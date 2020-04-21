package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.repositories.CredentialRepository;
import com.udacity.jwdnd.course1.cloudstorage.entities.Credential;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

@Service
public class CredentialService {
    @Autowired
    private CredentialRepository credentialRepository;

    @Autowired
    private EncryptionService encryptionService;

    private Credential encryptPassword(Credential credential) {
        String key = RandomStringUtils.random(16, true, true);
        credential.setKey(key);
        credential.setPassword(encryptionService.encryptValue(credential.getPassword(), key));
        return credential;
    }

    private String decryptPassword(Credential credential) {
        return encryptionService.decryptValue(credential.getPassword(), credential.getKey());
    }

    public Boolean isCredentialExists(String id, String userid) throws Exception {
        try {
            Credential credential = credentialRepository.findById(id, userid);
            if (credential == null) {
                return Boolean.FALSE;
            }
            return Boolean.TRUE;
        }  catch (Exception e) {
            throw e;
        }
    }

    public List<Credential> getAllCredentials(String userid) throws Exception {
        try {
            List<Credential> credentials = credentialRepository.get(userid);

            for(Credential credential : credentials) {
                credential.setDecodedPassword(decryptPassword(credential));
            }

//
//            Iterator<Credential> iterator = credentials.iterator();
//
//            if (iterator.hasNext()) {
//                Credential credential = iterator.next();
//                credential.setDecodedPassword(decryptPassword(credential));
//            }

            return credentials;
        }  catch (Exception e) {
            throw e;
        }
    }


    public void insertCredential(Credential credential, String userid) {
        try {
            credentialRepository.insert(encryptPassword(credential), userid);
        }  catch (Exception e) {
            throw e;
        }
    }

    public void updateCredentialById(Credential credential, String userid) {
        try {
            credentialRepository.updateById(encryptPassword(credential), userid);
        }  catch (Exception e) {
            throw e;
        }
    }

    public void deleteCredentialById(String id, String userid) {
        try {
            credentialRepository.deleteById(id, userid);
        }  catch (Exception e) {
            throw e;
        }
    }
}