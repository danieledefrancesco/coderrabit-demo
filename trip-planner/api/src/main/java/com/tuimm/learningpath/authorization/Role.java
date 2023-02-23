package com.tuimm.learningpath.authorization;

public enum Role {
    UNAUTHENTICATED {
        @Override
        public Role getParentRole() {
            return null;
        }
    },
    OPERATOR {
        @Override
        public Role getParentRole() {
            return UNAUTHENTICATED;
        }
    }, MANAGER {
        @Override
        public Role getParentRole() {
            return OPERATOR;
        }
    };

    public abstract Role getParentRole();
}
