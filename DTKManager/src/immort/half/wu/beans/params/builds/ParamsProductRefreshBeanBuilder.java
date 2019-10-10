package immort.half.wu.beans.params.builds;

import immort.half.wu.beans.params.ParamsProductRefreshBean;

public class ParamsProductRefreshBeanBuilder {
    private String startTime;
    private String endTime;
    private String cids;
    private String subcid;
    private String juHuaSuan;
    private String taoQiangGou;
    private String tmall;
    private String tchaoshi;
    private String goldSeller;
    private String haitao;
    private String brand;
    private String brandIds;
    private String priceLowerLimit;
    private String priceUpperLimit;
    private String couponPriceLowerLimit;
    private String commissionRateLowerLimit;
    private String monthSalesLowerLimit;
    private String sort;

    public ParamsProductRefreshBeanBuilder setStartTime(String startTime) {
        this.startTime = startTime;
        return this;
    }

    public ParamsProductRefreshBeanBuilder setEndTime(String endTime) {
        this.endTime = endTime;
        return this;
    }

    public ParamsProductRefreshBeanBuilder setCids(String cids) {
        this.cids = cids;
        return this;
    }

    public ParamsProductRefreshBeanBuilder setSubcid(String subcid) {
        this.subcid = subcid;
        return this;
    }

    public ParamsProductRefreshBeanBuilder setJuHuaSuan(String juHuaSuan) {
        this.juHuaSuan = juHuaSuan;
        return this;
    }

    public ParamsProductRefreshBeanBuilder setTaoQiangGou(String taoQiangGou) {
        this.taoQiangGou = taoQiangGou;
        return this;
    }

    public ParamsProductRefreshBeanBuilder setTmall(String tmall) {
        this.tmall = tmall;
        return this;
    }

    public ParamsProductRefreshBeanBuilder setTchaoshi(String tchaoshi) {
        this.tchaoshi = tchaoshi;
        return this;
    }

    public ParamsProductRefreshBeanBuilder setGoldSeller(String goldSeller) {
        this.goldSeller = goldSeller;
        return this;
    }

    public ParamsProductRefreshBeanBuilder setHaitao(String haitao) {
        this.haitao = haitao;
        return this;
    }

    public ParamsProductRefreshBeanBuilder setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public ParamsProductRefreshBeanBuilder setBrandIds(String brandIds) {
        this.brandIds = brandIds;
        return this;
    }

    public ParamsProductRefreshBeanBuilder setPriceLowerLimit(String priceLowerLimit) {
        this.priceLowerLimit = priceLowerLimit;
        return this;
    }

    public ParamsProductRefreshBeanBuilder setPriceUpperLimit(String priceUpperLimit) {
        this.priceUpperLimit = priceUpperLimit;
        return this;
    }

    public ParamsProductRefreshBeanBuilder setCouponPriceLowerLimit(String couponPriceLowerLimit) {
        this.couponPriceLowerLimit = couponPriceLowerLimit;
        return this;
    }

    public ParamsProductRefreshBeanBuilder setCommissionRateLowerLimit(String commissionRateLowerLimit) {
        this.commissionRateLowerLimit = commissionRateLowerLimit;
        return this;
    }

    public ParamsProductRefreshBeanBuilder setMonthSalesLowerLimit(String monthSalesLowerLimit) {
        this.monthSalesLowerLimit = monthSalesLowerLimit;
        return this;
    }

    public ParamsProductRefreshBeanBuilder setSort(String sort) {
        this.sort = sort;
        return this;
    }

    public ParamsProductRefreshBean createParamsProductRefreshBean(int pageSize, int pageId) {
        return new ParamsProductRefreshBean(pageSize + "", pageId + "", startTime, endTime, cids, subcid, juHuaSuan, taoQiangGou, tmall, tchaoshi, goldSeller, haitao, brand, brandIds, priceLowerLimit, priceUpperLimit, couponPriceLowerLimit, commissionRateLowerLimit, monthSalesLowerLimit, sort);
    }
}