package com.codepine.api.testrail.model

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * TestRail template.
 */
class Template {

    private val id: Int = 0

    @JsonProperty(value = "is_default")
    private val isDefault: Boolean = false

    private val name: String? = null
}
