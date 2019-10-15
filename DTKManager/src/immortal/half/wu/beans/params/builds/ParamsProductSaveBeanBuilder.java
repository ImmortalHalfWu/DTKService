package immortal.half.wu.beans.params.builds;

import immortal.half.wu.beans.params.ParamsProductSaveBean;

public class ParamsProductSaveBeanBuilder {
    private String pageSize;
    private String cid;
    private String trailerType;
    private String sort;
    private String collectionTimeOrder;

    public ParamsProductSaveBeanBuilder setPageSize(String pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public ParamsProductSaveBeanBuilder setCid(String cid) {
        this.cid = cid;
        return this;
    }

    public ParamsProductSaveBeanBuilder setTrailerType(String trailerType) {
        this.trailerType = trailerType;
        return this;
    }

    public ParamsProductSaveBeanBuilder setSort(String sort) {
        this.sort = sort;
        return this;
    }

    public ParamsProductSaveBeanBuilder setCollectionTimeOrder(String collectionTimeOrder) {
        this.collectionTimeOrder = collectionTimeOrder;
        return this;
    }

    public ParamsProductSaveBean createParamsProductSaveBean(String pageId) {
        return new ParamsProductSaveBean(pageSize, pageId, cid, trailerType, sort, collectionTimeOrder);
    }
}