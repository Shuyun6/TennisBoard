package Data;

/**
 * Created by Shuyun on 2/19/2016/019.
 */
public class ItemBean {
    public String imageUriStr;
    public String name;
    public String height, weight;
    public String phone;
    public String sex;

    public ItemBean(String imageUriStr, String name, String height, String weight, String phone, String sex) {
        this.imageUriStr = imageUriStr;
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.phone = phone;
        this.sex = sex;
    }

}
