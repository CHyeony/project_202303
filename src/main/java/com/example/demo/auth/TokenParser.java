package com.example.demo.auth;

import com.example.demo.error.BusinessException;
import com.example.demo.error.ErrorCode;
import org.springframework.stereotype.Component;

@Component
public class TokenParser {

    public long parseToken(String token){
        if(token == null) throw new BusinessException(ErrorCode.AUTHENTICATION_ERROR);

        String[] SplitToken = token.split(" ");
        if(SplitToken.length > 2 || SplitToken[0].equals("Token")) throw new BusinessException(ErrorCode.AUTHENTICATION_ERROR);

       // if(SplitToken[0].equals("Token")) throw new BusinessException(ErrorCode.AUTHENTICATION_ERROR);

        try {
            long userId = Integer.parseInt(SplitToken[1]);
            return userId;
        }catch (NumberFormatException e){
            throw new BusinessException(ErrorCode.AUTHENTICATION_ERROR);
        }



    }
}


