/*
 * Copyright (C) 2007-2008 JVending Masa
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.jayway.maven.plugins.android.standalonemojos;

import com.jayway.maven.plugins.android.CommandExecutor;
import com.jayway.maven.plugins.android.ExecutionException;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Copy file/dir from device.
 * @goal pull
 * @requiresProject false
 */
public class PullMojo extends AbstractMojo {

    /**
     * @parameter expression="${source}"
     * @required
     */
    private File source;

    /**
     * @parameter expression="${destination}"
     * @required
     */
    private File destination;

    public void execute() throws MojoExecutionException, MojoFailureException {
        CommandExecutor executor = CommandExecutor.Factory.createDefaultCommmandExecutor();
        executor.setLogger(this.getLog());

        List<String> commands = new ArrayList<String>();
        commands.add("pull");
        commands.add(source.getAbsolutePath());
        commands.add(destination.getAbsolutePath());

        getLog().info("adb " + commands.toString());
        try {
            executor.executeCommand("adb", commands);
        } catch (ExecutionException e) {
            throw new MojoExecutionException("Pull failed.", e);
        }
    }
}