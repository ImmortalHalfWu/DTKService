package immotal.half.wu.appManager.pagers.beans;

public class UserInfoBean {

    private final String name;
    private final String postedNum;

    public UserInfoBean(String name, String postedNum) {
        this.name = name;
        this.postedNum = postedNum;
    }

    public String getName() {
        return name;
    }

    public String getPostedNum() {
        return postedNum;
    }

    @Override
    public String toString() {
        return "UserInfoBean{" +
                "name='" + name + '\'' +
                ", postedNum='" + postedNum + '\'' +
                '}';
    }
}
