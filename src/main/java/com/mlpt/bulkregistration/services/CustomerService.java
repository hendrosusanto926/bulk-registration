package com.mlpt.bulkregistration.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.mlpt.bulkregistration.entity.Approval;
import com.mlpt.bulkregistration.entity.MBUser;
import com.mlpt.bulkregistration.entity.RIBUser;
import com.mlpt.bulkregistration.model.AccountMessage;
import com.mlpt.bulkregistration.model.ApproveRequest;
import com.mlpt.bulkregistration.model.MasterUserResponse;
import com.mlpt.bulkregistration.model.RegisterCustomerRequest;
import com.mlpt.bulkregistration.model.RegisterCustomerResponse;
import com.mlpt.bulkregistration.model.WritableAccountMessage;
import com.mlpt.bulkregistration.repository.ApprovalRepository;
import com.mlpt.bulkregistration.repository.MBUserRepository;
import com.mlpt.bulkregistration.repository.RIBUserRepository;

@Service
public class CustomerService {

    @Value("${rest.timeout}")
	private String restTimeout;

    @Value("${rest.url}")
	private String restUrl;

    @Value("${rest.masteruser.atm.uri}")
	private String restMasterUser;

    @Value("${rest.register.uri}")
	private String restRegister;

	@Value("${rest.approve.uri}")
	private String restApprove;

	@Value("${user.id}")
	private String userId;

	@Value("${branch.code}")
	private String branchCode;

	@Value("${selected.approver.id}")
	private String selectedApproverId;

	@Value("${backoffice.user.id}")
	private String backofficeUserId;

	@Value("${backoffice.branch.user.id}")
	private String backofficeBranchUserId;

	@Autowired
	private ApprovalRepository approvalRepository;

	@Autowired
	private RIBUserRepository ribuserRepository;

	@Autowired
	private MBUserRepository mbuserRepository;

    @Autowired
    private RestTemplate restTemplate;

	private static final Log log = LogFactory.getLog(CustomerService.class);

    public MasterUserResponse getMasterUserResponse(String atmNo) {
		String url = restUrl+restMasterUser;
		log.info("call Get Master User URL : "+url);
		Map<String, String> vars = new HashMap<String, String>();
		vars.put("atmNo", atmNo);
		log.info(vars);
		
		MasterUserResponse masterUserResponse = (MasterUserResponse) callGetService(atmNo, MasterUserResponse.class, url, vars);
		return masterUserResponse;
	}

