package com.example.Omafourm.service.request;

/**
 * @param: VerifyRequest
 * @package: com.example.Omafourm.service.request
 * @className: VerifyRequest
 * @description: TODO
 * @return:
 */
public class VerifyRequest {
    private String email;
    private String code;

    public VerifyRequest(String email, String code) {
        this.email = email;
        this.code = code;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
