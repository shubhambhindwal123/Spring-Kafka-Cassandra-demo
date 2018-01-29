package com.exa.kafka;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.kafka.test.rule.KafkaEmbedded;
import org.springframework.kafka.test.utils.ContainerTestUtils;
import org.springframework.test.context.junit4.SpringRunner;

import com.exa.kafka.consumer.Receiver;
import com.exa.kafka.producer.Sender;
import com.exa.pojo.Assistant;
import com.exa.pojo.Cohost;
import com.exa.pojo.EventContainerClass;
import com.exa.pojo.Participants;
import com.exa.pojo.Tenant;
import com.exa.pojo.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EventServiceTest {

	@Autowired
	private Sender sender;

	@Autowired
	private Receiver receiver;

	@Autowired
	private KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry;

	@ClassRule
	public static KafkaEmbedded embeddedKafka = new KafkaEmbedded(1, true, "consumer");

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.setProperty("kafka.bootstrap-servers", embeddedKafka.getBrokersAsString());
	}

	@Before
	public void setUp() throws Exception {
		// wait until the partitions are assigned
		for (MessageListenerContainer messageListenerContainer : kafkaListenerEndpointRegistry
				.getListenerContainers()) {
			ContainerTestUtils.waitForAssignment(messageListenerContainer, embeddedKafka.getPartitionsPerTopic());
		}
	}

	@Test
	public void test() throws Exception {
		System.out.println("Test cases started");
		testInsert();
	//	testRead();
	//	testUpdate();
	//	testDelete();
	}

	public void testInsert() throws Exception {

		Tenant tenant = new Tenant();
		tenant.setId("id");
		tenant.setTenantname("tname");

		Cohost cohost = new Cohost();
		cohost.setId("id");
		cohost.setFullName("fu");
		cohost.setUsername("uc");

		Assistant assistant = new Assistant();
		assistant.setId("id");
		assistant.setUsername("sdjj");
		assistant.setFullName("fulkkc");

		User user = new User();
		user.setId("id");
		user.setUsername("kch");
		user.setFullName("ff");

		List<String> days = new ArrayList<>();
		days.add("Monday");
		days.add("Tuesday");
		days.add("Wednesday");

		Participants p = new Participants();
		p.setUsername("paiuname");
		p.setId("pid");
		p.setFullName("punamefullname");
		p.setUsername("uname");
		p.setId("iop");
		p.setFullName("dihd");
		List<String> participantsList = new ArrayList<>();
		participantsList.add(p.getId());
		participantsList.add(p.getUsername());
		participantsList.add(p.getFullName());

		Map<String, String> tenantMap = new HashMap<String, String>();

		tenantMap.put("tenaName", "tenName");
		tenantMap.put("tenId", "tenId");

		UUID organisation = UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d");

		UUID eventid = UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d");
		byte[] cover = new String("exatip").getBytes();

		EventContainerClass event = new EventContainerClass(true, "location", "tag", "repeat", "type", participantsList,
				eventid, "currency", cohost, "add", "title", false, assistant, 1000, days, tenant, "description",
				"repeatend", cover, user, organisation);
		sender.send(String.valueOf(event));

		receiver.getLatch().await(1000000, TimeUnit.MILLISECONDS);
		assertThat(receiver.getLatch().getCount()).isEqualTo(0);
	}

	public void testUpdate() throws Exception {

		Tenant tenant = new Tenant();
		tenant.setId("id");
		tenant.setTenantname("tname");

		Cohost cohost = new Cohost();
		cohost.setId("id");
		cohost.setFullName("fu");
		cohost.setUsername("uc");

		Assistant assistant = new Assistant();
		assistant.setId("id");
		assistant.setUsername("sdjj");
		assistant.setFullName("fulkkc");

		User user = new User();
		user.setId("id");
		user.setUsername("kch");
		user.setFullName("ff");

		List<String> days = new ArrayList<>();
		days.add("Monday");
		days.add("Tuesday");
		days.add("Wednesday");

		Participants p = new Participants();
		p.setUsername("paiuname");
		p.setId("pid");
		p.setFullName("punamefullname");
		p.setUsername("uname");
		p.setId("iop");
		p.setFullName("dihd");
		List<String> participantsList = new ArrayList<>();
		participantsList.add(p.getId());
		participantsList.add(p.getUsername());
		participantsList.add(p.getFullName());

		Map<String, String> tenantMap = new HashMap<String, String>();

		tenantMap.put("tenaName", "tenName");
		tenantMap.put("tenId", "tenId");

		UUID organisation = UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d");

		UUID eventid = UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d");
		byte[] cover = new String("exatip").getBytes();

		EventContainerClass event = new EventContainerClass(true, "location", "tag", "repeat", "type", participantsList,
				eventid, "currency", cohost, "update", "title", false, assistant, 1000, days, tenant, "description",
				"repeatend", cover, user, organisation);
		sender.send(String.valueOf(event));

		receiver.getLatch().await(1000000, TimeUnit.MILLISECONDS);
		assertThat(receiver.getLatch().getCount()).isEqualTo(0);
	}

	public void testRead() throws Exception {

		Tenant tenant = new Tenant();
		tenant.setId("id");
		tenant.setTenantname("tname");

		Cohost cohost = new Cohost();
		cohost.setId("id");
		cohost.setFullName("fu");
		cohost.setUsername("uc");

		Assistant assistant = new Assistant();
		assistant.setId("id");
		assistant.setUsername("sdjj");
		assistant.setFullName("fulkkc");

		User user = new User();
		user.setId("id");
		user.setUsername("kch");
		user.setFullName("ff");

		List<String> days = new ArrayList<>();
		days.add("Monday");
		days.add("Tuesday");
		days.add("Wednesday");

		Participants p = new Participants();
		p.setUsername("paiuname");
		p.setId("pid");
		p.setFullName("punamefullname");
		p.setUsername("uname");
		p.setId("iop");
		p.setFullName("dihd");
		List<String> participantsList = new ArrayList<>();
		participantsList.add(p.getId());
		participantsList.add(p.getUsername());
		participantsList.add(p.getFullName());

		Map<String, String> tenantMap = new HashMap<String, String>();

		tenantMap.put("tenaName", "tenName");
		tenantMap.put("tenId", "tenId");

		UUID organisation = UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d");

		UUID eventid = UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d");
		byte[] cover = new String("exatip").getBytes();

		EventContainerClass event = new EventContainerClass(true, "location", "tag", "repeat", "type", participantsList,
				eventid, "currency", cohost, "read", "title", false, assistant, 1000, days, tenant, "description",
				"repeatend", cover, user, organisation);
		sender.send(String.valueOf(event));

		receiver.getLatch().await(1000000, TimeUnit.MILLISECONDS);
		assertThat(receiver.getLatch().getCount()).isEqualTo(0);
	}

	public void testDelete() throws Exception {

		Tenant tenant = new Tenant();
		tenant.setId("id");
		tenant.setTenantname("tname");

		Cohost cohost = new Cohost();
		cohost.setId("id");
		cohost.setFullName("fu");
		cohost.setUsername("uc");

		Assistant assistant = new Assistant();
		assistant.setId("id");
		assistant.setUsername("sdjj");
		assistant.setFullName("fulkkc");

		User user = new User();
		user.setId("id");
		user.setUsername("kch");
		user.setFullName("ff");

		List<String> days = new ArrayList<>();
		days.add("Monday");
		days.add("Tuesday");
		days.add("Wednesday");

		Participants p = new Participants();
		p.setUsername("paiuname");
		p.setId("pid");
		p.setFullName("punamefullname");
		p.setUsername("uname");
		p.setId("iop");
		p.setFullName("dihd");
		List<String> participantsList = new ArrayList<>();
		participantsList.add(p.getId());
		participantsList.add(p.getUsername());
		participantsList.add(p.getFullName());

		Map<String, String> tenantMap = new HashMap<String, String>();

		tenantMap.put("tenaName", "tenName");
		tenantMap.put("tenId", "tenId");

		UUID organisation = UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d");

		UUID eventid = UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d");
		byte[] cover = new String("exatip").getBytes();

		EventContainerClass event = new EventContainerClass(true, "location", "tag", "repeat", "type", participantsList,
				eventid, "currency", cohost, "delete", "title", false, assistant, 1000, days, tenant, "description",
				"repeatend", cover, user, organisation);
		sender.send(String.valueOf(event));

		receiver.getLatch().await(1000000, TimeUnit.MILLISECONDS);
		assertThat(receiver.getLatch().getCount()).isEqualTo(0);
	}
}
