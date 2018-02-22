package org.client.com.api;

import feign.Body;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import org.client.com.api.model.TokenModel;
import org.client.com.util.resultJson.ResponseResult;
import org.springframework.web.bind.annotation.RequestBody;

public interface TokenInterface {

    @Headers("Content-Type: application/json")
    @RequestLine("POST /token/token")
    ResponseResult<TokenModel> add(@RequestBody TokenModel model);

    @RequestLine("POST /token/updateToken")
//    @Body("a={a}&b={b}")// post 提交
    @Body("token={token}")
    ResponseResult<TokenModel> updateToken(@Param("token") String token);

    @RequestLine("GET /token/token/{token}")
    ResponseResult<TokenModel> getByToken(@Param("token") String token);
}
