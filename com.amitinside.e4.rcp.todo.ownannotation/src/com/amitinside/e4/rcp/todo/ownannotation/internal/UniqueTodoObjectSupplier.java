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
package com.amitinside.e4.rcp.todo.ownannotation.internal;

import java.util.Date;

import org.eclipse.e4.core.di.suppliers.ExtendedObjectSupplier;
import org.eclipse.e4.core.di.suppliers.IObjectDescriptor;
import org.eclipse.e4.core.di.suppliers.IRequestor;

import com.amitinside.e4.rcp.todo.model.Todo;

public class UniqueTodoObjectSupplier extends ExtendedObjectSupplier {
	@Override
	public Object get(IObjectDescriptor descriptor, IRequestor requestor,
			boolean track, boolean group) {
		System.out.println("Own annotation processor");
		// Just for the purpose of this example
		// return a hard-coded Todo
		// You could add checks which makes this Todo unique, e.g.
		// be access the TodoService and reading all existing ID's
		final Todo todo = new Todo(15, "Checked", "Checked", false, new Date(),
				"AMIT");
		return todo;
	}
}