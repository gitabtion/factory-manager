package net.hrsoft.transparent_factory_manager.network;

import net.hrsoft.transparent_factory_manager.account.models.LoginRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * @author abtion.
 * @since 17/8/25 17:59.
 * email caiheng@hrsoft.net
 */

public interface APIService {

  @POST("user/login")
  Call<APIResponse> login(@Body LoginRequest loginRequest);

}
