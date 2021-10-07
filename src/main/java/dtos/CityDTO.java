package dtos;

import entities.Address;
import entities.City;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;

@Schema(name="CityInfo")
public class CityDTO {
  @Schema(required = false)
  private Long id;
  @Schema(required = true)
  private String ZipCode;
  @Schema(required = true)
  private String city;
  private String countryName;
  private List<Address> addresses;

  public CityDTO(){}

  public CityDTO(City entity) {
    this.id = id;
    this.ZipCode = ZipCode;
    this.countryName = countryName;
    this.addresses = new ArrayList();

  }

  public CityDTO(String zipCode, String city) {
    this.ZipCode = ZipCode;
    this.city = city;
  }


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getZipCode() {
    return ZipCode;
  }

  public void setZipCode(String zipCode) {
    this.ZipCode = ZipCode;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }


  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("CityInfoDTO{");
    sb.append("id=").append(id);
    sb.append(", zipCode='").append(ZipCode).append('\'');
    sb.append(", city='").append(city).append('\'');
    sb.append('}');
    return sb.toString();
  }
}
