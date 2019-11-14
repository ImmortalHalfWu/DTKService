package immortal.half.wu.apps.IdleFish.beans;

public class UserInfoBean {

    private final String name;
    private final String postedNum;

    public UserInfoBean(String name, String postedNum) {
        this.name = name;
        this.postedNum = postedNum;
    }

    @Override
    public String toString() {
        return "UserInfoBean{" +
                "name='" + name + '\'' +
                ", postedNum='" + postedNum + '\'' +
                '}';
    }
}
