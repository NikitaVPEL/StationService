package com.vst.station.converter;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.vst.station.dto.ConnectorDTO;
import com.vst.station.dto.ConnectorUpdateDTO;
import com.vst.station.dto.MeterValueDTO;
import com.vst.station.model.Connector;

@Component
public class ConnectorConverter {

	
	public Connector dtoToEntity(ConnectorDTO connectorDTO ) {
		Connector connector = new Connector();
		BeanUtils.copyProperties(connectorDTO, connector);
		return connector;
	}
	
	public ConnectorDTO entityToDto(Connector connector) {
		ConnectorDTO connectorDTO = new ConnectorDTO();
		BeanUtils.copyProperties(connector, connectorDTO);
		return connectorDTO;
	}
	
	public Connector dtoToEntity1(ConnectorUpdateDTO connectorDTO ) {
		Connector connector = new Connector();
		BeanUtils.copyProperties(connectorDTO, connector);
		return connector;
	}
	
//	public MeterValueDTO entityToMeterDTO(MeterValueDTO meterValueDTO) {
//		
//		MeterValueDTO dto = new MeterValueDTO();
//		BeanUtils.copyProperties(meterValueDTO, dto);
//		System.out.println("converter DTO:--"+dto);
//		return dto;	
//	}
	
}
