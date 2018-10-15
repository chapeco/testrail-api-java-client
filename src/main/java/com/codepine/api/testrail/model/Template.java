package com.codepine.api.testrail.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * TestRail template.
 */
public class Template {

    private int id;

    @JsonProperty(value = "is_default")
    private boolean isDefault;

    private String name;
}
