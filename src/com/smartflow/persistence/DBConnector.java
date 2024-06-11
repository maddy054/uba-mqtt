package com.smartflow.persistence;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.smartflow.enums.Tables;
import com.smartflow.models.Flow;
import com.smartflow.models.LineConnection;
import com.smartflow.models.Location;
import com.smartflow.models.Project;
import com.smartflow.models.Pump;
import com.smartflow.models.Sensor;
import com.smartflow.models.Tank;
import com.smartflow.models.User;
import com.smartflow.utilities.OwnException;
import com.smartflow.utilities.QueryBuilder;


public class DBConnector implements Connector {
	
	public User getUserDetails(String userName) throws OwnException {
		try {
			User user = new User();
			QueryBuilder queryBuilder = new QueryBuilder(Tables.USER.get());
			String query = queryBuilder.where(1).buildSelect();
			Map<Integer, Object> resultList = queryBuilder.executeQuery(query, userName).get(0);
			
			user.setUserName((String)resultList.get(1));
			user.setName((String)resultList.get(3));
			return user;
			
		}catch(SQLException e) {
			throw new OwnException(e.getMessage(),e);
		}
	}
	
	public void setUser(User user) throws OwnException{
		try {
			QueryBuilder queryBuilder =new  QueryBuilder(Tables.USER.get());
			String query = queryBuilder.buildInsert();
			queryBuilder.execute(query, user.getUserName(),user.getPassWord(),user.getName());
			
		}catch(SQLException e) {
			e.printStackTrace();
			throw new OwnException(e.getMessage(),e);
		}
		
	}
	public List<Project> getProject(String userName) throws OwnException {
		try {
			List<Project> projectList = new ArrayList<>(); 
			QueryBuilder  queryBuilder = new QueryBuilder(Tables.PROJECT.get());
			String query = queryBuilder.where(2).buildSelect();
			
			List<Map<Integer,Object>> resultList = queryBuilder.executeQuery(query, userName);
			for(Map<Integer,Object> map : resultList) {
				Project project = new  Project();
				project.setUserName((String) map.get(0));
				project.setProjectId((int) map.get(1));
				project.setProjectName((String) map.get(2));
				projectList.add(project);
			}
			return projectList;
			
		}catch(SQLException e) {
			throw new OwnException(e.getMessage(),e);
		}
		
	}
	
	public void setProject(Project project) throws OwnException {
		try {
			QueryBuilder  queryBuilder = new QueryBuilder(Tables.PROJECT.get());
			String query = queryBuilder.column(2,3).buildInsert();
			queryBuilder.execute(query, project.getUserName(),project.getProjectName());
			
		}catch(SQLException e) {
			throw new OwnException(e.getMessage(),e);
		}
	
	}
	
	public Map<String, Tank> getTank(int projectId) throws OwnException {
		try {
			Map<String,Tank> result = new HashMap<String, Tank>();
			QueryBuilder queryBuilder = new QueryBuilder(Tables.TANK.get());
			String query = queryBuilder.where(5).buildSelect();
			List<Map<Integer,Object>> resultList = queryBuilder.executeQuery(query, projectId);
			
			for(Map<Integer,Object> map : resultList) {
				Tank tank = new Tank();
				tank.setProjectId((int) map.get(5));
				tank.setLocationId((int) map.get(1));
				tank.setCapacity((long) map.get(2));
				tank.setHeight((int) map.get(3));
				tank.setWaterAvailability((long) map.get(4));
				getLocation(tank);
				result.put(tank.getLocationName(), tank);
			}
			return result;
		}catch(SQLException e) {
			throw new OwnException(e.getMessage(),e);
		}
	}
	
	private void getLocation(Location location) throws OwnException {
		try {
			QueryBuilder queryBuilder = new QueryBuilder(Tables.LOCATION.get());
			String query = queryBuilder.where(2).buildSelect();
			Map<Integer,Object> resultList = queryBuilder.executeQuery(query, location.getLocationId()).get(0);
			
			location.setProjectId((int) resultList.get(1));
			location.setLocationId((int) resultList.get(2));
			location.setLocation((String) resultList.get(3));
			location.setLocationName((String) resultList.get(4));
			location.setLocationType((String) resultList.get(5));
			location.setDescription((String) resultList.get(6));	
			
		}catch(SQLException e) {
			throw new OwnException(e.getMessage(),e);
		}
		
	}
	
	public void setTank(Tank tank) throws OwnException {
		try {
			setLocation(tank);
			QueryBuilder  queryBuilder = new QueryBuilder(Tables.TANK.get());
			String query = queryBuilder.buildInsert();
			queryBuilder.execute(query, getLocationId(tank.getLocation()),tank.getCapacity(),tank.getHeight(),tank.getWaterAvailability(),tank.getProjectId());
			
		}catch(SQLException e) {
			throw new  OwnException(e.getMessage());
		}
	}
	
	private int getLocationId(String location) throws SQLException {
		QueryBuilder  queryBuilder = new QueryBuilder(Tables.LOCATION.get());
		String query = queryBuilder.column(2).where(3).buildSelect();
		return (int) queryBuilder.executeQuery(query,location).get(0).get(2);
		
	}
	private void setLocation(Location location) throws OwnException {
		try {

			QueryBuilder  queryBuilder = new QueryBuilder(Tables.LOCATION.get());
			String query = queryBuilder.buildInsert();
			queryBuilder.execute(query,location.getProjectId(),location.getLocationId(),location.getLocation(),location.getLocationName(),location.getLocationType(),location.getDescription());
			
		}catch(SQLException e) {
			throw new  OwnException(e.getMessage());
		}
	}
	
