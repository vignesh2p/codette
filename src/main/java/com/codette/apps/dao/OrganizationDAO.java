package com.codette.apps.dao;

import com.codette.apps.dto.NewOrganizationDTO;

public interface OrganizationDAO {

	Object createOrganization(NewOrganizationDTO newOrgDTO);

}
