package com.sevilayonogul.controller;

import com.sevilayonogul.dto.DtoGallerist;
import com.sevilayonogul.dto.DtoGalleristIU;

public interface IRestGalleristController {

	public RootEntity<DtoGallerist> saveGallerist(DtoGalleristIU dtoGalleristIU);
}
