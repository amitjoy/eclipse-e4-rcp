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
package com.amitinside.e4.rcp.todo.events;

public interface MyEventConstants {

	// Only used for event registration, you cannot
	// send out generic events
	String TOPIC_TODO_ALLTOPICS = "TOPIC_TODO/*";

	String TOPIC_TODO_NEW = "TOPIC_TODO/NEW";

	String TOPIC_TODO_DELETE = "TOPIC_TODO/DELETED";

	String TOPIC_TODO_UPDATE = "TOPIC_TODO/UPDATED";
}
