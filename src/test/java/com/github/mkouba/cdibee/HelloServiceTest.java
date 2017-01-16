/*
 * Copyright 2017 Martin Kouba
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.mkouba.cdibee;

import static org.junit.Assert.assertEquals;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import org.junit.Test;

import com.githhub.mkouba.cdibee.HelloService;

/**
 *
 * @author Martin Kouba
 */
public class HelloServiceTest {

    @Test
    public void testHelloService1() {
        WeldContainer container = new Weld().initialize();
        HelloService helloService = container.select(HelloService.class).get();
        assertEquals("Hello world!", helloService.hello("world"));
        container.shutdown();
    }

    @Test
    public void testHelloService2() {
        // try-with-resources
        try (WeldContainer container = new Weld().initialize()) {
            HelloService helloService = container.select(HelloService.class).get();
            assertEquals("Hello world!", helloService.hello("world"));
        }
    }

    @Test
    public void testHelloService3() {
        // Bootstrap optimized for tests
        try (WeldContainer container = testWeld()
                .packages(HelloService.class)
                .initialize()) {
            HelloService helloService = container.select(HelloService.class).get();
            assertEquals("Hello world!", helloService.hello("world"));
        }
    }

    public static Weld testWeld() {
        // Disable discovery and concurrent deployment
        return new Weld().disableDiscovery().property("org.jboss.weld.bootstrap.concurrentDeployment", false);
    }

}