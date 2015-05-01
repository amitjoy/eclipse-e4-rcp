/*******************************************************************************
 * Copyright (C) 2015 - Amit Kumar Mondal <admin@amitinside.com>
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
 *******************************************************************************/
package com.amitinside.e4.rcp.todo.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.amitinside.e4.rcp.todo.model.Todo;

public class TodoTest {

	private Todo t;

	@Before
	public void setUp() throws Exception {
		System.out.println("TodoTest#setup");
		t = new Todo();
	}

	@Test
	public void testIsDone() {
		t.setDone(true);
		assertTrue(t.isDone());
	}
	
	
}
