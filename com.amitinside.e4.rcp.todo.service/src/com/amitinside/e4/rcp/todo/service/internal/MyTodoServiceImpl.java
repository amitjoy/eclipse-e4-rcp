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
package com.amitinside.e4.rcp.todo.service.internal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import org.eclipse.e4.core.services.events.IEventBroker;
import com.amitinside.e4.rcp.todo.events.MyEventConstants;
import com.amitinside.e4.rcp.todo.model.ITodoService;
import com.amitinside.e4.rcp.todo.model.Todo;

public class MyTodoServiceImpl implements ITodoService {

	private static int current = 1;
	private List<Todo> todos;

	// @Inject in the MyTodoServiceImpl!
	@Inject
	private IEventBroker broker;

	public MyTodoServiceImpl() {
		todos = createInitialModel();
	}

	// Always return a new copy of the data
	@Override
	public List<Todo> getTodos() {
		List<Todo> list = new ArrayList<Todo>();
		for (Todo todo : todos) {
			list.add(todo.copy());
		}
		return list;
	}

	// Saves or updates
	@Override
	public synchronized boolean saveTodo(Todo newTodo) {
		boolean created = false;
		Todo updateTodo = findById(newTodo.getId());
		if (updateTodo == null) {
			created = true;
			updateTodo = new Todo(current++);
			todos.add(updateTodo);
		}
		updateTodo.setSummary(newTodo.getSummary());
		updateTodo.setDescription(newTodo.getDescription());
		updateTodo.setDone(newTodo.isDone());
		updateTodo.setDueDate(newTodo.getDueDate());
		updateTodo.setNote(newTodo.getNote());

		// Send out events
		if (created) {
			broker.post(MyEventConstants.TOPIC_TODO_NEW, updateTodo);
		} else {
			broker.post(MyEventConstants.TOPIC_TODO_UPDATE, updateTodo);
		}
		return true;
	}

	@Override
	public Todo getTodo(long id) {
		Todo todo = findById(id);

		if (todo != null) {
			return todo.copy();
		}
		return null;
	}

	@Override
	public boolean deleteTodo(long id) {
		Todo deleteTodo = findById(id);

		if (deleteTodo != null) {
			todos.remove(deleteTodo);
			broker.post(MyEventConstants.TOPIC_TODO_DELETE, deleteTodo);
			return true;
		}
		return false;
	}

	// Example data, change if you like
	private List<Todo> createInitialModel() {
		List<Todo> list = new ArrayList<Todo>();
		list.add(createTodo("Application model", "Flexible and extensible",
				"A1"));
		list.add(createTodo("DI", "@Inject as programming mode", "B1"));
		list.add(createTodo("OSGi", "Services", "C1"));
		list.add(createTodo("SWT", "Widgets", "D1"));
		list.add(createTodo("JFace", "Especially Viewers!", "E1"));
		list.add(createTodo("CSS Styling", "Style your application", "F1"));
		list.add(createTodo("Eclipse services", "Selection, model, Part", "G1"));
		list.add(createTodo("Renderer", "Different UI toolkit", "H1"));
		list.add(createTodo("Compatibility Layer", "Run Eclipse 3.x", "I1"));
		return list;
	}

	private Todo createTodo(String summary, String description, String notes) {
		return new Todo(current++, summary, description, false, new Date(),
				notes);
	}

	private Todo findById(long id) {
		for (Todo todo : todos) {
			if (id == todo.getId()) {
				return todo;
			}
		}
		return null;
	}

}
