package com.vst.station.service;

import java.util.List;

import com.vst.station.dto.ChargerDTO;
import com.vst.station.dto.ChargerUpdateDTO;
import com.vst.station.dto.ConnectorDTO;
import com.vst.station.dto.ConnectorUpdateDTO;
import com.vst.station.dto.ocppVerificationDTO;
import com.vst.station.model.Charger;
import com.vst.station.model.Connector;

public interface ChargerServiceInterface {

	public boolean addCharger(String stationId, ChargerDTO chargerDTO);

	public boolean addConnector(String stationId, String chargerId, ConnectorDTO connectorDTO);

	public boolean updateCharger(String stationId, String chargerId, ChargerUpdateDTO chargerDTO);

	public boolean updateConnectorById(String connectorId, ConnectorUpdateDTO connectorDTO);

	public boolean removeStationCharger(String stationId, String chargerId);

	public boolean removeConnector(String connectorId);

	List<Charger> getAllStationChargers(String stationId);

	List<Connector> getAllStationConnector(String stationId, String chargerId);

	Connector getConnector(String connectorId);

	Charger getCharger(String chargerId);

	public boolean updateChargerStatus(String stationId, List<String> chargerIdList, String status);

	public boolean removeCharger(String chargerId);

	public boolean getChargerStatusByChargerSerialNumber(String chargerSerialNumber);

	public Boolean initialVerification(String chargerSerialNumber, ocppVerificationDTO ocppVerificationDTO);

	public String getChargerOCPPProtocol(String chargerSerialNumber);

}
