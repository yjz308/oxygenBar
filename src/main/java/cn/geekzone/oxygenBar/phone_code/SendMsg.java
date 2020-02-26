package cn.geekzone.oxygenBar.phone_code;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

import cn.geekzone.oxygenBar.common.entity.Constants;



public class SendMsg {
	// 产品名称:云通信短信API产品,开发者无需替换
	static final String product = "Dysmsapi";
	// 产品域名,开发者无需替换
	static final String domain = "dysmsapi.aliyuncs.com";

	// TODO 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)
	static final String accessKeyId = Constants.alidayu_accessKeyId;
	static final String accessKeySecret = Constants.alidayu_accessKeySecret;

	// static final String signName = "阿里云短信测试专用";
	static final String signName = Constants.alidayu_signName;

	// static final String accessKeyId = "LTAIu37dYp1wHwua";
	// static final String accessKeySecret = "HlKqeGl2LcvciM2od96A8ctFAZRicC";
	//
	// // static final String signName = "阿里云短信测试专用";
	// static final String signName = "立德共创机器人服务平台";

	// 登录验证码
	public static SendSmsResponse sendSms(String phone, String param, String templateCode) throws ClientException {

		// 可自助调整超时时间
		System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
		System.setProperty("sun.net.client.defaultReadTimeout", "10000");

		// 初始化acsClient,暂不支持region化
		IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
		DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
		IAcsClient acsClient = new DefaultAcsClient(profile);

		// 组装请求对象-具体描述见控制台-文档部分内容
		SendSmsRequest request = new SendSmsRequest();
		// 必填:待发送手机号
		request.setPhoneNumbers(phone);
		// 必填:短信签名-可在短信控制台中找到
		request.setSignName(signName);
		// 必填:短信模板-可在短信控制台中找到
		// ----request.setTemplateCode("SMS_130085077");
		request.setTemplateCode(templateCode);
		// 可选:模板中的变量替换JSON串,如模板内容为"您的验证码为${code}"时,此处的值为
		// ----request.setTemplateParam("{\"code\":\""+code+"\"}");
		request.setTemplateParam(param);

		// 选填-上行短信扩展码(无特殊需求用户请忽略此字段)
		// request.setSmsUpExtendCode("90997");

		// 可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
		request.setOutId("yourOutId");

		// hint 此处可能会抛出异常，注意catch
		SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
		if (!sendSmsResponse.getCode().equals("OK")) {
			sendSmsResponse.setMessage("操作太过频繁，请稍后重试");
		}

		System.out.println("短信接口返回的数据----------------");
		System.out.println("Code=" + sendSmsResponse.getCode());
		System.out.println("Message=" + sendSmsResponse.getMessage());
		System.out.println("RequestId=" + sendSmsResponse.getRequestId());
		System.out.println("BizId=" + sendSmsResponse.getBizId());

		return sendSmsResponse;
	}

	public static void main(String[] args) throws ClientException {
		String phone = "15522662520";
		String password = "aaa";
		// 发短信
		// SendSmsResponse response = sendSms("15522662520", "{\"phone\":\"" +
		// "15522662520" + "\"}", "SMS_157453924");
		SendSmsResponse response = sendSms("15522662520",
				"{\"phone\":\"" + phone + "\",\"password\":\"" + password + "\"}", "SMS_157453924");

		System.out.println("短信接口返回的数据----------------");
		System.out.println("Code=" + response.getCode());
		System.out.println("Message=" + response.getMessage());
		System.out.println("RequestId=" + response.getRequestId());
		System.out.println("BizId=" + response.getBizId());
	}
}
