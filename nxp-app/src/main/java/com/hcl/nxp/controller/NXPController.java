package com.hcl.nxp.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hcl.nxp.exception.ServiceException;
import com.hcl.nxp.service.NXPService;
import com.hcl.nxp.vo.Candidate;
import com.hcl.nxp.vo.Favourite;
import com.hcl.nxp.vo.PipeLines;
import com.hcl.nxp.vo.PipeLinesDTO;
import com.hcl.nxp.vo.Request;
import com.hcl.nxp.vo.Response;
import com.hcl.nxp.vo.User;
import com.hcl.nxp.vo.UserDetails;

@RestController
@RequestMapping("/v1")
public class NXPController {
	//    export const apiRoot: string = 'http://localhost:8080/nxp-app/v1';

	private static final Logger LOGGER = LogManager.getLogger(NXPController.class);

	@Autowired
	NXPService nxpService;

	public NXPController() {
		super();
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value = "/login", consumes = "application/json", produces = "application/json")
	public UserDetails login(@RequestBody final User user) {

		if (user == null) {
			throw new ServiceException(HttpStatus.BAD_REQUEST.value(), "User details is missing!.");
		}

		LOGGER.info("Login details is verifying : " + user.getUserName());

		if (StringUtils.isEmpty(user.getUserName())) {
			throw new ServiceException(HttpStatus.BAD_REQUEST.value(), "UserName is missing!.");
		}
		if (StringUtils.isEmpty(user.getPassword())) {
			throw new ServiceException(HttpStatus.BAD_REQUEST.value(), "Password is missing!.");
		}

		final Optional<User> result = nxpService.login(user);
		if (!result.isPresent()) {
			throw new ServiceException(HttpStatus.NOT_FOUND.value(), "user not found!.");
		}
		return nxpService.getUserDetails(user.getUserName());
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value = "/register", consumes = "application/json", produces = "application/json")
	public Response registerUser(@RequestBody final Request request) {
		LOGGER.info("Request data received for registering user: " + request);
		final UserDetails userDetails = nxpService.registerUser(request);
		return new Response("User registered successfully.", userDetails);
	}

	@ResponseStatus(HttpStatus.OK)
	@DeleteMapping("/logout")
	public Response logout(final HttpServletRequest request) {
		return new Response("You logout successfully..");
	}

	/*
	 * Logs Related code
	 */
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/getPipeLines")
	public HttpEntity<List<PipeLines>> getPipeLines() throws IOException {
		return new HttpEntity<>(nxpService.getPipeLines());
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/logs/{id}")
	public PipeLinesDTO getPipeLinesData(@PathVariable final Integer id) throws IOException {
		if (id == null) {
			throw new ServiceException(HttpStatus.BAD_REQUEST.value(), "File name not present in request.");
		}
		final PipeLines pipeLines = nxpService.validatePipeLines(id);
		if (pipeLines == null) {
			throw new ServiceException(HttpStatus.NOT_FOUND.value(), "PipeLines does not exist.");
		}
		return nxpService.getPipeLinesData(pipeLines);
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/logs/downloads/{id}/{headersDisplay}")
	public ResponseEntity<byte[]> getPipeLinesLogs(@PathVariable final Integer id,
			@PathVariable final String headersDisplay) throws IOException {
		if (id == null) {
			throw new ServiceException(HttpStatus.BAD_REQUEST.value(), "PipeLines not present in request.");
		}
		System.out.println(id);
		final PipeLines pipeLines = nxpService.validatePipeLines(id);
		if (pipeLines == null) {
			throw new ServiceException(HttpStatus.NOT_FOUND.value(), "PipeLines does not exist.");
		}
		final byte[] data = nxpService.getLogs(id, headersDisplay).getBytes();

		final String fileName = pipeLines.getName();
		final HttpHeaders respHeaders = new HttpHeaders();
		respHeaders.setContentLength(data.length);
		respHeaders.setContentType(new MediaType("text", "json"));
		respHeaders.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		respHeaders.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
		return new ResponseEntity<>(data, respHeaders, HttpStatus.OK);
	}

	/************************************************************************************************************************
	 *
	 *
	 *
	 *
	 *
	 *
	 *
	 *
	 *
	 *
	 *
	 *
	 *
	 *
	 *
	 *
	 *
	 *
	 *
	 * @param file
	 * @return
	 * @throws IOException
	 */

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response uploadFile(@RequestPart("file") final MultipartFile file) throws IOException {
		if (file.isEmpty()) {
			throw new ServiceException(HttpStatus.BAD_REQUEST.value(), "Please select a file to upload.");
		}
		// nxpService.upload(file.getBytes(), file.getOriginalFilename());
		nxpService.uploadFile(file);
		return new Response("File Uploaded successfully.");
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/download/{fileName:.+}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public HttpEntity<String> downloadFile(@PathVariable final String fileName) throws IOException {
		if (StringUtils.isEmpty(fileName)) {
			throw new ServiceException(HttpStatus.BAD_REQUEST.value(), "File name not present in request.");
		}
		final HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_TYPE, "application/zip");
		headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
		nxpService.download(fileName);
		return new HttpEntity<>(fileName + "download successfully!.", headers);
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/downloads/{fileName:.+}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public HttpEntity<String> downloadFiles(@PathVariable final String fileName) throws IOException {
		if (StringUtils.isEmpty(fileName)) {
			throw new ServiceException(HttpStatus.BAD_REQUEST.value(), "File name not present in request.");
		}
		final HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_TYPE, "application/zip");
		headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
		nxpService.download(fileName);
		return new HttpEntity<>(fileName + "download successfully!.", headers);
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/getFiles")
	public HttpEntity<List<String>> getFiles() throws IOException {
		return new HttpEntity<>(nxpService.getFiles());
	}


}
