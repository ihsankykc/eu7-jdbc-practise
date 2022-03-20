
package review_with_oscar;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class ZipCode {

    @SerializedName("post code")
    @Expose
    private int postCode;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("country abbreviation")
    @Expose
    private String countryAbbreviation;
    @SerializedName("places")
    @Expose
    private List<Place> places = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ZipCode() {
    }

    /**
     * 
     * @param country
     * @param places
     * @param countryAbbreviation
     * @param postCode
     */
    public ZipCode(int postCode, String country, String countryAbbreviation, List<Place> places) {
        super();
        this.postCode = postCode;
        this.country = country;
        this.countryAbbreviation = countryAbbreviation;
        this.places = places;
    }

    public int getPostCode() {
        return postCode;
    }

    public void setPostCode(int postCode) {
        this.postCode = postCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountryAbbreviation() {
        return countryAbbreviation;
    }

    public void setCountryAbbreviation(String countryAbbreviation) {
        this.countryAbbreviation = countryAbbreviation;
    }

    public List<Place> getPlaces() {
        return places;
    }

    public void setPlaces(List<Place> places) {
        this.places = places;
    }

}
