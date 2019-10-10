package immort.half.wu.beans.params.builds;

import immort.half.wu.beans.params.ParamsUrlConveterBean;

public class ParamsUrlConveterBeanBuilder {
    private String couponId;
    private String pid;
    private String channelId;

    public ParamsUrlConveterBeanBuilder setCouponId(String couponId) {
        this.couponId = couponId;
        return this;
    }

    public ParamsUrlConveterBeanBuilder setPid(String pid) {
        this.pid = pid;
        return this;
    }

    public ParamsUrlConveterBeanBuilder setChannelId(String channelId) {
        this.channelId = channelId;
        return this;
    }

    public ParamsUrlConveterBean createParamsUrlConveterBean(String goodsId) {
        return new ParamsUrlConveterBean(goodsId, couponId, pid, channelId);
    }
}