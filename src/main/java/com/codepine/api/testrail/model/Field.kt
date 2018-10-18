/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 Kunal Shah
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.codepine.api.testrail.model

import com.codepine.api.testrail.TestRail
import com.codepine.api.testrail.internal.IntToBooleanDeserializer
import com.codepine.api.testrail.internal.StringToMapDeserializer
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonView
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.annotation.JsonDeserialize

import java.math.BigInteger
import java.util.HashMap

/**
 * TestRail field.
 */
open class Field {

    var id: Int = 0
    var label: String? = null
    var name: String? = null
    var description: String? = null
    var systemName: String? = null
    var typeId: Int = 0
    var type: Type? = null
    var displayOrder: Int = 0
    var configs: List<Config>? = null

    /**
     * TestRail type of field.
     *
     *
     * Map of TestRail field types to their corresponding Java types:
     * <pre>
     * STRING -- java.lang.String
     * INTEGER -- java.lang.Integer
     * TEXT -- java.lang.String
     * URL -- java.lang.String
     * CHECKBOX -- java.lang.Boolean
     * DROPDOWN -- java.lang.String
     * USER -- java.lang.Integer
     * DATE -- java.lang.String
     * MILESTONE -- java.lang.Integer
     * STEPS -- java.util.List<[Step]>
     * STEP_RESULTS -- java.util.List<[StepResult]>
     * MULTI_SELECT -- java.util.List<String>
    </String></pre> *
     *
     */
    enum class Type private constructor(optionsClass: Any, typeReference: TypeReference<*>) {
        UNKNOWN(Config.Options::class.java, object : TypeReference<Any>() {

        }),
        STRING(Config.StringOptions::class.java, object : TypeReference<String>() {

        }),
        INTEGER(Config.IntegerOptions::class.java, object : TypeReference<Int>() {

        }),
        TEXT(Config.TextOptions::class.java, object : TypeReference<String>() {

        }),
        URL(Config.UrlOptions::class.java, object : TypeReference<String>() {

        }),
        CHECKBOX(Config.CheckboxOptions::class.java, object : TypeReference<Boolean>() {

        }),
        DROPDOWN(Config.DropdownOptions::class.java, object : TypeReference<String>() {

        }),
        USER(Config.UserOptions::class.java, object : TypeReference<Int>() {

        }),
        DATE(Config.DateOptions::class.java, object : TypeReference<String>() {

        }),
        MILESTONE(Config.MilestoneOptions::class.java, object : TypeReference<Int>() {

        }),
        STEPS(Config.StepsOptions::class.java, object : TypeReference<List<Step>>() {

        }),
        STEP_RESULTS(Config.StepResultsOptions::class.java, object : TypeReference<List<StepResult>>() {

        }),
        MULTI_SELECT(Config.MultiSelectOptions::class.java, object : TypeReference<List<String>>() {

        });

        var optionsClass: Class<out Config.Options>? = null
        var typeReference: TypeReference<*>? = null

        companion object {

            fun getType(typeId: Int): Type {
                return if (typeId >= 0 && typeId < Type.values().size) Type.values()[typeId] else UNKNOWN
            }
        }

    }

    /**
     * Configuration for a `Field`.
     */
    class Config {

        var id: String? = null
        var context: Context? = null
        var options: Options? = null

        /**
         * Options for a `Field` configuration.
         */
        open class Options {

            @JsonProperty
            var isRequired: Boolean = false
            private var unknownFields: MutableMap<String, Any>? = null

            @JsonAnySetter
            private fun addUnknownField(key: String, value: Any): Options {
                if (unknownFields == null) {
                    unknownFields = HashMap()
                }
                unknownFields!![key] = value
                return this
            }

        }

        /**
         * Options for a `Field` of type [Type.STRING].
         */
        class StringOptions : Options() {
            var defaultValue: String? = null
        }

        /**
         * Options for a `Field` of type [Type.INTEGER].
         */
        class IntegerOptions : Options() {
            var defaultValue: BigInteger? = null
        }

        /**
         * Options for a `Field` of type [Type.TEXT].
         */
        class TextOptions : Options() {
            var defaultValue: String? = null
            var format: String? = null
            var rows: Int = 0
        }

        /**
         * Options for a `Field` of type [Type.URL].
         */
        class UrlOptions : Options() {
            var defaultValue: String? = null
        }

        /**
         * Options for a `Field` of type [Type.CHECKBOX].
         */
        class CheckboxOptions : Options() {
            @JsonDeserialize(using = IntToBooleanDeserializer::class)
            var defaultValue: Boolean = false
        }

        /**
         * Options for a `Field` of type [Type.DROPDOWN].
         */
        class DropdownOptions : Options() {
            var defaultValue: String? = null
            @JsonDeserialize(using = StringToMapDeserializer::class)
            var items: Map<String, String>? = null
        }

        /**
         * Options for a `Field` of type [Type.USER].
         */
        class UserOptions : Options() {
            var defaultValue: Int = 0
        }

        /**
         * Options for a `Field` of type [Type.DATE].
         */
        class DateOptions : Options()

        /**
         * Options for a `Field` of type [Type.MILESTONE].
         */
        class MilestoneOptions : Options()

        /**
         * Options for a `Field` of type [Type.STEPS].
         */
        class StepsOptions : Options() {
            var format: String? = null
            var hasExpected: Boolean = false
            var rows: Int = 0
        }

        /**
         * Options for a `Field` of type [Type.STEP_RESULTS].
         */
        class StepResultsOptions : Options() {
            var format: String? = null
            var hasExpected: Boolean = false
            var hasActual: Boolean = false
        }

        /**
         * Options for a `Field` of type [Type.MULTI_SELECT].
         */
        class MultiSelectOptions : Options() {
            @JsonDeserialize(using = StringToMapDeserializer::class)
            var items: Map<String, String>? = null
        }

        class Context {
            @JsonProperty
            var isGlobal: Boolean = false
            var projectIds: List<Int>? = null
        }

    }

    /**
     * Step; a custom field type.
     */
    class Step {

        @JsonView(TestRail.Cases.Add::class, TestRail.Cases.Update::class)
        var content: String? = null
        @JsonView(TestRail.Cases.Add::class, TestRail.Cases.Update::class)
        var expected: String? = null

    }

    /**
     * Step result; a custom field type.
     */
    class StepResult {

        @JsonView(TestRail.Results.Add::class, TestRail.Results.AddForCase::class, TestRail.Results.AddList::class, TestRail.Results.AddListForCases::class)
        var content: String? = null
        @JsonView(TestRail.Results.Add::class, TestRail.Results.AddForCase::class, TestRail.Results.AddList::class, TestRail.Results.AddListForCases::class)
        var expected: String? = null
        @JsonView(TestRail.Results.Add::class, TestRail.Results.AddForCase::class, TestRail.Results.AddList::class, TestRail.Results.AddListForCases::class)
        var actual: String? = null
        @JsonView(TestRail.Results.Add::class, TestRail.Results.AddForCase::class, TestRail.Results.AddList::class, TestRail.Results.AddListForCases::class)
        var statusId: Int? = null

    }

}
