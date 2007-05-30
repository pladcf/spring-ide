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
package org.springframework.ide.eclipse.webflow.core.internal.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.ui.IPersistableElement;
import org.springframework.ide.eclipse.beans.core.BeansCorePlugin;
import org.springframework.ide.eclipse.beans.core.internal.model.BeansModelUtils;
import org.springframework.ide.eclipse.beans.core.model.IBeansModel;
import org.springframework.ide.eclipse.core.model.IModelElement;
import org.springframework.ide.eclipse.core.model.IModelElementVisitor;
import org.springframework.ide.eclipse.webflow.core.model.IWebflowConfig;
import org.springframework.ide.eclipse.webflow.core.model.IWebflowProject;

/**
 * @author Christian Dupuis
 * @since 2.0
 */
@SuppressWarnings("restriction")
public class WebflowConfig extends AbstractModelElement
		implements IWebflowConfig, IAdaptable {

	private Set<String> beansConfigs = new HashSet<String>();

	private IFile resource;

	private String name;

	@SuppressWarnings("unused")
	private IWebflowProject project;

	public IWebflowProject getProject() {
		return project;
	}

	public WebflowConfig(IWebflowProject project) {
		this.project = project;
	}

	public java.util.Set<IModelElement> getBeansConfigs() {
		IBeansModel model = BeansCorePlugin.getModel();

		java.util.Set<IModelElement> configs = new HashSet<IModelElement>();
		if (beansConfigs != null) {
			for (String configName : this.beansConfigs) {
				IModelElement config = model.getElement(configName);
				if (config != null) {
					configs.add(config);
				}
			}

		}
		return configs;
	}

	public IFile getResource() {
		return this.resource;
	}

	public void setBeansConfigs(java.util.Set<IModelElement> beansConfigs) {
		this.beansConfigs = new HashSet<String>();
		if (beansConfigs != null) {
			for (IModelElement config : beansConfigs) {
				if (BeansModelUtils.getProject(config) != null
						&& BeansModelUtils.getProject(config).getProject()
								.equals(project.getProject())) {
					this.beansConfigs.add(config.getElementID());
				}
			}
		}
	}

	public void setResource(IFile file) {
		this.resource = file;

		if (this.name == null && this.resource != null) {
			int i = this.resource.getName().lastIndexOf('.');
			if (i > 0) {
				this.name = this.resource.getName().substring(0, i);
			}
			else {
				this.name = this.resource.getName();
			}
		}
	}

	public void setBeansConfigsElementIds(java.util.Set<String> beansConfigs) {
		this.beansConfigs = beansConfigs;
	}

	public void addBeansConfigElementId(String id) {
		this.beansConfigs.add(id);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void accept(IModelElementVisitor visitor,
			IProgressMonitor monitor) {
	}

	public String getElementName() {
		return this.name;
	}

	public int getElementType() {
		return CONFIG;
	}
 
	public IModelElement[] getElementChildren() {
		List<IModelElement> children = new ArrayList<IModelElement>();
		return children.toArray(new IModelElement[children.size()]);
	}

	@SuppressWarnings("unchecked")
	public Object getAdapter(Class adapter) {
		if (adapter == IPersistableElement.class) {
			return new WebflowModelElementToPersistableElementAdapter(this);
		}
		else if (adapter == IResource.class) {
			return getResource();
		}
		return null;
	}
	
	public IModelElement getElementParent() {
		return this.project;
	}

	public IResource getElementResource() {
		return this.resource;
	}

	public boolean isElementArchived() {
		return false;
	}
}
