/**
 *
 * Copyright (C) 2009 Cloud Conscious, LLC. <info@cloudconscious.com>
 *
 * ====================================================================
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
 * ====================================================================
 */
package org.jclouds.aws.ec2.compute;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutorService;

import org.jclouds.aws.ec2.EC2AsyncClient;
import org.jclouds.aws.ec2.EC2Client;
import org.jclouds.aws.ec2.compute.config.EC2ComputeServiceContextModule;
import org.jclouds.aws.ec2.config.EC2RestClientModule;
import org.jclouds.compute.ComputeServiceContextBuilder;
import org.jclouds.http.config.JavaUrlHttpCommandExecutorServiceModule;
import org.jclouds.logging.jdk.config.JDKLoggingModule;

import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.TypeLiteral;

/**
 * Creates {@link EC2ComputeServiceContext} or {@link Injector} instances based on the most commonly
 * requested arguments.
 * <p/>
 * Note that Threadsafe objects will be bound as singletons to the Injector or Context provided.
 * <p/>
 * <p/>
 * If no <code>Module</code>s are specified, the default {@link JDKLoggingModule logging} and
 * {@link JavaUrlHttpCommandExecutorServiceModule http transports} will be installed.
 * 
 * @author Adrian Cole
 * @see EC2ComputeServiceContext
 */
public class EC2ComputeServiceContextBuilder extends
         ComputeServiceContextBuilder<EC2AsyncClient, EC2Client> {

   public EC2ComputeServiceContextBuilder(Properties props) {
      super(new TypeLiteral<EC2AsyncClient>() {
      }, new TypeLiteral<EC2Client>() {
      }, props);
   }

   @Override
   public EC2ComputeServiceContextBuilder withExecutorService(ExecutorService service) {
      return (EC2ComputeServiceContextBuilder) super.withExecutorService(service);
   }

   @Override
   public EC2ComputeServiceContextBuilder withModules(Module... modules) {
      return (EC2ComputeServiceContextBuilder) super.withModules(modules);
   }

   @Override
   protected void addContextModule(List<Module> modules) {
      modules.add(new EC2ComputeServiceContextModule());
   }

   @Override
   protected void addClientModule(List<Module> modules) {
      modules.add(new EC2RestClientModule());
   }

}
