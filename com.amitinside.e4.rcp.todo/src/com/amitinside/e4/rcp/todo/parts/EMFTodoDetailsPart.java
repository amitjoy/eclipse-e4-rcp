package com.amitinside.e4.rcp.todo.parts;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
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
import org.eclipse.emf.databinding.EMFProperties;
import org.eclipse.jface.databinding.fieldassist.ControlDecorationSupport;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.amitinside.e4.rcp.model.emf.Todo.Todo;
import com.amitinside.e4.rcp.model.emf.Todo.TodoPackage;
import com.amitinside.e4.rcp.model.emf.service.ITodoEMFService;
import com.amitinside.e4.rcp.todo.i18n.LocalizationHelper;
import com.amitinside.e4.rcp.todo.i18n.Messages;
import com.amitinside.swt.layout.grid.GridLayoutUtil;

public class EMFTodoDetailsPart {

	private final MDirtyable dirty;
	private final Logger logger;
	private final Messages messages;

	private Text txtSummary;
	private Text txtDescription;
	private Text txtNote;
	private Button btnDone;
	private DateTime dateTime;
	private Label lblSummary;
	private Label lblDescription;

	private final DataBindingContext ctx = new DataBindingContext();
	private final IChangeListener listener;

	private Todo todo;

	private Label lblNote;

	private Label lblNewLabel;

	@Inject
	public EMFTodoDetailsPart(@Translation Messages messages, Logger logger,
			MDirtyable dirty) {
		this.messages = messages;
		this.logger = logger;
		this.dirty = dirty;

		this.listener = new IChangeListener() {
			@Override
			public void handleChange(ChangeEvent event) {
				updateDirtyable();
			}
		};
	}

	private void updateDirtyable() {
		if (this.dirty != null) {
			this.dirty.setDirty(true);
		}
	}

	@PostConstruct
	public void createControls(Composite parent) {

		GridLayoutUtil.applyGridLayout(parent).numColumns(2).marginRight(10)
				.marginLeft(10).horizontalSpacing(10).marginWidth(0);

		lblSummary = new Label(parent, SWT.NONE);
		lblSummary.setText(messages.TodoDetailsPart_0);

		txtSummary = new Text(parent, SWT.BORDER);
		txtSummary
				.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

		lblDescription = new Label(parent, SWT.NONE);
		lblDescription.setText(messages.TodoDetailsPart_1);

		txtDescription = new Text(parent, SWT.BORDER | SWT.MULTI);
		final GridData gd = new GridData(SWT.FILL, SWT.CENTER, true, false);
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
	public void save(MDirtyable dirty, ITodoEMFService model) {
		model.saveTodo(todo);
		dirty.setDirty(false);
	}

	@Inject
	public void setTodo(
			@Optional @Named(IServiceConstants.ACTIVE_SELECTION) Todo todo) {
		if (todo != null) {
			this.todo = todo;
			updateUserInterface(todo);
		}
	}

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

		if (todo == null) {
			enableUserInterface(false);
			return;
		} else {
			enableUserInterface(true);
		}

		if (txtSummary != null && !txtSummary.isDisposed()) {

			IObservableList providers = ctx.getValidationStatusProviders();
			for (final Object o : providers) {
				final Binding b = (Binding) o;
				b.getTarget().removeChangeListener(listener);
			}

			ctx.dispose();

			IObservableValue target = WidgetProperties.text(SWT.Modify)
					.observe(txtSummary);
			IObservableValue model = EMFProperties.value(
					TodoPackage.Literals.TODO__SUMMARY).observe(todo);
			ctx.bindValue(target, model);

			target = WidgetProperties.selection().observe(btnDone);
			model = EMFProperties.value(TodoPackage.Literals.TODO__DONE)
					.observe(todo);
			ctx.bindValue(target, model);

			target = WidgetProperties.text(SWT.Modify).observe(txtNote);
			model = EMFProperties.value(TodoPackage.Literals.TODO__NOTE)
					.observe(todo);

			final IValidator validator = new IValidator() {

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

			final UpdateValueStrategy strategy = new UpdateValueStrategy();
			strategy.setBeforeSetValidator(validator);

			final Binding bindvalue = ctx.bindValue(target, model, strategy,
					null);
			ControlDecorationSupport.create(bindvalue, SWT.TOP | SWT.LEFT);

			final IObservableValue observeSelectionDateTimeObserveWidget = WidgetProperties
					.selection().observe(dateTime);
			final IObservableValue dueDateTodoObserveValue = EMFProperties
					.value(TodoPackage.Literals.TODO__DUE_DATE).observe(todo);
			ctx.bindValue(observeSelectionDateTimeObserveWidget,
					dueDateTodoObserveValue);

			// register listener for any changes
			providers = ctx.getValidationStatusProviders();
			for (final Object o : providers) {
				final Binding b = (Binding) o;
				b.getTarget().addChangeListener(listener);
			}

			// Register for the changes
			providers = ctx.getValidationStatusProviders();
			for (final Object o : providers) {
				final Binding b = (Binding) o;
				b.getTarget().addChangeListener(listener);
			}

		}
	}

	@Focus
	public void onFocus() {
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
