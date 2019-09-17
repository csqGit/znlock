package com.app.bzpower.dao;

import java.util.List;

import com.app.bzpower.entity.Company;

public interface CompanyMapper {
	
	Company selectCompanyByPrimaryKey(int id);
	
	
	Company selectCompanyByPrimaryCompanyname(String companyname);
	
	List<Company> selectCompany();

//	/**
//	 * This method was generated by MyBatis Generator. This method corresponds to the database table company
//	 * @mbg.generated  Thu Aug 22 17:02:51 CST 2019
//	 */
//	long countByExample(Company example);
//
//	/**
//	 * This method was generated by MyBatis Generator. This method corresponds to the database table company
//	 * @mbg.generated  Thu Aug 22 17:02:51 CST 2019
//	 */
//	int deleteByExample(Company example);
//
//	/**
//	 * This method was generated by MyBatis Generator. This method corresponds to the database table company
//	 * @mbg.generated  Thu Aug 22 17:02:51 CST 2019
//	 */
//	int deleteByPrimaryKey(Integer id);
//
//	/**
//	 * This method was generated by MyBatis Generator. This method corresponds to the database table company
//	 * @mbg.generated  Thu Aug 22 17:02:51 CST 2019
//	 */
//	int insert(Company record);
//
//	/**
//	 * This method was generated by MyBatis Generator. This method corresponds to the database table company
//	 * @mbg.generated  Thu Aug 22 17:02:51 CST 2019
//	 */
//	int insertSelective(Company record);
//
//	/**
//	 * This method was generated by MyBatis Generator. This method corresponds to the database table company
//	 * @mbg.generated  Thu Aug 22 17:02:51 CST 2019
//	 */
//	List<Company> selectByExample(Company example);
//
//	/**
//	 * This method was generated by MyBatis Generator. This method corresponds to the database table company
//	 * @mbg.generated  Thu Aug 22 17:02:51 CST 2019
//	 */
//	Company selectByPrimaryKey(Integer id);
//
//	/**
//	 * This method was generated by MyBatis Generator. This method corresponds to the database table company
//	 * @mbg.generated  Thu Aug 22 17:02:51 CST 2019
//	 */
//	int updateByExampleSelective(@Param("record") Company record, @Param("example") Company example);
//
//	/**
//	 * This method was generated by MyBatis Generator. This method corresponds to the database table company
//	 * @mbg.generated  Thu Aug 22 17:02:51 CST 2019
//	 */
//	int updateByExample(@Param("record") Company record, @Param("example") Company example);
//
//	/**
//	 * This method was generated by MyBatis Generator. This method corresponds to the database table company
//	 * @mbg.generated  Thu Aug 22 17:02:51 CST 2019
//	 */
//	int updateByPrimaryKeySelective(Company record);
//
//	/**
//	 * This method was generated by MyBatis Generator. This method corresponds to the database table company
//	 * @mbg.generated  Thu Aug 22 17:02:51 CST 2019
//	 */
//	int updateByPrimaryKey(Company record);
}