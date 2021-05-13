package com.incs83.hrm.common;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class RoleRequest {

    private UUID id;
    private String name;
    private String description;
    private String permission;
    private Set<UUID> userIds = new HashSet<>();

    public Set<UUID> getUserIds() {
        return userIds;
    }

    public void setUserIds(Set<UUID> userIds) {
        this.userIds = userIds;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
}
