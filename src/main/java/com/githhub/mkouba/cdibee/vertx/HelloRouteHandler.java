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
package com.githhub.mkouba.cdibee.vertx;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import com.githhub.mkouba.cdibee.HelloService;

import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;

/**
 * Vert.x handler used for "/hello" route in our webapp.
 *
 * @author Martin Kouba
 */
@Dependent
public class HelloRouteHandler implements Handler<RoutingContext> {

    @Inject
    HelloService helloService;

    @Override
    public void handle(RoutingContext ctx) {
        ctx.response().end(helloService.hello(ctx.request().getParam("name")));
    }

}
