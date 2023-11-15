package com.mohsen.springtunes.dao;

import com.mohsen.springtunes.entity.Role;

public interface RoleDAO {

    public Role findRoleByName(String theRoleName);

}