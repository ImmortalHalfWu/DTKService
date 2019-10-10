package immort.half.wu.beans.params.builds;

import immort.half.wu.beans.params.ParamsProductInfoBean;

public class ParamsProductInfoBeanBuilder {
    private String id;
    private String goodsId;

    public ParamsProductInfoBeanBuilder setId(String id) {
        this.id = id;
        return this;
    }

    public ParamsProductInfoBeanBuilder setGoodsId(String goodsId) {
        this.goodsId = goodsId;
        return this;
    }

    public ParamsProductInfoBean createParamsProductInfoBean() {
        return new ParamsProductInfoBean(id, goodsId);
    }
}