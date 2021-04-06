package com.yumeng.libbaseProject.service.repository

import com.yumeng.libbaseProject.service.ResponseModel.TestResponse
import com.yumeng.libbaseProject.service.http.Net
import com.yumeng.libcore.IResult
import com.yumeng.libcore.base.BaseRepository
import com.yumeng.libcore.response.BaseResponseDataList


class NewsDetailRepository : BaseRepository() {
    //获取文章详情
    suspend fun getArticles(id: String): IResult<BaseResponseDataList<TestResponse>> {
        return safeApiCall(
            call = { executeResponse(Net.service.getArticles(pageNo = 1,catalogId = id,lastTime = 0L)) },
            errorMessage = ""
        )
    }
//
//    //评论点赞
//    suspend fun thumbsUp(commentId: String): IResult<ThumbsUpResponse> {
//        return safeApiCall(
//            call = { executeResponse(
//                Net.service.thumbsUp(
//                    RequestBodyHelper.createJsonBody(
//                        CommentThumpsUpRequestModel(commentId)
//                    )
//                )
//            )},
//            errorMessage = ""
//        )
//    }


}