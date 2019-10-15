package immortal.half.wu.beans.params.builds;

import immortal.half.wu.beans.params.ParamsProduct9Bean;

public class ParamsProduct9BeanBuilder {

    public ParamsProduct9Bean createParamsProduct9Bean(String pageSize, String pageId, String nineCid) {
        return new ParamsProduct9Bean(pageSize, pageId, nineCid);
    }
}