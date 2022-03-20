
package review_with_oscar;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Place {

    @SerializedName("place name")
    @Expose
    private String placeName;
    @SerializedName("longitude")
    @Expose
    private double longitude;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("state abbreviation")
    @Expose
    private String stateAbbreviation;
    @SerializedName("latitude")
    @Expose
    private double latitude;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Place() {
    }

    /**
     * 
     * @param stateAbbreviation
     * @param latitude
     * @param state
     * @param placeName
     * @param longitude
     */
    public Place(String placeName, double longitude, String state, String stateAbbreviation, double latitude) {
        super();
        this.placeName = placeName;
        this.longitude = longitude;
        this.state = state;
        this.stateAbbreviation = stateAbbreviation;
        this.latitude = latitude;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStateAbbreviation() {
        return stateAbbreviation;
    }

    public void setStateAbbreviation(String stateAbbreviation) {
        this.stateAbbreviation = stateAbbreviation;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

}
