package com.app.bzpower.controller.app;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.bzpower.entity.Admin;
import com.app.bzpower.entity.Company;
import com.app.bzpower.entity.Lockinfo;
import com.app.bzpower.entity.RequestResult;
import com.app.bzpower.entity.Requestlog;
import com.app.bzpower.entity.TransformerSub;
import com.app.bzpower.entity.User;
import com.app.bzpower.service.AdminService;
import com.app.bzpower.service.CompanyService;
import com.app.bzpower.service.LockService;
import com.app.bzpower.service.RequestlogService;
import com.app.bzpower.service.TransformerSubService;
import com.app.bzpower.service.UserService;
import com.app.bzpower.util.JPushSQUtils;
import com.app.bzpower.util.JPushUtils;
import com.app.bzpower.util.Md5Utils;

/**
 * 用户app控制类，主要的功能有： 
 * 1、用户app的登录 userLogin 
 * 2、用户注册userRegister
 * 3、用户申请开锁insertRequestlog 
 * 4、用户查询开锁历史记录selectLogHostoryList 
 * 5、用户查询智能锁信息 selectLock 
 * 6、用户查询审核结果 selectExamineList
 */
@Controller("appController")
@RequestMapping("/appUser")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private LockService lockService;

	@Autowired
	private RequestlogService requestlogService;

	@Autowired
	private AdminService adminService;
	
	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private TransformerSubService transformersubService;

