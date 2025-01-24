package videoshack.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import videoshack.controller.model.VideoShackData;
import videoshack.controller.model.VideoShackData.VideoShackCustomer;
import videoshack.controller.model.VideoShackData.VideoShackEmployee;
import videoshack.service.VideoShackService;

// Final Create VideoShack Controller Add @Post and @Get Mapping 
 

@RestController
@RequestMapping("/videoshack")
@Slf4j

public class VideoShackController<videoShackService> {
	@Autowired 
	private VideoShackService videoShackService;
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public VideoShackData insertVideoshack(@RequestBody VideoShackData videoShackData) {
		log.info("Creating Video Shack()", videoShackData);
		return videoShackService.saveVideoShack(videoShackData);
	}
	
	@PutMapping("/{videoShackId}")	
	public VideoShackData updateVideoShack(@PathVariable Long videoShackId,
		@RequestBody VideoShackData videoShackData) {
	videoShackData.setVideoShackId(videoShackId);
	log.info("Updating Video Shack {}", videoShackData);
	return videoShackService.saveVideoShack(videoShackData);
	
	}
	
	@PostMapping("/{videoShackId}/employee")
	@ResponseStatus(code = HttpStatus.CREATED)
	public VideoShackEmployee addEmployeeToVideoShack(@PathVariable Long videoShackId,
		@RequestBody VideoShackEmployee videoShackEmployee) {
	log.info("Adding Employee {} to the Video Shack with ID={}", videoShackEmployee,
			videoShackId);
	return videoShackService.saveEmployee(videoShackId, videoShackEmployee);
	
	}
	
	@PostMapping("/{videoShackId}/customer")
	@ResponseStatus(code = HttpStatus.CREATED)
	public VideoShackCustomer addCustomerToVideoShack(@PathVariable Long videoShackId,
		@RequestBody VideoShackCustomer videoShackCustomer) {
	log.info("Adding Customer {} to the Video Shack with ID={}", videoShackCustomer,
			videoShackId);
	return videoShackService.saveCustomer(videoShackId, videoShackCustomer);
	}
	
	@GetMapping 
	public List<VideoShackData> retriveAllVideoShacks() {
		log.info("retreive all Video Shack stores");
	return videoShackService.retrieveAllVideoShacks();
	}
	@GetMapping("/{videoShackId}")
	public VideoShackData retrivePetStoreById(@PathVariable Long videoShackId) {
		log.info("retreive all Video Shacks with ID=()", videoShackId);
	return videoShackService.retrieveVideoShackById(videoShackId);
	}
	
	@DeleteMapping("/{videoShackId}")
	public Map<String, String> deletePetStoreById(@PathVariable Long videoShackId) {
		log.info("Deleteing Video Shack with ID={}", videoShackId);
		
		videoShackService.deleteVideoShackById(videoShackId);
		
		return Map.of("message", "Video shack with ID=" + videoShackId + " deleted.");
	}
}
		
