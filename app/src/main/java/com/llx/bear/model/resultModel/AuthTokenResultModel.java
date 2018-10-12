package com.llx.bear.model.resultModel;

/**
 * Author:  fanlei
 * Time: 2017/9/9 10:09
 * 接收 token 的model
 */

public class AuthTokenResultModel {


    /**
     * access_token : Nr3l6EBsqRTP_fBjO1kefeIE0kL2y6_lpHmCX_R2lZ41gngPSRKrYV6kAaB8S22AgAfefU9xwlHpFmKCyW5fzIg_ScrHe5CNY99bKpeKNQ5PeeMSBeAgjBCGGr6-_HUAJiksiIdyazJkDrbANCdZrOl7ZbqV4H82C0RonxTTCPitJXd1gDwfZANgkblBV-BRbROXyOcHAkLVTdoI8hHVwhIUp9Q
     * token_type : bearer
     * expires_in : 31535999
     * refresh_token : YegEyHHK5kOnlztLYrOvYkR_Tu1MS-tYoM94DQyCXC0k9VFzMnGbRUfvQLC7regcHLtU4rtFTF0jC6Pk1_lKZJoy6MqGfMQytrEzhMqQyo5ghwTLpGsJiShAw3v-55hS8Ap31RtI0zFh3Cu_0EQ41pQtH54sBVG-AdoVH7WQV2HODpVaipA32YZ2jEacxJHEPNgkRA8bVnrcct5qjgVlCYkVyjk
     */

    private String access_token;
    private String token_type;
    private int expires_in;
    private String refresh_token;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }
}
