package com.hcl.nxp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.hcl.nxp.vo.PipeLines;
import com.hcl.nxp.vo.PipeLinesDTO;
import com.hcl.nxp.vo.Request;
import com.hcl.nxp.vo.User;
import com.hcl.nxp.vo.UserDetails;

public interface NXPService {

	UserDetails registerUser(final Request request);

	Optional<User> login(final User user);

	UserDetails getUserDetails(final String username);

	List<PipeLines> getPipeLines();

	PipeLines validatePipeLines(final Integer id);

	PipeLinesDTO getPipeLinesData(final PipeLines pipeLines);

	void download(final String fileName);

	String upload(byte[] content, final String fileName);

	List<String> getFiles();

	String uploadFile(final MultipartFile file);

	String getLogs(final Integer id, final String name);

}
