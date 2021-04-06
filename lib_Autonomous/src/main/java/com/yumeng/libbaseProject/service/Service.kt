package com.yumeng.libbaseProject.service


import androidx.room.Delete
import com.yumeng.libbaseProject.Contants.Constants
import com.yumeng.libbaseProject.dataSupport.AddFriendBean
import com.yumeng.libbaseProject.dataSupport.FriendInfo
import com.yumeng.libbaseProject.dataSupport.GroupInfo
import com.yumeng.libbaseProject.dataSupport.RoomSettingRecords
import com.yumeng.libbaseProject.model.UserInfo
import com.yumeng.libbaseProject.service.ResponseModel.AddFriendResponse
import com.yumeng.libbaseProject.service.ResponseModel.LoginStatusResponse
import com.yumeng.libbaseProject.service.ResponseModel.TestResponse
import com.yumeng.libcore.IResult
import com.yumeng.libcore.response.BaseResponse
import com.yumeng.libcore.response.BaseResponseDataList
import com.yumeng.libcore.service.Api
import okhttp3.RequestBody
import retrofit2.http.*

interface Service {


    companion object {
        const val BASE_DOMAIN_NAME = "base_domain_name"

    }

    /**
     * 获取文章关注列表
     * TODO
     */
    @Headers(Api.URL_NAME + BASE_DOMAIN_NAME)
    @GET("article/articleMobile")
    suspend fun getArticles(
        @Query("pageNo") pageNo: Int,
        @Query("catalogId") catalogId: String,
        @Query("lastTime") lastTime: Long,
        @Query("pageSize") pageSize: Int = 30
    ): BaseResponse<BaseResponseDataList<TestResponse>>

//
//    /**
//     * 获取文章栏目
//     * TODO
//     */
//    @Headers(Api.URL_NAME + BASE_DOMAIN_NAME)
//    @GET("article/articleMobile")
//    suspend fun getArticles(
//        @Query("pageNo") pageNo: Int,
//        @Query("catalogId") catalogId: String,
//        @Query("lastTime") lastTime: Long,
//        @Query("pageSize") pageSize: Int = 30
//    ): BaseResponse<NewsResponse>
//
//    /**
//     * 按搜索获取文章栏目
//     * TODO
//     */
//    @Headers(Api.URL_NAME + BASE_DOMAIN_NAME)
//    @GET("article/search")
//    suspend fun getSearchArticles(
//        @Query("pageNo") pageNo: Int,
//        @Query("keyword") keyword: String,
//        @Query("lastTime") lastTime: Long,
//        @Query("pageSize") pageSize: Int = 30
//    ): BaseResponse<NewsResponse>
//
//    /**
//     * 获取推荐顶置接口
//     * TODO
//     */
//    @Headers(Api.URL_NAME + BASE_DOMAIN_NAME)
//    @GET("article/tops")
//    suspend fun getArticleTop(): BaseResponse<List<InformationMultiItemBean>>
//
//    /**
//     * 获取文章目录
//     * TODO
//     */
//    @Headers(Api.URL_NAME + BASE_DOMAIN_NAME)
//    @GET("catalog")
//    suspend fun getCatalog(
//        @Query("type") catalogType: Int = 1
//    ): BaseResponse<List<ChannelBean>>
//
//    /**
//     * 获取消息红点提示
//     * TODO
//     */
//    @Headers(Api.URL_NAME + BASE_DOMAIN_NAME)
//    @GET("articleNotice/newFlag")
//    suspend fun getNoticeNewFlag(): BaseResponse<NewsNoticeFlagResponse>
//
//    /**
//     * 设置目录顺序
//     * TODO
//     */
//    @Headers(Api.URL_NAME + BASE_DOMAIN_NAME)
//    @PUT("catalog")
//    suspend fun setCatalog(
//        @Body body: RequestBody
//    ): BaseResponse<String>
//
//    /**
//     * 清空资讯通知列表
//     * TODO
//     */
//    @Headers(Api.URL_NAME + BASE_DOMAIN_NAME)
//    @DELETE("articleNotice/clear")
//    suspend fun clearMessageNotice(): BaseResponse<String>
//
//    /**
//     * 获取资讯通知列表
//     * TODO
//     */
//    @Headers(Api.URL_NAME + BASE_DOMAIN_NAME)
//    @GET("articleNotice/list")
//    suspend fun getMessagesNotice(
//        @Query("pageNo") pageNo: Int,
//        @Query("pageSize") pageSize: Int = 30
//    ): BaseResponse<MessageNoticeListResponse>
//
//    /**
//     * 获取文章详情
//     * TODO
//     */
//    @Headers(Api.URL_NAME + BASE_DOMAIN_NAME)
//    @GET("article/{id}")
//    suspend fun getNewsDetail(
//        @Path("id") id: String
//    ): BaseResponse<InformationMultiItemBean>
//
//    /**
//     * 获取文章评论列表
//     * TODO
//     */
//    @Headers(Api.URL_NAME + BASE_DOMAIN_NAME)
//    @GET("comment/{id}")
//    suspend fun getNewsDetailComments(
//        @Path("id") id: String,
//        @Query("pageNo") pageNo: Int,
//        @Query("pageSize") pageSize: Int = 30
//    ): BaseResponse<NewsCommentListResponse>
//
//    /**
//     * 获取文章评论列表
//     * TODO
//     */
//    @Headers(Api.URL_NAME + BASE_DOMAIN_NAME)
//    @GET("reply/{commentId}")
//    suspend fun getSubComments(
//        @Path("commentId") id: String,
//        @Query("pageNo") pageNo: Int,
//        @Query("pageSize") pageSize: Int = 30
//    ): BaseResponse<NewsCommentSubListResponse>
//
//    /**
//     * 关注自媒体
//     * TODO
//     */
//    @Headers(Api.URL_NAME + BASE_DOMAIN_NAME)
//    @POST("userFocus")
//    suspend fun following(
//        @Body body: RequestBody
//    ): BaseResponse<FollowingResponse>
//
//
//    /**
//     * 发送一级评论
//     * TODO
//     */
//    @Headers(Api.URL_NAME + BASE_DOMAIN_NAME)
//    @POST("comment")
//    suspend fun addComment(
//        @Body body: RequestBody
//    ): BaseResponse<NewsCommentBean>
//
//    /**
//     * 发送二级评论
//     * TODO  这个需要搞
//     */
//    @Headers(Api.URL_NAME + BASE_DOMAIN_NAME)
//    @POST("reply")
//    suspend fun addSubComment(
//        @Body body: RequestBody
//    ): BaseResponse<NewsSubCommentBean>
//
//
//    /**
//     * 评论点赞/取消点赞
//     * TODO
//     */
//    @Headers(Api.URL_NAME + BASE_DOMAIN_NAME)
//    @POST("comment/upvote")
//    suspend fun thumbsUp(
//        @Body body: RequestBody
//    ): BaseResponse<ThumbsUpResponse>
//
//    /**
//     * 二级评论点赞/取消点赞
//     * TODO
//     */
//    @Headers(Api.URL_NAME + BASE_DOMAIN_NAME)
//    @POST("reply/upvote")
//    suspend fun replyThumbsUp(
//        @Body body: RequestBody
//    ): BaseResponse<ThumbsUpResponse>
//
//    /**
//     * 文章点赞
//     * TODO
//     */
//    @Headers(Api.URL_NAME + BASE_DOMAIN_NAME)
//    @POST("article/upvote")
//    suspend fun articleThumbsUp(
//        @Body body: RequestBody
//    ): BaseResponse<ThumbsUpResponse>
//
//    /**
//     * 文章、投诉|收藏等
//     * TODO
//     */
//    @Headers(Api.URL_NAME + BASE_DOMAIN_NAME)
//    @POST("article/operate")
//    suspend fun operateArticle(
//        @Body body: RequestBody
//    ): BaseResponse<Any>
//
//
//    /**
//     * 获取关注列表
//     * TODO
//     */
//    @Headers(Api.URL_NAME + BASE_DOMAIN_NAME)
//    @GET("userFocus")
//    suspend fun getFollowings(
//        @Query("pageNo") pageNo: Int,
//        @Query("pageSize") pageSize: Int = 30
//    ): BaseResponse<NewsResponse>
//
//    /**
//     * 获取粉丝列表
//     * TODO
//     */
//    @Headers(Api.URL_NAME + BASE_DOMAIN_NAME)
//    @GET("userFocus/fans")
//    suspend fun getFans(
//        @Query("pageNo") pageNo: Int,
//        @Query("pageSize") pageSize: Int = 30
//    ): BaseResponse<NewsResponse>
//
//    /**
//     * 获取关注列表
//     * TODO
//     */
//    @Headers(Api.URL_NAME + BASE_DOMAIN_NAME)
//    @GET("userFocus/search")
//    suspend fun searchFollowings(
//        @Query("keyword") keyword : String
//    ): BaseResponse<NewsResponse>


