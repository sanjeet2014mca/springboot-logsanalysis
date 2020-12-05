package com.hcl.nxp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcl.nxp.repository.custom.PipeLinesDataCustomRepository;
import com.hcl.nxp.vo.PipeLinesData;

public interface PipeLinesDataRepository extends JpaRepository<PipeLinesData, Integer>, PipeLinesDataCustomRepository {

}
