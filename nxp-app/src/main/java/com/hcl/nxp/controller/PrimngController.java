package com.hcl.nxp.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.hcl.nxp.exception.ServiceException;
import com.hcl.nxp.vo.Candidate;
import com.hcl.nxp.vo.Favourite;

@RestController
@RequestMapping("/v1")

public class PrimngController {

	//  webApiUri: string = 'http://localhost:56708/api/Candidate';
	//http://localhost:8080/nxp-app/v1/candidate

	public static List<Candidate> getCandidate(){
		List<Candidate> candidate=new ArrayList<Candidate>();
		Candidate candidateOb=new Candidate();
		candidateOb.setDate(new Date());
		candidateOb.setGender("Male");
		candidateOb.setId(101);
		candidateOb.setName("sanju");
		List<Favourite> obList=new ArrayList<Favourite>();
		Favourite favouriteOne=new Favourite();
		favouriteOne.setFavKey("game");
		favouriteOne.setFavValue("Cricket");
		Favourite ob2=new Favourite();
		ob2.setFavKey("Game");
		ob2.setFavValue("Football");
		obList.add(favouriteOne);
		obList.add(ob2);
		candidateOb.setFavouriteCollection(obList);;
		candidate.add(candidateOb);

		Candidate candidateOb1=new Candidate();
		candidateOb1.setDate(new Date());
		candidateOb1.setGender("Female");
		candidateOb1.setId(103);
		candidateOb1.setName("Annu");
		List<Favourite> obList1=new ArrayList<Favourite>();
		Favourite favouriteOne1=new Favourite();
		favouriteOne1.setFavKey("game");
		favouriteOne1.setFavValue("KhoKho");
		Favourite ob=new Favourite();
		ob.setFavKey("Game");
		ob.setFavValue("Kabaddi");
		obList1.add(favouriteOne1);
		obList1.add(ob);
		candidateOb1.setFavouriteCollection(obList1);
		candidate.add(candidateOb1);
		return candidate;
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/candidate")
	@ResponseBody
	public List<Candidate> candidateDetails() {
		System.out.println("inside NXPController.candidateDetails();");
		return PrimngController.getCandidate();
	}	

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/candidate/{id}")
	public @ResponseBody List<Candidate> deleteCandidate(@PathVariable final Integer id) throws IOException {
		List<Candidate> excludedObj=new ArrayList<Candidate>();
		if (id == null) {
			throw new ServiceException(HttpStatus.BAD_REQUEST.value(), "Candidate not present in request.");
		}else{
			System.out.println("--------------->"+id);
			 for(Candidate cobj:PrimngController.getCandidate()){
				 if(cobj.getId()!=id)
					 excludedObj.add(cobj);
			 }
			return excludedObj;
		}
	}
}
