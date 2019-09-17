package com.app.bzpower.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.app.bzpower.entity.Admin;
import com.app.bzpower.entity.PageData;
import com.app.bzpower.entity.Requestlog;

public interface RequestlogMapper {

	/**
	 * 管理员查询等待审核的数据
	 * 
	 * @param requesttime
	 * @param opentime
	 * @param phone
	 * @return
	 */
	List<Requestlog> selectlogListJSONByOpentimerIsNull(@Param("status") int status, @Param("companyid") int companyid,
			@Param("requesttime") String requesttime);
	
	
	
	/**
	 * 管理员查询未审核的信息
	 * @param status
	 * @param companyid
	 * @param requesttime
	 * @return
	 */
	List<Requestlog> selectlogListJSONByOpentimerIsNotNull( @Param("companyid") int companyid,
			@Param("requesttime") String requesttime);

	
	/**
	 * 根据单位名称，电压等级分页查询日志
	 * @param pageData
	 * @param companyname
	 * @param voltage
	 * @return
	 */
	List<Requestlog> selectlogList(@Param("pageData") PageData pageData, @Param("companyId") int companyId,
			@Param("voltage") String voltage);
	
	
	/**
	 * 根据管理员id和单位id查询日志
	 * @param adminId
	 * @param companyId
	 * @return
	 */
	List<Requestlog> selectLogHositoryListByAdminIdAndCompanyId(@Param("adminId")int adminId,@Param("companyId") int companyId);

	
	/**
	 * 根据主键查询日志
	 * @param id
	 * @return
	 */
	Requestlog selectByPrimaryKey(@Param("id") int id);

	/**
	 * 用户根据电话号码和单位id查询自己的使用锁的历史记录
	 * @param phone
	 * @param companyId
	 * @return
	 */
	List<Requestlog> selectLogHositoryList(@Param("phone") String phone, @Param("companyId") int companyId);
	
	
	/**
	 * 用户根据用户电话号码，请求时间，申请状态查询日志集合
	 * @param phone
	 * @param requesttime
	 * @param status
	 * @return
	 */
	List<Requestlog> selectRequestLogByOpentimeIsNull(@Param("phone") String phone,
			@Param("requesttime")  String requesttime, @Param("status") int status);
	
	
	
	/**
	 * 用户根据电话号码查询已经开锁的日志集合
	 * @param phone
	 * @param requesttime
	 * @return
	 */
	List<Requestlog> selectRequestLogByOpentimeIsNotNull(@Param("phone") String phone,
			@Param("requesttime")  String requesttime);

	
	/**
	 * 查询日志总数
	 * @param example
	 * @return
	 */
	long countByExample(Admin example);

	
	/**
	 * 选择更新日志
	 * @param requestlog
	 * @return
	 */
	int updateByPrimaryKeySelective(@Param("requestlog") Requestlog requestlog);

	/**
	 * 新增日志信息
	 * @param record
	 * @return
	 */
	int insertSelective(@Param("record") Requestlog record);

//    /**
//     * This method was generated by MyBatis Generator.
//     * This method corresponds to the database table requestlog
//     *
//     * @mbg.generated Thu Aug 22 17:35:27 CST 2019
//     */
//    
//
//    /**
//     * This method was generated by MyBatis Generator.
//     * This method corresponds to the database table requestlog
//     *
//     * @mbg.generated Thu Aug 22 17:35:27 CST 2019
//     */
//    int deleteByExample(Requestlog example);
//
//    /**
//     * This method was generated by MyBatis Generator.
//     * This method corresponds to the database table requestlog
//     *
//     * @mbg.generated Thu Aug 22 17:35:27 CST 2019
//     */
//    int deleteByPrimaryKey(Integer id);
//
//    /**
//     * This method was generated by MyBatis Generator.
//     * This method corresponds to the database table requestlog
//     *
//     * @mbg.generated Thu Aug 22 17:35:27 CST 2019
//     */
//    int insert(Requestlog record);
//
//    /**
//     * This method was generated by MyBatis Generator.
//     * This method corresponds to the database table requestlog
//     *
//     * @mbg.generated Thu Aug 22 17:35:27 CST 2019
//     */
//    
//
//    /**
//     * This method was generated by MyBatis Generator.
//     * This method corresponds to the database table requestlog
//     *
//     * @mbg.generated Thu Aug 22 17:35:27 CST 2019
//     */
//    List<Requestlog> selectByExample(Requestlog example);
//
//    /**
//     * This method was generated by MyBatis Generator.
//     * This method corresponds to the database table requestlog
//     *
//     * @mbg.generated Thu Aug 22 17:35:27 CST 2019
//     */
//    Requestlog selectByPrimaryKey(Integer id);
//
//    /**
//     * This method was generated by MyBatis Generator.
//     * This method corresponds to the database table requestlog
//     *
//     * @mbg.generated Thu Aug 22 17:35:27 CST 2019
//     */
//    int updateByExampleSelective(@Param("record") Requestlog record, @Param("example") Requestlog example);
//
//    /**
//     * This method was generated by MyBatis Generator.
//     * This method corresponds to the database table requestlog
//     *
//     * @mbg.generated Thu Aug 22 17:35:27 CST 2019
//     */
//    int updateByExample(@Param("record") Requestlog record, @Param("example") Requestlog example);
//
//    /**
//     * This method was generated by MyBatis Generator.
//     * This method corresponds to the database table requestlog
//     *
//     * @mbg.generated Thu Aug 22 17:35:27 CST 2019
//     */
//    int updateByPrimaryKeySelective(Requestlog record);
//
//    /**
//     * This method was generated by MyBatis Generator.
//     * This method corresponds to the database table requestlog
//     *
//     * @mbg.generated Thu Aug 22 17:35:27 CST 2019
//     */
//    int updateByPrimaryKey(Requestlog record);
}