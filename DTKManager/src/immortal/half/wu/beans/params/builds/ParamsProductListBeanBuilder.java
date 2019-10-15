package immortal.half.wu.beans.params.builds;

import immortal.half.wu.beans.params.ParamsProductListBean;

public class ParamsProductListBeanBuilder {
    private String pageSize = "";
    private String sort = "";
    private String cids = "";
    private String subcid = "";
    private String juHuaSuan = "";
    private String taoQiangGou = "";
    private String tmall = "";
    private String tchaoshi = "";
    private String goldSeller = "";
    private String haitao = "";
    private String pre = "";
    private String brand = "";
    private String brandIds = "";
    private String priceLowerLimit = "";
    private String priceUpperLimit = "";
    private String couponPriceLowerLimit = "";
    private String commissionRateLowerLimit = "";
    private String monthSalesLowerLimit = "";

    public ParamsProductListBeanBuilder setPageSize(String pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public ParamsProductListBeanBuilder setSort(String sort) {
        this.sort = sort;
        return this;
    }

    public ParamsProductListBeanBuilder setCids(String cids) {
        this.cids = cids;
        return this;
    }

    public ParamsProductListBeanBuilder setSubcid(String subcid) {
        this.subcid = subcid;
        return this;
    }

    public ParamsProductListBeanBuilder setJuHuaSuan(String juHuaSuan) {
        this.juHuaSuan = juHuaSuan;
        return this;
    }

    public ParamsProductListBeanBuilder setTaoQiangGou(String taoQiangGou) {
        this.taoQiangGou = taoQiangGou;
        return this;
    }

    public ParamsProductListBeanBuilder setTmall(String tmall) {
        this.tmall = tmall;
        return this;
    }

    public ParamsProductListBeanBuilder setTchaoshi(String tchaoshi) {
        this.tchaoshi = tchaoshi;
        return this;
    }

    public ParamsProductListBeanBuilder setGoldSeller(String goldSeller) {
        this.goldSeller = goldSeller;
        return this;
    }

    public ParamsProductListBeanBuilder setHaitao(String haitao) {
        this.haitao = haitao;
        return this;
    }

    public ParamsProductListBeanBuilder setPre(String pre) {
        this.pre = pre;
        return this;
    }

    public ParamsProductListBeanBuilder setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public ParamsProductListBeanBuilder setBrandIds(String brandIds) {
        this.brandIds = brandIds;
        return this;
    }

    public ParamsProductListBeanBuilder setPriceLowerLimit(String priceLowerLimit) {
        this.priceLowerLimit = priceLowerLimit;
        return this;
    }

    public ParamsProductListBeanBuilder setPriceUpperLimit(String priceUpperLimit) {
        this.priceUpperLimit = priceUpperLimit;
        return this;
    }

    public ParamsProductListBeanBuilder setCouponPriceLowerLimit(String couponPriceLowerLimit) {
        this.couponPriceLowerLimit = couponPriceLowerLimit;
        return this;
    }

    public ParamsProductListBeanBuilder setCommissionRateLowerLimit(String commissionRateLowerLimit) {
        this.commissionRateLowerLimit = commissionRateLowerLimit;
        return this;
    }

    public ParamsProductListBeanBuilder setMonthSalesLowerLimit(String monthSalesLowerLimit) {
        this.monthSalesLowerLimit = monthSalesLowerLimit;
        return this;
    }

    public ParamsProductListBean createParamsProductListBean(String pageId) {
        return new ParamsProductListBean(pageSize, pageId, sort, cids, subcid, juHuaSuan, taoQiangGou, tmall, tchaoshi, goldSeller, haitao, pre, brand, brandIds, priceLowerLimit, priceUpperLimit, couponPriceLowerLimit, commissionRateLowerLimit, monthSalesLowerLimit);
    }
}