    @POST(Constants.getSMS)
    suspend fun getCodeFromNetWork(
        @Body body: RequestBody
    ): BaseResponse<Any>

    @POST(Constants.loginMsgCode)
    suspend fun getMsgCode(
        @Body body: RequestBody
    ): BaseResponse<Any>


    @POST(Constants.login)
    suspend fun login(
        @Body body: RequestBody
    ): BaseResponse<UserInfo>


    @DELETE(Constants.logout)
    suspend fun logout(): BaseResponse<Any>

    @GET(Constants.getUserInfo)
    suspend fun getUserInfo(): BaseResponse<UserInfo>


    @POST(Constants.complaintFriend)
    suspend fun complaintFriend(
        @Body body: RequestBody
    ): BaseResponse<Any>


    @POST(Constants.feedback)
    suspend fun feedback(
        @Body body: RequestBody
    ): BaseResponse<Any>


    @GET(Constants.getBlackList)
    suspend fun getBlackList(): BaseResponse<List<FriendInfo>>

    @PUT(Constants.updateBlackList)
    suspend fun updateBlackList(
        @Body body: RequestBody
    ): BaseResponse<FriendInfo>


    @GET(Constants.getRoomSetting)
    suspend fun getRoomSettingList(): BaseResponse<List<RoomSettingRecords>>

