package com.app.bzpower.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.bzpower.dao.AdminMapper;
import com.app.bzpower.dao.RequestlogMapper;
import com.app.bzpower.entity.Admin;
import com.app.bzpower.entity.PageData;
import com.app.bzpower.entity.Requestlog;
import com.app.bzpower.service.RequestlogService;
import com.app.bzpower.util.DateFormatUtils;
import com.app.bzpower.util.DefaultUtils;

@Service
public class RequestLogServiceImpl implements RequestlogService {

	@Autowired
	private RequestlogMapper requestlogMapper;

	@Autowired
	private AdminMapper adminMapper;

	private int maxResult = DefaultUtils.maxResult;

	/**
	 * 管理员查询申请信息app
	 */
	public List<Requestlog> adminSelectlogList(String requesttime, String param, String phone) {
		try {
			Admin admin = adminMapper.selectAdminByPhone(phone);
			if (admin != null) {
				int status = 0;
				int companyId = admin.getCompanyId().getId();
				requesttime = requesttime.substring(0, 10);
				if ("audited".equals(param)) {// 已经审核的日志
					return requestlogMapper.selectlogListJSONByOpentimerIsNotNull( companyId, requesttime);
					
				} else {
					return requestlogMapper.selectlogListJSONByOpentimerIsNull(status, companyId, requesttime);
				}
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 用户查询审核信息app
	 */
	public List<Requestlog> selectRequestLogList(String requesttime, String phone, String examinetype) {

		try {
			requesttime = DateFormatUtils.dateFormat(requesttime);
			if ("selectWaitExamine".equals(examinetype)) {// 查询当天未审核的请求信息
				
				return requestlogMapper.selectRequestLogByOpentimeIsNull(phone, requesttime, 0);
			} else if ("selectAlreadyExamine".equals(examinetype)) {// 查询当天已经审核 模糊查询
				
				return requestlogMapper.selectRequestLogByOpentimeIsNotNull(phone, requesttime);
			} else if ("selectThisExamine".equals(examinetype)) {// 当天的全部待审核信息和已审核信息 模糊查询
				
			} else if ("selectRequesttime".equals(examinetype)) {// 刚提交的待审核信息
			} else if ("selectExamineAll".equals(examinetype)) {// 查询所有时间的请求信息和审核信息

			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 保存用户请求信息app
	 */
	public void insertRequestlog(Requestlog record) {
		try {
			requestlogMapper.insertSelective(record);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 删除用户请求日志信息
	 */
	public void deleteRequestlog(int id) {
//		requestlogMapper.deleteByPrimaryKey(id);
	}

	/**
	 * 更新用户请求日志信息app
	 */
	public void updateRequestlog(Requestlog requestlog) {
		try {
			requestlogMapper.updateByPrimaryKeySelective(requestlog);
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	/**
	 * 根据id查询请求日志信息app
	 */
	public Requestlog selectRequestLogById(Integer id) {
		try {
			return requestlogMapper.selectByPrimaryKey(id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 查询请求日志pc admin查询
	 */
	public List<Requestlog> selectRequestLogList(PageData pageData, int companyId, String voltage) {
		List<Requestlog> list = null;
		try {
			list = requestlogMapper.selectlogList(pageData, companyId, voltage);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * pc admin查询总页数
	 */
	public int selectRequestLogPages(String voltage) {
//		example.createCriteria().andVoltageEqualTo(voltage);
//		int pages = (int) requestlogMapper.countByExample(example);
		int pages = 0;
		if (maxResult == 0)
			maxResult = 10;
		int result = pages / maxResult;
		if (pages % maxResult != 0)
			result++;
		return result;
	}

	/**
	 * pc user根据用户名查询总页数
	 */
	public int selectRequestLogByUserNamePages(String phone) {
//		Requestlog example = new Requestlog();
//		example.createCriteria().andPhoneEqualTo(phone);
//		List<Requestlog> list = requestlogMapper.selectByExample(example);
		int count = 12;
		int pages = count / maxResult;
		if (count % maxResult != 0)
			pages++;
		return pages;
	}

	/**
	 * 管理员查询所管理的请求日志信息， 根据电压等级来区分
	 */
	public long selectCountByExample(Admin admin) {

		return requestlogMapper.countByExample(admin);
	}

	/**
	 * 用户查询自己的请求日志信息
	 */
	public int selectCountByUser(String phone) {
//		Requestlog example = new Requestlog();
//		example.createCriteria().andPhoneEqualTo(phone);
//		return (int) requestlogMapper.countByExample(example);
		return 0;
	}

	public List<Requestlog> selectLogHositoryList(String voltage) {
//		Requestlog example = new Requestlog();
//		com.app.bzpower.entity.Requestlog.Criteria criteria = example.createCriteria();
//		criteria.andVoltageEqualTo(voltage);
//		example.setOrderByClause("requesttime desc");
//		List<Requestlog> logList = requestlogMapper.selectByExample(example);
		return null;

	}

}
