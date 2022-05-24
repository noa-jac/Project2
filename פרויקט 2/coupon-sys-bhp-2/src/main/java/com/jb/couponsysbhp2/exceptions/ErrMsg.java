package com.jb.couponsysbhp2.exceptions;

public enum ErrMsg {
    COMPANY_NAME_UPDATE_ATTEMPT("cannot update company name"),
    COMPANY_EMAIL_EXIST("cannot add company with exiting company email"),
    COMPANY_ID_NOT_EXIST("cannot alter company with a non exiting id"),
    CUSTOMER_ID_NOT_EXIST("cannot alter customer with a non exiting id"),
    CUSTOMER_ALREADY_PURCHASE_COUPON("coupon can only been purchase once"),
    COUPON_ID_NOT_EXIST("cannot alter coupon with a non exiting id"),
    EXPIRED_COUPON("this coupon is expired"),
    OUT_OF_STOCK_COUPON("this coupon is run out of stock"),
    CANNOT_ALTER_OTHER_COMPANIES_COUPONS("can only alter coupons from you DB"),
    CUSTOMER_EMAIL_EXIST("cannot add customer with exiting customer email"),
    TITLE_ALREADY_EXIST_IN_COMPANY_DB("company already got coupon with same title, cannot add coupon");

    private String desc;

    ErrMsg(String desc){
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
