package com.smartflow.logics;

import java.util.List;
import java.util.Map;

import com.smartflow.enums.SensorType;
import com.smartflow.models.Flow;
import com.smartflow.models.LineConnection;
import com.smartflow.models.Project;
import com.smartflow.models.Pump;
import com.smartflow.models.Sensor;
import com.smartflow.models.Tank;
import com.smartflow.models.User;
import com.smartflow.persistence.DBConnector;
import com.smartflow.utilities.OwnException;
import com.smartflow.utilities.SHAHash;

public class SmartWater {
	DBConnector connector = new DBConnector();
	
	public User getuserDetails(String userName) throws OwnException {
		return connector.getUserDetails(userName);
	}
	
	public void setUserDetails(User user) throws OwnException {
		user.setPassWord(SHAHash.getHash(user.getPassWord()));
		connector.setUser(user);
	}
	public List<Project> getProjectDetails(String userName) throws OwnException {
		return connector.getProject(userName);
	}
	
	public void addProject(Project project) throws OwnException {
		connector.setProject(project);
	}
	
	public Map<String, Tank> getTank(int projectId) throws OwnException {
		 return connector.getTank(projectId);
	}
	
	public void addTank(Tank tank) throws OwnException {
		connector.setTank(tank);
		
	}
	
	public Map<String, Pump> getPump(int projectId) throws OwnException {
		return connector.getPump(projectId);
	}
	
	public void addPump(Pump pump) throws OwnException {
		connector.setPump(pump);
	}
	
	public Map<Integer, LineConnection> getConnection(int projectId) throws OwnException {
		return connector.getConnection(projectId);
	}
	public void addConnection(LineConnection connection) throws OwnException {
		connector.setConnection(connection);
	}
	
	public List<Flow> getFlow(int connectionId,int limit,int offset) throws OwnException {
		return connector.getFLow(connectionId, limit, offset);
	}
	
	public void addFlow(Flow flow) throws OwnException {
		flow.setLeakage(flow.getFromValue() - flow.getToValue());
		connector.setFlow(flow);
	}
	public List<Sensor> getSensorData(int locationId,SensorType sensor,int limit,int offset) throws OwnException {
		return connector.getSensorData(locationId, sensor.ordinal(), limit, offset);
	}
	public void addSensorData(Sensor sensor) throws OwnException {
		connector.setSensorData(sensor);
	}
}
