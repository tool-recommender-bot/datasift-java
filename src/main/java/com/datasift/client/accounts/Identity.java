package com.datasift.client.accounts;

import com.datasift.client.BaseDataSiftResult;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author Courtney Robinson <courtney@crlog.info>
 */
public class Identity extends BaseDataSiftResult {
    protected String id;
    @JsonProperty("api_key")
    protected String apiKey;
    protected String label;
    protected IdentityStatus status;
    protected boolean master;
    @JsonProperty("created_at")
    protected long createdAt;
    @JsonProperty("updated_at")
    protected long updatedAt;
    @JsonProperty("expires_at")
    protected long expiresAt;

    public String id() {
        return id;
    }

    public String apiKey() {
        return apiKey;
    }

    public String label() {
        return label;
    }

    public IdentityStatus status() {
        return status;
    }

    public boolean isMaster() {
        return master;
    }

    public long createdAt() {
        return createdAt;
    }

    public long updatedAt() {
        return updatedAt;
    }

    public long expiresAt() {
        return expiresAt;
    }

    public Date createdAtAsDate() {
        return new Date(TimeUnit.SECONDS.toMillis(createdAt));
    }

    public Date updatedAtAsDate() {
        return new Date(TimeUnit.SECONDS.toMillis(updatedAt));
    }

    public Date expiresAtAsDate() {
        return new Date(TimeUnit.SECONDS.toMillis(expiresAt));
    }

    public static class IdentityStatus {
        private final String value;

        @JsonCreator
        IdentityStatus(String name) {
            this.value = name;
        }

        public String value() {
            return value;
        }
    }
}
