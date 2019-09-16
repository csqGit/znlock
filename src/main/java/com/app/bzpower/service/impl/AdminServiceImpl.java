package com.app.bzpower.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.bzpower.dao.AdminMapper;
import com.app.bzpower.dao.RequestlogMapper;
import com.app.bzpower.dao.TransformersubMapper;
import com.app.bzpower.entity.Admin;
import com.app.bzpower.entity.PageData;
import com.app.bzpower.entity.Requestlog;
import com.app.bzpower.entity.TransformerSub;
import com.app.bzpower.service.AdminService;

/**
 * 用户Service
 * 
 * @author Admin
 *
 */
@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminMapper adminMapper;
	
	@Autowired
	private RequestlogMapper requestlogMapper;
	
	@Autowired
	private TransformersubMapper transformersubMapper;


	public int insertAdmin(Admin record) {
		try {
			int result = adminMapper.insertSelective(record);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public Admin adminLogin(Admin admin) {
		
		List<Admin> adminList = adminMapper.selectByExample(admin);
		if(adminList.size() > 0)
			return adminList.get(0);
		else
		return null;
	}

	public List<Admin> selectAdminList(PageData pageData, Admin admin) {
		
		List<Admin> adminList = null;
		try {
			adminList = adminMapper.selectAdminList(pageData, admin);
			return adminList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Admin selectAdminById(int id) {
		try {
			Admin admin = adminMapper.selectByPrimaryKey(id);
			return admin;
		} catch (Exception e) {
			return null;
		}
	}

	public List<Admin> selectAdminByName(String username) {
		Admin example = new Admin();
		example.setUsername(username);
		
		return adminMapper.selectByExample(example);
	}

	public int deleteAdmin(int id) {
		try {
			return adminMapper.deleteByPrimaryKey(id);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public int updateAdmin(Admin record) {
		try {
			return adminMapper.updateByPrimaryKeySelective(record);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}

	}

	public Admin selectAdmin(Admin admin) {
		// TODO Auto-generated method stub
		return null;
	}

	public Admin selectAdminByPhone(String phone) {
		try {
			return adminMapper.selectAdminByPhone(phone);
		} catch (Exception e) {
			e.printStackTrace();
			return null; 
		}
	}

	public List<Admin> selectAdminByVoltage(String voltage) {
		// TODO Auto-generated method stub
		return null;
	}

	public int selectPages() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int selectCount(Admin admin) {
		
		return adminMapper.countByExample(admin);
	}

	public int updatePassword(Admin admin) {
		try {
			return adminMapper.updatePasswordByUsername(admin);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		
	}

	public Admin selectAdminByUsername(String username) {
		try {
			return adminMapper.selectAdminByUsername(username);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Admin> selectAdminByCompany(int companyId) {
		try {
			return adminMapper.selectAdminByCompany(companyId);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Requestlog> selectLogHositoryList(int adminId, int companyId) {
		try {
			return requestlogMapper.selectLogHositoryListByAdminIdAndCompanyId(adminId, companyId);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<TransformerSub> selectTransformerSubListByCompanyId(int companyId) {
		try {
			return transformersubMapper.selectTransformerSubListByCompanyId(companyId);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
