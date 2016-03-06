package com.codette.apps.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;

import com.codette.apps.dao.LeaveDAO;
import com.codette.apps.dto.LeaveManagementDTO;
import com.codette.apps.dto.ResponseBean;
import com.codette.apps.mapper.LeaveManagementRowMapper;
import com.codette.apps.util.CommonConstants;
import com.codette.apps.util.CommonUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class LeaveManagementImpl  extends NamedParameterJdbcDaoSupport implements LeaveDAO{

	final static Logger logger = Logger.getLogger(LeaveManagementImpl.class);
	public static final Gson gson = new GsonBuilder().setDateFormat(CommonConstants.ISO_DATE_FORMAT).create();
	@Resource
	private CommonUtil commonUtil;
	
	@Override
	public Object getPendingLeave(Integer orgId, String status, Integer userId,
			String role) throws Exception{
        Object object = null;
		String ID_STATUS = "SELECT ID FROM status where STATUS = '"+status+"'";
		String LEAVE_FORM = "SELECT * FROM leave_management A left outer join user U ON A.ID_USER = U.ID "
				+ "left outer join status S ON A.ID_FORM_STATUS = S.ID "
				+ "WHERE A.ID_FORM_STATUS =?";
				
			Integer statusId=  getJdbcTemplate().queryForObject(
                    ID_STATUS, Integer.class);
				
			if(!role.equalsIgnoreCase(CommonConstants.ROLE_ADMIN)){
				LEAVE_FORM = LEAVE_FORM+" AND A.ID_USER = ?";
				Integer[] inputs = {statusId,userId};
				object = getJdbcTemplate().query(LEAVE_FORM,inputs, new LeaveManagementRowMapper());
			}else{
			Integer[] input = {statusId};
			object = getJdbcTemplate().query(LEAVE_FORM,input, new LeaveManagementRowMapper());

			}
			
			return object;
		}



	@Override
	public Object getHistoryLeave(Integer orgId, String status, Integer userId,
			String role) throws Exception{
        Object object = null;
		String ID_STATUS = "SELECT ID FROM status where STATUS = '"+CommonConstants.PENDING+"'";
		String LEAVE_FORM = "SELECT * FROM leave_management A left outer join user U ON A.ID_USER = U.ID "
				+ "left outer join status S ON A.ID_FORM_STATUS = S.ID "
				+ "WHERE A.ID_FORM_STATUS <> ? ";
				
			Integer statusId=  getJdbcTemplate().queryForObject(
                    ID_STATUS, Integer.class);
				
			if(!role.equalsIgnoreCase(CommonConstants.ROLE_ADMIN)){
				LEAVE_FORM = LEAVE_FORM+" AND A.ID_USER =?";
				Integer[] inputs = {statusId,userId};
				object = getJdbcTemplate().query(LEAVE_FORM,inputs, new LeaveManagementRowMapper());
			}else{
				Integer[] input = {statusId};
				object = getJdbcTemplate().query(LEAVE_FORM,input, new LeaveManagementRowMapper());
			}
			return object;
		}
		
		


	@Override
	public Object applyleave(LeaveManagementDTO leave, Integer orgId,
			Integer userId, Integer accessId) throws Exception{
		// TODO Auto-generated method stub
		ResponseBean responseBean= new ResponseBean(); 

		String ID_STATUS = "SELECT ID FROM status where STATUS =? ";
		String INSERT_LEAVE = "INSERT INTO `leave_management`(";
				INSERT_LEAVE = INSERT_LEAVE+"`ID_USER`,";
				if(leave.getStartTime()!=null){
					INSERT_LEAVE = INSERT_LEAVE+ " `START_TIME`,";
				}
				if(leave.getEndTime() != null){
					INSERT_LEAVE = INSERT_LEAVE+" `END_TIME`, ";
				}
				if(leave.getReason() != null){
					INSERT_LEAVE = INSERT_LEAVE+ "`REASON`,";
				}
				if(leave.getIsTaken() != null){
					INSERT_LEAVE = INSERT_LEAVE+ " `IS_TAKEN`, ";
				}
				INSERT_LEAVE = INSERT_LEAVE+ " `ID_FORM_STATUS`, ";
				INSERT_LEAVE = INSERT_LEAVE+ "`IS_DELETED`,"
				+ "`CREATED_ON`,"
				+ " `CREATED_BY`) "
				+ "VALUES (";

					INSERT_LEAVE = INSERT_LEAVE+ +accessId+",";
					if(leave.getStartTime()!=null){
						INSERT_LEAVE = INSERT_LEAVE+ commonUtil.stringFeilds(leave.getStartTime())+",";
					}
					if(leave.getEndTime() != null){
						INSERT_LEAVE = INSERT_LEAVE+commonUtil.stringFeilds(leave.getEndTime())+",";
					}
					if(leave.getReason() != null){
						INSERT_LEAVE = INSERT_LEAVE+ commonUtil.stringFeilds(leave.getReason())+",";
					}
					if(leave.getIsTaken() != null){
						INSERT_LEAVE = INSERT_LEAVE+ leave.getIsTaken()+",";
					}
					INSERT_LEAVE = INSERT_LEAVE+"?,";
				INSERT_LEAVE = INSERT_LEAVE+ CommonConstants.NOT_DELETED+","+"NOW()"+","+accessId
				+")";
					
					String[] statusInput= {"PENDING"};
					System.out.println("statusInput   "+statusInput);
					Integer statusId=  getJdbcTemplate().queryForObject(
		                    ID_STATUS,statusInput,Integer.class);
					
					if(statusId!= null)
					{
						  Object [] inputs = {statusId};
						getJdbcTemplate().update(INSERT_LEAVE, inputs );		
					  System.out.println(INSERT_LEAVE);
							responseBean.setStatus("SUCCESS");
							responseBean.setMessage("Leave Is applied");
						
					}
					
		return responseBean;
	}


	@Override
	public Object statusChange(List<LeaveManagementDTO> leaveDTO,
			Integer orgId, Integer userId, Integer accessId) throws Exception{
		ResponseBean responceBean = new ResponseBean();
		String ID_STATUS = "SELECT ID FROM status where STATUS =? ";
			
		for(LeaveManagementDTO leave : leaveDTO){
		String UPDATE_LEAVE = "UPDATE `leave_management` SET"; 
		
			if(leave.getStartTime()!=null){
			    UPDATE_LEAVE = UPDATE_LEAVE+"`START_TIME`="+leave.getStartTime()+",";
			}
			if(leave.getEndTime() != null){
			    UPDATE_LEAVE = UPDATE_LEAVE+"`END_TIME`="+leave.getEndTime()+",";
			}
			if(leave.getReason() != null){
				UPDATE_LEAVE = UPDATE_LEAVE+"`REASON`="+leave.getReason()+",";
			}
			if(leave.getIsTaken() != null){
				UPDATE_LEAVE = UPDATE_LEAVE+"`IS_TAKEN`="+leave.getIsTaken()+",";
			}
			if(leave.getFormStatus() != null){
				String[] statusInput= {leave.getFormStatus().getStatus()};
				Integer statusId=  getJdbcTemplate().queryForObject(
	                    ID_STATUS,statusInput,Integer.class);
			
				UPDATE_LEAVE = UPDATE_LEAVE+"`ID_FORM_STATUS`="+statusId+",";
			}
			UPDATE_LEAVE = UPDATE_LEAVE+"`UPDATED_BY`="+userId;	
			UPDATE_LEAVE = UPDATE_LEAVE+" WHERE ID = "+leave.getId();
			
			getJdbcTemplate().execute(UPDATE_LEAVE);
		}
		responceBean.setStatus("SUCCESS");
		responceBean.setMessage("Your Leave Form is updated successfully");
		
		return responceBean;
	}

	}