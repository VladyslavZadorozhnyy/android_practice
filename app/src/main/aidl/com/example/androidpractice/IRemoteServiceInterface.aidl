// IRemoteServiceInterface.aidl
package com.example.androidpractice;

import com.example.androidpractice.ObjectToBePassedParcellable;
import com.example.androidpractice.ObjectToBeRetrievedParcellable;


interface IRemoteServiceInterface {
    void passToService(in ObjectToBeRetrievedParcellable obj);

    ObjectToBePassedParcellable passFromService();
}