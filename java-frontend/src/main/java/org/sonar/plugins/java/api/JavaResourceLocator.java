/*
 * SonarQube Java
 * Copyright (C) 2012-2016 SonarSource SA
 * mailto:contact AT sonarsource DOT com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package org.sonar.plugins.java.api;

import com.google.common.annotations.Beta;

import org.sonar.api.batch.BatchSide;
import org.sonar.api.batch.fs.InputFile;

import javax.annotation.CheckForNull;

import java.io.File;
import java.util.Collection;

/**
 * Interface to get the mapping between java classes and files (as multiple classes can be declared in one file).
 */
@Beta
@BatchSide
public interface JavaResourceLocator extends JavaFileScanner {

  /**
   * Retrieve a SonarQube resource by the class name.
   * @param className fully qualified name of the analyzed class
   * @return null if not found
   */
  @CheckForNull
  InputFile findResourceByClassName(String className);

  /**
   * get source file key by class name.
   * @deprecated since 4.1 : will be dropped with no replacement.
   * @param className fully qualified name of the analyzed class.
   * @return key of the source file for the given class.
   */
  @Deprecated
  String findSourceFileKeyByClassName(String className);

  /**
   * fully qualified names of the classes to be analyzed by sonar java analyzer.
   * @deprecated since 4.1 : used by bytecode visitor scanner and should be dropped when it is removed.
   * @return collection of fully qualified names.
   */
  @Deprecated
  Collection<String> classKeys();

  /**
   * .class files to be analyzed.
   * Used by the findbugs plugin.
   * @return a list of .class files corresponding to the source files to be analyzed.
   */
  Collection<File> classFilesToAnalyze();

  /**
   * Classpath configured for the project.
   * This classpath method is used by the findbugs plugin to configure the analysis.
   * @return the list of jar and class files constituting the classpath of the analyzed project.
   */
  Collection<File> classpath();

  /**
   * Get the first line of a method definition.
   * @deprecated since 4.1 : used by bytecode visitor scanner and should be dropped when it is removed.
   * @param fullyQualifiedMethodName the full name of the method.
   * @return the line number where this method is defined.
   */
  @Deprecated
  Integer getMethodStartLine(String fullyQualifiedMethodName);
}
