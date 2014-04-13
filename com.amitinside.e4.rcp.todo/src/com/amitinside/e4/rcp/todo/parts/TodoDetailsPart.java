package com.amitinside.e4.rcp.todo.parts;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.BeanProperties;
import org.eclipse.core.databinding.observable.ChangeEvent;
import org.eclipse.core.databinding.observable.IChangeListener;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.core.services.log.Logger;
import org.eclipse.e4.core.services.nls.Translation;
import org.eclipse.e4.core.services.translation.TranslationService;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.Persist;
import org.eclipse.e4.ui.model.application.ui.MDirtyable;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.databinding.fieldassist.ControlDecorationSupport;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.amitinside.e4.rcp.todo.i18n.LocalizationHelper;
import com.amitinside.e4.rcp.todo.i18n.Messages;
import com.amitinside.e4.rcp.todo.model.ITodoService;
import com.amitinside.e4.rcp.todo.model.Todo;

public class TodoDetailsPart {

	@Inject
	MDirtyable dirty;
	
	@Inject
	Logger logger;

	@Inject
	@Translation
	Messages messages;

	private Text txtSummary;
	private Text txtDescription;
	private Text txtNote;
	private Button btnDone;
	private DateTime dateTime;
	private DataBindingContext ctx = new DataBindingContext();

	private Label lblSummary;
	private Label lblDescription;

	// Define listener for the databinding
	IChangeListener listener = new IChangeListener() {
		@Override
		public void handleChange(ChangeEvent event) {
			if (dirty != null) {
				dirty.setDirty(true);
			}
		}
	};
	private Todo todo;

	private Label lblNote;

	private Label lblNewLabel;

	@PostConstruct
	public void createControls(Composite parent) {

		GridLayout gl_parent = new GridLayout(2, false);
		gl_parent.marginRight = 10;
		gl_parent.marginLeft = 10;
		gl_parent.horizontalSpacing = 10;
		gl_parent.marginWidth = 0;
		parent.setLayout(gl_parent);

		lblSummary = new Label(parent, SWT.NONE);
		lblSummary.setText(messages.TodoDetailsPart_0);

		txtSummary = new Text(parent, SWT.BORDER);
		txtSummary
				.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

		lblDescription = new Label(parent, SWT.NONE);
		lblDescription.setText(messages.TodoDetailsPart_1);

		txtDescription = new Text(parent, SWT.BORDER | SWT.MULTI);
		GridData gd = new GridData(SWT.FILL, SWT.CENTER, true, false);
		gd.heightHint = 122;
		txtDescription.setLayoutData(gd);

		lblNote = new Label(parent, SWT.NONE);
		lblNote.setText(messages.TodoDetailsPart_2);

		txtNote = new Text(parent, SWT.BORDER);
		txtNote.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

		lblNewLabel = new Label(parent, SWT.NONE);
		lblNewLabel.setText(messages.TodoDetailsPart_3);

		dateTime = new DateTime(parent, SWT.BORDER);
		dateTime.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false,
				1, 1));
		new Label(parent, SWT.NONE);

		btnDone = new Button(parent, SWT.CHECK);
		btnDone.setText(messages.TodoDetailsPart_4);

		updateUserInterface(todo);
	}

	@Persist
	public void save(MDirtyable dirty, ITodoService model) {
		model.saveTodo(todo);
		dirty.setDirty(false);
	}

	@Inject
	public void setTodo(
			@Optional @Named(IServiceConstants.ACTIVE_SELECTION) Todo todo) {
		if (todo != null) {
			// Remember the todo as field
			this.todo = todo;
			// update the user interface
			updateUserInterface(todo);
		}
	}

	// allows to disable/ enable the user interface fields
	// if no todo is et
	private void enableUserInterface(boolean enabled) {
		if (txtSummary != null && !txtSummary.isDisposed()) {
			txtSummary.setEnabled(enabled);
			txtDescription.setEnabled(enabled);
			dateTime.setEnabled(enabled);
			btnDone.setEnabled(enabled);
			txtNote.setEnabled(enabled);
		}
	}

	private void updateUserInterface(Todo todo) {

		// check if Todo is null
		if (todo == null) {
			enableUserInterface(false);
			return;
		} else {
			enableUserInterface(true);
		}

		// Check if the user interface is available
		// assume you have a field called "summary"
		// for a widget
		if (txtSummary != null && !txtSummary.isDisposed()) {

			// Deregister change listener to the old binding
			IObservableList providers = ctx.getValidationStatusProviders();
			for (Object o : providers) {
				Binding b = (Binding) o;
				b.getTarget().removeChangeListener(listener);
			}

			// Remove bindings
			ctx.dispose();

			IObservableValue target = WidgetProperties.text(SWT.Modify)
					.observe(txtSummary);
			IObservableValue model = BeanProperties.value(Todo.FIELD_SUMMARY)
					.observe(todo);
			ctx.bindValue(target, model);

			target = WidgetProperties.selection().observe(btnDone);
			model = BeanProperties.value(Todo.FIELD_DONE).observe(todo);
			ctx.bindValue(target, model);

			target = WidgetProperties.selection().observe(btnDone);
			model = BeanProperties.value(Todo.FIELD_DONE).observe(todo);
			ctx.bindValue(target, model);

			target = WidgetProperties.text(SWT.Modify).observe(txtNote);
			model = BeanProperties.value(Todo.FIELD_NOTE).observe(todo);
			IValidator validator = new IValidator() {

				@Override
				public IStatus validate(Object value) {
					if (value instanceof String) {
						if (((String) value)
								.matches(messages.TodoDetailsPart_5)) {
							return ValidationStatus.OK_STATUS;
						}
					}
					return ValidationStatus.error(messages.TodoDetailsPart_6);
				}
			};

			UpdateValueStrategy strategy = new UpdateValueStrategy();
			strategy.setBeforeSetValidator(validator);

			Binding bindvalue = ctx.bindValue(target, model, strategy, null);
			ControlDecorationSupport.create(bindvalue, SWT.TOP | SWT.LEFT);

			IObservableValue observeSelectionDateTimeObserveWidget = WidgetProperties
					.selection().observe(dateTime);
			IObservableValue dueDateTodoObserveValue = BeanProperties.value(
					Todo.FIELD_DUEDATE).observe(todo);
			ctx.bindValue(observeSelectionDateTimeObserveWidget,
					dueDateTodoObserveValue);

			// register listener for any changes
			providers = ctx.getValidationStatusProviders();
			for (Object o : providers) {
				Binding b = (Binding) o;
				b.getTarget().addChangeListener(listener);
			}

			// Register for the changes
			providers = ctx.getValidationStatusProviders();
			for (Object o : providers) {
				Binding b = (Binding) o;
				b.getTarget().addChangeListener(listener);
			}

		}
	}

	@Focus
	public void onFocus() {
		// The following assumes that you have Text fields
		// called summary and note
		txtSummary.setFocus();
		txtNote.setFocus();
	}

	@Inject
	@Optional
	private void getNotified(@Named(TranslationService.LOCALE) String s,
			@Translation Messages messages, @Named("locale") String locale) {
		System.out.println(locale);
		LocalizationHelper.updateLabelText(lblSummary,
				messages.TodoDetailsPart_0);
		LocalizationHelper.updateLabelText(lblDescription,
				messages.TodoDetailsPart_1);
		LocalizationHelper.updateLabelText(lblNote, messages.TodoDetailsPart_2);
		LocalizationHelper.updateLabelText(lblNewLabel,
	messages.TodoDetailsPart_3);
	}
}
