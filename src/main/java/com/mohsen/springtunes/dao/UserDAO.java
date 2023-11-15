package com.mohsen.springtunes.dao;

import com.mohsen.springtunes.entity.User;

public interface UserDAO {

    User findByUserName(String userName);

}
