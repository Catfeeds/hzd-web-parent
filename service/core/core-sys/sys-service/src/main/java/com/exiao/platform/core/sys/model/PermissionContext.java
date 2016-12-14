package com.exiao.platform.core.sys.model;



import java.io.Serializable;
import java.util.Set;

/**
 * <p>User: xiaojun
 * <p>Date: 
 * <p>Version: 1.0
 */
public class PermissionContext implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Set<String> roles;
    private Set<String> permissions;

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public Set<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<String> permissions) {
        this.permissions = permissions;
    }


    @Override
    public String toString() {
        return "PermissionContext{" +
                ", roles=" + roles +
                ", permissions=" + permissions +
                '}';
    }
}
