package com.exa.eventdao;

import static java.lang.System.out;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.exa.connector.CassandraConnector;
import com.exa.pojo.Cohost;
import com.exa.pojo.EventContainerClass;
import com.exa.pojo.Tenant;
import com.exa.pojo.User;
import com.fasterxml.jackson.core.JsonProcessingException;

public class EventDao {
	Logger logger = LoggerFactory.getLogger(EventDao.class);
	EventContainerClass eventContainerClass = new EventContainerClass();
	Session session = connect();

	public EventContainerClass update(EventContainerClass jsonNode) {
		logger.info("In update method of EventDao");
		try {
			String tenantId = jsonNode.getTenant().getId();
			String userId = jsonNode.getUser().getId();
			String startTime = String.valueOf(System.currentTimeMillis());
			UUID eventId = jsonNode.getEventid();
			String assistant = jsonNode.getAssistant().getUsername();
			String cohost = jsonNode.getCohost().getUsername();
			// String cover=jsonNode.getCohost().getUsername();
			String currency = jsonNode.getCurrency();
			List<String> days = jsonNode.getDays();
			List<String> participants = jsonNode.getParticipants();
			String description = jsonNode.getDescription();
			String finishRepeatingon = jsonNode.getRepeatend();
			String location = jsonNode.getLocation();
			UUID organisationId = jsonNode.getOrganisation();
			boolean paid = jsonNode.isPaid();
			double price = jsonNode.getPrice();
			boolean privateEvent = jsonNode.isPrivateevent();
			String repeat = jsonNode.getRepeat();
			String tags = jsonNode.getTag();
			String title = jsonNode.getTitle();

			logger.info("In update field");

			if (tenantId != null && eventId != null && eventId != null) {
				
				String selectCQL = "SELECT * FROM demo.eventsFinal WHERE tenantid='" + tenantId + "'" + "AND userid='" + userId
						+ "' AND eventid=" + eventId + ";";
				
				ResultSet resultSet = session.execute(selectCQL);
				Row row = resultSet.one();
				if(row.getBool("deleted") == true)
					return null;
				
				logger.info("Tenat id" + tenantId + "EventId" + eventId);
				System.out.println("Update query entering!!");
				String updateCql = "UPDATE demo.eventsFinal SET assistant='" + assistant + "'" + ",cohost='" + cohost
						+ "'" + ",currency='" + currency + "'" + ",days='" + days + "'" + ",participants='"
						+ participants + "'" + ",finishrepeatingon='" + finishRepeatingon + "'" + ",location='"
						+ location + "'" + ",organisationid=" + organisationId + "" + ",paid=" + paid + ""
						+ ",privateevent='" + privateEvent + "'" + ",repeat='" + repeat + "'" + ",tags='" + tags + "'"
						+ ",title='" + title + "'" + ",price=" + price + "" + ",days='" + days + "'" + ",repeat='"
						+ repeat + "'" + "WHERE tenantid='" + tenantId + "'" + "AND userid='" + userId
						+ "' AND eventid=" + eventId + ";";

				session.execute(updateCql);
				logger.info("Update query fired in EventDao");

				System.out.println("" + updateCql);
				logger.info("UpdateCql of EventDao" + updateCql);
				logger.info("Field Updated");
				System.out.println("UPDATED");
			} else {
				logger.info("EventId and TenantId are null!! cannot update fields");
				return null;

			}
		} catch (Exception e) {
			logger.info("Exception found in update method" + e.getMessage());

		}
		logger.info("Updation successful!!! ");
		return jsonNode;

	}

	public EventContainerClass delete(EventContainerClass jsonNode) {
		// jsonNode = getJsonNode();
		logger.info(" In Deleted method of EventDao");

		try {
			logger.info("In try block of delete call");
			String tenantId = jsonNode.getTenant().getId();
			String userId = jsonNode.getUser().getId();
			UUID eventId = jsonNode.getEventid();
			logger.info(tenantId + "" + userId + "" + eventId);
			if (tenantId != null && userId != null && eventId != null) {
				

				logger.info("Tenat id" + tenantId + "EventId" + eventId);
				System.out.println("Update query entering!!");
				String updateCql = "UPDATE demo.eventsFinal SET deleted=true WHERE tenantid='" + tenantId + "'" + "AND userid='" + userId
						+ "' AND eventid=" + eventId + ";";

				session.execute(updateCql);
				logger.info("Update query fired in EventDao");

				logger.info("Delete Query fired");
				out.println("deleted");
				logger.info("Deleted");
			} else {
				logger.info("Cannot delete as id is null");
				// Nothing to delete..
				return null;
			}
		} catch (Exception e) {
			// response failure
			logger.info("Exception found" + e.getMessage());
		}
		return jsonNode;

	}

