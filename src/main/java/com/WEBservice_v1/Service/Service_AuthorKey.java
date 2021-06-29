package com.WEBservice_v1.Service;

import com.WEBservice_v1.Entity.AuthorKey;
import com.WEBservice_v1.Repository.Repository_AuthorKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Service_AuthorKey {
    @Autowired
    Repository_AuthorKey res_Author;

    public int getCount(){
        return res_Author.getCount();
    }
    public void deleteAll(){
        res_Author.deleteAll();
    }
    public String getSecrectKey(){
        try {
            String secrect = res_Author.getSecrectKey();
            return secrect;
        } catch (Exception e){
            return null;
        }
    }
    public boolean saveSecrectKey(String secretKey){
        AuthorKey author = new AuthorKey();
        author.setSecretkey(secretKey);
        try {
            res_Author.save(author);
            return true;
        } catch (Exception e){
            System.out.println("(Service_Authorkey ERR) USER NOT EXIST OR SOMETHING ELSE");
            return false;
        }
    }
}
