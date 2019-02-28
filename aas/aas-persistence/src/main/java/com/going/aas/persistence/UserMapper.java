package com.going.aas.persistence;

import org.apache.ibatis.annotations.Param;

import com.going.aas.domain.User;

//@Mapper
public interface UserMapper {

    User selectByUsername(@Param(value = "username") String username);
}
