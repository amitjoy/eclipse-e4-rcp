/**
 */
package com.amitinside.e4.rcp.model.emf.Todo;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see com.amitinside.e4.rcp.model.emf.Todo.TodoPackage
 * @generated
 */
public interface TodoFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	TodoFactory eINSTANCE = com.amitinside.e4.rcp.model.emf.Todo.impl.TodoFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Todo</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Todo</em>'.
	 * @generated
	 */
	Todo createTodo();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	TodoPackage getTodoPackage();

} //TodoFactory
