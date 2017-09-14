package net.hrsoft.transparent_factory_manager.network;

import net.hrsoft.transparent_factory_manager.account.models.LoginRequest;
import net.hrsoft.transparent_factory_manager.account.models.LoginResponse;
import net.hrsoft.transparent_factory_manager.account.models.UserModel;
import net.hrsoft.transparent_factory_manager.home.models.GetProcedureResponse;
import net.hrsoft.transparent_factory_manager.home.models.ProcedureModel;
import net.hrsoft.transparent_factory_manager.mine.models.CreateLeaderRequest;
import net.hrsoft.transparent_factory_manager.mine.models.GetGroupLIstResponse;
import net.hrsoft.transparent_factory_manager.mine.models.LeaderAccountModel;
import net.hrsoft.transparent_factory_manager.mine.models.UpdateMobileModel;
import net.hrsoft.transparent_factory_manager.mine.models.UpdatePasswordModel;
import net.hrsoft.transparent_factory_manager.mine.models.UpdateUserNameModel;
import net.hrsoft.transparent_factory_manager.order.models.CreateOrderRequest;
import net.hrsoft.transparent_factory_manager.order.models.CreateOrderResponse;
import net.hrsoft.transparent_factory_manager.order.models.CurrentOrderModel;
import net.hrsoft.transparent_factory_manager.order.models.GetProcedureDataResponse;
import net.hrsoft.transparent_factory_manager.order.models.OrderModel;
import net.hrsoft.transparent_factory_manager.order.models.OrderResponse;
import net.hrsoft.transparent_factory_manager.order.models.ProcedureDataModel;
import net.hrsoft.transparent_factory_manager.order.models.UpdateProcedureRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
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

    //登录
    @POST("user/login")
    Call<APIResponse<LoginResponse>> login(@Body LoginRequest loginRequest);

    //未开始订单列表
    @GET("order/unstart")
    Call<APIResponse<OrderResponse<OrderModel[]>>> getUnstarOrders(@Query("page") int page, @Query("size") int size);

    //已结束订单列表
    @GET("order/past")
    Call<APIResponse<OrderResponse<OrderModel[]>>> getPastOrders(@Query("page") int page, @Query("size") int size);

    //进行中订单列表
    @GET("order/current")
    Call<APIResponse<OrderResponse<CurrentOrderModel[]>>> getCurrentOrders(@Query("page") int page, @Query("size") int
            size);

    //班组负责的工序列表
    @GET("procedure/list/{workGroupId}")
    Call<APIResponse<GetProcedureResponse>> getGroupProcedure(@Path("workGroupId") int workGroupId, @Query("page")
            int page, @Query("size") int size);

    //某一订单下的工序列表
    @GET("order/{orderId}/procedures")
    Call<APIResponse<GetProcedureResponse>> getOrderProcedure(@Path("orderId") int orderId);

    //更新用户姓名，应产品要求取消该功能
    @PUT("user/{id}")
    Call<APIResponse> updateName(@Path("id") int userId, @Body UpdateUserNameModel updateUserNameModel);

    //更新用户密码
    @PUT("user/{id}")
    Call<APIResponse> updatePassword(@Path("id") int userId, @Body UpdatePasswordModel updatePasswordModel);

    //更新用户手机号
    @PUT("user/{id}")
    Call<APIResponse> updateMobile(@Path("id") int userId, @Body UpdateMobileModel updateMobileModel);

    //获取班组列表
    @GET("group/list")
    Call<APIResponse<GetGroupLIstResponse>> getGroupList();

    //获取班组长列表
    @GET("leaders")
    Call<APIResponse<UserModel[]>> getLeaders();

    //创建班组长账号
    @POST("/admin/leaders/create")
    Call<APIResponse> createLeader(@Body CreateLeaderRequest createLeaderRequest);

    //更新员工信息
    @PUT("user/{id}")
    Call<APIResponse> updateUserInfo(@Path("id") int userId, @Body LeaderAccountModel leaderAccountModel);

    //创建订单
    @POST("order")
    Call<APIResponse<CreateOrderResponse>> createOrder(@Body CreateOrderRequest createOrderRequest);

    //创建工序
    @POST("procedure/add")
    Call<APIResponse> createProcedure(@Body UpdateProcedureRequest updateProcedureRequest);

    //修改工序详情
    @PUT("procedure/update/{procedureId}")
    Call<APIResponse> updateProcedure(@Path("procedureId") int procedureId, @Body UpdateProcedureRequest
            updateProcedureRequest);

    //更新订单信息
    @PUT("order/{orderId}")
    Call<APIResponse> updateOrder(@Path("orderId") int orderId, @Body CreateOrderRequest createOrderRequest);

    //获取工序数据
    @GET("procedure/{procedureId}/logs")
    Call<APIResponse<GetProcedureDataResponse>> getProcedureData(@Path("procedureId") int procedureId);

    //获取工序详情
    @GET("procedure/detail/{procedureId}")
    Call<APIResponse<ProcedureModel>> getProcedureInfo(@Path("procedureId")int procedureId);

    //删除工序
    @DELETE("procedure/delete/{procedureId}")
    Call<APIResponse> deleteProcedure(@Path("procedureId")int procedureId);

}
