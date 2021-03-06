/*
 * Copyright 2016 OmniFaces
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
 */
package org.omnifaces.test.exceptionhandler.fullajaxexceptionhandler;

import static org.jboss.arquillian.graphene.Graphene.guardAjax;
import static org.junit.Assert.assertTrue;
import static org.omnifaces.test.OmniFacesIT.ArchiveBuilder.buildWebArchive;
import static org.omnifaces.test.OmniFacesIT.FacesConfig.withFullAjaxExceptionHandler;
import static org.omnifaces.test.OmniFacesIT.WebXml.withErrorPage;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.omnifaces.test.OmniFacesIT;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FullAjaxExceptionHandlerIT extends OmniFacesIT {

	@FindBy(id="exception")
	private WebElement exception;

	@FindBy(id="form:throwDuringInvokeApplication")
	private WebElement throwDuringInvokeApplication;

	@FindBy(id="form:throwDuringUpdateModelValues")
	private WebElement throwDuringUpdateModelValues;

	@FindBy(id="form:throwDuringRenderResponse")
	private WebElement throwDuringRenderResponse;

	@Deployment(testable=false)
	public static WebArchive createDeployment() {
		return buildWebArchive(FullAjaxExceptionHandlerIT.class)
			.withFacesConfig(withFullAjaxExceptionHandler)
			.withWebXml(withErrorPage)
			.createDeployment();
	}

	@Test
	public void throwDuringInvokeApplication() {
		guardAjax(throwDuringInvokeApplication).click();
		assertTrue(exception.getText().contains("throwDuringInvokeApplication"));
	}

	@Test
	public void throwDuringUpdateModelValues() {
		guardAjax(throwDuringUpdateModelValues).click();
		assertTrue(exception.getText().contains("throwDuringUpdateModelValues"));
	}

	@Test
	public void throwDuringRenderResponse() {
		guardAjax(throwDuringRenderResponse).click();
		assertTrue(exception.getText().contains("throwDuringRenderResponse"));
	}

}