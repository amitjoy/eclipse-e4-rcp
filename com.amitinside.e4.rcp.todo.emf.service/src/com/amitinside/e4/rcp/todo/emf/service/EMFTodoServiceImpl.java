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
package com.amitinside.e4.rcp.todo.emf.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.emf.ecore.util.EcoreUtil;

import com.amitinside.e4.rcp.model.emf.Todo.Todo;
import com.amitinside.e4.rcp.model.emf.Todo.TodoFactory;
import com.amitinside.e4.rcp.model.emf.service.ITodoEMFService;
import com.amitinside.e4.rcp.todo.events.MyEventConstants;

import de.ralfebert.rcputils.random.RandomData;

public class EMFTodoServiceImpl implements ITodoEMFService {

	private static int current = 1;
	private final List<Todo> todos;

	private final IEventBroker broker;

	@Inject
	public EMFTodoServiceImpl(IEventBroker broker) {
		this.broker = broker;
		todos = createInitialModel();
	}

	@Override
	public List<Todo> getTodos() {
		final List<Todo> list = new ArrayList<Todo>();
		for (final Todo todo : todos) {
			list.add(EcoreUtil.copy(todo));
		}
		return list;
	}

	@Override
	public synchronized boolean saveTodo(Todo newTodo) {
		boolean created = false;
		Todo updateTodo = findById(newTodo.getId());
		if (updateTodo == null) {
			created = true;
			final Todo todo = TodoFactory.eINSTANCE.createTodo();
			todo.setId(current++);
			updateTodo = todo;
			todos.add(updateTodo);
		}
		updateTodo.setSummary(newTodo.getSummary());
		updateTodo.setDescription(newTodo.getDescription());
		updateTodo.setDone(newTodo.isDone());
		updateTodo.setDueDate(newTodo.getDueDate());
		updateTodo.setNote(newTodo.getNote());

		if (created) {
			broker.post(MyEventConstants.TOPIC_TODO_NEW, updateTodo);
		} else {
			broker.post(MyEventConstants.TOPIC_TODO_UPDATE, updateTodo);
		}
		return true;
	}

	@Override
	public Todo getTodo(long id) {
		final Todo todo = findById(id);

		if (todo != null) {
			return EcoreUtil.copy(todo);
		}
		return null;
	}

	@Override
	public boolean deleteTodo(long id) {
		final Todo deleteTodo = findById(id);

		if (deleteTodo != null) {
			todos.remove(deleteTodo);
			broker.post(MyEventConstants.TOPIC_TODO_DELETE, deleteTodo);
			return true;
		}
		return false;
	}

	private List<Todo> createInitialModel() {
		final List<Todo> list = new ArrayList<Todo>();
		for (int i = 0; i < 10; i++) {
			list.add(createTodo(RandomData.CITIES[i],
					RandomData.GIVEN_NAMES[i], RandomData.LAST_NAMES[i]));
		}
		return list;
	}

	private Todo createTodo(String summary, String description, String notes) {
		final Todo todo = TodoFactory.eINSTANCE.createTodo();
		todo.setDescription(description);
		todo.setSummary(summary);
		todo.setNote(notes);
		todo.setId(current++);
		todo.setDone(false);
		todo.setDueDate(new Date());
		return todo;
	}

	private Todo findById(long id) {
		for (final Todo todo : todos) {
			if (id == todo.getId()) {
				return todo;
			}
		}
		return null;
	}

}
