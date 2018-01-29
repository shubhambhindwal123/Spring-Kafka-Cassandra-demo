package com.exa.connector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Cluster.Builder;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.SocketOptions;
import com.datastax.driver.core.policies.ConstantReconnectionPolicy;
import com.datastax.driver.core.policies.DowngradingConsistencyRetryPolicy;

@Component
@Configuration
public class CassandraConnector {

	@Value("${cassandra.keyspace}")
	private String cassandraKeyspace;

	@Value("${cassandra.cassandraport}")
	private String cassandraListenPort;

	@Value("${cassandra.cassandratimeout}")
	private String cassandraTimeout;

	@Value("${cassandra.cassandrahost}")
	private String cassandraHost;

	static Logger log = LoggerFactory.getLogger(CassandraConnector.class.getName());
	private static Cluster cluster;
	private static Session session;
	private static CassandraConnector cassandraConnector;


	public CassandraConnector() {

	}

	@Bean
	public CassandraConnector getInstance() {

		if (cassandraConnector == null) {
			cassandraConnector = new CassandraConnector();
			int sockettimeoutinmillis = Integer.parseInt("100000");

			SocketOptions so = new SocketOptions();
			so.setConnectTimeoutMillis(sockettimeoutinmillis);
			
			cluster = Cluster.builder().addContactPoint(cassandraHost).withPort(Integer.parseInt(cassandraListenPort)).withSocketOptions(so).build();
			Builder builder = new Cluster.Builder().addContactPoints(cassandraHost).withPort(Integer.parseInt(cassandraListenPort));

			// builder.withPoolingOptions(new
			// PoolingOptions().setCoreConnectionsPerHost(HostDistance.LOCAL,
			// new
			// PoolingOptions().getMaxConnectionsPerHost(HostDistance.LOCAL)));

			cluster = builder.withRetryPolicy(DowngradingConsistencyRetryPolicy.INSTANCE)
					.withReconnectionPolicy(new ConstantReconnectionPolicy(10000L)).build();

			session = cluster.connect();
			
		}
		return cassandraConnector;
	}

	public Cluster getCluster() {
		return this.cluster;
	}

	public Session getSession() {
		return this.session;
	}

	public static void resetConnection() {
		session.close();
		cluster.close();
		cassandraConnector = null;
	}
}