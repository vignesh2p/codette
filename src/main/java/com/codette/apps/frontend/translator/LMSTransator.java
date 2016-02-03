package com.codette.apps.frontend.translator;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.google.gson.reflect.TypeToken;
import com.codette.apps.dto.StatusDTO;
import com.codette.apps.dto.LeaveManagementDTO;
import com.codette.apps.dto.UserDTO;
import com.codette.apps.frontend.model.LeaveManagement;
import com.codette.apps.frontend.model.Staff;
import com.codette.apps.util.CommonConstants;

@Component
public class LMSTransator extends BaseTranslator{

	/**
	 * 
	 * @param object
	 * @return
	 */
	public List<LeaveManagementDTO> convertToListOfLMSDTO(Object object){
		Type listType = new TypeToken<List<LeaveManagementDTO>>() {}.getType();
		List<LeaveManagementDTO> leaveManagementDTOList = gson.fromJson(translateObjectToJson(object), listType);
		return leaveManagementDTOList;
	}

	/**
	 * 
	 * @param leaveManagementDTOList
	 * @return
	 * @throws ParseException 
	 */
	public List<LeaveManagement> translateToLMSList(List<LeaveManagementDTO> leaveManagementDTOList) throws ParseException {
		List<LeaveManagement> lmsList = new ArrayList<LeaveManagement>();
		if(leaveManagementDTOList != null && !leaveManagementDTOList.isEmpty()){
			for(LeaveManagementDTO leaveManagementDTO : leaveManagementDTOList){
				lmsList.add(translateToLMS(leaveManagementDTO));
			}
		}
		return lmsList;
	}

	/**
	 * 
	 * @param leaveManagementDTO
	 * @return
	 * @throws ParseException 
	 */

	public LeaveManagement translateToLMS(LeaveManagementDTO leaveManagementDTO) throws ParseException {
		LeaveManagement leaveManagement = null;
		if(leaveManagementDTO != null){
			leaveManagement = new LeaveManagement();
			if(leaveManagementDTO.getId()!= null){
				leaveManagement.setId(leaveManagementDTO.getId());
			}
			if(leaveManagementDTO.getStartTime() != null){
				leaveManagement.setFrom(commonUtil.formatTimeStampToDateString(leaveManagementDTO.getStartTime().substring(0, leaveManagementDTO.getStartTime().length() - 2) , CommonConstants.TIMESTAMP_DD_MMMM_YYYY_HH_MM_SS, CommonConstants.DATE_DD_MMMM_YYYY));
				leaveManagement.setFromTime(commonUtil.formatTimeStampToDateString(leaveManagementDTO.getStartTime().substring(0, leaveManagementDTO.getStartTime().length() - 2) , CommonConstants.TIMESTAMP_DD_MMMM_YYYY_HH_MM_SS, CommonConstants.TIME_FORMAT));
			}
			if(leaveManagementDTO.getEndTime() != null){
				leaveManagement.setTo(commonUtil.formatTimeStampToDateString(leaveManagementDTO.getEndTime().substring(0, leaveManagementDTO.getEndTime().length() - 2) , CommonConstants.TIMESTAMP_DD_MMMM_YYYY_HH_MM_SS, CommonConstants.DATE_DD_MMMM_YYYY));
				leaveManagement.setToTime(commonUtil.formatTimeStampToDateString(leaveManagementDTO.getStartTime().substring(0, leaveManagementDTO.getStartTime().length() - 2) , CommonConstants.TIMESTAMP_DD_MMMM_YYYY_HH_MM_SS, CommonConstants.TIME_FORMAT));
			}
			if(leaveManagementDTO.getReason() != null){
				leaveManagement.setReason(leaveManagementDTO.getReason());
			}
			if(leaveManagementDTO.getFormStatus() != null){
				leaveManagement.setStatus(leaveManagementDTO.getFormStatus().getStatus());
			}
			if(leaveManagementDTO.getStaff() != null){
				//leaveManagement.setStatus(leaveManagementDTO.getFormStatus().getStatus());
				Staff staff = new Staff();
				staff.setFirstName(leaveManagementDTO.getStaff().getFirstName());
				staff.setLastName(leaveManagementDTO.getStaff().getLastName());
				staff.setId(leaveManagementDTO.getStaff().getId());
				leaveManagement.setStaff(staff);
			}
		}
		return leaveManagement;
	}

	public LeaveManagementDTO translateToLMSDTO(LeaveManagement leaveManagement, String status) throws ParseException {
		LeaveManagementDTO leaveManagementDTO = null ;
		if(leaveManagement != null){
			leaveManagementDTO = new LeaveManagementDTO();
			
			if(leaveManagement.getId() != null){
				leaveManagementDTO.setId(leaveManagement.getId());
			}
			
			if(leaveManagement.getFrom() != null && leaveManagement.getFromTime() != null){
				leaveManagementDTO.setStartTime(commonUtil.formatgivenStringToDate(leaveManagement.getFrom(), CommonConstants.DATE_DD_MMMM_YYYY, CommonConstants.DATE_TIME_FORMAT)+" "+timeFormatter(leaveManagement.getFromTime()));
			}
			
			if(leaveManagement.getTo() != null && leaveManagement.getToTime() != null){
				leaveManagementDTO.setEndTime(commonUtil.formatgivenStringToDate(leaveManagement.getTo(), CommonConstants.DATE_DD_MMMM_YYYY, CommonConstants.DATE_TIME_FORMAT)+" "+timeFormatter(leaveManagement.getToTime()));
			}
			
			if(leaveManagement.getReason() != null && !leaveManagement.getReason().isEmpty()){
				leaveManagementDTO.setReason(leaveManagement.getReason());
			}
			
			if(status != null){
				StatusDTO formStatusDTO = new StatusDTO();
				formStatusDTO.setStatus(status);
				leaveManagementDTO.setFormStatus(formStatusDTO);
			}
			if(leaveManagement.getStaff()!= null){
				UserDTO staff = new UserDTO();
				staff.setId(leaveManagement.getStaff().getId());
				leaveManagementDTO.setStaff(staff);
			}
			
		}
		return leaveManagementDTO;
	}

	/**
	 * 
	 * @param leaveManagementList
	 * @return
	 * @throws ParseException
	 */
	public List<LeaveManagementDTO> translateToLMSDTOList(List<LeaveManagement> leaveManagementList) throws ParseException {
		String status = null;
		List<LeaveManagementDTO> leaveManagementDTOList = null;
		if(leaveManagementList != null){
			leaveManagementDTOList = new ArrayList<LeaveManagementDTO>();
			for(LeaveManagement leaveManagement: leaveManagementList){
				if(leaveManagement.isApproveStatus()){
					status = CommonConstants.STATUS_APPROVED;
				}else{
					status = CommonConstants.STATUS_DECLINED;
				}
				leaveManagementDTOList.add(translateToLMSDTO(leaveManagement, status));
			}
		}
		return leaveManagementDTOList;
	}
	
	public String timeFormatter(String time) throws ParseException{
		 SimpleDateFormat displayFormat = new SimpleDateFormat("HH:mm:ss");
		 SimpleDateFormat parseFormat = new SimpleDateFormat("hh:mm a");
	     Date date = parseFormat.parse(time);
		return displayFormat.format(date);
	}
}
