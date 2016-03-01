package com.codette.apps.dao;

import org.springframework.stereotype.Component;

import com.codette.apps.dto.NewOrganizationDTO;
@Component
public interface OrganizationDAO {

	Object createOrganization(NewOrganizationDTO newOrgDTO);

}
