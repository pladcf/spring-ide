/*******************************************************************************
 * Copyright (c) 2005, 2007 Spring IDE Developers
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Spring IDE Developers - initial API and implementation
 *******************************************************************************/
package org.springframework.ide.eclipse.webflow.core.internal.model.validation.rules;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.IType;
import org.springframework.binding.convert.ConversionService;
import org.springframework.binding.convert.support.DefaultConversionService;
import org.springframework.ide.eclipse.beans.core.internal.model.BeansModelUtils;
import org.springframework.ide.eclipse.core.MessageUtils;
import org.springframework.ide.eclipse.core.model.IModelElement;
import org.springframework.ide.eclipse.core.model.validation.IValidationContext;
import org.springframework.ide.eclipse.core.model.validation.IValidationRule;
import org.springframework.ide.eclipse.webflow.core.internal.model.Attribute;
import org.springframework.ide.eclipse.webflow.core.internal.model.validation.WebflowValidationContext;
import org.springframework.util.StringUtils;

/**
 * @author Christian Dupuis
 * @since 2.0
 */
@SuppressWarnings("restriction")
public class AttributeValidationRule implements
		IValidationRule<Attribute, WebflowValidationContext> {

	private ConversionService conversionService = null;
	
	public boolean supports(IModelElement element, IValidationContext context) {
		return element instanceof Attribute
				&& context instanceof WebflowValidationContext;
	}

	public void validate(Attribute attribute, WebflowValidationContext context,
			IProgressMonitor monitor) {
		if (!StringUtils.hasText(attribute.getName())) {
			context.error(this, "NO_NAME_ATTRIBUTE", attribute,
					"Element 'attribute' requires 'name' attribute");
		}
		if (StringUtils.hasText(attribute.getType())
				&& getJavaType(attribute.getType(), context) == null) {
			context.error(this, "NO_TYPE_FOUND", attribute, MessageUtils.format(
					"Attribute 'type' \"{0}\" cannot be resolved", attribute
							.getType()));
		}
		if (!StringUtils.hasText(attribute.getValue())) {
			context.error(this, "NO_VALUE_ATTRIBUTE", attribute,
					"Element 'attribute' requires a 'value'");
		}
	}
	
	private IType getJavaType(String className, WebflowValidationContext context) {
		IType type = BeansModelUtils.getJavaType(context.getWebflowConfig()
				.getProject().getProject(), className);
		if (type == null) {
			Class clazz = getConversionService().getClassByAlias(className);
			if (clazz != null) {
				type = BeansModelUtils.getJavaType(context.getWebflowConfig()
						.getProject().getProject(), clazz
						.getName());
			}
		}
		return type;
	}

	private ConversionService getConversionService() {
		if (this.conversionService == null) {
			this.conversionService = new DefaultConversionService();
		}
		return this.conversionService;
	}
}
