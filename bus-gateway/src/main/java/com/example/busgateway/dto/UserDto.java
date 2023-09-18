package com.example.busgateway.dto;

/*사용자의 로그인 상태를 표현하고 전달하기 위해 사용될 것이고
   예를 들어, 사용자가 로그인할 때 혹은 로그인 상태를 검색할 때,
    UserDto 객체를 사용하여 해당 정보를 전달하거나 반환할 수 있다.

 */
public class UserDto {
    private Boolean logging;

    public UserDto(Boolean logging) {
        this.logging = logging;
    }

    public UserDto() {
    }

    public Boolean getLogging() {
        return logging;
    }

    public void setLogging(Boolean logging) {
        this.logging = logging;
    }
}