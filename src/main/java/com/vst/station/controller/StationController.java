package com.vst.station.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vst.station.dto.StationDTO;
import com.vst.station.dto.StationDTO1;
import com.vst.station.dto.StationFindDTO;
import com.vst.station.dto.StationUpdateDTO;
import com.vst.station.exception.InValidDataException;
import com.vst.station.exception.StationException;
import com.vst.station.model.Station;
import com.vst.station.service.StationServiceImpl;

@RequestMapping("/manageStation")
@CrossOrigin(origins = "*")
@RestController
public class StationController {

	@Autowired
	StationServiceImpl stationServiceImpl;

//	boolean flag=false;

	public static final Logger logger = LogManager.getLogger(StationController.class);

	/**
	 * Usage: Add new station
	 * 
	 * HTTP method : POST and URL : manageStation/addStation
	 * 
	 * @param stationDto
	 * @return Http response and string message "Station added successfully"
	 */
	@PostMapping("/addStation")
	public ResponseEntity<String> saveStation(@Valid @RequestBody StationDTO stationDTO) {

		if (stationServiceImpl.addStation(stationDTO) == true)
			return new ResponseEntity<>("Station Added", HttpStatus.OK);
		else
			return new ResponseEntity<>("Something Went Wrong", HttpStatus.BAD_REQUEST);
	}

	/**
	 * Usage: update the station details
	 * 
	 * HTTP method : PUT and URL : manageStation/updateStation
	 * 
	 * @param stationId, stationDto
	 * @return Http response and string message "Station updated successfully"
	 */
	@PutMapping("/updateStation")
	public ResponseEntity<String> updateStationDetails(@RequestParam("stationId") String stationId,
			@Valid @RequestBody StationUpdateDTO stationUpdateDTO) {
		if (stationServiceImpl.updateStation(stationId, stationUpdateDTO) == true)
			return new ResponseEntity<>("Station Details Updated Successfully", HttpStatus.OK);
		else
			return new ResponseEntity<>("Something Went Wrong", HttpStatus.NOT_FOUND);
	}

	/**
	 * Usage: delete the specific Station using Station Id
	 * 
	 * HTTP method : DELETE and URL : manageStation/deleteStation
	 * 
	 * @param stationId
	 * @return Http response and string message "Station successfully deleted"
	 */
	@DeleteMapping("/deleteStation")
	public ResponseEntity<String> deleteStationDetails(@RequestParam("stationId") String stationId) {
		if (stationServiceImpl.removeStation(stationId) == true)
			return new ResponseEntity<>("Station Deleted Successfully", HttpStatus.OK);
		else
			return new ResponseEntity<>("Something Went Wrong", HttpStatus.NOT_FOUND);
	}

	/**
	 * Usage: Get the Station object/ details by Station Id
	 * 
	 * HTTP method : GET and URL : manageStation/getStation
	 * 
	 * @param stationId
	 * @return Http response and Station object
	 */
	@GetMapping("/getStation")
	public ResponseEntity<?> getStationById(@RequestParam("stationId") String stationId) {
		
		Station obj = stationServiceImpl.show(stationId);
		if (obj != null)
			return ResponseEntity.ok(obj);
		else
			return ResponseEntity.ok(obj);
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Station Not Available");
		
	}

	/**
	 * Usage: Get/read the details of all the available Station
	 * 
	 * HTTP method : GET and URL : manageStation/getStations
	 * 
	 * @return Http response and list of Station object
	 */
	@GetMapping("/getStations")
	public ResponseEntity<List<?>> getAllStation() {
		
		return ResponseEntity.ok(stationServiceImpl.showAll());

	}

	/**
	 * Usage: Get the Station object/ details by Station host Id
	 * 
	 * HTTP method : GET and URL : manageStation/getStationHost
	 * 
	 * @param stationHostId
	 * @return Http response and list of Station object
	 */
	@GetMapping("/getStationHost")
	public ResponseEntity<List<Station>> getStationByHostId(@RequestParam("stationHostId") String stationHostId) {
		List<Station> finalList = stationServiceImpl.getByHostId(stationHostId);
		if (!finalList.isEmpty())
			return ResponseEntity.ok(finalList);
		else
			return ResponseEntity.ok(finalList);
	}

	/**
	 * Usage: Get the Station object/ details by Station Vendor Id
	 * 
	 * HTTP method : GET and URL : manageStation/getStationVendor
	 * 
	 * @param stationVendorId
	 * @return Http response and list of Station object
	 */
	@GetMapping("/getStationVendor")
	public ResponseEntity<List<Station>> getStationByVendorId(@RequestParam("stationVendorId") String stationVendorId) {
		List<Station> finalList = stationServiceImpl.getByVendorId(stationVendorId);
		if (!finalList.isEmpty())
			return ResponseEntity.ok(finalList);
		else
			return ResponseEntity.ok(finalList);
	}

//	@GetMapping("/getStationHistory")
//	public ResponseEntity<List<Station>> getInactive() {
//		List<Station> finalList = stationServiceImpl.getInactiveStation();
//		if (!finalList.isEmpty())
//			return ResponseEntity.ok(finalList);
//		else
//			return ResponseEntity.ok(finalList);
//	}

