/** (C) Copyright 2010 Hal Hildebrand, All Rights Reserved
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 *     
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and 
 * limitations under the License.
 */
package com.hellblazer.gossip.fd;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.hellblazer.gossip.FailureDetector;
import com.hellblazer.gossip.FailureDetectorFactory;

@JsonSubTypes({ @JsonSubTypes.Type(name = "com.hellblazer.gossip.fd.TimedFailureDectorFactory", value = FailureDetectorFactory.class) })
public class TimedFailureDetectorFactory implements FailureDetectorFactory {
	@JsonProperty
    final long maxInterval;

    public TimedFailureDetectorFactory(long maxInterval) {
        this.maxInterval = maxInterval;
    }

    @Override
    public FailureDetector create() {
        return new TimedFailureDetector(maxInterval);
    }

}
