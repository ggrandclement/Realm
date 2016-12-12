package com.example.guillaume_grand_clement.herowars.network.request;

import android.content.Context;

import rx.Observable;

public abstract class AbsRequest<T> {

    //region Constants *****************************************************************************

    //endregion

    //region Fields ********************************************************************************

    protected final Context mContext;

    //endregion

    //region Override Methods **********************************************************************

    //endregion

    //region Public Methods ************************************************************************

    public AbsRequest(Context context) {
        mContext = context;
    }

    public abstract Observable<T> asObservable();

    //endregion

    //region Protected Methods *********************************************************************

    //endregion

    //region Private Methods ***********************************************************************

    //endregion

    //region Inner Classes or Interfaces ***********************************************************

    //endregion
}
