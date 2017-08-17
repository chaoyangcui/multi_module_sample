/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sssarm.zookeeper;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

public abstract class ClientBase {
    protected static final Logger LOG = LoggerFactory.getLogger(ClientBase.class);

    public static int CONNECTION_TIMEOUT = 30000;
    static final File BASETEST =
            new File(System.getProperty("build.test.dir", "build"));

    public ClientBase() {
        super();
    }

    public static File createTmpDir() throws IOException {
        return createTmpDir(BASETEST);
    }

    static File createTmpDir(File parentDir) throws IOException {
        File tmpFile = File.createTempFile("test", ".junit", parentDir);
        // don't delete tmpFile - this ensures we don't attempt to create
        // a tmpDir with a duplicate name
        File tmpDir = new File(tmpFile + ".dir");
        Assert.assertFalse(tmpDir.exists()); // never true if tmpfile does it's job
        Assert.assertTrue(tmpDir.mkdirs());

        return tmpDir;
    }

}
