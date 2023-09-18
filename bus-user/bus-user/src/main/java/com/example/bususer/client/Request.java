package com.example.bususer.client;

import java.util.UUID;

public record Request(
         UUID id, // 유저 아이디
         String username

) {

}