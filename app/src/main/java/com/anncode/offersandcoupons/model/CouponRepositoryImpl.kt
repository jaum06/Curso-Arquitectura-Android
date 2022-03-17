package com.anncode.offersandcoupons.model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CouponRepositoryImpl : CouponRepository {

    private var coupons = MutableLiveData<List<Coupon>>()
    //Subject MutableLiveData
    //Observers List Coupon
    //Change List Coupon - MutableLiveData
    //Observe

    override fun getCoupons(): MutableLiveData<List<Coupon>>{
        return coupons
    }

    // Toda la logica de conexion
    override fun callCouponsAPI() {
        // CONTROLLER
        val couponsList: ArrayList<Coupon>? = ArrayList<Coupon>()
        val apiAdapter = ApiAdapter()
        val apiService = apiAdapter.getClientService()
        val call = apiService.getCoupons()

        call.enqueue(object : Callback<JsonObject> {
            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                t.message?.let { Log.e("ERROR: ", it) }
                t.stackTrace
            }

            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                val offersJsonArray = response.body()?.getAsJsonArray("offers")
                offersJsonArray?.forEach { jsonElement: JsonElement ->
                    val jsonObject = jsonElement.asJsonObject
                    val coupon = Coupon(jsonObject)
                    couponsList?.add(coupon)
                }
                coupons.value = couponsList
            }
        })
        // CONTROLLER

    }
}