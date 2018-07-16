package com.codepine.api.testrail.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;

/**
 * TestRail template.
 */
@Data
public class Template {

    private int id;

    @JsonProperty(value = "is_default")
    @Getter(onMethod = @_({@JsonIgnore}))
    private boolean isDefault;

    private String name;
}
