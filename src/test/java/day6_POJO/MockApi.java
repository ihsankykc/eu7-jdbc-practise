
package day6_POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MockApi {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("phone")
    @Expose
    private String phone;

    /**
     * No args constructor for use in serialization
     * 
     */
    public MockApi() {
    }

    /**
     * 
     * @param gender
     * @param phone
     * @param name
     */
    public MockApi(String name, String gender, String phone) {
        super();
        this.name = name;
        this.gender = gender;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
