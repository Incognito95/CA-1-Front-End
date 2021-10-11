package dtos;

import entities.Address;

public class AddressDTO {

  private Long id;
  private String street;
  private String additionalInfo;
  private CityDTO city;

  public AddressDTO(){}

  public AddressDTO(Long id, String street, String additionalInfo, CityDTO city) {
    this.id = id;
    this.street = street;
    this.additionalInfo = additionalInfo;
    this.city = city;
  }

  public AddressDTO(String street, String additionalInfo) {
    this.street = street;
    this.additionalInfo = additionalInfo;
  }

  public AddressDTO(String street, String additionalInfo, CityDTO cityInfo) {
    this.street = street;
    this.additionalInfo = additionalInfo;
    this.city = cityInfo;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public String getAdditionalInfo() {
    return additionalInfo;
  }

  public void setAdditionalInfo(String additionalInfo) {
    this.additionalInfo = additionalInfo;
  }

  public CityDTO getCityInfo() {
    return city;
  }

  public void setCityInfo(CityDTO city) {
    this.city = city;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("AddressDTO{");
    sb.append("id=").append(id);
    sb.append(", street='").append(street).append('\'');
    sb.append(", additionalInfo='").append(additionalInfo).append('\'');
    sb.append(", cityInfo=").append(city);
    sb.append('}');
    return sb.toString();
  }
}