//	

	/**
	 * {@link http://localhost:8080/bozpower/appUser/userLogin?phone=17609346217&password=123456}
	 * 
	 * @用户app登录
	 *
	 * @param user
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/userLogin", produces = "application/json;charset=utf-8")
	@ResponseBody
	@CrossOrigin(origins = "*", maxAge = 3600)
	public RequestResult userLogin(User user) {
		Map map = new HashMap();
		RequestResult rr = new RequestResult();
		try {
			User oldUser = userService.selectUserByPhone(user.getPhone());

			if (oldUser != null) {
				// 密码加密时根据用户名和密码，防止用户密码相同
				boolean flag = Md5Utils.checkPassword(user.getPhone() + user.getPassword(), oldUser.getPassword());
				if (flag) {
					rr.setCode(100);
					map.put("msg", "登录成功");
					map.put("user", oldUser);
					rr.setResult(map);
				} else {
					rr.setCode(200);
					map.put("msg", "密码错误");
					rr.setResult(map);
				}
			} else {
				rr.setCode(200);
				map.put("msg", "用户名错误");
				rr.setResult(map);
			}

		} catch (Exception e) {
			e.printStackTrace();
			rr.setCode(200);
			map.put("msg", "登录失败");
			rr.setResult(null);
			return rr;
		}
		return rr;
	}

	/**
	 * {@link http://localhost:8080/bozpower/appUser/registerUser?} app用户注册
	 * 
	 * @param user
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/registerUser", produces = "application/json;charset=UTF-8")
	@ResponseBody
	@CrossOrigin(origins = "*", maxAge = 3600)
	public RequestResult userRegister(User user) {
		RequestResult rr = new RequestResult();
		Map map = new HashMap();
		try {
			// 判断用户名是否已经存在，保证每一个用户注册用户名都是唯一的
			String phone = user.getPhone();
			if ("".equals(phone) || phone == null) {
				rr.setCode(200);
				map.put("msg", "电话号码不能为空");
				rr.setResult(map);
				return rr;
			}
			User newUser = userService.selectUserByPhone(phone);
			if (newUser != null) {
				rr.setCode(200);
				map.put("msg", "电话号码已经存在");
				rr.setResult(map);
				return rr;
			} else {
				String newPass = user.getPassword();
				String newPhone = user.getPhone();
				
				newPass = Md5Utils.encodingMd5(newPhone + newPass);// 密码加密
				user.setPassword(newPass);
				user.setUsername(user.getPhone());
				userService.insertUser(user);
				rr.setCode(100);
				map.put("msg", "注册成功");
				map.put("user", user);
				rr.setResult(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
			rr.setCode(200);
			map.put("msg", "注册失败");
			rr.setResult(map);
		}
		return rr;
	}

	/**
	 * {@link http://localhost:8080/bozpower/appUser/selectLock?mac=868729038778044}
	 * 
	 * @用户扫码根据mac查询锁信息
	 *
	 * @param mac
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/selectLock", produces = "application/json;charset=utf-8")
	@ResponseBody
	@CrossOrigin(origins = "*", maxAge = 3600)
	public RequestResult selectLock(String mac) {
		RequestResult rr = new RequestResult();
		try {
			Lockinfo lock = lockService.selectLockByMac(mac);
			rr.setCode(100);
			rr.setResult(lock);
			return rr;
		} catch (Exception e) {
			e.printStackTrace();
			rr.setCode(200);
			rr.setResult("请求失败！！！");
			return rr;
		}

	}

	/**
	 * 新增用户请求开锁信息
	 *http://localhost:8080/bozpower/appUser/insertRequestlog?did=000001
	 * @param requestlog
	 * @return RequestResult
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/insertRequestlog", produces = "application/json;charset=UTF-8")
	@ResponseBody
	@CrossOrigin(origins = "*", maxAge = 3600)
	public RequestResult insertRequestlog(Requestlog requestlog) {
		RequestResult rr = new RequestResult();
		try {
			Lockinfo lock = lockService.selectLockByDid(requestlog.getDid());
			User user = userService.selectUserByPhone(requestlog.getPhone());
			Company company = lock.getCompanyId();
			TransformerSub ts = lock.getTransformersubId();
			
			//变电站id
			requestlog.setTransformersubId(ts);
			//单位id
			requestlog.setCompanyId(company);
			//lockinfo_id
			requestlog.setLockinfoId(lock);
			//电压等级
			requestlog.setVoltage(lock.getVoltage());
			//锁状态0：申请开锁	1：同意开锁	2：拒绝开锁
			requestlog.setStatus(0);
			requestlogService.insertRequestlog(requestlog);
			// 调用推送
			List<Admin> adminList = adminService.selectAdminByCompany(requestlog.getCompanyId().getId());
			// 用户将申请信息推送给管理员app
			for (int i = 0; i < adminList.size(); i++) {
				String adminUsername = adminList.get(i).getUsername();
				String voltage = adminList.get(i).getVoltage();
//				if (requestlog.getVoltage().equals(voltage) && requestlog.getCompanyId().getId() == adminList.get(i).getCompanyId().getId()) {
//					JPushSQUtils.pushMessageToAdmin(adminUsername, "消息", "用户" + user.getRealname() + "请求开锁");
//				} else {
//				}
				if (requestlog.getVoltage().equals(voltage) && requestlog.getCompanyId().getId() == adminList.get(i).getCompanyId().getId()) {
					JPushUtils.pushMessageToUser(adminUsername,"消息", "用户" + user.getRealname() + "请求开锁");
				} else {
				}
			}
			rr.setCode(100);
			rr.setResult("请求开锁成功");
		} catch (Exception e) {
			rr.setCode(200);
			e.printStackTrace();
			rr.setResult("请求开锁失败");
		}
		return rr;
	}

	/**
	 * {@link http://localhost:8080/bozpower/appUser/selectExamineList?phone=17609346217&requesttime=2019-09-09&examinetype=}
	 * 
	 * @用户查询当前审核的信息结果，如果requesttime 为空，则表示查询全部
	 * 
	 * @param phone用户电话号码
	 * @param requesttime请求时间
	 * @param examinetype     请求状态
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/selectExamineList", produces = "application/json;charset=utf-8")
	@ResponseBody
	@CrossOrigin(origins = "*", maxAge = 3600)
	public RequestResult selectExamineList(String phone, String requesttime, String examinetype) {
		RequestResult rr = new RequestResult();
		try {
			List<Requestlog> logList = requestlogService.selectRequestLogList(requesttime, phone, examinetype);
			rr.setCode(100);
			rr.setResult(logList);
		} catch (Exception e) {
			e.printStackTrace();
			rr.setCode(200);
			return rr;
		}
		return rr;
	}

	/**
	 * 用户查询自己的使用智能锁的历史记录
	 * {@link http://localhost:8080/bozpower/appUser/selectLogHostoryList?phone=17609346217}
	 * 
	 * @param phone 用户的电话号码
	 * @return RequestResult对象，该对象包括请求码和请求值
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/selectLogHostoryList")
	@ResponseBody
	@CrossOrigin(origins = "*", maxAge = 3600)
	public RequestResult selectLogHostoryList(String phone) {
		RequestResult rr = new RequestResult();
		try {
			User user = userService.selectUserByPhone(phone);
			List<Requestlog> requestlogList = userService.selectLogHositoryList(phone, user.getCompanyId().getId());
			rr.setCode(100);
			rr.setResult(requestlogList);
		} catch (Exception e) {
			rr.setCode(200);
		}
		return rr;
	}
	
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/selectCompany")
	@ResponseBody
	@CrossOrigin(origins = "*", maxAge = 3600)
	public RequestResult selectCompany() {
		RequestResult rr = new RequestResult();
		try {
			List<Company> companyList = userService.selectCompany();
			rr.setCode(100);
			rr.setResult(companyList);
			return rr;
		} catch (Exception e) {
			e.printStackTrace();
			rr.setCode(200);
			return rr;
		}
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/selectTransfromerSub")
	@ResponseBody
	@CrossOrigin(origins = "*", maxAge = 3600)
	public RequestResult selectTransfromerSub(int companyId, String voltage) {
		RequestResult rr = new RequestResult();
		try {
			List<TransformerSub> transList = transformersubService.selectTransfromerSub(companyId, voltage);
			rr.setCode(100);
			rr.setResult(transList);
			return rr;
		} catch (Exception e) {
			e.printStackTrace();
			rr.setCode(200);
			return rr;
		}
	}

}
