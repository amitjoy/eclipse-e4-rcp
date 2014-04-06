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
