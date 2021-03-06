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
package com.amitinside.e4.rcp.model.emf.service;

import java.util.List;

import com.amitinside.e4.rcp.model.emf.Todo.Todo;

public interface ITodoEMFService {
	List<Todo> getTodos();

	boolean saveTodo(Todo todo);

	Todo getTodo(long id);

	boolean deleteTodo(long id);
}
