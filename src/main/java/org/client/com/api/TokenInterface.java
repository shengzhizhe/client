package org.client.com.api;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import org.client.com.model.TokenModel;
import org.client.com.util.resultJson.ResponseResult;
import org.springframework.web.bind.annotation.PathVariable;

public interface TokenInterface {

    @Headers("Content-Type: application/json")
    @RequestLine("POST /token/token")
    ResponseResult<TokenModel> add(@Param("model") TokenModel model);

    @RequestLine("PATCH /token/token")
    ResponseResult<TokenModel> updateByToken(@Param("token") String token, @Param("use") String use);

    @RequestLine("GET /token/{account}")
    ResponseResult<TokenModel> getByAccount(@PathVariable("account") String account);
}
