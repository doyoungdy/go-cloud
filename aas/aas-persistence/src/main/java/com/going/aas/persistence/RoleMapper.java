package com.going.aas.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.going.aas.domain.Role;

//@Mapper
public interface RoleMapper {
	
	  List<Role> getRoleByUserId(@Param(value = "userId") String userId);

}
