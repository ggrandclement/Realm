package com.example.guillaume_grand_clement.herowars.network.request.impl;

import android.content.Context;

import com.example.guillaume_grand_clement.herowars.data.pojo.SamplePojo;
import com.example.guillaume_grand_clement.herowars.network.client.WebClient;
import com.example.guillaume_grand_clement.herowars.network.request.AbsRequest;

import rx.Observable;

public class SampleRequest extends AbsRequest<SamplePojo> {

    //region Constants *****************************************************************************

    //endregion

    //region Fields ********************************************************************************

    //endregion

    //region Override Methods **********************************************************************

    //endregion

    //region Public Methods ************************************************************************

    public SampleRequest(Context context) {
        super(context);
    }

    @Override
    public Observable<SamplePojo> asObservable() {
        return WebClient.getService(mContext).sampleService().asObservable();
    }
    //endregion

    //region Protected Methods *********************************************************************

    //endregion

    //region Private Methods ***********************************************************************

    //endregion

    //region Inner Classes or Interfaces ***********************************************************

    //endregion
}
