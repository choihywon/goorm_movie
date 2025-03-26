package com.shakkib.netflixclone.dtoes;

import lombok.Getter;
import lombok.Setter;

public class JoinDTO {

    @Getter
    @Setter
    public static class Request{
        private String email;
        private String password;
        private String nickname;
        //다른건 나중에 추가


    }
}