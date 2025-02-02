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

package cn.sliew.scaleph.application.doris.service.param;

import cn.sliew.scaleph.application.doris.operator.spec.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class WsDorisOperatorInstanceAddParam {

    @NotNull
    @Schema(description = "project id")
    private Long projectId;

    @NotNull
    @Schema(description = "cluster credential id")
    private Long clusterCredentialId;

    @NotBlank
    @Schema(description = "name")
    private String name;

    @NotBlank
    @Schema(description = "namespace")
    private String namespace;

    @Schema(description = "admin user")
    private AdminUser admin;

    @NotNull
    @Schema(description = "fe spec")
    private FeSpec feSpec;

    @Schema(description = "be spec")
    private BeSpec beSpec;

    @Schema(description = "cn spec")
    private CnSpec cnSpec;

    @Schema(description = "broker spec")
    private BrokerSpec brokerSpec;

    @Schema(description = "remark")
    private String remark;
}
