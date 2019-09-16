package com.app.bzpower.service;

import java.util.List;

import com.app.bzpower.entity.Admin;
import com.app.bzpower.entity.Company;
import com.app.bzpower.entity.PageData;
import com.app.bzpower.entity.Requestlog;
import com.app.bzpower.entity.User;

public interface UserService {
	
	/**
	 * 用户登录
	 * @param user
	 * @return
	 */
	public User selectUser(User user);
	
	
	public User selectUserByPhone(String phone);
	
	
	/**
	 * 新增用户
	 */
	int insertUser(User user);
//	
	/**
	 * 查询用户
	 * @param page
	 * @param maxResult
	 * @return
	 */
	List<User> selectUserList(PageData pageData, Admin admin);
	
	
	/**
	 * 根据电压等级查询用户
	 * @param voltage
	 * @return
	 */
	List<User> selectUserListByCompanyId(String voltage, int companyId);
	
	
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	User selectUserById(int id);
	
	/**
	 * 根据username查询
	 * @param UserName
	 * @return
	 */
	User selectUserByUsername(String username);

	
	/**
	 * 用户总数
	 * @return
	 */
	
	long  countByExample(String companyname, String voltage);
	
	
	/**
	 * 
	 * @param phone 根据用户的电话号码查询
	 * @param voltage 根据用户的 电压等级查询
	 * @return
	 */
	public List<Requestlog> selectLogHositoryList(String phone, int companyId);
	
	
	/**
	 * 查询单位名称
	 * @return
	 */
	List<Company> selectCompany();
	
	
	/**
	 * 删除用户根据id
	 * @param id
	 */
	int deleteUser(int id);
	
	/**
	 * 更新用户
	 * @param User
	 */
	int updateUser(User user);

}