	/**
	 * Usage: Get the Station object/ details by Station Id
	 * 
	 * HTTP method : GET and URL : manageStation/getStationById
	 * 
	 * @param stationId
	 * @return Http response and Station object without chargers and connectors
	 */
	@GetMapping("/getStationById")
	public ResponseEntity<Station> getStationData(@RequestParam("stationId") String stationId) {
		return ResponseEntity.ok(stationServiceImpl.getStation(stationId));
	}

	/**
	 * Usage: Get list of required (variable) details of the Station object/ details
	 * by Station Id
	 * 
	 * HTTP method : GET and URL : manageStation/getStationInterface
	 * 
	 * @return Http response and list of Station object without chargers and
	 *         connectors
	 */
	@GetMapping("/getStationInterface")
	public ResponseEntity<List<StationDTO1>> getRequiredStationData() {
		return ResponseEntity.ok(stationServiceImpl.getRequiredStationData());
	}

	/**
	 * Usage: Get list of the Station object/ details by keyword from search bar
	 * 
	 * HTTP method : GET and URL : manageStation/getStationsByKeyword
	 * 
	 * @param query(keyword)
	 * @return Http response and list of Station object without chargers and
	 *         connectors
	 */
	@GetMapping("/getStationsByKeyword")
	public ResponseEntity<List<StationDTO1>> searchStation(@RequestParam("query") String query) {
		return ResponseEntity.ok(stationServiceImpl.stationforApplication(query));
	}

	@GetMapping("/test")
	public Station test() {
		Station station = new Station();
		return station;
	}

	/**
	 * Usage: Get list of the Station object/ details by map radius(interface)
	 * 
	 * HTTP method : GET and URL : manageStation/getStationsLocation
	 * 
	 * @param longitude, latitude, maxDistance
	 * @return Http response and list of Station object present in that particular
	 *         radius
	 */
	@GetMapping("/getStationsLocation")
	public ResponseEntity<List<StationDTO1>> geoLocation(@RequestParam("longitude") double longitude,
			@RequestParam("latitude") double latitude, @RequestParam("maxDistance") double maxDistance) {
		return ResponseEntity.ok(stationServiceImpl.getAllStationforRadius(longitude, latitude, maxDistance));
	}

	/**
	 * Usage: Get the name and address of station by station id
	 * 
	 * HTTP method : GET and URL : manageStation/getStationAddress
	 * 
	 * @param stationId
	 * @return Http response and Station object
	 */
	@GetMapping("/getStationAddress")
	public ResponseEntity<StationFindDTO> getAddrerss(@RequestParam("stationId") String stationId) {
		
			return ResponseEntity.ok(stationServiceImpl.getNameAndAddressStation(stationId));

	}

	@PostMapping("/addUserAccess")
	public ResponseEntity<String> addUserAccess(@RequestParam("stationId") String stationId,
			@RequestParam("type") String type, @RequestBody List<String> list) {
			if (stationServiceImpl.addUserAccess(stationId, type, list) == true) {
				return new ResponseEntity<String>("user added successfully", HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("invalid details, user not added", HttpStatus.BAD_REQUEST);
			}
	}

	@GetMapping("/getRestrictedUsers")
	public ResponseEntity<List<String>> getUserAccess(@RequestParam("stationId") String stationId) {

			List<String> userAccess = stationServiceImpl.GetUserAccessList(stationId);
			if (!userAccess.isEmpty() && userAccess != null) {
				return new ResponseEntity<>(userAccess, HttpStatus.OK);
			} else
				return new ResponseEntity<>(userAccess, HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/addUserAccessList")
	public ResponseEntity<String> addUserAccessList(@RequestParam("stationId") String stationId,
			@RequestBody List<String> userIdList) {

			if (stationServiceImpl.addUserAccessList(stationId, userIdList) == true) {
				return new ResponseEntity<String>( "user added successfully", HttpStatus.OK);
			} else {
				 return new ResponseEntity<String>("Invalid details, user not added", HttpStatus.BAD_REQUEST);
			}

	}

	@GetMapping("/getIsUserPresentInRestrictionList")
			public ResponseEntity<Boolean> getIsUserPresentInRestrictionList(@RequestParam("stationId") String stationId, @RequestParam("userId") String userId) {
		
			return new ResponseEntity<Boolean>(stationServiceImpl.getIsUserPresentInRestrictionList(stationId, userId), HttpStatus.OK );	
			
	}
	
	@DeleteMapping("/deleteUserFromRestrictionList")
	public ResponseEntity<String> deleteUserFromRestrictionList(@RequestParam("stationId") String stationId, @RequestParam("userId") String userId) {
		
		if (stationServiceImpl.deleteUserFromRestrictionList(stationId, userId) == true) {
			return new ResponseEntity<String>( "user Deleted successfully", HttpStatus.OK);
		} else {
			 return new ResponseEntity<String>("Invalid User, user not Found", HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/getHostStation")
	public ResponseEntity<List<?>> getStationHostId(@RequestParam("stationHostId") String HostId){
			return ResponseEntity.ok(stationServiceImpl.getStationByHostId(HostId));
	}
	
	
}
