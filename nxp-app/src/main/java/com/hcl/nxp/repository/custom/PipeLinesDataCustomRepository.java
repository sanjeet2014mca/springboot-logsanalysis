package com.hcl.nxp.repository.custom;

import java.util.List;

import com.hcl.nxp.vo.PipeLinesData;

public interface PipeLinesDataCustomRepository {

	List<PipeLinesData> fetchRecords(Integer id);

	String getLogs(Integer id, String name);
}
