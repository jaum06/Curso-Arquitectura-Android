package com.anncode.offersandcoupons.presenter

import com.anncode.offersandcoupons.model.Coupon
import com.anncode.offersandcoupons.model.CouponInteractor
import com.anncode.offersandcoupons.model.CouponInteractorImpl
import com.anncode.offersandcoupons.view.CouponView

class CouponPresenterImpl(var couponView: CouponView): CouponPresenter {

    private var couponInteractor: CouponInteractor = CouponInteractorImpl(this)

    override fun showCoupons(coupons: ArrayList<Coupon>?) {
        couponView.showCoupons(coupons)
    }

    override fun getCoupons() {
        couponInteractor.getCouponsAPI()
    }

}