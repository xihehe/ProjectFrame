# ProjectFrame

使用方法

	lib_base 用来存放 一些图片选择 号码选择 扫一扫 这种通用的Activity
	lic_common 用来存放公用的工具
	lib_common_view 用来存放公共的view 以及 一些Activity 这类的
	lib_net 网络框架
	lib_common 第三方现在都放在这里，还有一些没放，根据需要自己添加
	集成关系 是 lib_common -> lib_commonview -> lib_net -> lib_base - > lib_Autonomous

本框开发模式为MVVM模式
	
    #lib_Socket 是基于java-websocket的Socket封装

 
    #使用
    public void initMessageWebSocket(String token){
        WebSocketSetting setting = getWeSocketSetting(token);
        //通过 init 方法初始化默认的 WebSocketManager 对象
        WebSocketManager manager = WebSocketHandler.initGeneralWebSocket(SocketHelper.INSTANCE.DEFAULT_SOCKET,setting);
        //启动连接
        manager.start();
        //注意，需要在 AndroidManifest 中配置网络状态获取权限
        //注册网路连接状态变化广播
        WebSocketHandler.registerNetworkChangedReceiver(this);
    }
    @NotNull
    public WebSocketSetting getWeSocketSetting(String token) {
        WebSocketSetting setting = new WebSocketSetting();
        //连接地址，必填，例如 wss://echo.websocket.org
        setting.setConnectUrl();
        //设置连接超时时间
        setting.setConnectTimeout(15 * 1000);
        //设置心跳间隔时间
        setting.setConnectionLostTimeout(40);
        //设置断开后的重连次数，可以设置的很大，不会有什么性能上的影响
        setting.setReconnectFrequency(60);
        //设置消息分发器，接收到数据后先进入该类中处理，处理完再发送到下游
        setting.setResponseProcessDispatcher(new AppResponseDispatcher());
        //接收到数据后是否放入子线程处理，只有设置了 ResponseProcessDispatcher 才有意义
        setting.setProcessDataOnBackground(true);
        //网络状态发生变化后是否重连，
        //需要调用 WebSocketHandler.registerNetworkChangedReceiver(context) 方法注册网络监听广播
        setting.setReconnectWithNetworkChanged(true);
        return setting;
    }
    
然后新建object SocketHelper类，实现 SimpleListener 接口。
      
    const val DEFAULT_SOCKET = "default_socket"
    fun getDefault(): WebSocketManager? {return WebSocketHandler.getWebSocket(DEFAULT_SOCKET) }
    fun reconnect(token: String) {getDefault()?.reconnect(MyApplication.getInstants().getWeSocketSetting(token))}
    fun reconnect() {getDefault()?.reconnect()}
    fun initListener() {getDefault()?.addListener(listener)}
    fun removeListener() {getDefault()?.removeListener(listener)}
    private val listener = object : SimpleListener() {impl todo}
    
    
Http使用
    
    先定义这些
    @Headers(Api.URL_NAME + BASE_DOMAIN_NAME)
    @GET("article/articleMobile")
    suspend fun getArticles(
        @Query("pageNo") pageNo: Int,
        @Query("catalogId") catalogId: String,
        @Query("lastTime") lastTime: Long,
        @Query("pageSize") pageSize: Int = 30
    ): BaseResponse<BaseResponseDataList<TestResponse>>
    
    再调用
    suspend fun getArticles(id: String): IResult<BaseResponseDataList<TestResponse>> {
        return safeApiCall(
            call = { executeResponse(Net.service.getArticles(pageNo = 1,catalogId = id,lastTime = 0L)) },
            errorMessage = ""
        )
    }
    然后实例化
    private val repository by lazy { NewsDetailRepository() }
    在VM下的实现
    fun getTest() {
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                emitUiState(showLoading = true)
            }
            val result = repository.getArticles("0")
            withContext(Dispatchers.Main) {
                if (result is IResult.Success) {
                    val data = result.data
                    if (data?.records?.size == 0) {
                        emitUiState(showError = "123123")
                        return@withContext
                    }
                    emitUiState(
                        showSuccess = data?.records
                    )
                } else if (result is IResult.Error) {
                    LogUtils.e("test","result:${result.toString()}")
                    emitUiState(showError = result.exception.message)
                }
            }
        }
    }
    
    然后再   
    mViewModel.getTest() 调用
    
    通过startObserve 监听数值的变化然后打印出来
    override fun startObserve() {
        super.startObserve()
        mViewModel.uiState.observe(this, Observer {
            it.showError?.let { error ->
                LogUtils.e("test", "showError :$error")
            }
            it.showLoading?.let { loading ->
                LogUtils.e("test", "showError :$loading")
            }
            it.showSuccess?.let { success ->
                LogUtils.e("test", "showError :$success")
            }
        })
    }
    
    
数据存储  因为写了顶层函数，所以有些调用是直接.调用
		
	    存
	    //第一种方式
            Preference<String>().putValue(Preference.USER_JSON, "555555")
            //第二种方式
            var userId by Preference(Preference.USER_ID, "")
            userId = "22"
            //第三种方式
            Preference("").putValue(Preference.TOKEN, "12344444")
            //第四种
            1123123.putData("tttt")
	    
	    取
	    val userInfo by Preference(Preference.USER_JSON, "")
            val userId by Preference(Preference.USER_ID, "")
            val token by Preference(Preference.TOKEN, "")
            val tttt by Preference("tttt", 0)
            val tttt2 = Preference<Long>().getValue("tttt", 0)
	    
	    
扫码
	 RxPermissions(this).request(Manifest.permission.CAMERA)
                .subscribe { aBoolean ->
                    if (aBoolean) {
                        QRCodeScanActivity.start(this, true)
                    }
                }

相册选取
	
	 RxPermissions(this).request(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
                ?.subscribe { aBoolean ->
                    //权限已经开启   enableCrop:是否裁剪
                    if (aBoolean == true) {

                        Matisse.from(this)
                            .choose(MimeType.ofAll(), false)
                            .countable(false)
                            .maxOriginalSize(10 * 1024 * 1024)//最大设置10M每张
                            .originalEnable(true)
                            .theme(R.style.Matisse_Zhihu)
                            .maxSelectable(9)
                            .imageEngine(GlideEngine())
                            .forResult(REQUEST_CODE_CHOOSE)
                    }
                }

区号跳转选择
		startKtxActivityForResult<TelephoneCodeActivity>(BASE_REQUEST_CODE)
	
	
EventBus使用
		
	    发送
		EventBus.getDefault().post(MessageChatEvent("111111","22222").apply {
                putValue("a","aaaaa")
                putValue("b","bbbbb")
                putValue("c",TestEventModels("lili","25"))
                //putValue("d", TestEventModelp("bibi","26"))
            	})
	    
	    接收

	    val message = event as MessageChatEvent
            val content = "msg:${message.target}  ${message.behavior} ${message.getData("a","")}  ${message.getData("b", "")}"
            val content2 = "name:${message.getData("c",TestEventModels()).name}  age: ${message.getData("c",TestEventModels()).age}"
            //val content3 = "name:${message.getData("d",TestEventModelp()).nameP}  age: ${message.getData("d",TestEventModelp()).ageP}"
            LogUtils.e("test", "showEvent :$content  $content2  ")//$content3

等等，还有很多工具，不一一介绍了，代码就在这，随便看


