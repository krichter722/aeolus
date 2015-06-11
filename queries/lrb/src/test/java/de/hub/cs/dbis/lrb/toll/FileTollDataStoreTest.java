/*
 * #!
 * %
 * Copyright (C) 2014 - 2015 Humboldt-Universität zu Berlin
 * %
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #_
 */
package de.hub.cs.dbis.lrb.toll;

import java.io.IOException;
import java.util.Random;
import org.junit.Test;
import static org.junit.Assert.*;





/**
 * 
 * @author richter
 */
public class FileTollDataStoreTest {
	private static final Random random = new Random();
	
	/**
	 * Test of retrieveToll method, of class FileTollDataStore.
	 * 
	 * @throws java.io.IOException
	 */
	@Test
	public void testRetrieveToll() throws IOException {
		int xWay = random.nextInt();
		int day = random.nextInt();
		int vehicleIdentifier = random.nextInt();
		int toll = random.nextInt();
		FileTollDataStore instance = new FileTollDataStore();
		instance.storeToll(xWay, day, vehicleIdentifier, toll);
		int result = instance.retrieveToll(xWay, day, vehicleIdentifier);
		assertEquals(toll, result);
	}
	
	/**
	 * Test of storeToll method, of class FileTollDataStore.
	 * 
	 * @throws java.io.IOException
	 */
	@Test
	public void testStoreToll() throws IOException {
		int xWay = random.nextInt();
		int day = random.nextInt();
		int vehicleIdentifier = random.nextInt();
		int toll = random.nextInt();
		FileTollDataStore instance = new FileTollDataStore();
		instance.storeToll(xWay, day, vehicleIdentifier, toll);
		int result = instance.retrieveToll(xWay, day, vehicleIdentifier);
		assertEquals(toll, result);
		
		// exhaustive test
		instance = new FileTollDataStore();
		for(int i = 0; i < 10000; i++) {
			xWay = random.nextInt();
			day = random.nextInt();
			vehicleIdentifier = random.nextInt();
			toll = random.nextInt();
			instance.storeToll(xWay, day, vehicleIdentifier, toll);
		}
		
		// @TODO: test that sequential writes succeed
	}
	
	/**
	 * Test of removeEntry method, of class FileTollDataStore.
	 * 
	 * @throws java.io.IOException
	 */
	@Test
	public void testRemoveEntry() throws IOException {
		int xWay = random.nextInt();
		int day = random.nextInt();
		int vehicleIdentifier = random.nextInt();
		int toll = random.nextInt();
		FileTollDataStore instance = new FileTollDataStore();
		instance.storeToll(xWay, day, vehicleIdentifier, toll);
		instance.removeEntry(xWay, day, vehicleIdentifier);
		Integer result = instance.retrieveToll(xWay, day, vehicleIdentifier);
		assertEquals(null, result);
	}
	
}
