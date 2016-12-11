
package com.ibm.mobileappbuilder.livelator20161017172308.ds;
import java.net.URL;
import com.ibm.mobileappbuilder.livelator20161017172308.R;
import ibmmobileappbuilder.ds.RestService;
import ibmmobileappbuilder.util.StringUtils;

/**
 * "UserDbDSService" REST Service implementation
 */
public class UserDbDSService extends RestService<UserDbDSServiceRest>{

    public static UserDbDSService getInstance(){
          return new UserDbDSService();
    }

    private UserDbDSService() {
        super(UserDbDSServiceRest.class);

    }

    @Override
    public String getServerUrl() {
        return "https://ibm-pods.buildup.io";
    }

    @Override
    protected String getApiKey() {
        return "1WynqywN";
    }

    @Override
    public URL getImageUrl(String path){
        return StringUtils.parseUrl("https://ibm-pods.buildup.io/app/58050d7167a7520300308e1f",
                path,
                "apikey=1WynqywN");
    }

}
