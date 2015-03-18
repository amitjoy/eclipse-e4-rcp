package com.amitinside.e4.rcp.model.emf.service;

import java.util.List;

import com.amitinside.e4.rcp.model.emf.Todo.Todo;

public interface ITodoEMFService {
	List<Todo> getTodos();

	boolean saveTodo(Todo todo);

	Todo getTodo(long id);

	boolean deleteTodo(long id);
}
