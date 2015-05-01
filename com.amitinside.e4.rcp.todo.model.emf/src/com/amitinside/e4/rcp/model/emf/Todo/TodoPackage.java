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
/**
 */
package com.amitinside.e4.rcp.model.emf.Todo;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EPackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see com.amitinside.e4.rcp.model.emf.Todo.TodoFactory
 * @model kind="package"
 * @generated
 */
public interface TodoPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "Todo";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.amitinside.com/Todo";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "Todo";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	TodoPackage eINSTANCE = com.amitinside.e4.rcp.model.emf.Todo.impl.TodoPackageImpl.init();

	/**
	 * The meta object id for the '{@link com.amitinside.e4.rcp.model.emf.Todo.impl.TodoImpl <em>Todo</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.amitinside.e4.rcp.model.emf.Todo.impl.TodoImpl
	 * @see com.amitinside.e4.rcp.model.emf.Todo.impl.TodoPackageImpl#getTodo()
	 * @generated
	 */
	int TODO = 0;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TODO__ID = 0;

	/**
	 * The feature id for the '<em><b>Summary</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TODO__SUMMARY = 1;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TODO__DESCRIPTION = 2;

	/**
	 * The feature id for the '<em><b>Done</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TODO__DONE = 3;

	/**
	 * The feature id for the '<em><b>Due Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TODO__DUE_DATE = 4;

	/**
	 * The feature id for the '<em><b>Note</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TODO__NOTE = 5;

	/**
	 * The number of structural features of the '<em>Todo</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TODO_FEATURE_COUNT = 6;

	/**
	 * The number of operations of the '<em>Todo</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TODO_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '<em>Date</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.util.Date
	 * @see com.amitinside.e4.rcp.model.emf.Todo.impl.TodoPackageImpl#getDate()
	 * @generated
	 */
	int DATE = 1;


	/**
	 * Returns the meta object for class '{@link com.amitinside.e4.rcp.model.emf.Todo.Todo <em>Todo</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Todo</em>'.
	 * @see com.amitinside.e4.rcp.model.emf.Todo.Todo
	 * @generated
	 */
	EClass getTodo();

	/**
	 * Returns the meta object for the attribute '{@link com.amitinside.e4.rcp.model.emf.Todo.Todo#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see com.amitinside.e4.rcp.model.emf.Todo.Todo#getId()
	 * @see #getTodo()
	 * @generated
	 */
	EAttribute getTodo_Id();

	/**
	 * Returns the meta object for the attribute '{@link com.amitinside.e4.rcp.model.emf.Todo.Todo#getSummary <em>Summary</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Summary</em>'.
	 * @see com.amitinside.e4.rcp.model.emf.Todo.Todo#getSummary()
	 * @see #getTodo()
	 * @generated
	 */
	EAttribute getTodo_Summary();

	/**
	 * Returns the meta object for the attribute '{@link com.amitinside.e4.rcp.model.emf.Todo.Todo#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see com.amitinside.e4.rcp.model.emf.Todo.Todo#getDescription()
	 * @see #getTodo()
	 * @generated
	 */
	EAttribute getTodo_Description();

	/**
	 * Returns the meta object for the attribute '{@link com.amitinside.e4.rcp.model.emf.Todo.Todo#isDone <em>Done</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Done</em>'.
	 * @see com.amitinside.e4.rcp.model.emf.Todo.Todo#isDone()
	 * @see #getTodo()
	 * @generated
	 */
	EAttribute getTodo_Done();

	/**
	 * Returns the meta object for the attribute '{@link com.amitinside.e4.rcp.model.emf.Todo.Todo#getDueDate <em>Due Date</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Due Date</em>'.
	 * @see com.amitinside.e4.rcp.model.emf.Todo.Todo#getDueDate()
	 * @see #getTodo()
	 * @generated
	 */
	EAttribute getTodo_DueDate();

	/**
	 * Returns the meta object for the attribute '{@link com.amitinside.e4.rcp.model.emf.Todo.Todo#getNote <em>Note</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Note</em>'.
	 * @see com.amitinside.e4.rcp.model.emf.Todo.Todo#getNote()
	 * @see #getTodo()
	 * @generated
	 */
	EAttribute getTodo_Note();

	/**
	 * Returns the meta object for data type '{@link java.util.Date <em>Date</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Date</em>'.
	 * @see java.util.Date
	 * @model instanceClass="java.util.Date"
	 * @generated
	 */
	EDataType getDate();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	TodoFactory getTodoFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link com.amitinside.e4.rcp.model.emf.Todo.impl.TodoImpl <em>Todo</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.amitinside.e4.rcp.model.emf.Todo.impl.TodoImpl
		 * @see com.amitinside.e4.rcp.model.emf.Todo.impl.TodoPackageImpl#getTodo()
		 * @generated
		 */
		EClass TODO = eINSTANCE.getTodo();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TODO__ID = eINSTANCE.getTodo_Id();

		/**
		 * The meta object literal for the '<em><b>Summary</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TODO__SUMMARY = eINSTANCE.getTodo_Summary();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TODO__DESCRIPTION = eINSTANCE.getTodo_Description();

		/**
		 * The meta object literal for the '<em><b>Done</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TODO__DONE = eINSTANCE.getTodo_Done();

		/**
		 * The meta object literal for the '<em><b>Due Date</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TODO__DUE_DATE = eINSTANCE.getTodo_DueDate();

		/**
		 * The meta object literal for the '<em><b>Note</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TODO__NOTE = eINSTANCE.getTodo_Note();

		/**
		 * The meta object literal for the '<em>Date</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.util.Date
		 * @see com.amitinside.e4.rcp.model.emf.Todo.impl.TodoPackageImpl#getDate()
		 * @generated
		 */
		EDataType DATE = eINSTANCE.getDate();

	}

} //TodoPackage
