package com.smartflow.persistence;

import java.util.List;
import java.util.Map;

import com.smartflow.models.Flow;
import com.smartflow.models.LineConnection;
import com.smartflow.models.Project;
import com.smartflow.models.Pump;
import com.smartflow.models.Sensor;
import com.smartflow.models.Tank;
import com.smartflow.models.User;
import com.smartflow.utilities.OwnException;

public interface Connector {
	
	public User getUserDetails(String userName) throws OwnException ;
	
	public void setUser(User user) throws OwnException;
	
	public List<Project> getProject(String userName) throws OwnException;
	
	public void setProject(Project project) throws OwnException ;
	
	public Map<String, Tank> getTank(int projectId) throws OwnException ;
	
	public void setTank(Tank tank) throws OwnException ;
	
	public Map<String, Pump> getPump(int projectId) throws OwnException;
	
	public void setPump(Pump pump) throws OwnException ;
	
	public  Map<Integer, LineConnection> getConnection(int projectId) throws OwnException;
	
	public void setConnection(LineConnection connection) throws OwnException;
	
	public List<Flow> getFLow(int connectionId,int limit,int offset) throws OwnException ;
	
	public void setFlow(Flow flow) throws OwnException;
	
	public List<Sensor> getSensorData(int locationId,int sensorType,int limit,int offset) throws OwnException ;
	
	public void setSensorData(Sensor sensor) throws OwnException;
	
	
	
	
}
