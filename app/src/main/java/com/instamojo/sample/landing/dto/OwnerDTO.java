package com.instamojo.sample.landing.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by manoj on 8/3/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class OwnerDTO {
    @JsonProperty("display_name")
    public String displayName;

    @JsonProperty("user_type")
    public String userType;

    @JsonProperty("profile_image")
    public String profileImage;

    public String link;

    public int reputation;

    @JsonProperty("user_id")
    public int userId;
}
