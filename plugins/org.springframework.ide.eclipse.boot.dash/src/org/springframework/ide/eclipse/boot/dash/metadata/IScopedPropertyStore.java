/*******************************************************************************
 * Copyright (c) 2015 Pivotal, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Pivotal, Inc. - initial API and implementation
 *******************************************************************************/
package org.springframework.ide.eclipse.boot.dash.metadata;

/**
 * An instance of this provides a way to store and retrieve
 * properties associated with some 'scope' object of type T.
 * <p>
 * The properties are persisted somehow.
 */
public interface IScopedPropertyStore<T> {
	String get(T scope, String key);
	void put(T scope, String key, String value) throws Exception;
}
