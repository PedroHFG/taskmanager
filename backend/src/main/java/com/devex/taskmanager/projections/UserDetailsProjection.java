package com.devex.taskmanager.projections;

public interface UserDetailsProjection {
    String getUsername();
	String getPassword();
	Long getRoleId();
	String getAuthority();
}