	public EventContainerClass readByEvent(EventContainerClass jsonNode) {

		logger.info("In read method of EventDao!!!");
		String assistant = null;
		String tenantId = jsonNode.getTenant().getId();
		UUID eventId = jsonNode.getEventid();
		String userId = jsonNode.getUser().getId();
		String query = "select * from demo.eventsFinal where tenantid='" + tenantId + "' and userid = '" + userId
				+ "' and eventid = " +eventId +";";
		String get = null;
		UUID getEventid = null;
		String eassistant = null;
		String cohost = null;
		String userid = null;
		String location = null;
		String description = null;
		Date starttime = null;
		UUID organisation = null;
		boolean paid = false;
		double price = 0;
		String cover = null;

		try {
			ResultSet results = session.execute(query);
			logger.info("Read Query EventDao class" + query);
			if (results != null) {
				logger.info("Fetching results in EventDao class");
				Row row = results.one();

				if(row.getBool("deleted") == true)
					return null;
				
				
				// Tenant Object
				Tenant tenant = new Tenant();

				Map<String, String> tenantMap = row.getMap("tenantmap", String.class, String.class);
				if (tenantMap != null) {
					tenant.setId(tenantMap.get("id"));
					tenant.setTenantname(tenantMap.get("tenantname"));

				}

				// User Object
				User user = new User();
				user.setId(row.getString("userid"));

				// Cohost Object
				Cohost cohost1 = new Cohost();
				cohost1.setId(row.getString("cohost"));
				cohost1.setUsername("userCohost");
				cohost1.setFullName("fullcohost");

				eventContainerClass.setTenant(tenant);

				eventContainerClass.setLocation(row.getString("location"));

				eventContainerClass.setDays(row.getList("days", String.class));

				eventContainerClass.setEventid(row.getUUID("eventid"));

				eventContainerClass.setCurrency(row.getString("currency"));

				eventContainerClass.setTitle(row.getString("title"));

				eventContainerClass.setParticipants(row.getList("participants", String.class));

				eventContainerClass.setCohost(cohost1);

				eventContainerClass.setDescription(row.getString("description"));

				eventContainerClass.setCurrency("currency");

				eventContainerClass.setOrganisation(row.getUUID("organisation"));

				eventContainerClass.setPaid(row.getBool("paid"));

				eventContainerClass.setPrice(row.getDouble("price"));

				eventContainerClass.setOperation("read");
					

			}
			logger.info("EventContainerList of object" + eventContainerClass);
			System.out.println("result : " + eventContainerClass.toString());
		} catch (Exception e) {
			e.printStackTrace();

		}
		return eventContainerClass;

	}

	public List<EventContainerClass> readAll(EventContainerClass jsonNode) {

		logger.info("In read method of EventDao!!!");

		List<EventContainerClass> eventContainerClassList = new ArrayList<EventContainerClass>();

		String assistant = null;
		String tenantId = jsonNode.getTenant().getId();
		String userId = jsonNode.getUser().getId();
		String query = "select * from demo.finalevents where tenantid='" + tenantId + "' and userid = '" + userId
				+ "';";
		String get = null;
		UUID getEventid = null;
		String eassistant = null;
		String cohost = null;
		String userid = null;
		String location = null;
		String description = null;
		Date starttime = null;
		UUID organisation = null;
		boolean paid = false;
		double price = 0;
		String cover = null;

		try {
			ResultSet results = session.execute(query);
			logger.info("Read Query EventDao class" + query);

			for (Row row : results) {
				logger.info("Fetching results in EventDao class");

				if(row.getBool("deleted") == true){
					continue;
				}
				
				// Tenant Object
				Tenant tenant = new Tenant();

				Map<String, String> tenantMap = row.getMap("tenantmap", String.class, String.class);
				if (tenantMap != null) {
					tenant.setId(tenantMap.get("id"));
					tenant.setTenantname(tenantMap.get("tenantname"));

				}

				// User Object
				User user = new User();
				user.setId(row.getString("userid"));

				// Cohost Object
				Cohost cohost1 = new Cohost();
				cohost1.setId(row.getString("cohost"));
				cohost1.setUsername("userCohost");
				cohost1.setFullName("fullcohost");

				eventContainerClass.setTenant(tenant);

				eventContainerClass.setLocation(row.getString("location"));

				eventContainerClass.setDays(row.getList("days", String.class));

				eventContainerClass.setEventid(row.getUUID("eventid"));

				eventContainerClass.setCurrency(row.getString("currency"));

				eventContainerClass.setTitle(row.getString("title"));

				eventContainerClass.setParticipants(row.getList("participants", String.class));

				eventContainerClass.setCohost(cohost1);

				eventContainerClass.setDescription(row.getString("description"));

				eventContainerClass.setCurrency("currency");

				eventContainerClass.setOrganisation(row.getUUID("organisation"));

				eventContainerClass.setPaid(row.getBool("paid"));

				eventContainerClass.setPrice(row.getDouble("price"));

				eventContainerClassList.add(eventContainerClass);
				eventContainerClass = null;

			}
			logger.info("EventContainerList of object" + eventContainerClass);
			System.out.println("result : " + eventContainerClass.toString());
		} catch (Exception e) {

		}
		return eventContainerClassList;

	}

