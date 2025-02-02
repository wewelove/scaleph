/*
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

package cn.sliew.scaleph.application.flink.resource.handler;

import cn.sliew.scaleph.application.flink.operator.spec.FlinkDeploymentSpec;
import cn.sliew.scaleph.application.flink.operator.util.TemplateMerger;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class LoggingHandler {

    public void handle(Map<String, String> logConfiguration, FlinkDeploymentSpec spec) {
        Map<String, String> configuration = Optional.ofNullable(spec.getFlinkConfiguration()).orElse(new HashMap<>());
        Map<String, String> merge = TemplateMerger.merge(configuration, logConfiguration, Map.class);
        if (CollectionUtils.isEmpty(merge) == false) {
            spec.setLogConfiguration(null);
        } else {
            spec.setLogConfiguration(merge);
        }
    }

}