	public Map<String, Pump> getPump(int projectId) throws OwnException {
		try {
			Map<String,Pump> result = new HashMap<>();
			
			QueryBuilder queryBuilder = new QueryBuilder(Tables.PUMP.get());
			String query = queryBuilder.where(5).buildSelect();
			List<Map<Integer,Object>> resultList = queryBuilder.executeQuery(query, projectId);
			for(Map<Integer,Object> map : resultList) {
				Pump pump = new Pump();
				pump.setProjectId((int) map.get(5));
				pump.setLocationId((int) map.get(1));
				pump.setPowerRating((String) map.get(2));
				pump.setVoltageRating((String) map.get(3));
				pump.setPumpType((String) map.get(4));
				getLocation(pump);
				result.put(pump.getLocationName(), pump);
			}
			return result;
			
		}catch(SQLException e) {
			throw new OwnException(e.getMessage());
		}
	}
	public void setPump(Pump pump) throws OwnException {
		try {
			setLocation(pump);
			QueryBuilder  queryBuilder = new QueryBuilder(Tables.PUMP.get());
			String query = queryBuilder.buildInsert();
			queryBuilder.execute(query,getLocationId(pump.getLocation()),pump.getPowerRating(),pump.getVoltageRating(),pump.getPumpType(),pump.getProjectId());
			
		}catch(SQLException e) {
			throw new OwnException(e.getMessage());
		}
	}
	public  Map<Integer, LineConnection> getConnection(int projectId) throws OwnException {
		try {
			Map<Integer,LineConnection> result = new HashMap<>();
			
			QueryBuilder queryBuilder = new QueryBuilder(Tables.CONNECTION.get());
			String query = queryBuilder.where(4).buildSelect();
			List<Map<Integer,Object>> resultList = queryBuilder.executeQuery(query, projectId);
			for(Map<Integer,Object> map : resultList) {
				LineConnection connection = new LineConnection();
				connection.setConnectionId((int) map.get(1));
				connection.setFromLocation((int) map.get(2));
				connection.setToLocation((int) map.get(3));
				connection.setProjectId((int) map.get(4));	
				
				result.put(connection.getConnectionId(), connection);
			}
			return result;
			
		}catch(SQLException e) {
			throw new OwnException(e.getMessage());
		}
	}
	
	public void setConnection(LineConnection connection) throws OwnException {
		try {
			QueryBuilder  queryBuilder = new QueryBuilder(Tables.CONNECTION.get());
			String query = queryBuilder.buildInsert();
			queryBuilder.execute(query,connection.getConnectionId(),connection.getFromLocation(),connection.getToLocation(),connection.getProjectId());
			
		}catch(SQLException e) {
			throw new OwnException(e.getMessage());
		}
		
	}
	public List<Flow> getFLow(int connectionId,int limit,int offset) throws OwnException {
		try {
			List<Flow> result = new ArrayList<>();
			
			QueryBuilder queryBuilder = new QueryBuilder(Tables.FLOW.get());
			String query = queryBuilder.where(1).limit().offset().buildSelect();
			List<Map<Integer,Object>> resultList = queryBuilder.executeQuery(query, connectionId,limit,offset);
			for(Map<Integer,Object> map : resultList) {
				Flow flow = new Flow();
				flow.setConnectionId((int) map.get(1));
				flow.setFromTime((long) map.get(2));
				flow.setToTime((long) map.get(3));
				flow.setFromValue((int) map.get(4));
				flow.setToValue((int) map.get(5));
				result.add(flow);
			}
			return result;
			
		}catch(SQLException e) {
			throw new OwnException(e.getMessage());
		}
	}
	public void setFlow(Flow flow) throws OwnException {
		try {
			QueryBuilder  queryBuilder = new QueryBuilder(Tables.FLOW.get());
			String query = queryBuilder.buildInsert();
			queryBuilder.execute(query,flow.getConnectionId(),flow.getFromTime(),flow.getToTime(),flow.getFromValue(),flow.getToValue(),flow.getLeakage());
		}catch(SQLException e) {
			throw new OwnException(e.getMessage());
		}
		
	}
	public List<Sensor> getSensorData(int locationId,int sensorType,int limit,int offset) throws OwnException {
		try {
			
			List<Sensor> result = new ArrayList<>();
			QueryBuilder queryBuilder = new QueryBuilder(Tables.SENSOR_DATA.get());
			String query = queryBuilder.where(1,3).limit().offset().buildSelect();
			List<Map<Integer,Object>> resultList = queryBuilder.executeQuery(query,locationId,sensorType,limit,offset);
			for(Map<Integer,Object> map : resultList) {
				Sensor sensor = new Sensor();
				sensor.setLocationId((int) map.get(1));
				sensor.setTime((long) map.get(2));
				sensor.setParameter((int) map.get(3));
				sensor.setValue((String) map.get(4));
				result.add(sensor);
			}
			return result;
		}catch(SQLException e) {
			throw new OwnException(e.getMessage());
		}
	}
	public void setSensorData(Sensor sensor) throws OwnException {
		try {
			QueryBuilder  queryBuilder = new QueryBuilder(Tables.SENSOR_DATA.get());
			String query = queryBuilder.buildInsert();
			queryBuilder.execute(query,sensor.getLocationId(),sensor.getTime(),sensor.getParameter(),sensor.getValue());
			
		}catch(SQLException e) {
			throw new OwnException(e.getMessage());
		}
	}
	
}

