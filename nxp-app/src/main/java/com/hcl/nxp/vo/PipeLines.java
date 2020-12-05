package com.hcl.nxp.vo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "nxp_pipelines")
public class PipeLines implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "nxp_pipelines_id")
	private Integer id;
	private String name;

	@Transient
	@JsonManagedReference
	private List<PipeLinesData> pipeLinesDatas;

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public List<PipeLinesData> getPipeLinesDatas() {
		return pipeLinesDatas;
	}

	public void setPipeLinesDatas(final List<PipeLinesData> pipeLinesDatas) {
		this.pipeLinesDatas = pipeLinesDatas;
	}

	@Override
	public String toString() {
		return "PipeLines [id=" + id + ", name=" + name + ", pipeLinesDatas=" + pipeLinesDatas + "]";
	}

}