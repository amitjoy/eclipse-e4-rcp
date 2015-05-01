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
package com.amitinside.e4.rcp.todo.i18n;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * Helper class that checks if the {@link Control} to localize
 * is created and not disposed before setting the text.
 */
public class LocalizationHelper {

	/**
	 * Update the text of a {@link Label}. Checks if the given
	 * {@link Label} instance is <code>null</code> or disposed 
	 * before setting the text.
	 * 
	 * @param label The {@link Label} to set the text to
	 * @param text The text to set.
	 */
	public static void updateLabelText(Label label, String text) {
		if (label != null && !label.isDisposed())
			label.setText(text);
	}

	/**
	 * Update the text of a {@link Text}. Checks if the given
	 * {@link Text} instance is <code>null</code> or disposed 
	 * before setting the text.
	 * 
	 * @param textControl The {@link Text} to set the text to
	 * @param text The text to set.
	 */
	public static void updateTextText(Text textControl, String text) {
		if (textControl != null && !textControl.isDisposed())
			textControl.setText(text);
	}

	/**
	 * Update the text of a {@link Button}. Checks if the given 
	 * {@link Button} instance is <code>null</code> or disposed 
	 * before setting the text.
	 * 
	 * @param button The {@link Button} to set the text to
	 * @param text The text to set.
	 */
	public static void updateButtonText(Button button, String text) {
		if (button != null && !button.isDisposed())
			button.setText(text);
	}
}
