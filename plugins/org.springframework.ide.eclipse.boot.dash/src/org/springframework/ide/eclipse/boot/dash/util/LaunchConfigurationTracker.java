/*******************************************************************************
 * Copyright (c) 2012 Pivotal Software, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Pivotal Software, Inc. - initial API and implementation
 *******************************************************************************/
package org.springframework.ide.eclipse.boot.dash.util;

import static org.springframework.ide.eclipse.boot.launch.BootLaunchConfigurationDelegate.isHiddenFromBootDash;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationListener;
import org.eclipse.debug.core.ILaunchConfigurationType;
import org.eclipse.debug.core.ILaunchManager;
import org.springframework.ide.eclipse.boot.core.BootActivator;
import org.springframework.ide.eclipse.boot.dash.livexp.LiveSetVariable;
import org.springframework.ide.eclipse.boot.dash.livexp.ObservableSet;
import org.springframework.ide.eclipse.boot.dash.model.BootProjectDashElement;
import org.springframework.ide.eclipse.boot.launch.BootLaunchConfigurationDelegate;
import org.springsource.ide.eclipse.commons.livexp.core.AsyncLiveExpression.AsyncMode;
import org.springsource.ide.eclipse.commons.livexp.ui.Disposable;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSet.Builder;

/**
 * This class is responsible of maintaining a map of {@link ILaunchConfiguration}
 * that represent the children of {@link BootProjectDashElement}s.
 *
 * @author Kris De Volder
 */
public class LaunchConfigurationTracker implements Disposable {

	private final ILaunchManager launchManager;
	private final ILaunchConfigurationType launchType;
	private final Map<IProject, LiveSetVariable<ILaunchConfiguration>> configs = new HashMap<>();
	private ILaunchConfigurationListener launchConfListener;

	public LaunchConfigurationTracker(String launchTypeId, ILaunchManager launchManager) {
		this.launchManager = launchManager;
		this.launchType = launchManager.getLaunchConfigurationType(launchTypeId);
	}

	public LaunchConfigurationTracker(String typeId) {
		this(typeId, DebugPlugin.getDefault().getLaunchManager());
	}

	private void init() {
		launchManager.addLaunchConfigurationListener(launchConfListener = new ILaunchConfigurationListener() {
			@Override
			public void launchConfigurationRemoved(ILaunchConfiguration configuration) {
				refresh();
			}

			@Override
			public void launchConfigurationChanged(ILaunchConfiguration configuration) {
				refresh();
			}

			@Override
			public void launchConfigurationAdded(ILaunchConfiguration configuration) {
				refresh();
			}
		});
		refresh();
	}

	private void refresh() {
		Map<IProject, Set<ILaunchConfiguration>> newSets = new HashMap<>();
		synchronized (this) {
			for (IProject oldProject : configs.keySet()) {
				//enure there's at least an empty set for any relevant project
				//in the newSets map:
				getSet(newSets, oldProject);
			}
		}
		for (ILaunchConfiguration conf : getRelevantConfs()) {
			IProject project = BootLaunchConfigurationDelegate.getProject(conf);
			if (project!=null) {
				add(newSets, project, conf);
			}
		}
		for (Entry<IProject, Set<ILaunchConfiguration>> newEntry : newSets.entrySet()) {
			IProject newProject = newEntry.getKey();
			LiveSetVariable<ILaunchConfiguration> liveset = getVar(newProject);
			liveset.replaceAll(newEntry.getValue());
		}
	}

	private void add(Map<IProject, Set<ILaunchConfiguration>> index, IProject project, ILaunchConfiguration conf) {
		getSet(index, project).add(conf);
	}

	private Set<ILaunchConfiguration> getSet(Map<IProject, Set<ILaunchConfiguration>> index,
			IProject project) {
		Set<ILaunchConfiguration> elements = index.get(project);
		if (elements==null) {
			index.put(project, elements = new HashSet<>());
		}
		return elements;
	}

	public ObservableSet<ILaunchConfiguration> getConfigs(IProject project) {
		init();
		return getVar(project);
	}

	private LiveSetVariable<ILaunchConfiguration> getVar(IProject project) {
		LiveSetVariable<ILaunchConfiguration> existing = configs.get(project);
		if (existing==null) {
			configs.put(project, existing = new LiveSetVariable<>(AsyncMode.SYNC));
		}
		return existing;
	}

	private ImmutableSet<ILaunchConfiguration> getRelevantConfs() {
		try {
			ILaunchConfiguration[] allConfigs = launchManager.getLaunchConfigurations(launchType);
			Builder<ILaunchConfiguration> builder = ImmutableSet.builder();
			for (ILaunchConfiguration c : allConfigs) {
				if (isRelevant(c)) {
					builder.add(c);
				}
			}
			return builder.build();
		} catch (Exception e) {
			BootActivator.log(e);
			return ImmutableSet.of();
		}
	}

	private boolean isRelevant(ILaunchConfiguration c) {
		//Note: no need to check the launch conf type as only configs of the right type are passed in here.
		return !isHiddenFromBootDash(c);
	}

	@Override
	public void dispose() {
		if (launchConfListener!=null) {
			launchManager.removeLaunchConfigurationListener(launchConfListener);
			launchConfListener = null;
		}
	}

}
