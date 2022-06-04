package com.br.lis.model.member.service;

import java.util.Map;

import com.br.lis.vo.AdminVo;

public interface IAdminService {
	
	public AdminVo loginAdmin(Map<String, Object> map);
	
}
