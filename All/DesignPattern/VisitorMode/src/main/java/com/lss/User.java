package com.lss;

import com.Visitor;

/**
 * @author 10380
 */
public abstract class User {
    private String roleName;
    private String className;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public User(String roleName, String className) {
        this.roleName = roleName;
        this.className = className;
    }

    public abstract void accpect(Visitor visitor);

}
