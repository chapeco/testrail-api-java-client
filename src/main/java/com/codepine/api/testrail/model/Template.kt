package com.codepine.api.testrail.model

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * TestRail template.
 */
class Template {

    var id: Int = 0

    @JsonProperty(value = "is_default")
    var isDefault: Boolean = false

    var name: String? = null
}
