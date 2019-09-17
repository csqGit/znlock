package com.app.bzpower.controller.app;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.app.bzpower.entity.Admin;
import com.app.bzpower.entity.PageData;
import com.app.bzpower.entity.RequestResult;
import com.app.bzpower.entity.Requestlog;
import com.app.bzpower.entity.TransformerSub;
import com.app.bzpower.entity.User;
import com.app.bzpower.service.AdminService;
import com.app.bzpower.service.RequestlogService;
import com.app.bzpower.service.TransformerSubService;
import com.app.bzpower.service.UserService;
import com.app.bzpower.util.JPushSHUtils;
import com.app.bzpower.util.JPushUtils;
import com.app.bzpower.util.Md5Utils;

/**
 * 管理员app控制类，主要功能有：
 * 1、查看管理员是否有注册权限 adminRegister
 * 2、selectlog 查询用户的所有请求日志
 * 3、updateRequestlog 管理员审核用户开锁信息
 * 4、selectRequestlogById 管理员根据id查询请求日志信息
 * 5、selectUserList 管理员查询所有的变电站用户
 * 6、deleteUserById 管理员根据id删除用户信息
 * 7、insertTransformersub 管理员添加变电站信息
 * 8、selectLogHostoryList 管理员查询开锁日志
 * @author bozpower
 *
 */
@Controller("appAdminController")
@RequestMapping("/appAdmin")
public class AdminController {

	@Autowired
	private RequestlogService requestlogService;

	@Autowired
	private AdminService adminService;

	@Autowired
	private UserService userService;

	@Autowired
	private TransformerSubService transformerSubService;
	
