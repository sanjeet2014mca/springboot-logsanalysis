package com.hcl.nxp.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.hcl.nxp.exception.ServiceException;
import com.hcl.nxp.repository.PipeLinesDataRepository;
import com.hcl.nxp.repository.PipeLinesRepository;
import com.hcl.nxp.vo.PipeLines;
import com.hcl.nxp.vo.PipeLinesDTO;
import com.hcl.nxp.vo.Request;
import com.hcl.nxp.vo.User;
import com.hcl.nxp.vo.UserDetails;

@Service
@Transactional
public class NXPServiceImpl implements NXPService {

	@Autowired
	private Environment env;

	@Autowired
	private PipeLinesRepository pipeLinesRepository;
	@Autowired
	private PipeLinesDataRepository pipeLinesDataRepository;

	private static final List<UserDetails> userDetailsList = new ArrayList<>();
	private static final List<User> users = new ArrayList<>();

	static {
		users.add(new User("digvijay1684@gmail.com", "1234"));

		userDetailsList.add(new UserDetails(111, "manish", "kumar", "singh", "9898989898", "digvijay1684@gmail.com",
				"Male", "29-05-2020", "active", "test.txt"));
	}

	@Override
	public Optional<User> login(final User loggedUser) {
		return users.stream().filter(user -> loggedUser.getUserName().equals(user.getUserName())
				&& loggedUser.getPassword().equals(user.getPassword())).findAny();
	}

	@Override
	public UserDetails getUserDetails(final String username) {
		return userDetailsList.stream().filter(res -> username.equals(res.getEmail())).findAny().orElse(null);
	}

	@Override
	public UserDetails registerUser(final Request request) {
		final UserDetails userDetails = new UserDetails();
		userDetails.setId(System.currentTimeMillis());
		userDetails.setfName(request.getfName());
		userDetails.setmName(request.getmName());
		userDetails.setlName(request.getlName());
		userDetails.setEmail(request.getEmail());
		userDetails.setGender(request.getGender());
		userDetails.setMobile(request.getMobile());
		userDetails.setDob(request.getDob());
		userDetailsList.add(userDetails);

		final User user = new User();
		user.setUserName(userDetails.getEmail());
		user.setPassword(request.getPassword());
		users.add(user);

		return userDetails;
	}

	@Override
	public List<PipeLines> getPipeLines() {
		return pipeLinesRepository.findAll();
	}

	@Override
	public PipeLines validatePipeLines(final Integer id) {
		return pipeLinesRepository.findById(id).get();
	}

	@Override
	public PipeLinesDTO getPipeLinesData(final PipeLines pipeLines) {
		pipeLines.setPipeLinesDatas(pipeLinesDataRepository.fetchRecords(pipeLines.getId()));
		final PipeLinesDTO pipeLinesDTO = new PipeLinesDTO();
		pipeLinesDTO.setPipeLines(pipeLines);
		return pipeLinesDTO;
	}

	@Override
	public String getLogs(final Integer id, final String name) {
		return pipeLinesDataRepository.getLogs(id, name);
	}

	// +*********************************************************************************************************************

	@Override
	public String uploadFile(final MultipartFile file) {
		// Normalize file name
		final String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		try {
			final Path fileStorageLocation = Paths.get(env.getProperty("file.upload-dir")).toAbsolutePath().normalize();
			final Path targetLocation = fileStorageLocation.resolve(fileName);
			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
		} catch (final Exception ex) {
			System.out.println("Exception:" + ex);
		}
		return fileName;
	}

	@Override
	public String upload(final byte[] content, final String fileName) {
		final String dir = env.getProperty("file.upload-dir");
		try {
			// Get the file and save it somewhere
			final Path path = Paths.get(dir + '/' + fileName);
			Files.write(path, content);
		} catch (final IOException e) {
			e.printStackTrace();
			throw new ServiceException("Could not store the file. Error " + e.getMessage());
		}
		System.out.println(fileName + " uploaded successfully to " + dir);
		return "File uploaded successfully!.";
	}

	@Override
	public void download(final String fileName) {

		final String dir = env.getProperty("file.upload-dir");
		final String filePath = dir + '\\' + fileName;
		final File file = new File(filePath);

		InputStream inputStream;
		try {
			inputStream = new FileInputStream(file);
			IOUtils.toByteArray(inputStream);

			final FileOutputStream fos = new FileOutputStream(filePath);
			final byte[] buffer = new byte[1024];
			int count = 0;
			while ((count = inputStream.read(buffer)) != -1) {
				fos.write(buffer, 0, count);
			}
			fos.close();
			inputStream.close();
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<String> getFiles() {
		final String dir = env.getProperty("file.upload-dir");
		final List<String> dirFles = new ArrayList<>();

		try {
			final Stream<Path> files = Files.list(Paths.get(dir));
			files.forEach(file -> dirFles.add(file.toFile().getName()));
			files.close();
		} catch (final IOException e) {
			e.printStackTrace();
		}
		return dirFles;
	}

}
