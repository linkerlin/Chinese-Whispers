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

/**
 * 
 * @author <a href="mailto:hal.hildebrand@gmail.com">Hal Hildebrand</a>
 * 
 */
@JsonSubTypes({ @JsonSubTypes.Type(name = "com.hellblazer.gossip.fd.PhiFailureDectorFactory", value = FailureDetectorFactory.class) })
public class PhiFailureDetectorFactory implements FailureDetectorFactory {
	@JsonProperty
	private double convictionThreshold;
	@JsonProperty
	private int windowSize;
	@JsonProperty
	private long expectedSampleInterval;
	@JsonProperty
	private int initialSamples;
	@JsonProperty
	private double minimumInterval;
	@JsonProperty
	private boolean useMedian;
	
	public PhiFailureDetectorFactory() {
	}

	public PhiFailureDetectorFactory(double convictionThreshold,
			int windowSize, long expectedSampleInterval, int initialSamples,
			double minimumInterval, boolean useMedian) {
		this.convictionThreshold = convictionThreshold;
		this.windowSize = windowSize;
		this.expectedSampleInterval = expectedSampleInterval;
		this.initialSamples = initialSamples;
		this.minimumInterval = minimumInterval;
		this.useMedian = useMedian;
	}

	@Override
	public FailureDetector create() {
		return new PhiAccrualFailureDetector(convictionThreshold, useMedian,
				windowSize, expectedSampleInterval, initialSamples,
				minimumInterval);
	}

}
