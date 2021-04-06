package com.yumeng.libbaseProject.service.repository

import com.yumeng.libbaseProject.Contants.Constants
import com.yumeng.libbaseProject.dataSupport.AddFriendBean
import com.yumeng.libbaseProject.dataSupport.FriendInfo
import com.yumeng.libbaseProject.dataSupport.GroupInfo
import com.yumeng.libbaseProject.dataSupport.RoomSettingRecords
import com.yumeng.libbaseProject.model.*
import com.yumeng.libbaseProject.service.ResponseModel.AddFriendResponse
import com.yumeng.libbaseProject.service.ResponseModel.LoginStatusResponse
import com.yumeng.libbaseProject.service.http.Net
import com.yumeng.libcore.IResult
import com.yumeng.libcore.base.BaseRepository
import com.yumeng.libcore.response.BaseResponse
import com.yumeng.libcore.utils.RequestBodyHelper

class UserRepository : BaseRepository() {

    suspend fun getCodeFromNetWork(mobile: String, type: String, dialCode: String): IResult<Any> {
        return safeApiCall(
            call = {
                executeResponse(
                    Net.service.getCodeFromNetWork(
                        RequestBodyHelper.createJsonBody(
                            SmsModel(mobile, type, dialCode)
                        )
                    )
                )
            },
            errorMessage = ""
        )
    }

    suspend fun getMsgCode(mobile: String, type: String, dialCode: String): IResult<Any> {
        return safeApiCall(
            call = {
                executeResponse(
                    Net.service.getMsgCode(
                        RequestBodyHelper.createJsonBody(
                            SmsModel(mobile, type, dialCode)
                        )
                    )
                )
            },
            errorMessage = ""
        )
    }

    suspend fun login(mobile: String, smsCode: String, dialCode: String): IResult<UserInfo> {
        return safeApiCall(
            call = {
                executeResponse(
                    Net.service.login(
                        RequestBodyHelper.createJsonBody(
                            LoginModel(mobile, smsCode, dialCode)
                        )
                    )
                )
            },
            errorMessage = ""
        )
    }

    suspend fun logout(): IResult<Any> {
        return safeApiCall(
            call = {
                executeResponse(
                    Net.service.logout()
                )
            },
            errorMessage = ""
        )
    }

    suspend fun getUserInfo(): IResult<UserInfo> {
        return safeApiCall(
            call = {
                executeResponse(
                    Net.service.getUserInfo()
                )
            },
            errorMessage = ""
        )
    }

    suspend fun complaintFriend(id: String?, content: String?, type: Int): IResult<Any> {
        return safeApiCall(
            call = {
                executeResponse(
                    Net.service.complaintFriend(
                        RequestBodyHelper.createJsonBody(
                            ReportModel(content, id, type)
                        )
                    )
                )
            },
            errorMessage = ""
        )
    }

    suspend fun feedBack(content: String): IResult<Any> {
        return safeApiCall(
            call = {
                executeResponse(
                    Net.service.feedback(
                        RequestBodyHelper.createJsonBody(
                            FeedbackModel(content)
                        )
                    )
                )
            },
            errorMessage = ""
        )
    }

    suspend fun getBlackList(): IResult<List<FriendInfo>> {
        return safeApiCall(
            call = {
                executeResponse(
                    Net.service.getBlackList()
                )
            },
            errorMessage = ""
        )
    }

    suspend fun updateBlackList(friendId: String): IResult<FriendInfo> {
        return safeApiCall(
            call = {
                val param = HashMap<String, Any>()
                param["friendId"] = friendId
                executeResponse(
                    Net.service.updateBlackList(
                        RequestBodyHelper.createJsonBody(
                            param
                        )
                    )
                )
            },
            errorMessage = ""
        )
    }


    suspend fun getRoomSettingList(): IResult<List<RoomSettingRecords>> {
        return safeApiCall(
            call = {
                executeResponse(
                    Net.service.getRoomSettingList()
                )
            },
            errorMessage = ""
        )
    }


    suspend fun friendRequest(requestId: String): IResult<Any> {
        return safeApiCall(
            call = {
                val param = HashMap<String, Any>()
                param["requestId"] = requestId
                executeResponse(
                    Net.service.friendRequest(
                        RequestBodyHelper.createJsonBody(
                            param
                        )
                    )
                )
            },
            errorMessage = ""
        )
    }

