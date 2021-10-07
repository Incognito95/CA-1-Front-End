package dtos;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import entities.Address;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 *
 * @author chris
 */



@Schema(name = "Address")
public class AddressDTO {

  @Schema(required = false, example = "3")
  private Long id;
  @Schema(required = true)
  private String street;
  @Schema(required = false)
  private String additionalInfo;
  @Schema(required = true)
  private CityDTO city;

  public AddressDTO() {
  }

  public AddressDTO(Address entity) {
    this.id = entity.getId() == null ? null : entity.getId();
    this.street = entity.getStreet() == null ? null : entity.getStreet();
    this.additionalInfo = entity.getAdditionalInfo();
    this.city = entity.getCity() == null ? null : new CityDTO(entity.getCity());
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