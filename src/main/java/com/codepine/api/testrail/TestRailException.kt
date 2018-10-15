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

package com.codepine.api.testrail

import com.google.common.base.Preconditions

/**
 * Exception representing error returned by TestRail API.
 */
class TestRailException
/**
 * @param responseCode the HTTP response code from the TestRail server
 * @param error        the error message from TestRail service
 */
internal constructor(private val responseCode: Int, error: String?) : RuntimeException(responseCode.toString() + " - " + error) {

    /**
     * Builder for `TestRailException`.
     */
    internal class Builder {
        private val responseCode: Int = 0
        private val error: String? = null

        fun build(): TestRailException {
            Preconditions.checkNotNull(responseCode)
            Preconditions.checkNotNull<String>(error)
            return TestRailException(responseCode, error)
        }
    }

    companion object {

        private val serialVersionUID = -2131644110724458502L
    }
}