    public Object callGetService(Object request, Class<?> clz, String url, Map<String, String> params) {
		Object response = new Object();
		
		try {
			HttpEntity<Object> w_request = new HttpEntity<Object>(request,createHeader("temporary", "temporary"));
			log.info(w_request);
			
			ResponseEntity<?> re_response = restTemplate.exchange(url, HttpMethod.GET, w_request, clz, params);
			log.info("response : "+re_response.getBody().toString());
			response = re_response.getBody();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return response;
	}

    public HttpHeaders createHeader(String username, String password) {
		HttpHeaders httpHeaders = new HttpHeaders();
		String auth = username + ":" + password;
		byte[] encodeAuth = Base64.encodeBase64(auth.getBytes());
		String authHeader = "Basic " + new String( encodeAuth );
        httpHeaders.set( "Authorization", authHeader );
		return httpHeaders;
	}

    public MasterUserResponse filterAccount(MasterUserResponse masterUserResponse) {
		MasterUserResponse filteredResponse = new MasterUserResponse();
		BeanUtils.copyProperties(masterUserResponse, filteredResponse);;
		List<AccountMessage> newAccountList = new ArrayList<>();
		for (int i=0; i < masterUserResponse.getAccounts().size(); i++){
			//check if account type = Deposito --> remove from list if status != ACTIVE/TODAY_OPENING/BLOCKED || type = Loan --> remove if status != ACTIVE
			if (masterUserResponse.getAccounts().get(i).getType().equals("T")){
				if (masterUserResponse.getAccounts().get(i).getStatus().equals("ACTIVE") || masterUserResponse.getAccounts().get(i).getStatus().equals("TODAY_OPENING")
						|| masterUserResponse.getAccounts().get(i).getStatus().equals("BLOCKED")){
					//Add to list
					newAccountList.add(masterUserResponse.getAccounts().get(i));
				} else {
					//do nothing
				}
			} else if (masterUserResponse.getAccounts().get(i).getType().equals("L")){
				if (masterUserResponse.getAccounts().get(i).getStatus().equals("ACTIVE")){
					//Add to list
					newAccountList.add(masterUserResponse.getAccounts().get(i));
				} else {
					//do nothing
				}
			} else {
				//Add to list
				newAccountList.add(masterUserResponse.getAccounts().get(i));
			}
		}
		filteredResponse.setAccounts(newAccountList);
		return filteredResponse;
	}

	public RegisterCustomerResponse register(MasterUserResponse masterUserResponse, String channel, String channelMobile,
		String atmCardNo) {
		String mobilePin ="";
						
		String url = restUrl+restRegister;
		log.info("call register user url : "+url);
		RegisterCustomerRequest request = new RegisterCustomerRequest();
		
		request.setAddress(masterUserResponse.getAddress());
		request.setBackofficeUserId(userId);
		request.setBirthDate(masterUserResponse.getBirthDate());
		request.setChannel(channel);
		request.setChannelMobileNumber(channelMobile);
		request.setCif(masterUserResponse.getCif());
		request.setCustomerName(masterUserResponse.getCustomerName());
		request.setLastname(masterUserResponse.getLastName());
		request.setMother(masterUserResponse.getMotherName());
		request.setPhoneNumber(masterUserResponse.getMobileNumber());
		request.setSex(masterUserResponse.getSex());
		request.setShortName(masterUserResponse.getShortName());
		request.setMobilePin(mobilePin);
		request.setBranchId(branchCode);
		request.setAtmCardNo(atmCardNo);
		request.setSelectedApproverId(selectedApproverId);
		
		List<AccountMessage> allAccounts = masterUserResponse.getAccounts();
		
		List<WritableAccountMessage> tempList = new ArrayList<>();
		for (int i = 0; i < allAccounts.size(); i++){
			WritableAccountMessage temp = new WritableAccountMessage();
			temp.setAccountNo(allAccounts.get(i).getNewAccountNo());
			temp.setActive(allAccounts.get(i).isActive());
			temp.setVisible(true);
			temp.setType(allAccounts.get(i).getType());
			tempList.add(temp);
		}
		request.setAccountsSelected(tempList);
		
		RegisterCustomerResponse registerCustomerResponse = (RegisterCustomerResponse) callPostService(request, RegisterCustomerResponse.class, url);
		return registerCustomerResponse;
	}

	public Object callPostService(Object request, Class<?> clz, String url) {
		Object response = new Object();	
		try {
			HttpEntity<Object> w_request = new HttpEntity<Object>(request,createHeader("temporary", "temporary"));
			log.info(w_request);
			
			ResponseEntity<?> re_response = restTemplate.exchange(url, HttpMethod.POST, w_request, clz);
			log.info("response : "+re_response.getBody().toString());
 			response = re_response.getBody();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return response;
	}

	public Approval findApprovalByreffNumRegistrationandChannel(String reffNumRegistration, String channel) {
		List<Approval> listApprovals=null;
		List<RIBUser> listRIBUser;
		List<MBUser> listMBUser;
		if (channel.equals("RIB")){
			listRIBUser = ribuserRepository.findByReffNumRegistration(reffNumRegistration);
			if (listRIBUser.get(0)!=null){
				listApprovals = approvalRepository.findByIdEntity(listRIBUser.get(0).getId());
			}

		}else{
			listMBUser = mbuserRepository.findByReffNumRegistration(reffNumRegistration);
			if (listMBUser.get(0)!=null){
				listApprovals = approvalRepository.findByIdEntity(listMBUser.get(0).getId());
			}
		}

		if((listApprovals!=null) && (listApprovals.get(0)!=null)) {
			return listApprovals.get(0);
		}
		return null;
	}

	public String approvalResponse(String idApproval) {
		
		String url = restUrl+restApprove;
		ApproveRequest request = new ApproveRequest();
		
		request.setBackofficeUserId(backofficeUserId);
		request.setBranchId(backofficeBranchUserId);
		request.setIdApproval(idApproval);
		
		String response = (String) callPostService(request, String.class, url);
		return response;
	}

}
