package net.hrsoft.transparent_factory_manager.network;

import net.hrsoft.transparent_factory_manager.account.models.LoginRequest;
import net.hrsoft.transparent_factory_manager.account.models.LoginResponse;
import net.hrsoft.transparent_factory_manager.account.models.UserModel;
import net.hrsoft.transparent_factory_manager.home.models.GetProcedureResponse;
import net.hrsoft.transparent_factory_manager.mine.models.CreateLeaderRequest;
import net.hrsoft.transparent_factory_manager.mine.models.GetGroupLIstResponse;
import net.hrsoft.transparent_factory_manager.mine.models.UpdateMobileModel;
import net.hrsoft.transparent_factory_manager.mine.models.UpdatePasswordModel;
import net.hrsoft.transparent_factory_manager.mine.models.UpdateUserNameModel;
import net.hrsoft.transparent_factory_manager.order.models.CurrentOrderModel;
import net.hrsoft.transparent_factory_manager.order.models.OrderModel;
import net.hrsoft.transparent_factory_manager.order.models.OrderResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * @author abtion.
 * @since 17/8/25 17:59.
 * email caiheng@hrsoft.net
 */

public interface APIService {

  @POST("user/login")
  Call<APIResponse<LoginResponse>> login(@Body LoginRequest loginRequest);

  //未开始订单列表
  @GET("order/unstart")
  Call<APIResponse<OrderResponse<OrderModel[]>>> getUnstarOrders(@Query("page")int page,@Query("size")int size);

  //已结束订单列表
  @GET("order/past")
  Call<APIResponse<OrderResponse<OrderModel[]>>> getPastOrders(@Query("page")int page,@Query("size")int size);

  //进行中订单列表
  @GET("order/current")
  Call<APIResponse<OrderResponse<CurrentOrderModel[]>>> getCurrentOrders(@Query("page")int page, @Query("size")int
          size);

  //班组负责的工序列表
  @GET("procedure/list/{workGroupId}")
  Call<APIResponse<GetProcedureResponse>> getGroupProcedure(@Path("workGroupId")int workGroupId,@Query("page")int page,
                                                        @Query("size")int size);

  //某一订单下的工序列表
  @GET("order/{orderId}/procedures")
  Call<APIResponse<GetProcedureResponse>> getOrderProcedure(@Path("orderId")int orderId);

  @PUT("user/{id}")
  Call<APIResponse> updateName(@Path("id")int userId, @Body UpdateUserNameModel updateUserNameModel);

  @PUT("user/{id}")
  Call<APIResponse> updatePassword(@Path("id")int userId, @Body UpdatePasswordModel updatePasswordModel);

  @PUT("user/{id}")
  Call<APIResponse> updateMobile(@Path("id")int userId, @Body UpdateMobileModel updateMobileModel);

  @GET("group/list")
  Call<APIResponse<GetGroupLIstResponse>> getGroupList();

  @GET("leaders")
  Call<APIResponse<UserModel[]>> getLeaders();

  @POST("/admin/leaders/create")
  Call<APIResponse> createLeader(@Body CreateLeaderRequest createLeaderRequest);
}
