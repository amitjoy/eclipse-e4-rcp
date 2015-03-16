package com.amitinside.e4.rcp.todo.parts;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.BeanProperties;
import org.eclipse.core.databinding.observable.ChangeEvent;
import org.eclipse.core.databinding.observable.IChangeListener;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.e4.ui.di.Persist;
import org.eclipse.e4.ui.model.application.ui.MDirtyable;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.e4.ui.workbench.modeling.ISaveHandler;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.amitinside.e4.rcp.todo.model.ITodoService;
import com.amitinside.e4.rcp.todo.model.Todo;
import com.amitinside.swt.layout.grid.GridDataUtil;

public class SampleFormUsingFormsAPI {

	private final MDirtyable dirtyable;
	private final ISaveHandler handlerService;
	private final EPartService partService;
	private Label label;
	private Text textTo;
	private Text textSubject;
	private Text textMessage;
	private Button buttonOption;
	private Todo todo;
	private MPart part;

	private final IChangeListener listener = new IChangeListener() {

		@Override
		public void handleChange(ChangeEvent event) {
			if (dirtyable != null) {
				dirtyable.setDirty(true);
			}
		}
	};

	private final DataBindingContext dbc = new DataBindingContext();

	@Inject
	public SampleFormUsingFormsAPI(MDirtyable dirtyable,
			ISaveHandler handlerService, EPartService partService) {
		this.dirtyable = dirtyable;
		this.handlerService = handlerService;
		this.partService = partService;
	}

	@PostConstruct
	public void createContents(Composite parent) {

		final Composite composite = new Composite(parent, SWT.NULL);
		composite.setLayout(new FillLayout());

		// Sets up the toolkit.
		final FormToolkit toolkit = new FormToolkit(parent.getDisplay());

		// Creates a form instance.
		final Form form = toolkit.createForm(composite);
		GridDataUtil.applyGridData(form).withFill();

		// Sets title.
		form.setText("Composing an Email Message");

		// Adds body contents.
		form.getBody().setLayout(new GridLayout(2, false));
		Label label = toolkit.createLabel(form.getBody(), "To: ", SWT.NULL);
		textTo = toolkit.createText(form.getBody(), "");
		GridDataUtil.applyGridData(textTo).withHorizontalFill();

		label = toolkit.createLabel(form.getBody(), "Subject: ", SWT.NULL);
		textSubject = toolkit.createText(form.getBody(), "");
		GridDataUtil.applyGridData(textSubject).withHorizontalFill();

		label = toolkit.createLabel(form.getBody(), "Message: ", SWT.NULL);
		textMessage = toolkit.createText(form.getBody(), "");
		GridDataUtil.applyGridData(textMessage).withFill();

		label = toolkit.createLabel(form.getBody(), "Option: ", SWT.NULL);
		buttonOption = toolkit.createButton(form.getBody(), "save a copy",
				SWT.CHECK);

		final Button buttonClose = toolkit.createButton(form.getBody(),
				"Close", SWT.PUSH);
		GridDataUtil.applyGridData(buttonClose).horizontalSpan(2)
				.horizontalAlignment(GridData.END);

		// Adds toolbar items.
		form.getToolBarManager().add(new Action("Send") {
			@Override
			public void run() {
				System.out.println("Sending email ...");
			}
		});

		form.getToolBarManager().add(new Action("Cancel") {
			@Override
			public void run() {
				System.out.println("Cancelled.");
			}
		});

		form.updateToolBar();

		updateTheUI(todo);
	}

	private void updateTheUI(Todo todo) {
		part = partService
				.findPart("com.amitinside.e4.rcp.todo.part.samplePart.formsAPI");
		// System.out.println(handlerService.promptToSave(part));
		if (todo == null) {
			activateTheUI(false);
			return;
		} else {
			activateTheUI(true);
		}

		if (textTo != null && !textTo.isDisposed()) {
			// Deregister change listener to the old binding
			IObservableList providers = dbc.getValidationStatusProviders();
			for (final Object o : providers) {
				final Binding b = (Binding) o;
				b.getTarget().removeChangeListener(listener);
			}

			// Remove bindings
			dbc.dispose();

			final IObservableValue<Object> target = WidgetProperties.text()
					.observe(textTo);
			final IObservableValue<Object> model = BeanProperties.value(
					Todo.FIELD_SUMMARY).observe(todo);
			dbc.bindValue(target, model);

			// register listener for any changes
			providers = dbc.getValidationStatusProviders();
			for (final Object o : providers) {
				final Binding b = (Binding) o;
				b.getTarget().addChangeListener(listener);
			}

		}

	}

	@Persist
	private void saveTheUI(MDirtyable dirtyable, ITodoService service) {
		dirtyable.setDirty(false);
		service.saveTodo(todo);
	}

	@Inject
	public void setToDo(@Named(IServiceConstants.ACTIVE_SELECTION) Todo todo) {
		this.todo = todo;
		updateTheUI(todo);
	}

	private void activateTheUI(boolean flag) {
		if (textTo != null && !textTo.isDisposed()) {
			textTo.setEnabled(flag);
			textSubject.setEnabled(flag);
		}
	}

}