	/**
	 * 管理员注册
	 * 
	 * @param admin 管理员对象
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/adminLogin", produces = "application/json;charset=UTF-8")
	@ResponseBody
	@CrossOrigin(origins = "*", maxAge = 3600)
	public RequestResult adminRegister(Admin admin) {
		RequestResult rr = new RequestResult();
		try {
			Admin existAdmin = adminService.selectAdminByPhone(admin.getPhone());
			if (existAdmin != null) {
				rr.setCode(100);// 可以注册
				return rr;
			} else {
				rr.setCode(200);
				rr.setResult("你没有权限注册");
				return rr;
			}
		} catch (Exception e) {
			rr.setCode(200);
			rr.setResult("注册异常");
			return rr;
		}
	}
	
	
	@RequestMapping(value = "/adminLogin")
	@ResponseBody
	public RequestResult<Object> adminLogin(HttpServletRequest request, String username, String password, String voltage) {
		RequestResult<Object> rr = new RequestResult<Object>();

		try {

			Admin newAdmin = null;
			
			Admin existUser = adminService.selectAdminByUsername(username);
			if (existUser == null) {
				rr.setResult("用户名不存在");
				return rr;
			} else {
				String newPass = Md5Utils.encodingMd5(username + password);// 密码加密
				Admin admin = new Admin();
				admin.setUsername(username);
				admin.setPassword(newPass);
				admin.setVoltage(voltage);
				newAdmin = adminService.adminLogin(admin);
				if (newAdmin != null) {// 表示登录成功
					rr.setCode(100);
					rr.setResult(newAdmin);
					return rr;
				} else {
					rr.setResult("密码错误");
					return rr;
				}
			}

		} catch (Exception e) {
			rr.setResult("登录错误，请重试");
			return rr;
		}
	}
	
	

	/**
	 * 查询电话号码是否已经注册
	 * @param phone 电话号码
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/selectPhone", produces = "application/json;charset=UTF-8")
	@ResponseBody
	@CrossOrigin(origins = "*", maxAge = 3600)
	public RequestResult selectPhone(String phone) {
		RequestResult rr = new RequestResult();
		try {
			Admin admin = adminService.selectAdminByPhone(phone);
			if (admin != null) {
				rr.setCode(100);
				rr.setResult(admin);
				return rr;
			} else {
				rr.setCode(200);
				return rr;
			}
		} catch (Exception e) {
			rr.setCode(200);
			return rr;
		}
	}

	/**
	 * 查询用户请求日志
	 * <br />
	 * @param requesttime 申请时间
	 * @param param 待审核/已审核/未审核
	 * @param phone 管理员电话号码
	 * @return JSON对象，code为查询码，
	 * <p>100表示正确查询</p>
	 * <p>200表示错误查询</p>
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ResponseBody
	@RequestMapping(value = "/selectlog", produces = "application/json;charset=UTF-8")
	@CrossOrigin(maxAge = 3600, origins = "*")
	public RequestResult selectlog(String requesttime, String param, String phone) {
		RequestResult rr = new RequestResult();
		try {
			List<Requestlog> logList = requestlogService.adminSelectlogList(requesttime, param, phone);
			rr.setCode(100);
			rr.setResult(logList);
			return rr;
		} catch (Exception e) {
			e.printStackTrace();
			rr.setCode(200);
			return rr;
		}
	}

	/**
	 * 管理员审核用户请求开锁信息
	 * @param requestlog 日志信息
	 * @return 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/updateRequestlog", produces = "application/json;charset=UTF-8")
	@ResponseBody
	@CrossOrigin(origins = "*", maxAge = 3600)
	public RequestResult updateRequestlog(Requestlog requestlog) {
		RequestResult rr = new RequestResult();
		try {
			Requestlog req = requestlogService.selectRequestLogById(requestlog.getId());
			String phone = req.getPhone();
			
			User user = userService.selectUserByPhone(phone);
			if (user != null) {
				String dtm = req.getDtm();
				if(requestlog.getStatus() == 1) //表示同意开锁
					requestlog.setOpennum(this.computerDtm(dtm));
				requestlogService.updateRequestlog(requestlog);
//				JPushSHUtils.pushMessageToUser(username, "通知",
//						"管理员已" + (requestlog.getStatus() == 1 ? "同意" : "拒绝") + "开锁,开锁码：" + requestlog.getOpennum());
				JPushUtils.pushMessageToUser(requestlog.getPhone(), "通知", "管理员已" 
							+ (requestlog.getStatus() == 1 ? "同意" : "拒绝") + "开锁,开锁码：" + requestlog.getOpennum());
				rr.setResult("更新成功");
			} else {
				rr.setCode(200);
				rr.setResult("更新失败");
				return rr;
			}
//			rr.setCode(100);
//			rr.setResult("你没有权限同意");
		} catch (Exception e) {
			rr.setCode(200);
			e.printStackTrace();
			rr.setResult("更新失败");
		}
		return rr;
	}

	/**
	 * 根据动态码计算开锁码
	 * 
	 * @param dtm 用户申请提交的动态码
	 * @return
	 */
	private String computerDtm(String dtm) {
//		String dtm = "9999";
		int num = Integer.parseInt(dtm);
		int comNum = num * num + 3 * num + 131;
		String numStr = String.valueOf(comNum);
		numStr = numStr.substring(numStr.length() - 4, numStr.length());
		// y = x * x + 3 * x + 131
		// 截取字符串存入数组
		int[] intArray = new int[numStr.length()];

		for (int i = 0; i < numStr.length(); i++) {
			intArray[i] = Integer.parseInt(numStr.substring(i, i + 1));
			switch (intArray[i]) {
			case 0:
				intArray[i] = 1;
				break;

			}
		}
		String returnStr = "";
		for (int x : intArray) {
			returnStr += x;
		}
		return returnStr;
	}

