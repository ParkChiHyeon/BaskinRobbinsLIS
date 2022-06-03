package com.br.lis.model.member.mapper;

import java.util.Map;

import com.br.lis.vo.AdminVo;

public interface IAdminDao {
	
	public AdminVo loginAdmin(Map<String, Object> map);
	
}
