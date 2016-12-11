
package com.ibm.mobileappbuilder.livelator20161017172308.ds;
import java.util.List;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;
import retrofit.http.POST;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.Path;
import retrofit.http.PUT;
import retrofit.mime.TypedByteArray;
import retrofit.http.Part;
import retrofit.http.Multipart;

public interface UserDbDSServiceRest{

	@GET("/app/58050d7167a7520300308e1f/r/userDbDS")
	void queryUserDbDSItem(
		@Query("skip") String skip,
		@Query("limit") String limit,
		@Query("conditions") String conditions,
		@Query("sort") String sort,
		@Query("select") String select,
		@Query("populate") String populate,
		Callback<List<UserDbDSItem>> cb);

	@GET("/app/58050d7167a7520300308e1f/r/userDbDS/{id}")
	void getUserDbDSItemById(@Path("id") String id, Callback<UserDbDSItem> cb);

	@DELETE("/app/58050d7167a7520300308e1f/r/userDbDS/{id}")
  void deleteUserDbDSItemById(@Path("id") String id, Callback<UserDbDSItem> cb);

  @POST("/app/58050d7167a7520300308e1f/r/userDbDS/deleteByIds")
  void deleteByIds(@Body List<String> ids, Callback<List<UserDbDSItem>> cb);

  @POST("/app/58050d7167a7520300308e1f/r/userDbDS")
  void createUserDbDSItem(@Body UserDbDSItem item, Callback<UserDbDSItem> cb);

  @PUT("/app/58050d7167a7520300308e1f/r/userDbDS/{id}")
  void updateUserDbDSItem(@Path("id") String id, @Body UserDbDSItem item, Callback<UserDbDSItem> cb);

  @GET("/app/58050d7167a7520300308e1f/r/userDbDS")
  void distinct(
        @Query("distinct") String colName,
        @Query("conditions") String conditions,
        Callback<List<String>> cb);
    
    @Multipart
    @POST("/app/58050d7167a7520300308e1f/r/userDbDS")
    void createUserDbDSItem(
        @Part("data") UserDbDSItem item,
        @Part("image") TypedByteArray image,
        Callback<UserDbDSItem> cb);
    
    @Multipart
    @PUT("/app/58050d7167a7520300308e1f/r/userDbDS/{id}")
    void updateUserDbDSItem(
        @Path("id") String id,
        @Part("data") UserDbDSItem item,
        @Part("image") TypedByteArray image,
        Callback<UserDbDSItem> cb);
}
