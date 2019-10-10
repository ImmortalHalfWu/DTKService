package immort.half.wu.beans.params.builds;

import immort.half.wu.beans.params.ParamsProductSearchBean;

public class ParamsProductSearchBeanBuilder {
    private String tmall;
    private String haitao;
    private String sort;

    public ParamsProductSearchBeanBuilder setTmall(String tmall) {
        this.tmall = tmall;
        return this;
    }

    public ParamsProductSearchBeanBuilder setHaitao(String haitao) {
        this.haitao = haitao;
        return this;
    }

    public ParamsProductSearchBeanBuilder setSort(String sort) {
        this.sort = sort;
        return this;
    }

    public ParamsProductSearchBean createParamsProductSearchBean(int type, int pageId, int pageSize, String keyWords) {
        return new ParamsProductSearchBean(type + "", pageId + "", pageSize + "", keyWords, tmall, haitao, sort);
    }

}