    @POST(Constants.friendRequest)
    suspend fun friendRequest(
        @Body body: RequestBody
    ): BaseResponse<Any>

    @POST(Constants.qrLoginReject)
    suspend fun qrLoginReject(
        @Body body: RequestBody
    ): BaseResponse<Any>

    @POST(Constants.qrScan)
    suspend fun qrScan(
        @Body body: RequestBody
    ): BaseResponse<Any>

    @POST(Constants.qrLoginAgree)
    suspend fun qrLoginAgree(
        @Body body: RequestBody
    ): BaseResponse<Any>

    @POST(Constants.checkUserLoginStatus)
    suspend fun checkUserLoginStatus(): BaseResponse<LoginStatusResponse>

    @POST(Constants.webLoginExit)
    suspend fun webLoginExit(): BaseResponse<Any>


    @POST(Constants.shieldTrends)
    suspend fun shieldFriendTrends(
        @Body body: RequestBody
    ): BaseResponse<Any>


    @GET(Constants.searchUser)
    suspend fun searchUser(
        @Body body: RequestBody
    ):BaseResponse<AddFriendBean>


    @PUT(Constants.uid)
    suspend fun modifyId(
        @Body body: RequestBody
    ):BaseResponse<Any>


    @GET(Constants.searchFriendV1214)
    suspend fun searchFriendV1214(
        @Body body: RequestBody
    ):BaseResponse<AddFriendBean>


    @PUT(Constants.dissolved)
    suspend fun disbandGroup(
        @Body body: RequestBody
    ):BaseResponse<GroupInfo>


    @POST(Constants.addFriendRequest)
    suspend fun addFriendRequest(
        @Body body: RequestBody
    ):BaseResponse<AddFriendResponse>


}