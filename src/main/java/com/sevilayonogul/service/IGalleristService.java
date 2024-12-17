package com.sevilayonogul.service;

import com.sevilayonogul.dto.DtoGallerist;
import com.sevilayonogul.dto.DtoGalleristIU;

public interface IGalleristService {

	public DtoGallerist saveGallerist(DtoGalleristIU dtoGalleristIU);
}