    suspend fun qrLoginReject(uuid: String, type: String? = "qrLogin"): IResult<Any> {
        return safeApiCall(
            call = {
                val param = HashMap<String, Any>()
                param["content"] = uuid
                param["type"] = type!!
                executeResponse(
                    Net.service.qrLoginReject(
                        RequestBodyHelper.createJsonBody(
                            param
                        )
                    )
                )
            },
            errorMessage = ""
        )
    }

    suspend fun qrScan(uuid: String, type: String? = "qrLogin"): IResult<Any> {
        return safeApiCall(
            call = {
                val param = HashMap<String, Any>()
                param["content"] = uuid
                param["type"] = type!!
                executeResponse(
                    Net.service.qrScan(
                        RequestBodyHelper.createJsonBody(
                            param
                        )
                    )
                )
            },
            errorMessage = ""
        )
    }

    suspend fun qrLoginAgree(uuid: String, type: String? = "qrLogin"): IResult<Any> {
        return safeApiCall(
            call = {
                val param = HashMap<String, Any>()
                param["content"] = uuid
                param["type"] = type!!
                executeResponse(
                    Net.service.qrLoginAgree(
                        RequestBodyHelper.createJsonBody(
                            param
                        )
                    )
                )
            },
            errorMessage = ""
        )
    }


    suspend fun checkUserLoginStatus(): IResult<LoginStatusResponse> {
        return safeApiCall(
            call = {
                executeResponse(
                    Net.service.checkUserLoginStatus()
                )
            },
            errorMessage = ""
        )
    }

    ////强制下线
    suspend fun webLoginExit(): IResult<Any> {
        return safeApiCall(
            call = {
                executeResponse(
                    Net.service.webLoginExit()
                )
            },
            errorMessage = ""
        )
    }

    suspend fun shieldFriendTrends(model: FeedSheildSetModel): IResult<Any> {
        return safeApiCall(
            call = {
                executeResponse(
                    Net.service.shieldFriendTrends(
                        RequestBodyHelper.createJsonBody(
                            model
                        )
                    )
                )
            },
            errorMessage = ""
        )
    }

    suspend fun searchUser(
        friendId: String? = null,
        queryMobile: String? = null,
        userId: String? = null
    ): IResult<AddFriendBean> {
        return safeApiCall(
            call = {
                val param = HashMap<String, Any>()
                param["mobile"] = queryMobile!!
                param["userId"] = userId!!
                param["friendId"] = friendId!!
                executeResponse(
                    Net.service.searchUser(
                        RequestBodyHelper.createJsonBody(
                            param
                        )
                    )
                )
            },
            errorMessage = ""
        )
    }

    suspend fun modifyId(uid: String): IResult<Any> {
        return safeApiCall(
            call = {
                val param = HashMap<String, Any>()
                param["uid"] = uid
                executeResponse(
                    Net.service.modifyId(
                        RequestBodyHelper.createJsonBody(
                            param
                        )
                    )
                )
            },
            errorMessage = ""
        )
    }

    suspend fun searchFriendV1214(keyword: String):IResult<AddFriendBean>{
        return safeApiCall(
            call = {
                val param = HashMap<String, Any>()
                param["keyword"] = keyword
                executeResponse(
                    Net.service.searchFriendV1214(
                        RequestBodyHelper.createJsonBody(
                            param
                        )
                    )
                )
            },
            errorMessage = ""
        )
    }


    suspend fun disbandGroup(roomId: String):IResult<GroupInfo>{
        return safeApiCall(
            call = {
                val param = HashMap<String, Any>()
                param[Constants.RoomId] = roomId
                executeResponse(
                    Net.service.disbandGroup(
                        RequestBodyHelper.createJsonBody(
                            param
                        )
                    )
                )
            },
            errorMessage = ""
        )
    }


    suspend fun addFriendRequest(mark: String, receiveId: String,source:Int):IResult<AddFriendResponse>{
        return safeApiCall(
            call = {
                executeResponse(
                    Net.service.addFriendRequest(
                        RequestBodyHelper.createJsonBody(
                            AddFriendModel(receiveId, mark,source)
                        )
                    )
                )
            },
            errorMessage = ""
        )
    }

}