	/**
	 * 根据id查询用户请求日志
	 * @param id 日志请求的id
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/selectRequestlogById", produces = "application/json;charset=UTF-8")
	@ResponseBody
	@CrossOrigin(origins = "*", maxAge = 3600)
	public RequestResult selectRequestlogById(Integer id) {
		RequestResult rr = new RequestResult();
		try {
			Requestlog requestlog = requestlogService.selectRequestLogById(id);
			rr.setCode(100);
			rr.setResult(requestlog);
		} catch (Exception e) {
			rr.setCode(200);
			e.printStackTrace();
			rr.setResult("请求失败");
		}
		return rr;
	}

	

	/**
	 * 根据电压等级和单位名称查询所有用户
	 *{@link http://192.168.1.177:8080/bozpower/appAdmin/selectUserList}
	 * @param voltage 电压等级
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/selectUserList", produces = "application/json;charset=UTF-8")
	@ResponseBody
	@CrossOrigin(origins = "*", maxAge = 3600)
	public RequestResult selectUserList(String voltage, int companyId) {
		RequestResult rr = new RequestResult();
		try {
			List<User> userList = userService.selectUserListByCompanyId(voltage, companyId);
			rr.setCode(100);
			rr.setResult(userList);
			return rr;
		} catch (Exception e) {
			rr.setCode(200);
			e.printStackTrace();
			rr.setResult("请求失败");
			return rr;
		}
	}

	/**
	 * 根据id删除用户信息
	 * @param id 用户id
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/deleteUserById", produces = "application/json;charset=UTF-8")
	@ResponseBody
	@CrossOrigin(origins = "*", maxAge = 3600)
	public RequestResult deleteUserById(int id) {
		RequestResult rr = new RequestResult();
		try {
			userService.deleteUser(id);
			rr.setCode(100);
			rr.setResult("删除成功");
			return rr;
		} catch (Exception e) {
			rr.setCode(200);
			e.printStackTrace();
			rr.setResult("删除失败");
			return rr;
		}
	}

	/**
	 * 新增变电站信息
	 * {@link http://localhost:8080/bozpower/appAdmin/insertTransformersub}
	 * @param transformerSub 变电站对象
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/insertTransformersub", produces = "application/json;charset=UTF-8")
	@ResponseBody
	@CrossOrigin(origins = "*", maxAge = 3600)
	public RequestResult insertTransformersub(TransformerSub transformerSub) {
		RequestResult rr = new RequestResult();
		Map<String, String> map = new HashMap<String, String>();
		try {
//			TransformerSub ts = transformerSubService.selectTransformerSubList(transformerSub.getVoltage(),""
//					);
			TransformerSub ts = transformerSubService.selectTransformerSubList(transformerSub.getVoltage(), transformerSub.getTransformersub(), transformerSub.getCompanyId().getCompanyname());
			if (ts != null) {
				rr.setCode(200);
				map.put("msg", "变电站已存在");
				rr.setResult(map);
				return rr;
			} else {
				transformerSubService.insertTransformerSub(transformerSub);
				rr.setCode(100);
				map.put("msg", "添加成功");
				rr.setResult(map);
				return rr;
			}

		} catch (Exception e) {
			rr.setCode(200);
			map.put("msg", "添加失败");
			rr.setResult(map);
			e.printStackTrace();
			return rr;
		}
	}
	
	
    /**
     * {@link http://localhost:8080/bozpower/appAdmin/selectLogHostoryList?adminId=1&companyId=1}
     * <p>查询历史记录</p>
     * @param phone 管理员电话号码
     * @param voltage 管理员所属电压等级
     * @return
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    @RequestMapping("/selectLogHostoryList")
    @ResponseBody
    @CrossOrigin(origins = "*", maxAge = 3600)
    public RequestResult selectLogHostoryList(int adminId, int companyId) {
    	RequestResult rr = new RequestResult();
    	Map<String, Object> map = new HashMap<String, Object>();
    	try {
			List<Requestlog> requestlogList = adminService.selectLogHositoryList(adminId, companyId);
			List<TransformerSub>  tsList = adminService.selectTransformerSubListByCompanyId(companyId);
			rr.setCode(100);
			map.put("result", requestlogList);
			map.put("tsList", tsList);
			rr.setResult(map);
		} catch (Exception e) {
			rr.setCode(200);
			map.put("msg", "查询异常");
			rr.setResult(map);
			e.printStackTrace();
		}
    	return rr;
    }  

}
