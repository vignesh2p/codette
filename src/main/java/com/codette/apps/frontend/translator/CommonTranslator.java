package com.codette.apps.frontend.translator;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.google.gson.reflect.TypeToken;
import com.codette.apps.dto.CommunityDTO;
import com.codette.apps.dto.DesignationDTO;
import com.codette.apps.dto.ReligionDTO;
import com.codette.apps.dto.SectionDTO;
import com.codette.apps.dto.StandardDTO;
import com.codette.apps.frontend.model.DropDownValue;

@Component
public class CommonTranslator extends BaseTranslator {

	public List<CommunityDTO> convertToCommunityDTOList(Object object){
		Type listType = new TypeToken<List<CommunityDTO>>() {}.getType();
		List<CommunityDTO> communityDTO = gson.fromJson(translateObjectToJson(object), listType);
		return communityDTO;
	}

	public List<DesignationDTO> convertToDesignationDTOList(Object object) {
		Type listType = new TypeToken<List<DesignationDTO>>() {}.getType();
		List<DesignationDTO> designationDTO = gson.fromJson(translateObjectToJson(object), listType);
		return designationDTO;
	}
	/**
	 * 
	 * @param communityDTOList
	 * @return
	 */
	public List<DropDownValue> translateToCommunityDropDownList(List<CommunityDTO> communityDTOList) {
		List<DropDownValue> dropDownList = new ArrayList<DropDownValue>();
		if(communityDTOList != null){
			for(CommunityDTO communityDTO :communityDTOList){
				dropDownList.add(translateToDropDown(communityDTO.getId().toString(), communityDTO.getCommunity()));
			}
		}
		return dropDownList;
	}
	
	/**
	 * 
	 * @param id
	 * @param value
	 * @return
	 */
	public DropDownValue translateToDropDown(String id, String value){
		DropDownValue dropDownValue = new DropDownValue();
		if(id != null){
			dropDownValue.setId(id);
		}
		if(value != null){
			dropDownValue.setValue(value);
		}
		return dropDownValue;
	}

	public List<ReligionDTO> convertToReligionDTOList(Object object) {
		Type listType = new TypeToken<List<ReligionDTO>>() {}.getType();
		List<ReligionDTO> religionDTO = gson.fromJson(translateObjectToJson(object), listType);
		return religionDTO;
	}

	public List<DropDownValue> translateToReligionDropDownList(List<ReligionDTO> religionDTOList) {
		List<DropDownValue> dropDownList = new ArrayList<DropDownValue>();
		if(religionDTOList != null){
			for(ReligionDTO religionDTO :religionDTOList){
				dropDownList.add(translateToDropDown(religionDTO.getId().toString(), religionDTO.getReligion()));
			}
		}
		return dropDownList;
	}

	public List<DropDownValue> translateToDesignationDropDownList(List<DesignationDTO> designationDTOlist) {
		List<DropDownValue> dropDownList = new ArrayList<DropDownValue>();
		if(designationDTOlist != null){
			for(DesignationDTO designationDTO :designationDTOlist){
				dropDownList.add(translateToDropDown(designationDTO.getId().toString(), designationDTO.getDesignation()));
			}
		}
		return dropDownList;
	}

	public List<StandardDTO> convertToStandardDTOList(Object object) {
		Type listType = new TypeToken<List<StandardDTO>>() {}.getType();
		List<StandardDTO> standardDTOList = gson.fromJson(translateObjectToJson(object), listType);
		return standardDTOList;
	}

	public List<DropDownValue> translateToStandardDropDownList(List<StandardDTO> standardDTOList) {
		List<DropDownValue> dropDownList = null;
		if(standardDTOList != null && !standardDTOList.isEmpty()){
			dropDownList = new ArrayList<DropDownValue>();
			for(StandardDTO standardDTO : standardDTOList){
				dropDownList.add(translateToDropDown(standardDTO.getId().toString(), standardDTO.getStandard()));
			}
		}
		return dropDownList;
	}

	public List<SectionDTO> convertToSectionDTOList(Object object) {
		Type listType = new TypeToken<List<SectionDTO>>() {}.getType();
		List<SectionDTO> sectionDTOList = gson.fromJson(translateObjectToJson(object), listType);
		return sectionDTOList;
	}


	public List<DropDownValue> translateToSectionDropDownList(List<SectionDTO> sectionDTOList) {
		List<DropDownValue> dropDownList = null;
		if(sectionDTOList != null && !sectionDTOList.isEmpty()){
			dropDownList = new ArrayList<DropDownValue>();
			for(SectionDTO sectionDTO : sectionDTOList){
				dropDownList.add(translateToDropDown(sectionDTO.getId().toString(), sectionDTO.getSection()));
			}
		}
		return dropDownList;
	}
}
