/*
 * Copyright 2016 DiffPlug
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
package com.diffplug.gradle.spotless;

import java.io.IOException;

import org.junit.Test;

public class Antlr4ExtensionTest extends GradleIntegrationTest {

	@Test
	public void formatSingleGrammar() throws IOException {
		String testFile = "src/main/antlr4/Hello.g4";

		setFile("build.gradle").toLines(
				"plugins {",
				"    id 'com.diffplug.gradle.spotless'",
				"}",
				"spotless {",
				"    antlr4 {",
				"        target 'src/main/antlr4/**/*.g4'",
				"    }",
				"}");

		String unformatted = "antlr4/Hello.unformatted.g4";
		String formatted = "antlr4/Hello.formatted.g4";
		setFile(testFile).toResource(unformatted);

		gradleRunner().withArguments("spotlessApply").build();

		assertFile(testFile).sameAsResource(formatted);
	}
}
