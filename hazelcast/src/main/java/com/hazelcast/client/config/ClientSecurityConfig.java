/*
 * Copyright (c) 2008-2020, Hazelcast, Inc. All Rights Reserved.
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

package com.hazelcast.client.config;

import java.util.Objects;

import com.hazelcast.config.CredentialsFactoryConfig;
import com.hazelcast.config.security.CredentialsIdentityConfig;
import com.hazelcast.config.security.IdentityConfig;
import com.hazelcast.config.security.TokenIdentityConfig;
import com.hazelcast.config.security.UsernamePasswordIdentityConfig;
import com.hazelcast.security.Credentials;
import com.hazelcast.security.ICredentialsFactory;

/**
 * Contains the security configuration for the client.
 */
public class ClientSecurityConfig {

    private IdentityConfig identityConfig;

    public ClientSecurityConfig() {
    }

    public ClientSecurityConfig(ClientSecurityConfig securityConfig) {
        if (securityConfig.identityConfig == null) {
            identityConfig = null;
        } else {
            identityConfig = securityConfig.identityConfig.copy();
        }
    }

    public UsernamePasswordIdentityConfig getUsernamePasswordIdentityConfig() {
        return getIfType(identityConfig, UsernamePasswordIdentityConfig.class);
    }

    public ClientSecurityConfig setUsernamePasswordIdentityConfig(UsernamePasswordIdentityConfig identityConfig) {
        this.identityConfig = identityConfig;
        return this;
    }

    public ClientSecurityConfig setUsernamePasswordIdentityConfig(String username, String password) {
        this.identityConfig = new UsernamePasswordIdentityConfig(username, password);
        return this;
    }

    public TokenIdentityConfig getTokenIdentityConfig() {
        return getIfType(identityConfig, TokenIdentityConfig.class);
    }

    public ClientSecurityConfig setTokenIdentityConfig(TokenIdentityConfig identityConfig) {
        this.identityConfig = identityConfig;
        return this;
    }

    public CredentialsIdentityConfig getCredentialsIdentityConfig() {
        return getIfType(identityConfig, CredentialsIdentityConfig.class);
    }

    public ClientSecurityConfig setCredentialsIdentityConfig(CredentialsIdentityConfig identity) {
        this.identityConfig = identity;
        return this;
    }

    public ClientSecurityConfig setCredentials(Credentials credentials) {
        this.identityConfig = new CredentialsIdentityConfig(credentials);
        return this;
    }

    public CredentialsFactoryConfig getCredentialsFactoryConfig() {
        return getIfType(identityConfig, CredentialsFactoryConfig.class);
    }

    public ClientSecurityConfig setCredentialsFactoryConfig(CredentialsFactoryConfig identityConfig) {
        this.identityConfig = identityConfig;
        return this;
    }

    public ICredentialsFactory asCredentialsFactory(ClassLoader cl) {
        return identityConfig != null ? identityConfig.asCredentialsFactory(cl) : null;
    }

    public boolean hasIdentityConfig() {
        return identityConfig != null;
    }

    @Override
    public String toString() {
        return "ClientSecurityConfig{"
                + "identityConfig=" + identityConfig
                + '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(identityConfig);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        ClientSecurityConfig other = (ClientSecurityConfig) obj;
        return Objects.equals(identityConfig, other.identityConfig);
    }

    private <T> T getIfType(Object inst, Class<T> clazz) {
        return clazz.isInstance(inst) ? clazz.cast(inst) : null;
    }
}
