

package com.ibm.mobileappbuilder.livelator20161017172308.ds;

import android.content.Context;

import java.net.URL;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import ibmmobileappbuilder.ds.SearchOptions;
import ibmmobileappbuilder.ds.restds.AppNowDatasource;
import ibmmobileappbuilder.util.StringUtils;
import ibmmobileappbuilder.ds.restds.TypedByteArrayUtils;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * "UserDbDS" data source. (e37eb8dc-6eb2-4635-8592-5eb9696050e3)
 */
public class UserDbDS extends AppNowDatasource<UserDbDSItem>{

    // default page size
    private static final int PAGE_SIZE = 20;

    private UserDbDSService service;

    public static UserDbDS getInstance(SearchOptions searchOptions){
        return new UserDbDS(searchOptions);
    }

    private UserDbDS(SearchOptions searchOptions) {
        super(searchOptions);
        this.service = UserDbDSService.getInstance();
    }

    @Override
    public void getItem(String id, final Listener<UserDbDSItem> listener) {
        if ("0".equals(id)) {
                        getItems(new Listener<List<UserDbDSItem>>() {
                @Override
                public void onSuccess(List<UserDbDSItem> items) {
                    if(items != null && items.size() > 0) {
                        listener.onSuccess(items.get(0));
                    } else {
                        listener.onSuccess(new UserDbDSItem());
                    }
                }

                @Override
                public void onFailure(Exception e) {
                    listener.onFailure(e);
                }
            });
        } else {
                      service.getServiceProxy().getUserDbDSItemById(id, new Callback<UserDbDSItem>() {
                @Override
                public void success(UserDbDSItem result, Response response) {
                                        listener.onSuccess(result);
                }

                @Override
                public void failure(RetrofitError error) {
                                        listener.onFailure(error);
                }
            });
        }
    }

    @Override
    public void getItems(final Listener<List<UserDbDSItem>> listener) {
        getItems(0, listener);
    }

    @Override
    public void getItems(int pagenum, final Listener<List<UserDbDSItem>> listener) {
        String conditions = getConditions(searchOptions, getSearchableFields());
        int skipNum = pagenum * PAGE_SIZE;
        String skip = skipNum == 0 ? null : String.valueOf(skipNum);
        String limit = PAGE_SIZE == 0 ? null: String.valueOf(PAGE_SIZE);
        String sort = getSort(searchOptions);
                service.getServiceProxy().queryUserDbDSItem(
                skip,
                limit,
                conditions,
                sort,
                null,
                null,
                new Callback<List<UserDbDSItem>>() {
            @Override
            public void success(List<UserDbDSItem> result, Response response) {
                                listener.onSuccess(result);
            }

            @Override
            public void failure(RetrofitError error) {
                                listener.onFailure(error);
            }
        });
    }

    private String[] getSearchableFields() {
        return new String[]{"username", "password"};
    }

    // Pagination

    @Override
    public int getPageSize(){
        return PAGE_SIZE;
    }

    @Override
    public void getUniqueValuesFor(String searchStr, final Listener<List<String>> listener) {
        String conditions = getConditions(searchOptions, getSearchableFields());
                service.getServiceProxy().distinct(searchStr, conditions, new Callback<List<String>>() {
             @Override
             public void success(List<String> result, Response response) {
                                  result.removeAll(Collections.<String>singleton(null));
                 listener.onSuccess(result);
             }

             @Override
             public void failure(RetrofitError error) {
                                  listener.onFailure(error);
             }
        });
    }

    @Override
    public URL getImageUrl(String path) {
        return service.getImageUrl(path);
    }

    @Override
    public void create(UserDbDSItem item, Listener<UserDbDSItem> listener) {
                    
        if(item.imageUri != null){
            service.getServiceProxy().createUserDbDSItem(item,
                TypedByteArrayUtils.fromUri(item.imageUri),
                callbackFor(listener));
        }
        else
            service.getServiceProxy().createUserDbDSItem(item, callbackFor(listener));
        
    }

    private Callback<UserDbDSItem> callbackFor(final Listener<UserDbDSItem> listener) {
      return new Callback<UserDbDSItem>() {
          @Override
          public void success(UserDbDSItem item, Response response) {
                            listener.onSuccess(item);
          }

          @Override
          public void failure(RetrofitError error) {
                            listener.onFailure(error);
          }
      };
    }

    @Override
    public void updateItem(UserDbDSItem item, Listener<UserDbDSItem> listener) {
                    
        if(item.imageUri != null){
            service.getServiceProxy().updateUserDbDSItem(item.getIdentifiableId(),
                item,
                TypedByteArrayUtils.fromUri(item.imageUri),
                callbackFor(listener));
        }
        else
            service.getServiceProxy().updateUserDbDSItem(item.getIdentifiableId(), item, callbackFor(listener));
        
    }

    @Override
    public void deleteItem(UserDbDSItem item, final Listener<UserDbDSItem> listener) {
                service.getServiceProxy().deleteUserDbDSItemById(item.getIdentifiableId(), new Callback<UserDbDSItem>() {
            @Override
            public void success(UserDbDSItem result, Response response) {
                                listener.onSuccess(result);
            }

            @Override
            public void failure(RetrofitError error) {
                                listener.onFailure(error);
            }
        });
    }

    @Override
    public void deleteItems(List<UserDbDSItem> items, final Listener<UserDbDSItem> listener) {
                service.getServiceProxy().deleteByIds(collectIds(items), new Callback<List<UserDbDSItem>>() {
            @Override
            public void success(List<UserDbDSItem> item, Response response) {
                                listener.onSuccess(null);
            }

            @Override
            public void failure(RetrofitError error) {
                                listener.onFailure(error);
            }
        });
    }

    protected List<String> collectIds(List<UserDbDSItem> items){
        List<String> ids = new ArrayList<>();
        for(UserDbDSItem item: items){
            ids.add(item.getIdentifiableId());
        }
        return ids;
    }

}
