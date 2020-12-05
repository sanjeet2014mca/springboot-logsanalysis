package com.hcl.nxp.vo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "nxp_pipelines_logs")
public class PipeLinesData implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "nxp_pipelines_logs_id")
	private Integer id;

	@Column(name = "headers_name")
	private String headersName;

	@Column(name = "headers_display")
	private String headersDisplay;

	@Column(name = "headers_value")
	private String headersValue;

	private String logs;
	private Integer durations;

	@JoinColumn(name = "nxp_pipelines_id")
	@ManyToOne(fetch = FetchType.EAGER)
	@JsonBackReference
	private PipeLines pipeLinesID;

	public Integer getId() {
		return id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public String getHeadersName() {
		return headersName;
	}

	public void setHeadersName(final String headersName) {
		this.headersName = headersName;
	}

	public String getHeadersDisplay() {
		return headersDisplay;
	}

	public void setHeadersDisplay(final String headersDisplay) {
		this.headersDisplay = headersDisplay;
	}

	public String getHeadersValue() {
		return headersValue;
	}

	public void setHeadersValue(final String headersValue) {
		this.headersValue = headersValue;
	}

	public String getLogs() {
		return logs;
	}

	public void setLogs(final String logs) {
		this.logs = logs;
	}

	public Integer getDurations() {
		return durations;
	}

	public void setDurations(final Integer durations) {
		this.durations = durations;
	}

	public PipeLines getPipeLinesID() {
		return pipeLinesID;
	}

	public void setPipeLinesID(final PipeLines pipeLinesID) {
		this.pipeLinesID = pipeLinesID;
	}

	@Override
	public String toString() {
		return "PipeLinesData [id=" + id + ", headersName=" + headersName + ", headersDisplay=" + headersDisplay
				+ ", headersValue=" + headersValue + ", logs=" + logs + ", durations=" + durations + ", pipeLinesID="
				+ pipeLinesID + "]";
	}

}
