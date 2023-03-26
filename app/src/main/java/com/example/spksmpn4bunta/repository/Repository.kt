package com.example.spksmpn4bunta.repository
import com.example.spksmpn4bunta.datastore.Data
import com.example.spksmpn4bunta.datastore.DataStore
import com.example.spksmpn4bunta.model.datadiri.DataDiriReq
import com.example.spksmpn4bunta.model.auth.LoginReq
import com.example.spksmpn4bunta.model.auth.RegistReq
import com.example.spksmpn4bunta.model.Users
import com.example.spksmpn4bunta.model.alternatif.PostAlternatifReq
import com.example.spksmpn4bunta.model.auth.ChangeRegistReq
import com.example.spksmpn4bunta.service.ApiHelper
import kotlinx.coroutines.flow.Flow

class Repository(
    private val apiHelper: ApiHelper,
    private val dataStore: DataStore
) {
    suspend fun postRegist(requestBody: RegistReq)= apiHelper.postRegist(requestBody)
    suspend fun postLogin(requestBody: LoginReq)= apiHelper.postLogin(requestBody)
    suspend fun putRegist(token: String, requestBody: ChangeRegistReq) =
        apiHelper.putRegist(token, requestBody)

    //datastore
    suspend fun setDatastore(user: Users) = dataStore.setToken(user)
    suspend fun saveUserPref(user: Data)=dataStore.saveUser(user)
    suspend fun getUserPref () : Flow<Data> = dataStore.getUser()
    suspend fun deleteDataStore()=dataStore.delete()
    suspend fun getToken():Flow<Users> = dataStore.getToken()

    // data diri
//    suspend fun getDataDiri(token : String) = apiHelper.getDataDiri(token)
    suspend fun postDataDiri( token: String, requestBody: DataDiriReq) = apiHelper.postDataDiri(token,requestBody)
    suspend fun putDataDiri(token:String,requestBody: DataDiriReq)=apiHelper.putDataDiri(token, requestBody)
    suspend fun getNilaiSaw(token:String)=apiHelper.getNilaiSaw(token)

    suspend fun getUsers(token:String)=apiHelper.getUsers(token)
    suspend fun putActivated(token: String, id:Int) = apiHelper.putActivated(token, id)

    //nilai
    suspend fun postNilai(token: String, req: PostAlternatifReq) =
        apiHelper.postNilai(token, req)

    //detail
    suspend fun getDetail(token: String, id: Int) =
        apiHelper.getDetail(token, id)

    suspend fun getDetail2(token: String, id: Int) =
        apiHelper.getDetail2(token, id)

    suspend fun deleteDetail(token: String, id: Int) =
        apiHelper.deleteDetail(token, id)

//    // login and register
//    suspend fun postRegUser(requestBody: RegistReq) = apiHelper.postRegUser(requestBody)
//    suspend fun postLogin(requestBody: LoginReq) = apiHelper.postLoginUser(requestBody)
//    suspend fun getAllProduct(status:String?=null,categoryId:String?=null,search:String?=null) = apiHelper.getAllProduct(status,categoryId,search)
//    suspend fun getAllCategory() = apiHelper.getAllCategory()
//    suspend fun setDatastore(user: User) = dataStore.setToken(user)
//    suspend fun getDatastore() : Flow<User> = dataStore.getToken()
//    suspend fun deleteToken() = dataStore.delete()
//    suspend fun getDataUser(token: String) = apiHelper.getDataUser(token)
//    suspend fun updateDataUser(
//        token : String,
//        image: MultipartBody.Part?,
//        name: RequestBody?,
//        phoneNumber: RequestBody?,
//        address: RequestBody?,
//        city: RequestBody?,
//    ) = apiHelper.updateUser(token, image, name, phoneNumber, address, city)
//    //notification
//    suspend fun getNotif(token :String) = apiHelper.getNotif(token)
//    //details
//    suspend fun getDetail(id:Int) = apiHelper.getDetail(id)
//    suspend fun getSellerDetailProduct(token: String,id:Int) = apiHelper.getSellerProductDetail(token,id)
//
//    suspend fun postProduct(
//        token: String,
//        file : MultipartBody.Part?,
//        name: RequestBody?,
//        description: RequestBody?,
//        base_price: RequestBody?,
//        categoryIds: List<Int>?,
//        location: RequestBody?,
//    ) = apiHelper.postProduct(token, file, name, description, base_price, categoryIds, location)
//    //buyer history
//    suspend fun getBuyerHistory(token :String) = apiHelper.getBuyerHistory(token)
//    //seller product
//    suspend fun getSellerProduct(token :String) = apiHelper.getSellerProduct(token)
//    //seller order
//    suspend fun getSellerOrder(token :String) = apiHelper.getSellerOrder(token)
//    //post Bid
//    suspend fun postOrder(token: String,requestBody: PostOrderReq)= apiHelper.postOrder(token, requestBody)
//    //search section
//    suspend fun getSearchHistory() = dbHelper.getSearchHistory()
//
//    suspend fun insertSearchHistory(searchHistoryEntity: SearchHistoryEntity) =
//        dbHelper.insertSearchHistory(searchHistoryEntity)
//
//
//    suspend fun settingAccount(token: String, requestBody: SettingAccountRequest) = apiHelper.settingAccount(token,requestBody)
//
//
//    // wishlist
//    suspend fun getWishlist(token: String) = apiHelper.getWishlist(token)
//    suspend fun postWishlist(token: String, request: PostWishlistRequest) = apiHelper.postWishlist(token, request)
//    suspend fun deleteWishlist(token: String, id: Int) = apiHelper.deleteWishlist(token, id)
//
//
//
//    fun getProductStream(categoryId: Int? = null): Flow<PagingData<Product>> {
//
//        val pagingSourceFactory = { database.productDao().getProduct() }
//
//        @OptIn(ExperimentalPagingApi::class)
//        return Pager(
//            config = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false),
//            remoteMediator = ProductRemoteMediator(database, apiHelper, categoryId),
//            pagingSourceFactory = pagingSourceFactory
//        ).flow
//
//    }
//    suspend fun patchSellerOrderId(token: String,id:Int, request: PatchSellerOrderReq) = apiHelper.patchSellerOrderId(token,id, request)
//    suspend fun patchSellerProductId(token: String,id:Int, request: PatchSellerOrderReq) = apiHelper.patchSellerProductId(token,id, request)
//    suspend fun deleteSellerProductId(token: String,id: Int) = apiHelper.deleteSellerProduct(token,id)
//    suspend fun getSellerOrderId(token: String,id:Int) = apiHelper.getSellerOrderId(token,id)
//
//    suspend fun editProduct(
//        token: String,
//        id: Int,
//        file: MultipartBody.Part?,
//        name: RequestBody?,
//        description: RequestBody?,
//        base_price: RequestBody?,
//        categoryIds: List<Int>?,
//        location: RequestBody?,
//    ) = apiHelper.editProduct(token,id, file, name, description, base_price, categoryIds, location)
//
//    suspend fun putOrder(token: String,id:Int,requestBody: PutOrderReq)= apiHelper.putOrder(token,id,requestBody)
//    suspend fun deleteOrder(token: String,id: Int) = apiHelper.deleteOrder(token, id)
//
//    companion object{
//        const val PAGE_SIZE = 20
//    }
}
