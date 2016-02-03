package com.codette.apps.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;

import com.codette.apps.dao.LeaveDAO;
import com.codette.apps.dto.LeaveManagementDTO;
import com.codette.apps.dto.ResponseBean;
import com.codette.apps.mapper.LeaveManagementRowMapper;
import com.codette.apps.util.CommonConstants;
import com.codette.apps.util.CommonUtil;

public class LeaveManagementImpl  extends NamedParameterJdbcDaoSupport implements LeaveDAO{

	@Resource
	CommonUtil commonUtil;
	
	@Override
	public List<LeaveManagementDTO> getPendingLeave(String status,Integer userId,String role) {
		List<LeaveManagementDTO> leaveList = new ArrayList<LeaveManagementDTO>();
		String ID_STATUS = "SELECT ID FROM status where STATUS = '"+status+"'";
		String LEAVE_FORM = "SELECT * FROM leave_management A left outer join user U ON A.ID_STAFF = U.ID "
				+ "left outer join status S ON A.ID_FORM_STATUS = S.ID "
				+ "WHERE A.ID_FORM_STATUS =?";
				
		try{ 
			System.out.println(">>>>>>>>>> pending query"+ID_STATUS);
			Integer statusId=  getJdbcTemplate().queryForObject(
                    ID_STATUS, Integer.class);
			System.out.println("statusId------"+statusId);
				
			if(!role.equalsIgnoreCase(CommonConstants.ROLE_ADMIN)){
				LEAVE_FORM = LEAVE_FORM+" AND A.ID_STAFF = ?";
				Integer[] inputs = {statusId,userId};
					leaveList = getJdbcTemplate().query(LEAVE_FORM,inputs, new LeaveManagementRowMapper());
			}else{
			Integer[] input = {statusId};
			leaveList = getJdbcTemplate().query(LEAVE_FORM,input, new LeaveManagementRowMapper());

			}
			
		}
			catch (Exception e){
				   String eStr = e.getMessage();
				   System.out.println("estr-----------"+eStr);
			}
			
			return leaveList;
		}
		
	
	@Override
	public List<LeaveManagementDTO> getHistoryLeave(String status,Integer userId,String role) {
		List<LeaveManagementDTO> leaveList = new ArrayList<LeaveManagementDTO>();
		String ID_STATUS = "SELECT ID FROM status where STATUS = '"+CommonConstants.PENDING+"'";
		String LEAVE_FORM = "SELECT * FROM leave_management A left outer join user U ON A.ID_STAFF = U.ID "
				+ "left outer join status S ON A.ID_FORM_STATUS = S.ID "
				+ "WHERE A.ID_FORM_STATUS <> ? ";
				
		try{ 
			System.out.println(">>>>>>>>>> pending query"+ID_STATUS);
			Integer statusId=  getJdbcTemplate().queryForObject(
                    ID_STATUS, Integer.class);
			System.out.println(">>>>>>>>>> pending"+statusId);
				
			if(!role.equalsIgnoreCase(CommonConstants.ROLE_ADMIN)){
				LEAVE_FORM = LEAVE_FORM+" AND A.ID_STAFF =?";
				Integer[] inputs = {statusId,userId};
					leaveList = getJdbcTemplate().query(LEAVE_FORM,inputs, new LeaveManagementRowMapper());
			}else{
			Integer[] input = {statusId};
			leaveList = getJdbcTemplate().query(LEAVE_FORM,input, new LeaveManagementRowMapper());

			}
			
		}
			catch (Exception e){
				   String eStr = e.getMessage();
				   System.out.println(eStr);
			}
			
			return leaveList;
		}
		

	@Override
	public ResponseBean Applyleave(LeaveManagementDTO leave,Integer accessId) {
		ResponseBean responseBean= new ResponseBean(); 
		String ID_STATUS = "SELECT ID FROM status where STATUS =? ";
		String INSERT_LEAVE = "INSERT INTO `leave_management`(";
				INSERT_LEAVE = INSERT_LEAVE+"`ID_STAFF`,";
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
				INSERT_LEAVE = INSERT_LEAVE+ CommonConstants.IS_DELETED+","+"NOW()"+","+accessId
				+")";
				try{
					
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
					
				}
					catch(Exception e){
						responseBean.setStatus("FAILED");
						responseBean.setMessage( e.getMessage());
						e.printStackTrace();
					}
		        return responseBean;
				

}

	@Override
	public  ResponseBean statusChange(List<LeaveManagementDTO> leaveDTO, Integer userId) {
		// TODO Auto-generated method stub
		ResponseBean responceBean = new ResponseBean();
		String ID_STATUS = "SELECT ID FROM status where STATUS =? ";
		try{
			
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
		
		}catch(Exception e){
			responceBean.setStatus("FAILED");
			   String eStr = e.getMessage();
			   responceBean.setMessage(eStr);
		}
		return responceBean;
	}
	}