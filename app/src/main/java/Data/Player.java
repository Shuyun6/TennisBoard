package Data;

/**
 * Created by Shuyun on 2/12/2016/012.
 */
public class Player {
    private String image = null;
    private String name = null;
    private String sex = null;
    private String birth = null;
    private String email = null;
    private String phone = null;
    private String height = null, weight = null;

    public Player(String name) {
        this.name = name;
    }

    public Player(String name, String sex){
        this.name=name;
        this.sex=sex;
    }

    public Player(String image, String name, String sex, String birth, String email, String phone, String height, String weight) {
        this.image = image;
        this.name = name;
        this.sex = sex;
        this.birth = birth;
        this.email = email;
        this.phone = phone;
        this.height = height;
        this.weight = weight;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public void setHeightWeight(String height, String weight) {
        this.height = height;
        this.weight = weight;
    }


}
