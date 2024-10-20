package com.simple.services

import com.simple.config.network
import javax.inject.Inject

class networkService @Inject constructor(private val network: network ) {
    /*
    fun getGeneralInfo(networkServiceCallback: networkServiceCallback<GeneralInfo>?) {
    if (data.generalInfo.totalPlayers != 0) {
        networkServiceCallback?.onSuccess(data.generalInfo)
    } else {
        network._getGeneralInfo().enqueue(object : Callback<GeneralInfo> {
            override fun onResponse(call: retrofit2.Call<GeneralInfo>, response: retrofit2.Response<GeneralInfo>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        networkServiceCallback?.onSuccess(it)
                        data.generalInfo = it
                    } ?: networkServiceCallback?.onFailure()
                } else {
                    networkServiceCallback?.onFailure()
                }
            }
            override fun onFailure(call: retrofit2.Call<GeneralInfo>, t: Throwable) {
                networkServiceCallback?.onFailure()
            }
        })
    }
}
    */
}


//callback for networkService  true or false
// if true return jsonObject else return false
interface networkServiceCallback <T> {
    fun onSuccess(jsonObject: T)
    fun onFailure()
}