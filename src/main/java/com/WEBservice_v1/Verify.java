package com.WEBservice_v1;

import com.WEBservice_v1.Service.Service_AuthorKey;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

public  class Verify {


    public static void getKey(entity_KeyINFO login, Service_AuthorKey service_authorKey){
        login.setSecrectKey(service_authorKey.getSecrectKey());;
        login.setKey(Keys.hmacShaKeyFor(Decoders.BASE64.decode(login.getSecrectKey())));
    }
    public static boolean verifyLogin(String jws, entity_KeyINFO login, Service_AuthorKey service_Author){
        if(login.getKey()==null)
            getKey(login, service_Author);
        System.out.println("KEY: "+ login.getSecrectKey());

        try {
            Jwts.parserBuilder().setSigningKey(login.getKey()).build().parseClaimsJws(jws);

            System.out.println("Can be Trusted");
            String role = Jwts.parser().setSigningKey(login.getKey()).parseClaimsJws(jws).getBody().get("role", String.class);
            System.out.println("Role: "+ role);
            if(role.equals("admin")){
                System.out.println("Role accepted, Welcome!");
                return true;
            }

            else{
                System.out.println("Role not accepted, SORRY");
                return false;
            }
        }
        catch (JwtException e){
            System.out.println("Can't be Trusted");
            e.printStackTrace();
            return false;
        }
    }
    public static boolean verifyUserLogin(String jws, entity_KeyINFO login, Service_AuthorKey service_Author){
        if(login.getKey()==null)
            getKey(login, service_Author);
        System.out.println("KEY: "+ login.getSecrectKey());

        try {
            Jwts.parserBuilder().setSigningKey(login.getKey()).build().parseClaimsJws(jws);

            System.out.println("Can be Trusted");
            return true;
        }
        catch (JwtException e){
            System.out.println("Can't be Trusted");
            e.printStackTrace();
            return false;
        }
    }
}
