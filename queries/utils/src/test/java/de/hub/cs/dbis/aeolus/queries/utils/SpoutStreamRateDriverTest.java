/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package de.hub.cs.dbis.aeolus.queries.utils;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import de.hub.cs.dbis.aeolus.queries.utils.SpoutStreamRateDriver;
import de.hub.cs.dbis.aeolus.testUtils.IncSpout;
import backtype.storm.Config;
import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.IRichSpout;
import backtype.storm.topology.OutputFieldsDeclarer;





/**
 * @author Matthias J. Sax
 */
@RunWith(PowerMockRunner.class)
public class SpoutStreamRateDriverTest {
	
	@Test
	public void testForwardCalls() {
		IRichSpout worker = mock(IRichSpout.class);
		SpoutStreamRateDriver driver = new SpoutStreamRateDriver(worker, 10);
		
		Config cfg = mock(Config.class);
		TopologyContext c = mock(TopologyContext.class);
		SpoutOutputCollector col = mock(SpoutOutputCollector.class);
		
		driver.open(cfg, c, col);
		verify(worker).open(cfg, c, col);
		
		driver.close();
		verify(worker).close();
		
		driver.activate();
		verify(worker).activate();
		
		driver.deactivate();
		verify(worker).deactivate();
		
		driver.nextTuple();
		verify(worker).nextTuple();
		
		Object messageId = new Object();
		driver.ack(messageId);
		verify(worker).ack(messageId);
		
		driver.fail(messageId);
		verify(worker).fail(messageId);
		
		OutputFieldsDeclarer declarer = mock(OutputFieldsDeclarer.class);
		driver.declareOutputFields(declarer);
		verify(worker).declareOutputFields(declarer);
		
		Map<String, Object> config = worker.getComponentConfiguration();
		Assert.assertEquals(config, driver.getComponentConfiguration());
	}
	
	
	
	@Test
	public void testNextTuple() {
		SpoutStreamRateDriver driver = new SpoutStreamRateDriver(new IncSpout(), 10);
		
		Config cfg = mock(Config.class);
		TopologyContext c = mock(TopologyContext.class);
		SpoutOutputCollector col = mock(SpoutOutputCollector.class);
		driver.open(cfg, c, col);
		
		driver.activate();
		long start = System.nanoTime();
		for(int i = 0; i < 20; ++i) {
			driver.nextTuple();
		}
		long stop = System.nanoTime();
		
		Assert.assertTrue((stop - start) / 1000 / 1000 == 1900);
	}
}