	public EventContainerClass insert(EventContainerClass jsonNode) throws JsonProcessingException, IOException {

		logger.info("Insert method call");
		Map<String, String> tenantMap = new HashMap<String, String>();
		Map<String, String> userMap = new HashMap<String, String>();

		UUID eventUid = UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d");
		UUID orgUid = UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d");
		String tenantId = jsonNode.getTenant().getId();

		// FileInputStream fin = new
		// FileInputStream("/home/dj/Desktop/kerry.png");
		String userId = jsonNode.getUser().getId();
		String startTime = String.valueOf(System.currentTimeMillis());
		UUID eventId = eventUid;
		String assistant = jsonNode.getAssistant().getUsername();
		String cohost = jsonNode.getCohost().getUsername();
		byte [] cover=jsonNode.getCover();
		String currency = jsonNode.getCurrency();
		List<String> days = jsonNode.getDays();
		List<String> participants = jsonNode.getParticipants();
		String description = jsonNode.getDescription();
		String finishRepeatingon = jsonNode.getRepeatend();
		String location = jsonNode.getLocation();
		UUID organisationId = jsonNode.getOrganisation();
		boolean paid = jsonNode.isPaid();
		double price = jsonNode.getPrice();
		boolean privateEvent = jsonNode.isPrivateevent();
		String repeat = jsonNode.getRepeat();
		String tags = jsonNode.getTag();
		String title = jsonNode.getTitle();
		String blobCheck = "blob";
		// byte[] ar = new byte[fin.available() + 1];
		// fin.read(ar);
		 ByteBuffer buffer = ByteBuffer.wrap(cover);

		PreparedStatement ps = session
				.prepare("insert into demo.eventsFinal(tenantid,userid,starttime,eventid,assistant"
						+ ",cohost,cover,currency,days,description,finishrepeatingon,location,organisation,"
						+ "paid,price,privateevent,repeat,tags,title,tenantmap,participants,deleted"
						+ ") values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

		System.out.println(blobCheck.getBytes());
		BoundStatement bs = ps.bind();
		bs.setString("tenantid", tenantId);
		bs.setString("userid", userId);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		String localDate = sdf.format(new Date());
		bs.setTime("starttime", new Date().getTime());
		bs.setUUID("eventid", eventId);
		bs.setString("assistant", assistant);
		bs.setString("cohost", cohost);
		bs.setBytes("cover", buffer);
		bs.setString("currency", currency);
		bs.setList("days", days);
		bs.setList("participants", participants);
		bs.setString("description", description);
		bs.setTimestamp("finishrepeatingon", new Date());
		bs.setString("location", location);
		bs.setUUID("organisation", organisationId);
		bs.setBool("paid", paid);
		bs.setDouble("price", price);
		bs.setBool("privateevent", privateEvent);
		bs.setString("repeat", repeat);
		bs.setString("tags", tags);
		bs.setString("title", title);
		bs.setMap("tenantmap", tenantMap);
		bs.setBool("deleted", true);
		// bs.setMap("userMap",userMap);
		try {
			session.execute(bs);
			System.out.println("INSERTED SUCCESSFULLY");
			logger.info("Inserted Values!!!" + userId + eventId + currency + assistant + cohost + days + participants
					+ description + paid + title + tenantMap + repeat + location);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("Exception found" + e.getMessage());
			return null;
		}

		return jsonNode;

	}

	public Session connect() {

		CassandraConnector cassandraConnector = new CassandraConnector();
		session = cassandraConnector.getInstance().getSession();
		logger.info("Connecting..");
		return session;

	}
}